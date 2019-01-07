package com.baishuiyijiu.collections;

import java.util.*;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-03 20:20
 */
public class SynchronizedCollectionDemo {

    public static void main(String[] args) {

        // 定义一个线程安全的List
        List<String> safeList = Collections.synchronizedList(new ArrayList<>());

        Map<Integer, String> unsafeMap = new HashMap<>();
        // 将一个非线程安全的Map包装秤线程安全的Map
        Map<Integer, String> safeMap = Collections.synchronizedMap(unsafeMap);

        // 注意： 尽管集合是线程安全的，但是集合的迭代器并不是线程安全的，如果需要通过迭代器访问集合元素，还是需要同步，如下：
        Iterator<String> iterator = safeList.iterator();
        synchronized (safeList) {
            while (iterator.hasNext()) {
                String next = iterator.next();
                System.out.println(next);
            }
        }


    }
}
