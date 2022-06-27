package com.baiyi.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: BaiYi
 * @Description: 可重入测试
 * @Date: 2022/5/8 11:26
 */
@Slf4j
public class ReentrantLockTest1 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        lock.lock();
        try {
            log.info("m1 获取锁");
            m2();
        } catch (Exception e) {
            log.error("加锁失败");
        } finally {
            lock.unlock();
        }
    }

    private static void m2() {
        lock.lock();
        try {
            log.info("m2 获取锁");
            m3();
        } catch (Exception e) {
            log.error("加锁失败");
        } finally {
            lock.unlock();
        }
    }

    private static void m3() {
        lock.lock();
        try {
            log.info("m3 获取锁");
        } catch (Exception e) {
            log.error("加锁失败");
        } finally {
            lock.unlock();
        }
    }

}
