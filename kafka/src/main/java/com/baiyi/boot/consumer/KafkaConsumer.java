package com.baiyi.boot.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


/**
 * @author: BaiYi
 * @description: kafka consumer
 * @date: 2022/3/30 00:03
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * 根据主题进行消费
     *
     * @param records 拉取的消费
     * @param ack
     */
    /*@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
    public void listenTopic(ConsumerRecords<String, String> records, Acknowledgment ack) {
        records.forEach(record -> {
            log.info("topic: {}, partition: {}, offset: {}, key: {}, value: {}", record.topic(), record.partition(),
                    record.offset(), record.key(), record.value());
        });
        // 手动提交 offset
        ack.acknowledge();
    }*/

    /**
     * 指定消费组以及分区、主题、offset 进行消费
     * concurrency： 代表消费者的数量，一般设置小于等于分区数
     * @param records
     * @param ack
     */
    @KafkaListener(groupId = "${kafka.group}", topicPartitions = {
            @TopicPartition(topic = "test", partitions = {"0", "1"}),
            @TopicPartition(topic = "test1", partitions = {"1"}, partitionOffsets = @PartitionOffset(partition = "1",
            initialOffset = "100"))
    }, concurrency = "3")
    public void listenTopicPro(ConsumerRecords<String, String> records, Acknowledgment ack) {
        records.forEach(record -> {
            log.info("topic: {}, partition: {}, offset: {}, key: {}, value: {}", record.topic(), record.partition(),
                    record.offset(), record.key(), record.value());
        });
        // 手动提交 offset
        ack.acknowledge();
    }
}
