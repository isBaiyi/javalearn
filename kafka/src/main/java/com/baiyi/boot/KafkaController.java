package com.baiyi.boot;

import com.baiyi.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: BaiYi
 * @description:
 * @date: 2022/3/29 23:55
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaConfig kafkaConfig;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public String sendMessage() {
        kafkaTemplate.send(kafkaConfig.getTopic(), 0, "baiyi", "this is message");
        return "success";
    }

}
