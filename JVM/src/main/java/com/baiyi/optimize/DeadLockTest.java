package com.baiyi.optimize;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 死锁测试
 * @Date: 2022/6/15 00:27
 */
@Slf4j
public class DeadLockTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                log.info(Thread.currentThread().getName() + " get lock1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    log.info(Thread.currentThread().getName() + " try to get lock2");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock2) {
                log.info(Thread.currentThread().getName() + " get lock2");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    log.info(Thread.currentThread().getName() + " try to get lock1");
                }
            }
        }, "t2").start();

        log.info("main thread end");
    }
}
