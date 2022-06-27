package com.baiyi.memory;

/**
 * @Author: BaiYi
 * @Description: 垃圾收集算法细节之三色标记
 * @Date: 2022/6/11 22:09
 */
public class ThreeColorRemark {
    public static void main(String[] args) {
        A a = new A();
        //开始做并发标记
        D d = a.b.d;   // 1.读
        a.b.d = null;  // 2.写
        a.d = d;       // 3.写
    }
}

class A {
    B b = new B();
    D d = null;
}

class B {
    C c = new C();
    D d = new D();
}

class C {
}

class D {
}