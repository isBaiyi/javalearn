package com.baiyi.memory;

/**
 * @Author: BaiYi
 * @Description: 引用计数器回收算法 相互循环引用问题
 * @Date: 2022/6/1 08:01
 */
public class ReferenceCountingGC {
    Object instance = null;

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
    }
}
