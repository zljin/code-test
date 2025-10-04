package com.zljin.javase.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池：
 * https://blog.csdn.net/qq_35971258/article/details/115268948
 *
 * 降低资源消耗，提高响应速度，便于线程管理
 */
public class ThreadPoolManager {

    private ThreadPoolManager(){}

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;
    /**
     * CPU密集型： 线程数 = CPU核数 + 1
     * IO密集型： 线程数 = CPU核数 * 2
     */
    private static final int CORE_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 3;
    private static final int QUEUE_CAPACITY = 5;
    private static final int KEEP_ALIVE_TIME = 60;

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE
                , MAX_POOL_SIZE
                , KEEP_ALIVE_TIME
                , TimeUnit.SECONDS
                /**
                 * 工作队列，存放todo的线程
                 * ArrayBlockingQueue: 有界队列
                 * SynchronousQueue：直接交接，无队列存储 --> 造成线程数过度增加
                 * LinkedBlockingQueue: 无界队列，无限存储  -->造成任务堆积
                 */
                , new ArrayBlockingQueue<>(QUEUE_CAPACITY)
                /**
                 * 四大拒绝策略: 当最大线程数已满,阻塞队列已满
                 * AbortPolicy（默认） -> 直接抛出异常
                 * CallerRunsPolicy -> 将任务返回给原来的进程执行
                 * DiscardPolicy --> 直接抛弃任务
                 * DiscardOldestPolicy -> 此策略将丢弃最早的未处理的任务请求
                 */
                , new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 9会触发CallerRunsPolicy 和 MAX_POOL_SIZE
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " running");
            });
        }
        //等待正在进行任务执行完，进行停止后销毁
        ThreadPoolManager.THREAD_POOL_EXECUTOR.shutdown();

        //立即销毁,将未执行完的线程保存
//        List<Runnable> runnalbeList = executorService.shutdownNow();
    }
}
