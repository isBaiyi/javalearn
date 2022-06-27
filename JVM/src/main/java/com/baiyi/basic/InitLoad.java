package com.baiyi.basic;

/**
 * @Author: BaiYi
 * @Description: 初始化加载测试 注意：每次只能执行一个才能看出效果
 * @Date: 2022/5/14 14:39
 */
public class InitLoad {
    static {
        System.out.println("main init");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        /*// 1. 静态常量（基本类型和字符串）不会触发初始化
        System.out.println(B.b);
        // 2. 类对象.class 不会触发初始化
        System.out.println(B.class);
        // 3. 创建该类的数组不会触发初始化
        System.out.println(new B[0]);*/
        // // 4. 不会初始化类 B，但会加载 B、A
        // ClassLoader cl = Thread.currentThread().getContextClassLoader();
        // cl.loadClass("com.baiyi.basic.B");
        // 5. 不会初始化类 B，但会加载 B、A
        ClassLoader c2 = Thread.currentThread().getContextClassLoader();
        Class.forName("com.baiyi.basic.B", false, c2);

        // // 1. 首次访问这个类的静态变量或静态方法时
        // System.out.println(A.a);
        // // 2. 子类初始化，如果父类还没初始化，会引发
        // System.out.println(B.c);
        // // 3. 子类访问父类静态变量，只触发父类初始化
        // System.out.println(B.a);
        // // 4. 会初始化类 B，并先初始化类 A
        // Class.forName("com.baiyi.basic.InitLoad.B");
    }
}


