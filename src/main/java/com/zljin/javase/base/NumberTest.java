package com.zljin.javase.base;

import java.math.BigDecimal;

/**
 * BigDecimal 用compareTo比较
 */
public class NumberTest {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("1.00");
        System.out.println(a.equals(b)); // false
        System.out.println(a.compareTo(b) == 0); // true


        /**
         * Integer 类内部维护了一个缓存池，范围是-128 到 127
         * 对于这个范围内的整数，Integer 会复用已经存在的对象，因此使用 == 比较时会返回 true
         * 对于超出这个范围的整数，每次都会创建一个新的 Integer 对象
         * 因此使用 == 比较时会返回 false
         *
         * equals 方法比较的是值是否相等，所以不管是否在缓存范围内，结果都是 true
         */
        Integer c = 1000;
        Integer d = 1000;
        System.out.println(c == d);
        System.out.println(c.equals(d));
    }
}
