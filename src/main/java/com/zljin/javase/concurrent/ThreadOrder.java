package com.zljin.javase.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 如何保证线程按顺序执行
 */
public class ThreadOrder {

    private static final Semaphore semaphore1 = new Semaphore(0);
    private static final Semaphore semaphore2 = new Semaphore(0);

    public static void main(String[] args){

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" run");
            semaphore1.release();
        },"线程1").start();

        new Thread(()->{
            try {
                semaphore1.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" run");
            semaphore2.release();
        },"线程2").start();

        new Thread(()->{
            try {
                semaphore2.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" run");
            semaphore2.release();
        },"线程3").start();

    }
}
