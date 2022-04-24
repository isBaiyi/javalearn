package com.baiyi.security;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 共享资源导致的线程安全问题
 * @Date: 2022/4/13 18:51
 */
@Slf4j
public class Test1 {
    private static Integer counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 5000; i > 0; i--) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("counter: {}", counter);
    }
}
