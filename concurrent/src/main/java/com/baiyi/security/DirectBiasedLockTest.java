package com.baiyi.security;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.Vector;

/**
 * @Author: BaiYi
 * @Description: 重偏向锁测试 如果撤销偏向锁的次数达到阈值 20 时，JVM 会把对象偏向给新加锁的线程ID
 * JVM 添加参数： -XX:BiasedLockingStartupDelay=0
 * @Date: 2022/4/24 22:06
 */
@Slf4j
public class DirectBiasedLockTest {
    public static void main(String[] args) throws InterruptedException {
        test3();
    }
    private static void test3() throws InterruptedException {
        Vector<Dog> list = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("===============> ");
            for (int i = 0; i < 30; i++) {
                Dog d = list.get(i);
                log.debug("加锁前" +i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug("加锁中" + i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug("加锁后" + i + "\t" +ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t2");
        t2.start();
    }
}
