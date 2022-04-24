package com.baiyi.security;

/**
 * @Author: BaiYi
 * @Description:
 * @Date: 2022/4/22 16:32
 */
public class SynchronizeTest {
    private static Object lock = new Object();
    private static Integer counter = 0;
    public static void main(String[] args) {
        synchronized (lock) {
            counter++;
        }
    }
}
