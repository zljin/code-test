package com.zljin.algorithm.recursion;

/**
 * 递归
 * 函数自己调用自己
 *
 * 三个原则：
 * 一个问题的解可以分解为几个子问题的解；
 * 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样；
 * 存在递归终止条件。
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(fib(10));
    }

    private static int factorial(int i) {
        if (i == 1) {
            System.out.println(i + "=");
            return 1;
        }
        System.out.print(i + " * ");
        return i * factorial(i - 1);
    }

    private static int fib(int i) {
        if (i <= 1) {
            return i;
        }
        return fib(i - 1) + fib(i - 2);
    }
}
