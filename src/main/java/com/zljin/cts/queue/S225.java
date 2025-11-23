package com.zljin.cts.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Deque 双端队列实现即可
 */
public class S225 {
    public static void main(String[] args) {

        //as stack by Deque:LIFO
        Deque<String> stack = new ArrayDeque<>();

        stack.push("a");
        stack.push("b");
        stack.push("c");

        while (!stack.isEmpty()) {
            System.out.print(stack.pop()+" ");
        }

        System.out.println();

        //as queue by Deque:FIFO
        Deque<String> queue = new LinkedList<>();
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");

        while (!queue.isEmpty()) {
            System.out.print(queue.poll()+" ");
        }
        System.out.println();
    }
}
