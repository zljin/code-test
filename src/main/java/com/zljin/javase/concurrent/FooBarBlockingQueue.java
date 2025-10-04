package com.zljin.javase.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 两个线程交替打印 foo 和 bar
 * 使用阻塞队列实现
 */
public class FooBarBlockingQueue {

    private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);
    private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);

    private int times;

    public FooBarBlockingQueue(int times) {
        this.times = times;
    }

    public void printFoo(Runnable printFoo) throws Exception {
        for (int i = 0; i < times; i++) {
            foo.put(i);
            printFoo.run();
            bar.put(i);
        }
    }

    public void printBar(Runnable printBar) throws Exception {
        for (int i = 0; i < times; i++) {
            bar.take();
            printBar.run();
            foo.take();
        }
    }

    public static void main(String[] args) {
        FooBarBlockingQueue fooBar = new FooBarBlockingQueue(10);

        new Thread(() -> {
            try {
                fooBar.printFoo(() -> System.out.print("foo"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.printBar(() -> System.out.print("bar"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}
