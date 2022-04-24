package com.baiyi.security;

/**
 * @Author: BaiYi
 * @Description: 操作系统monitor测试 通过字节码方式查看
 * @Date: 2022/4/20 20:31
 */
public class MonitorTest {
    private static int counter = 0;
    public static void main(String[] args) {
        new Thread(() -> {
            counter += counter;
        }).start();
    }
}
