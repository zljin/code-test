package com.zljin.javase.clump;

import com.zljin.javase.base.Account;
import com.zljin.javase.base.Person;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 集合的排序
 */
public class ClumpSort {

    /**
     * 并发环境的排序
     * @param args
     */
    public static void main(String[] args) {
        List<Person> personList = new CopyOnWriteArrayList<>();
        personList.add(new Person("ls", 30));
        personList.add(new Person("ww", 10));
        personList.add(new Person("zs", 20));

        List<Person> sortedList = new ArrayList<>(personList);
        Collections.sort(sortedList);

        sortedList.forEach(a->{
            System.out.println(a.getName()+":"+a.getAge());
        });
    }


    /**
     * 集合的自然排序,实现Comparable接口重写compareTo方法
     * @param args
     */
    public static void main1(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("ls", 30));
        personList.add(new Person("ww", 10));
        personList.add(new Person("zs", 20));

        //自然排序
        //Collections.sort(personList);

        //Stream API + 定制排序,Comparator.comparing()方法
        List<Person> sortedList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());

        sortedList.forEach(a->{
            System.out.println(a.getName()+":"+a.getAge());
        });

    }

    /**
     * 集合的比较器排序Comparator
     * @param args
     */
    public static void main11(String[] args) {
        TreeSet<Account> ts = new TreeSet<>(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getBalance() - o2.getBalance();
            }
        });

        ts.add(new Account("zs", 100));
        ts.add(new Account("ls", 200));
        ts.add(new Account("ww", 50));

        ts.forEach(a->{
            System.out.println(a.getName()+":"+a.getBalance());
        });
    }
}
