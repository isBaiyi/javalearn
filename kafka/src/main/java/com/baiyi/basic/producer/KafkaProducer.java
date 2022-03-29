package com.baiyi.basic.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: BaiYi
 * @description: kafka 生产者
 * @date: 2022/3/27 16:03
 */
@Slf4j
public class KafkaProducer {
    private static final String TOPIC_NAME = "my-replicated-topic";

    public static void main(String[] args) {
        // 1. 设置相关参数
        Properties properties = new Properties();
        // Kafka 集群相关配置
        // properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.16.128:9092,172.16.16.128:9093,172.16.16.128:9094");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.107.73.103:9093,47.107.73.103:9094");
        // 把发送消息的 key 从字符串序列化为字节数组
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 将发送消息的 value 从字符串序列化为字节数组
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        /*
            Kafka 使用同步发送消息时，ack 相关配置：
            ack=0: 生产者往 Kafka clutter 发送消息，无需等待分区对应副本的 leader 接收到消息，broker 直接返回 ack 给生产者，
                   这种方式效率是最高的，但是容易丢失消息
            ack=1: 生产者往 Kafka clutter 发送消息，需要等待分区对应的副本的 leader 接收到消息，并把消息写到本地的 log 中，才会返回 ack 给生产者，
                   这种方式在性能和安全上是最均衡的。但是如果这时候 leader 还没来得及把消息同步到 flower 就已经挂掉了，也会丢失消息。
            ack=-1/all: 需要等待 min.insync.replicas(默认为1，推荐配置⼤于等于2) 这个参数配置的副本个数都成功写⼊⽇志，这种策略会保证只要有⼀个备份存活
                        就不会丢失数据。这是最强的数据保证。⼀般除⾮是⾦融级别，或跟钱打交道的场景才会使⽤这种配置。
         */
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        /*
            发送失败会重试，默认重试间隔100ms，重试能保证消息发送的可靠性，但是也可能造成消息重复发送，
            ⽐如⽹络抖动，所以需要在接收者那边做好消息接收的幂等性处理
        */
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 重试间隔设置
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);
        // 设置发送消息的本地缓冲区，如果设置了缓冲区，那么消息会先发到缓冲区中，可以提高消息的发送性能，默认值是 32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        /*
          kafka 本地线程会从本地缓冲区中读取数据，批量发送到 broker
          设置读取数据的大小 16384，默认即为 16K
         */
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        /*
            默认值是0，意思就是消息必须立即被发送，但这样会影响性能
            一般设置 10ms 左右，就是说这个消息发送完会进入到本地到一个 batch，如果 10 毫秒内，这个 batch 满了 16KB 就会随 batch 一起被发送出去
            如果 10毫秒内，batch 没满，那么也必须把消息发送出去，不能让消息的发送延迟时间太长
         */
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 10);


        // 2. 创建生产者的客户端，传入相关参数
        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);

        // 3. 创建消息 key：作用是指定往哪个分区上发 value：具体需要发送的内容
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, "mykey", "baiyi");

        // 4. 发送消息，将得到的元数据进行输出
        try {
            RecordMetadata recordMetadata = producer.send(producerRecord).get();
            log.info("同步方式发送消息结果： topic: {}, partition: {}, offset: {}", recordMetadata.topic(),
                    recordMetadata.partition(), recordMetadata.offset());
        } catch (InterruptedException | ExecutionException e) {
            log.error("同步方式发送消息失败，topic: {}, e: {}", TOPIC_NAME, e.getMessage(), e);
        }

        int msgNum = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(msgNum);
        for (int i = 0; i < 5; i++) {
            producerRecord = new ProducerRecord<>(TOPIC_NAME, "mykey" + i, "baiyi" + i);
            // 异步发送消息
            producer.send(producerRecord, (recordMetadata, e) -> {
                if (e != null) {
                    log.error("异步方式发送消息失败，topic: {}, e: {}", TOPIC_NAME, e.getMessage(), e);
                }
                if (recordMetadata != null) {
                    log.info("异步方式发送消息结果: topic: {}, partition: {}, offset: {}", recordMetadata.topic(),
                            recordMetadata.partition(), recordMetadata.offset());
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await(5, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.close();
    }

}
