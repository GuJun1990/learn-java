package com.baishuiyijiu.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-01 22:33
 */
public class ExerciseList {

    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

//        System.out.println(intList.get(1));
//
//        for(int num : intList) {
//            System.out.println(num);
//        }
//
//        System.out.println(intList.contains(1));
//        System.out.println(intList.contains(5));
//
//
//        for(int i = 0; i < intList.size(); i++) {
//            System.out.print(intList.get(i));
//            System.out.print(",");
//        }

        Object[] intArray = intList.toArray();

//        System.out.println(intArray);

        for(Object a : intArray) {
            System.out.println(a);
        }

//        Integer[] intArray2 = new Integer[];
        Integer[] intArray3 = intList.toArray(new Integer[0]);

        for(int b : intArray3) {
            System.out.println(b);
        }

    }
}
