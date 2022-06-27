package com.baiyi.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author: BaiYi
 * @Description: 自定义类加载器测试
 * @Date: 2022/5/15 14:55
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classpath;

        public MyClassLoader(String classpath) {
            this.classpath = classpath;
        }

        /**
         * 根据类名称把类通过IO流从磁盘中读入
         *
         * @param name 需要加载的名称
         * @return 二进制数组
         * @throws IOException IO异常
         */
        private byte[] loadBytes(String name) throws IOException {
            name = name.replaceAll("\\.", "/");
            FileInputStream fileInputStream = new FileInputStream(classpath + "/" + name + ".class");
            int len = fileInputStream.available();
            byte[] data = new byte[len];
            fileInputStream.read(data);
            fileInputStream.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadBytes(name);
                // defineClass 将一个字节数组转为 Class 对象，这个字节数组是 class 文件读取后最终的字节数组
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 打破双亲委派机制，使用自定义类加载进行加载指定目录的类
         * @param name 待加载类
         * @param resolve
         * @return 类实例
         * @throws ClassNotFoundException 类找不到异常
         */
        @Override
        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();

                    long t1 = System.nanoTime();
                    // 如果不是我们自定义包下的类，则使用双亲委派机制进行加载
                    if (!name.startsWith("com.baiyi")) {
                        c = this.getParent().loadClass(name);
                    } else {
                        // 自定义目录下的类使用自定义加载器进行加载
                        c = findClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 初始化自定义类加载器，会先初始化父类 ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器 AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("/Users/baiyi/Desktop/test/");
        // 在 /Users/baiyi/Desktop/test 创建 com/baiyi 几级目录，将 User1.class 放在该目录
        Class<?> clazz = classLoader.loadClass("com.baiyi.entity.User1");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("print",  null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader());

        MyClassLoader classLoader1 = new MyClassLoader("/Users/baiyi/Desktop/test1/");
        Class<?> clazz1 = classLoader1.loadClass("com.baiyi.entity.User1");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getDeclaredMethod("print",  null);
        method1.invoke(obj1,  null);
        System.out.println(clazz1.getClassLoader());
    }
}
