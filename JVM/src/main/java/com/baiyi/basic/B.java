package com.baiyi.basic;

public class B extends A {
    final static double b = 5.0;
    static boolean c = false;

    static {
        System.out.println("b init");
    }
}