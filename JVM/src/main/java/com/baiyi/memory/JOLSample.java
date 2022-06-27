package com.baiyi.memory;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: BaiYi
 * @Description: 对象头信息查看示例
 * @Date: 2022/5/29 16:51
 */
public class JOLSample {
    public static void main(String[] args) {
        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());

        System.out.println();
        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());

        System.out.println();
        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2.toPrintable());
    }

    /**
     * -XX:+UseCompressedOops           默认开启的压缩所有指针
     * -XX:+UseCompressedClassPointers  默认开启的只压缩对象头离的类型指针 Klass Pointer
     * Oops: Ordinary Object Pointers
     */
    public static class A {
                            // 8B mark word
                            // 4B Klass Pointer 如果关闭压缩 -XX:-UseCompressedClassPointers 或 -XX:-UseCompressedOops, 则占用 8B
        int id;             // 4B
        String name;        // 4B 如果关闭压缩 -XX:-UseCompressedOops, 则占用 8B
        byte b;             // 1B
        Object o;           // 4B 如果关闭压缩 -XX:-UseCompressedOops, 则占用 8
    }
}
