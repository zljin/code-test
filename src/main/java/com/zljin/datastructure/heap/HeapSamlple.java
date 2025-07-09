package com.zljin.datastructure.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 堆是一棵完全二叉树（所有层级都被完全填充，除了最后一层，且最后一层的节点尽可能靠左排列）
 *
 * 最大堆：每个节点的值都大于或等于其子节点的值
 * 最小堆：每个节点的值都小于或等于其子节点的值
 *
 * 堆通常采用数组的形式而非指针的形式
 *
 * Java中的堆实现：PriorityBlockingQueue (线程安全)
 */
public class HeapSamlple {
    public static void main(String[] args) {

        System.out.println("----最小堆----");
        PriorityBlockingQueue <Integer> minHeap = new PriorityBlockingQueue<>();//并发安全

        minHeap.add(30);
        minHeap.add(10);
        minHeap.add(20);
        minHeap.add(5);

        System.out.println("队头元素: " + minHeap.peek()); // 5
        System.out.println("遍历队列:");
        while (!minHeap.isEmpty()) {//使用poll()才能获取有序序列，不要用迭代
            System.out.print(minHeap.poll() + " "); // 5, 10, 20, 30
        }
        System.out.println("\n");

        System.out.println("----最大堆----");
        PriorityBlockingQueue <Integer> maxHeap = new PriorityBlockingQueue<>(100,Comparator.reverseOrder());//初始化容量可减少扩容

        maxHeap.add(30);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(5);

        System.out.println("队头元素: " + maxHeap.peek()); // 5
        System.out.println("遍历队列:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // 5, 10, 20, 30
        }
        System.out.println("\n");

        // 3. 自定义对象排序
        System.out.println("=== 自定义对象排序 ===");
        PriorityQueue<Person> ageHeap = new PriorityQueue<>(
                Comparator.comparingInt(Person::getAge)
        );

        ageHeap.add(new Person("Alice", 30));
        ageHeap.add(new Person("Bob", 25));
        ageHeap.add(new Person("Charlie", 35));

        System.out.println("按年龄排序:");
        while (!ageHeap.isEmpty()) {
            System.out.println(ageHeap.poll());
        }

    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

}
