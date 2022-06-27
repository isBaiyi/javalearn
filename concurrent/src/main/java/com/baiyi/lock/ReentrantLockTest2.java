package com.baiyi.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: BaiYi
 * @Description: 可打断测试
 * @Date: 2022/5/8 11:45
 */
@Slf4j
public class ReentrantLockTest2 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        String orderNos = "主卡订单号: " + 1111 + "\n副卡订单号: " + 2222;
        System.out.println(orderNos);

    }
}

class User {
    
}
