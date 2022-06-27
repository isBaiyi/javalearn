package com.baiyi.basic;

/**
 * @Author: BaiYi
 * @Description: 类加载测试
 * @Date: 2022/5/14 15:24
 */
public class DynamicLoadTest {
    static {
        System.out.println("load DynamicLoadTest");
    }

    public static void main(String[] args) {
        C c = new C();
        System.out.println("DynamicLoadTest.main");
        D d = null;
    }
}

class C {
    static {
        System.out.println("load C");
    }

    public C() {
        System.out.println("init C");
    }
}

class D {
    static {
        System.out.println("load D");
    }

    public D() {
        System.out.println("init D");
    }
}