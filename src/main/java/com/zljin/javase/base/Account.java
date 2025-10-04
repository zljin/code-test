package com.zljin.javase.base;

import java.util.*;

public class Account {

    private String name;
    private int balance;

    public Account() {
    }

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Java约定
     * 如果两个对象通过equals()比较是相等的，那么它们的hashCode()必须返回相同的值
     * @param o
     * @return
     */
    //idea自动生成：正确实现 equals 和 hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return balance == account.balance && Objects.equals(name, account.name);
    }

    //idea自动生成：正确实现 equals 和 hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }

    public static void main(String[] args) {
        Account a1 = new Account("zs",100);
        Account a2 = new Account("zs",100);
        System.out.println(a1.equals(a2));
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());

        //如果去掉重写的hashCode方法，结果为null
        Map<Account,String> retMap = new HashMap<>();
        retMap.put(a1,"hello");
        System.out.println(retMap.get(a2));

        //如果去掉重写的hashCode方法，结果为false
        Set<Account> retSet = new HashSet<>();
        retSet.add(a1);
        System.out.println(retSet.contains(a2));
    }
}
