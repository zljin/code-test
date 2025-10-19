package com.zljin.javase.concurrent.p2c;

import com.zljin.javase.concurrent.ThreadPoolManager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者-消费者模式注意事项
 * <p>
 * 1、缓冲区管理，使用阻塞有届队列ArrayBlockingQueue
 * 2、生产速率，队列满的拒绝策略，监控降速
 * 3、消费能力，如批量处理，错误处理，重试机制
 */
public class BlockingQueueP2C {

    public static void main(String[] args) {
        Factory factory = new Factory(10);

        ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(factory::producer);
        ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(factory::consumer);
        ThreadPoolManager.THREAD_POOL_EXECUTOR.shutdown();
    }


    static class Factory {

        private BlockingQueue<String> bq = new ArrayBlockingQueue<>(3);

        private int total;

        private volatile boolean producerFinished;

        public Factory(int total) {
            this.total = total;
        }

        public void producer() {
            try {
                for (int i = 0; i < total; i++) {
                    bq.put("product" + i);
                    Thread.sleep(1000);
                    System.out.println("product" + i + "done");
                }
            } catch (InterruptedException e) {
                System.err.println("生产者被中断: " + e.getMessage());
                Thread.currentThread().interrupt();
            } finally {
                producerFinished = true;
            }
        }

        public void consumer() {
            try {
                //退出条件：生产者完成且队列为空
                while (!bq.isEmpty() || !producerFinished) {
                    String take = bq.take();
                    Thread.sleep(2000);
                    System.out.println(take + " consumer done");
                }
            } catch (InterruptedException e) {
                System.err.println("消费者被中断: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}

