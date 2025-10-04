package com.zljin.javase.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印 foo 和 bar
 * 使用 ReentrantLock 实现
 */
public class FoorBarReentrantLock {

    private static final Lock lockA = new ReentrantLock();

    private int times;

    public FoorBarReentrantLock(int times) {
        this.times = times;
    }

    public void printFoo(Runnable printFoo) throws Exception {
        for (int i = 0; i < times; i++) {
            lockA.lock();
            try {
                printFoo.run();
            } finally {
                lockA.unlock();
            }
        }
    }

    public void printBar(Runnable printBar) throws Exception {
        for (int i = 0; i < times; i++) {
            lockA.lock();
            try {
                printBar.run();
            } finally {
                lockA.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FoorBarReentrantLock fooBar = new FoorBarReentrantLock(10);
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
