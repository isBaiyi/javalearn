package com.baiyi.security;

import java.util.ArrayList;

/**
 * @Author: BaiYi
 * @Description: 测试局部变量线程安全问题
 * @Date: 2022/4/21 14:24
 */
public class Test4 {
    private static final int THREAD_NUMBER = 2;
    private static final int LOOP_NUMBER = 2;

    public static void main(String[] args) {
        ThreadSafe test = new ThreadSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + i).start();
        }
    }
}

class ThreadSafe {
    public final void method1(int loopNumber) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }
    private void method2(ArrayList<String> list) {
        list.add("1");
    }
    private void method3(ArrayList<String> list) {
        list.remove(0);
    }
}

