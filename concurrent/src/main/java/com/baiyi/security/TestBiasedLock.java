package com.baiyi.security;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: BaiYi
 * @Description: 测试偏向锁 因为程序在启动几秒钟之后才会进行偏向锁，所以需要
 * 配置 jvm 参数指定程序一启动就使用偏向锁。 -XX:BiasedLockingStartupDelay=0
 * @Date: 2022/4/20 22:51
 */
@Slf4j
public class TestBiasedLock {
    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();
        // 分析 dog 对象的格式 打印对象头信息
        log.debug("加锁前: {}", ClassLayout.parseInstance(dog).toPrintable());

        // 调用对象的hashcode会覆盖了偏向锁，从而使用了轻量级锁
        dog.hashCode();
        synchronized (dog) {
            log.debug("加锁中: {}", ClassLayout.parseInstance(dog).toPrintable());
        }

        log.debug("加锁后: {}", ClassLayout.parseInstance(new Dog()).toPrintable());
    }
}

class Dog {

}
