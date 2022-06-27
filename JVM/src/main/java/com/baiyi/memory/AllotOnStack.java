package com.baiyi.memory;

import com.baiyi.entity.User;

/**
 * @Author: BaiYi
 * @Description: 栈上分配，标量替换 演示
 * 代码调用了 1亿次 alloc(), 如果是分配到堆上，大概需要 1GB 以上堆空间，如果堆空间小于该值，必然会触发 GC
 *
 * 使用如下参数不会发生 GC
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * 使用如下参数都会发生大量 GC
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * @Date: 2022/5/29 22:09
 */
public class AllotOnStack {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start) + " ms") ;
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("baiyi");
    }
}
