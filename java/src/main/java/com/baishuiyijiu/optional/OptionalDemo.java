package com.baishuiyijiu.optional;

import java.util.Optional;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-05 20:22
 */
public class OptionalDemo {

    public static void main(String[] args) {
        createEmptyOptional();
        createNonNullOptional();
    }

    public static void createEmptyOptional() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent()); // false

    }

    public static void createNonNullOptional() {
        String name = "hello";
        Optional<String> opt = Optional.of(name);
        System.out.println(opt.isPresent());
        System.out.println(opt);
    }

    public static void createNullOptional() {
        String name = null;

    }

}
