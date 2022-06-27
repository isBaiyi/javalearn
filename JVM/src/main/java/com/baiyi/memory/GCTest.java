package com.baiyi.memory;

/**
 * @Author: BaiYi
 * @Description: 添加参数 -XX:+PrintGCDetails 打印 GC 日志信息
 * -XX:PretenureSizeThreshold=1000000 可以指定大对象提前进入老年代，只对 Serial 和 ParNew 收集器有效
 * @Date: 2022/5/29 22:42
 */
public class GCTest {
    private static final int MB = 1024;
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[60000 * MB];
        allocation2 = new byte[8000 * MB];

        allocation3 = new byte[1000 * MB];
        allocation4 = new byte[1000 * MB];
        allocation5 = new byte[1000 * MB];
    }
}
