package com.baiyi.optimize;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BaiYi
 * @Description: GC 调优测试
 * @Date: 2022/6/13 22:52
 */
public class GCOptimizeTest {
    public static void main(String[] args) {
        init();
        while (true) {

        }
    }

    private static void init() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
    }
}
