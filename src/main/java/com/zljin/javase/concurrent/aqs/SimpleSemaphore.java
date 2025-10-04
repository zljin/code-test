package com.zljin.javase.concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AQS实现的简单信号量
 * 核心思想：
 * 状态管理：使用AQS的状态(volatile int state)来表示可用的许可证数量。
 * 队列管理：AQS内部维护一个FIFO等待队列，当线程尝试获取许可证但失败时，会被加入到这个队列中等待。
 * 模版管理：通过继承AQS并实现其模板方法(tryAcquireShared和tryReleaseShared)，定义许可证的获取和释放逻辑。
 * 共享模式：这个信号量是共享的，意味着多个线程可以同时持有许可证，前提是许可证数量足够。
 */
public class SimpleSemaphore extends AbstractQueuedSynchronizer {

    public SimpleSemaphore(int permits) {
        setState(permits);
    }

    @Override
    protected int tryAcquireShared(int acquires) {
        for (;;) {
            int available = getState();
            int remaining = available - acquires;
            if (remaining < 0 ||
                    compareAndSetState(available, remaining)) {
                return remaining;
            }
        }
    }

    @Override
    protected boolean tryReleaseShared(int releases) {
        for (;;) {
            int current = getState();
            int next = current + releases;
            if (compareAndSetState(current, next)) {
                return true;
            }
        }
    }

    //提供的对外方法
    public void acquire() {
        //共享模式
        acquireShared(1);
    }

    //提供的对外方法
    public void release() {
        releaseShared(1);
    }
}
