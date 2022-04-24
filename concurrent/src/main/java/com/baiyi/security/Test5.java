package com.baiyi.security;

import java.util.Hashtable;

/**
 * @Author: BaiYi
 * @Description: 测试线程安全类的方法组合
 * @Date: 2022/4/22 15:27
 */
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        Hashtable<Object, Object> table = new Hashtable<>();
        Thread t1 = new Thread(() -> {
            // 线程1，线程2
            if (table.get("key") == null) {
                table.put("key", 1);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            // 线程1，线程2
            if (table.get("key") == null) {

                table.put("key", 2);
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
