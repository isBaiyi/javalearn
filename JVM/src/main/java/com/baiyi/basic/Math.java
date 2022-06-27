package com.baiyi.basic;

import com.baiyi.entity.User;

/**
 * @Author: BaiYi
 * @Description: 简单查看 class 文件 使用 javap -v Math.class
 * @Date: 2022/4/3 11:41
 */
public class Math {
    private static final int initDate = 666;
    private static User user = new User();

    public static void main(String[] args) {
        Math math = new Math();
        int compute = math.compute();
        System.out.println("compute = " + compute);
    }

    public int compute() {// 一个方法对应一块栈帧内存区域
        int var1 = 1;
        int var2 = 2;
        int var3 = (var1 + var2) * 10;
        return var3;
    }
}
