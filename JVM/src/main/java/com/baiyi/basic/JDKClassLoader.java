package com.baiyi.basic;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @Author: BaiYi
 * @Description: 查看 jdk classLoader 示例
 * @Date: 2022/4/3 11:43
 */
public class JDKClassLoader {
    public static void main(String[] args) {
        System.out.println("String.class.getClassLoader() = " + String.class.getClassLoader());
        System.out.println("DESKeyFactory.class.getClassLoader() = " + DESKeyFactory.class.getClassLoader());
        System.out.println("JDKClassLoader.class.getClassLoader() = " + JDKClassLoader.class.getClassLoader());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();

        System.out.println("bootstrapClassLoader = " + bootstrapClassLoader);
        System.out.println("extClassLoader = " + extClassLoader);
        System.out.println("appClassLoader = " + appClassLoader);

        System.out.println("bootstrapClassLoader loading path .....");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urLs.length; i++) {
            System.out.println("urLs = " + urLs[i]);
        }

        System.out.println();
        System.out.println("extClassLoader loading path ....");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader loading path ....");
        System.out.println(System.getProperty("java.class.path"));
    }
}
