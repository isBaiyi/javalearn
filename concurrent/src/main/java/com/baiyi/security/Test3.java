package com.baiyi.security;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: BaiYi
 * @Description:
 * @Date: 2022/4/14 23:06
 */
public class Test3 {
    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();
        new Thread(() -> {number1.a();}).start();
        new Thread(() -> {number2.b();}).start();
    }

}

@Slf4j
class Number {
    public static synchronized void a() {
        try {
            Thread.sleep(1);
            log.debug("1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static synchronized void b() {
        log.debug("2");
    }
}
