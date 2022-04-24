package com.baiyi.security;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: BaiYi
 * @Description: 轻量级锁测试
 * @Date: 2022/4/22 16:51
 */
@Slf4j
public class LightweightLockTest {
    private static Object lock = new Object();

    public static void main(String[] args) {
        method1();
        method2();
    }

    public static void method1() {
        log.debug("加锁之前: {}", ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            // do something
            log.debug("加锁中: {}", ClassLayout.parseInstance(lock).toPrintable());
        }
        log.debug("加锁之后: {}", ClassLayout.parseInstance(lock).toPrintable());
    }

    public static void method2() {
        log.debug("加锁之前: {}", ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            // do something
            log.debug("加锁中: {}", ClassLayout.parseInstance(lock).toPrintable());
        }
        log.debug("加锁之后: {}", ClassLayout.parseInstance(lock).toPrintable());
    }
}
