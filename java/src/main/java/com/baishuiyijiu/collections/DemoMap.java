package com.baishuiyijiu.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gujun@qiyi.com
 * @since 2018-12-29 00:15
 */
public class DemoMap {

    public static void main(String[] args) {
        // new 一个hash map对象，key是String类型，value是Integer类型
        Map<String, Integer> map = new HashMap<>();

        // 往map总添加key-value对
        map.put("one", 1);
//        map.put("one", 2);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 3);

        // 通过key索引value
        int val = map.get("one");
        System.out.println(val);

        // 查找某个key是否在map中
        System.out.println(map.containsKey("two"));
        System.out.println(map.containsKey("four"));
        System.out.println(map.containsValue(3));
        System.out.println(map.containsValue(6));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
            entry.setValue(8);
            System.out.println(entry.getValue());
        }


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("a", 1);
        map2.putAll(map);

        for(Map.Entry<String, Integer> pair : map2.entrySet()) {
            System.out.println("key: " + pair.getKey() + ", value: " + pair.getValue());
        }
    }
}
