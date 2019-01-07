package com.baishuiyijiu;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-05 20:27
 */
public class OptionalTest {

    @Test
    public void testCreateEmptyOptional() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent()); // false
    }

    @Test
    public void testCreateNonNullOptional() {
        String name = "hello";
        Optional<String> opt = Optional.of(name); // of方法，name不能为null
        System.out.println(opt.isPresent()); // true
        System.out.println(opt);
    }

    @Test
    public void testCreateNullOptional() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        System.out.println(opt.isPresent());
        System.out.println(opt);
    }

    @Test
    public void testIfPresent() {
        Optional<String> opt = Optional.of("hello world");
        opt.ifPresent((s) -> System.out.println(s.length())); // 有值则操作，没有则略过
        Optional<String> opt2 = Optional.ofNullable(null);
        opt2.ifPresent((s) -> System.out.println(s.length()));
    }

    @Test
    public void testOrElse() {
        String name = null;
        String result = Optional.ofNullable(name).orElse("else"); // orElse：有值返回值，没值返回设定值
        System.out.println(result);

        name = "gujun";
        result = Optional.of(name).orElse("else");
        System.out.println(result);
    }

    @Test
    public void testOrElseGet() {
        String name = null;
        String result = Optional.ofNullable(name).orElseGet(() -> "hello"); // orElseGet: 有值返回值，没值返回lambda函数设定的值
        System.out.println(result);
    }

    @Test
    public void testGet() {
        String name = "gujun";
        System.out.println(Optional.of(name).get());

        System.out.println(Optional.ofNullable(null).get()); // throw NoSuchElementException
    }

    @Test
    public void testFilter() {
        int year = 2019;
        Optional<Integer> opt = Optional.of(year);
        System.out.println(opt.filter(y -> y == 2018).isPresent());
        System.out.println(opt.filter(y -> y == 2019).isPresent());
    }

    static public class Modem {
        private Double price;

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Modem(Double price) {
            this.price = price;
        }
    }

    public boolean priceIsInRange1(Modem modem) {
        boolean isInRange = false;
        if (modem != null && modem.getPrice() != null
            && (modem.getPrice() >= 10) && modem.getPrice() <= 15) {
            isInRange = true;
        }
        return isInRange;
    }

    public boolean priceIsInRange2(Modem modem) {
        return Optional.ofNullable(modem)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }

    @Test
    public void testPriceIsInRange() {
        System.out.println(priceIsInRange1(new Modem(10.0)));
        System.out.println(priceIsInRange1(new Modem(9.0)));
        System.out.println(priceIsInRange1(new Modem(11.0)));

        System.out.println(priceIsInRange2(new Modem(10.0)));
        System.out.println(priceIsInRange2(new Modem(9.0)));
        System.out.println(priceIsInRange2(new Modem(11.0)));
        System.out.println(priceIsInRange2(null));
    }

    @Test
    public void testMap() {
        List<String> companyNames = Arrays.asList(
                "iqiyi", "alibaba", "apple", "microsoft"
        );
        Optional<List<String>> optional = Optional.of(companyNames);
        int size = optional.map(List::size).orElse(0);
        System.out.println(size);
    }

    @Test
    public void testMapWithFilter() {
        String password = " password ";
        System.out.println(Optional.of(password).filter(pass -> pass.equals("password")).isPresent());
        System.out.println(Optional.of(password).map(String::trim).filter(pass -> pass.equals("password")).isPresent());
    }




}
