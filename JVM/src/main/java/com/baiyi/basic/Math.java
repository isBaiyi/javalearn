package com.baiyi.basic;

/**
 * @Author: BaiYi
 * @Description: 简单查看 class 文件 使用 javap -v Math.class
 * @Date: 2022/4/3 11:41
 */
public class Math {
    public static void main(String[] args) {
        int compute = compute();
        System.out.println("compute = " + compute);
    }

    public static int compute() {
        int var1 = 1;
        int var2 = 2;
        int var3 = (var1 + var2) * 10;
        return var3;
    }
}
