package com.baishuiyijiu.collections;

import java.util.Random;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-01 23:10
 */
public class DemoArray {

    public static void main(String[] args) {

        int[] intArray = new int[100];

        for (int i = 0; i < intArray.length; ++i) {
            intArray[i] = new Random().nextInt();
        }
    }
}
