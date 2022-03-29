package com.baiyi.basic.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author: BaiYi
 * @description: kafka consumer
 * @date: 2022/3/27 23:20
 */
@Slf4j
public class KafkaConsumer {
    private static final String TOPIC_NAME = "my-replicated-topic";
    private static final String CONSUMER_GROUP_NAME = "testGroup11";

    public static void main(String[] args) {
        Properties properties = new Properties();
        // properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.16.128:9092,172.16.16.128:9093,172.16.16.128:9094");
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.107.73.103:9093,47.107.73.103:9094");
        // 设置消费组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 自动提交 offset 默认就是自动提交 offset
        // properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // ⾃动提交offset的间隔时间
        // properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // ⼀次poll最⼤拉取消息的条数，可以根据消费速度的快慢来设置
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        //如果两次poll的时间如果超出了30s的时间间隔，kafka会认为其消费能⼒过弱，将其踢出消费组。将分区分配给其他消费者。rebalance
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);


        // 创建一个消费者客户端
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer =
                new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);
        // 消费者订阅主题列表
        // consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        // 从指定分区开始消费
        consumer.assign(Collections.singleton(new TopicPartition(TOPIC_NAME, 0)));

        while (true) {
            // 每 1000 ms 拉取一次消息
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            records.forEach(record -> {
                log.info("消费者消费到消息, topic: {}, partition: {}, offset: {}, key: {}, value: {}", record.topic(),
                        record.partition(), record.offset(), record.key(), record.value());
            });

            //所有的消息已消费完
            if (records.count() > 0) {//有消息
                // ⼿动同步提交offset，当前线程会阻塞直到offset提交成功
                // ⼀般使⽤同步提交，因为提交之后⼀般也没有什么逻辑代码了
                // consumer.commitSync();//=======阻塞=== 提交成功
                consumer.commitAsync((map, e) -> {
                    if (e != null) {
                        log.error("消费者手动异步提交offset失败, offsets: {}, e: {}", map, e.getMessage(), e);
                        // 执行相关操作 写异常日志....
                    }
                });
            }
        }
    }
}
