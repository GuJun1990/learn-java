package com.baishuiyijiu;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-06 15:32
 */
public class StreamTest {

    // Stream的基本工作方式
    @Test public void testHowStreamsWorks() {
        List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1");
        list.stream()
                .filter(s -> s.startsWith("c")) // 过滤出以字符c打头的string
                .map(String::toUpperCase) // 将string转成大写
                .sorted() // 按照字典序排序
                .forEach(System.out::println); // 打印
        // filter/map/sorted 都是中间操作，而forEach是终端操作。
    }

    // 不同类型的Stream
    @Test public void testDifferentStream() {
        Arrays.asList("a1", "a2", "a3").stream()
                .findFirst()
                .ifPresent(System.out::println); // a1

        // 无需通过集合来创建Stream
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);
    }

    // 特殊类型的Stream, IntStream, LongStream, DoubleStream
    @Test public void testSpecialStream() {
        IntStream.range(0, 4).forEach(System.out::println);

        // 特殊的Stream支持终端集合操作sum()和average
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println); // 9.0

        // 对象Stream转成IntStream
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt) // 转成InStream
                .max()
                .ifPresent(System.out::println); // 3

        // IntStream转成对象Stream
        IntStream.range(0, 5)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    // Stream操作顺序
    @Test public void testOperationSequence() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
//        输出结果如下：
//        filter: d2
//        forEach: d2
//        filter: a2
//        forEach: a2
//        filter: b1
//        forEach: b1
//        filter: b3
//        forEach: b3
//        filter: c
//        forEach: c

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
//        输出结果如下：
//        map: d2
//        anyMatch: D2
//        map: a2
//        anyMatch: A2
    }


    // 高级应用
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    List<Person> persons = Arrays.asList(
            new Person("Max", 18),
            new Person("Peter", 23),
            new Person("Pamela", 23),
            new Person("David", 12)
    );

    @Test public void testCollector() {
        List<Person> filtered = persons.stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(filtered);

        // 按照年龄分组
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));
        personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));


        // Collectors在Stream元素上创建聚合，计算平均年龄
        double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);
    }


}
