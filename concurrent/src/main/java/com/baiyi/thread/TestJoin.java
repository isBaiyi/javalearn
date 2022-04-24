package com.baiyi.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 测试 join 函数
 * @Date: 2022/4/12 20:43
 */
@Slf4j
public class TestJoin {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args){
        // test2();
        test3();
    }

    private static void test2() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.error(Thread.currentThread().getName() + "出现异常, e: {}", e.getMessage(), e);
            }
            r1 = 10;
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.error(Thread.currentThread().getName() + "出现异常, e: {}", e.getMessage(), e);
            }
            r2 = 20;
        }, "t2");
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.debug("r1: {}, r2: {}, cost: {}", r1, r2, end - start);
    }

    public static void test3() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 30;
        }, "t1");
        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致 join 结束
        try {
            t1.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.debug("r1: {}, cost: {}", r1, end - start);
    }
}
