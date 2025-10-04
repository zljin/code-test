package com.zljin.javase.concurrent.foobar;


/**
 * 使用 synchronized、wait() 和 notify() 实现两个线程交替打印 "foo" 和 "bar"
 * 1. synchronized 关键字用于确保同一时间只有一个线程可以执行被同步的方法或代码块
 * 2. wait() 方法使当前线程进入等待状态，并释放锁，直到另一个线程调用 notify() 或 notifyAll() 方法
 * 3. notify() 方法唤醒一个正在等待该对象锁的线程
 */
public class FooBarSync {

    private static final Object lock = new Object();

    int flag = 1; // 1: foo, 2: bar

    private int times;

    public FooBarSync(int times) {
        this.times = times;
    }

    public void printFoo(Runnable printFoo) throws Exception {
        for (int i = 0; i < times; i++) {
            synchronized (lock) {
                /**
                 * 这里什么为什么用while而不是if?
                 * 虚假唤醒：线程可能在没有notify()的情况下被唤醒,始终在循环中检查等待条件
                 */
                while (flag != 1) {
                    lock.wait();
                }
                printFoo.run();
                flag = 2;
                /**
                 * notify()和notifyAll()的区别?
                 * notify()随机唤醒一个等待线程
                 * notifyAll()唤醒所有等待线程
                 * 在这个场景下，notify()就足够了，因为只有两个线程在等待
                 * 使用notifyAll()会增加不必要的上下文切换开销
                 * 如果有多个线程等待同一个锁，且这些线程中有多个线程可以继续执行，那么使用notifyAll()可能更合适，适合消费者模型
                 * 但在这个特定的场景下，使用notify()更高效
                 */
                lock.notify();
            }
        }
    }

    public void printBar(Runnable printBar) throws Exception {
        for (int i = 0; i < times; i++) {
            synchronized (lock) {
                while (flag != 2) {
                    // 线程状态变化：WAITING
                    lock.wait();
                }
                printBar.run();

                //线程状态变化：TIMED_WAITING
//                Thread.sleep(1000);
                flag = 1;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        FooBarSync fooBar = new FooBarSync(10);
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
