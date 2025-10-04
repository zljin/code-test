package com.zljin.javase.concurrent.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AQS
 *
 * 核心思想：
 * 状态管理：使用AQS的状态(volatile int state)来表示锁的状态，0表示未锁定，1表示锁定。+ CAS
 * 队列管理：AQS内部维护一个FIFO等待队列，当线程尝试获取锁但失败时，会被加入到这个队列中等待。
 * 模版管理：通过继承AQS并实现其模板方法(tryAcquire和tryRelease)，定义锁的获取和释放逻辑。
 * 独占模式：这个互斥锁是独占的，意味着同一时间只能有一个线程持有锁。
 **
 */
public class SimpleMutex extends AbstractQueuedSynchronizer {
    // 尝试获取锁
    @Override
    protected boolean tryAcquire(int acquires) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    // 尝试释放锁
    @Override
    protected boolean tryRelease(int releases) {
        if (getState() == 0)
            throw new IllegalMonitorStateException();
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    // 对外提供的锁方法
    public void lock() {
        //独占模式
        acquire(1);
    }

    public void unlock() {
        release(1);
    }
}
