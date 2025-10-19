package com.zljin.javase.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * 怎么获取线程的执行结果并汇总
 * <p>
 * 1. https://juejin.cn/post/6844904195162636295
 */
public class CompletableFutureTask {

    public static void main(String[] args) throws Exception {
        // 模拟用户服务
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("获取用户信息...");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            return "用户A";
        },ThreadPoolManager.THREAD_POOL_EXECUTOR);

        // 模拟订单服务
        CompletableFuture<Integer> orderFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("获取订单数量...");
            try { Thread.sleep(800); } catch (InterruptedException e) {}
            return 5;
        },ThreadPoolManager.THREAD_POOL_EXECUTOR);

        // 组合两个服务的结果
        CompletableFuture<String> resultFuture = userFuture
                .thenCombine(orderFuture, (user, orderCount) ->
                        String.format("用户 %s 有 %d 个订单", user, orderCount))
                .exceptionally(ex -> "服务调用失败: " + ex.getMessage());

        System.out.println("最终结果: " + resultFuture.get());
        ThreadPoolManager.THREAD_POOL_EXECUTOR.shutdown();
    }


    public static void main1(String[] args) throws Exception {
        CompletableFutureTask ct = new CompletableFutureTask();
        long t1 = System.currentTimeMillis();
        //ct.test01();
        //ct.test02();
        //ct.test03();
        //ct.test031();
        //ct.test04();
        ct.test05();
        long costTime = System.currentTimeMillis() - t1;
        System.out.println("cost: " + costTime);
        ThreadPoolManager.THREAD_POOL_EXECUTOR.shutdown();
    }


    //基础创建方法
    void test01() throws Exception {
        CompletableFuture<String> futureTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步任务");
            return "result1";
        }, ThreadPoolManager.THREAD_POOL_EXECUTOR);

        CompletableFuture.runAsync(() -> {
            System.out.println("无返回值的异步任务");
        });
        System.out.println(futureTask.get());
    }

    //结果处理链式操作
    void test02() throws Exception {

        //thenApply - 转换结果
        CompletableFuture<String> returnFuture = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(String::toUpperCase);
        System.out.println(returnFuture.get());

        //thenAccept - 消费结果
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")
                .thenAccept(result -> System.out.println(result));

        //thenRun - 完成后执行操作
        CompletableFuture.supplyAsync(() -> "hello").
                thenRun(() -> System.out.println("完成任务后的操作"));
    }

    //组合多个 Future
    void test03() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");


        //thenCompose - 链式组合
        CompletableFuture.supplyAsync(() -> "Hello2")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World2"))
                .thenAccept(System.out::println);


        //thenCombine - 合并两个结果
        CompletableFuture<String> futureCombine = future1.thenCombine(future2, (f1, f2) -> {
            System.out.println("合并两个结果中");
            return f1 + " " + f2;
        });
        futureCombine.thenAccept(System.out::println);
    }

    //组合多个 Future
    void test031() throws Exception {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "任务1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "任务2");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "任务3");

        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2, future3);
        all.get(); // 等待所有任务完成
        System.out.println("所有任务都完成了");

        CompletableFuture<String> fast = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            return "快速任务";
        });

        CompletableFuture<String> slow = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return "慢速任务";
        });

        CompletableFuture<Object> any = CompletableFuture.anyOf(fast, slow);
        System.out.println("第一个完成的结果: " + any.get()); // 输出: 第一个完成的结果: 快速任务
    }

    //异常处理 exceptionally - 异常恢复
    void test04() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("出错了!");
            return "正常结果";
        }).exceptionally(ex -> "异常恢复结果: " + ex.getMessage());

        System.out.println(future.get()); // 输出: 异常恢复结果: java.lang.RuntimeException: 出错了!
    }

    //handle - 同时处理结果和异常
    void test05() throws Exception{
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                return "成功结果";
            } else {
                throw new RuntimeException("随机失败");
            }
        }).handle((result, ex) -> {
            if (ex != null) {
                return "处理异常: " + ex.getMessage();
            }
            return "处理结果: " + result;
        });

        System.out.println(future.get());
    }
}
