package com.baiyi.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: BaiYi
 * @Description: wait notify 测试
 * @Date: 2022/4/25 15:17
 */
public class WaitNotifyTest {
    private static Logger logger = LoggerFactory.getLogger(WaitNotifyTest.class);
    private static final Object lock = new Object();
    private static boolean hasCigarette = false;
    private static boolean hasTakeFood = false;

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                logger.debug("有烟没? [{}]", hasCigarette);
                while (!hasCigarette) {
                    logger.debug("没烟，歇一会");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                logger.debug("开始干活");
            }
        }, "小南").start();

        new Thread(() -> {
            synchronized (lock) {
                logger.debug("有外卖没? [{}]", hasTakeFood);
                while (!hasTakeFood) {
                    logger.debug("没外卖，歇一会");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                logger.debug("开始干活");
            }
        }, "小女").start();

        new Thread(() -> {
            synchronized (lock) {
                logger.debug("外卖到叻！");
                hasTakeFood = true;
                lock.notifyAll();
            }
        }, "送外卖的").start();

        new Thread(() -> {
            synchronized (lock) {
                logger.debug("烟到叻！");
                hasCigarette = true;
                lock.notifyAll();
            }
        }, "送烟的").start();

    }
}
