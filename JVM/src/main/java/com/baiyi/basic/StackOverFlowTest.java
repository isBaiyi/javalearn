package com.baiyi.basic;

/**
 * @Author: BaiYi
 * @Description: stackOverflow error 测试
 * @Date: 2022/5/17 23:01
 */
public class StackOverFlowTest {
    static int counter = 0;

    static void redo() {
        counter++;
        redo();
    }
    public static void main(String[] args) {
        try {
            redo();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("counter = " + counter);
        }
    }

}
