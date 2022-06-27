package com.baiyi;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: BaiYi
 * @Description:
 * @Date: 2022/6/15 00:01
 */
@SpringBootTest
public class ApiTest {
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 用于测试:
     */
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            restTemplate.getForObject("http://localhost:8080/user/process", String.class);
            Thread.sleep(100);
        }
    }

}
