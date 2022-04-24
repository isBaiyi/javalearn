package com.baiyi.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 测试守护线程
 * @Date: 2022/4/13 16:49
 */
@Slf4j
public class TestDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("开始运行");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("开始结束");
        }, "t1");
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(1000);
        log.debug("开始结束");
    }
}
