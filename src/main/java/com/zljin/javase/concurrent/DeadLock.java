package com.zljin.javase.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 防死锁：
 * 1. 避免：实现全局锁排序 or 设置锁超时
 */
public class DeadLock {

    private static final Object lockFirst = new Object();
    private static final Object lockSecond = new Object();

    /**
     * 全局锁排序，每个额线程必须先lockFirst 再 lockSecond，否则死锁
     * @param args
     */
    public static void main2(String[] args) {
        new Thread(()->{
            synchronized (lockFirst){
                System.out.println(Thread.currentThread().getName()+" 获取到 lockFirst");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 等待获取 lockSecond");
                synchronized (lockSecond){
                    System.out.println(Thread.currentThread().getName()+" 获取到 lockSecond");
                }
            }
        },"线程1").start();

        new Thread(()->{
            synchronized (lockFirst){
                System.out.println(Thread.currentThread().getName()+" 获取到 lockSecond");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 等待获取 lockFirst");
                synchronized (lockSecond){
                    System.out.println(Thread.currentThread().getName()+" 获取到 lockFirst");
                }
            }
        },"线程2").start();
    }

    private static final Lock lockA = new ReentrantLock();
    private static final Lock lockB = new ReentrantLock();


    public static void main(String[] args) throws Exception{
        new Thread(()->{
            while (true) {
                boolean gotLockA = false;
                boolean gotLockB = false;
                try {
                    gotLockA = lockA.tryLock(100, TimeUnit.MILLISECONDS);
                    //返回true则为获取锁成功
                    if (gotLockA) {
                        System.out.println(Thread.currentThread().getName() + " 获取到 lockA");
                        gotLockB = lockB.tryLock(100, TimeUnit.MILLISECONDS);
                        if (gotLockB) {
                            System.out.println(Thread.currentThread().getName() + " 获取到 lockB");
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (gotLockB) {
                        lockB.unlock();
                    }
                    if (gotLockA) {
                        lockA.unlock();
                    }
                }
            }
        },"线程1").start();

        new Thread(()->{
            while (true) {
                boolean gotLockA = false;
                boolean gotLockB = false;
                try {
                    gotLockA = lockA.tryLock(100, TimeUnit.MILLISECONDS);
                    if (gotLockA) {
                        System.out.println(Thread.currentThread().getName() + " 获取到 lockA");
                        gotLockB = lockB.tryLock(100, TimeUnit.MILLISECONDS);
                        if (gotLockB) {
                            System.out.println(Thread.currentThread().getName() + " 获取到 lockB");
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (gotLockB) {
                        lockB.unlock();
                    }
                    if (gotLockA) {
                        lockA.unlock();
                    }
                }
            }
        },"线程2").start();

    }

}
