package com.baiyi.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description: 测试线程的六种状态
 * @Date: 2022/4/13 17:20
 */
@Slf4j
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> log.debug("running"), "t1"); // new

        Thread t2 = new Thread(() -> {
            while (true) { // runnable

            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> log.debug("running"), "t3"); // terminate
        t3.start();

        Thread t4 = new Thread(() -> { // timed_waiting
            synchronized (TestState.class) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join(); // waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (TestState.class) { // block
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t6");
        t6.start();

        log.debug("main thread running");
        Thread.sleep(500);
        log.debug("t1 state: {}", t1.getState());
        log.debug("t2 state: {}", t2.getState());
        log.debug("t3 state: {}", t3.getState());
        log.debug("t4 state: {}", t4.getState());
        log.debug("t5 state: {}", t5.getState());
        log.debug("t6 state: {}", t6.getState());
    }
}
