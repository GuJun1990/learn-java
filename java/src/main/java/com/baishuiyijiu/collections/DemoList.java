package com.baishuiyijiu.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gujun@qiyi.com
 * @since 2018-12-28 23:58
 */
public class DemoList {

    public static void main(String[] args) {
        // new一个ArrayList, 元素类型为Integer
        List<Integer> intList = new ArrayList<>();

        // 添加元素
        intList.add(1);
        intList.add(2);

        for (int i = 3; i < 10; i++) {
            intList.add(i);
        }

        // 获取特定索引元素
        int val = intList.get(1);
        System.out.println(val);

        // 遍历元素，原始方法
        for (int i = 0; i < intList.size(); ++i) {
            System.out.println(intList.get(i));
        }

        // 遍历元素，for-each方法
        for (int n : intList) {
            System.out.println(n);
        }
    }
}
