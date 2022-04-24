package com.baiyi.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 打断阻塞线程
 * @Date: 2022/4/12 21:20
 */
@Slf4j
public class TestInterrupt1 {
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(() -> {
            try {
                log.debug("t1 sleep");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        log.debug("main thread sleep");
        Thread.sleep(1000);
        t1.interrupt();
        log.debug("interrupt flag: {}", t1.isInterrupted());
    }
}
