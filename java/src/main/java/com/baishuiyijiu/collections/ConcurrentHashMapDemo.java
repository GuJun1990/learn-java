package com.baishuiyijiu.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-03 21:26
 */
public class ConcurrentHashMapDemo {

    /**
     * 线程共享变量，测试一个线程修改map，另一个线程遍历map
     */
    private static ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {

        map.put("one", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);

        new Thread(()->{
            try {
                Thread.sleep(1000);
                map.clear();
                map.put("one", 11);
                map.put("Two", 22);
                map.put("Three", 33);
                map.put("Four", 44);
                map.put("Five", 55);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        for(Map.Entry<String, Integer> me : map.entrySet()) {
            System.out.println("{Key=" + me.getKey() + "\t" + "Value=" + me.getValue() + "}");
            Thread.sleep(2000);
        }

    }
}
