package com.baiyi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: BaiYi
 * @description: Kafka 配置
 * @date: 2022/3/29 23:56
 */
@Component
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaConfig {
    private String topic;
    private String consumerGroup;
}
