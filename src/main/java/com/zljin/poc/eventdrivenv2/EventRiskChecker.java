package com.zljin.poc.eventdrivenv2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class EventRiskChecker {

    private final ScheduledExecutorService scheduler;
    private final ExecutorService workerPool;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public EventRiskChecker() {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.workerPool = Executors.newWorkStealingPool();
    }

    public void start() {
        if (running.compareAndSet(false, true)) {
            log.info("启动风险检查任务调度器");
            // 每5分钟执行一次轮询
            scheduler.scheduleWithFixedDelay(this::pollAndProcess, 0, 5, TimeUnit.MINUTES);
        }
    }

    public void stop() {
        if (running.compareAndSet(true, false)) {
            log.info("停止风险检查任务调度器");
            scheduler.shutdown();
            workerPool.shutdown();
        }
    }

    // 手动触发风险检查
    public void triggerCheck() {
        log.info("手动触发风险检查");
        pollAndProcess();
    }

    private void pollAndProcess() {
        if (!running.get()) return;

        try {
            // todo 查询队列的所有数据(满足重试<3，且不为COMPLETED的数据)
            List<MyDB.EventTask> tasks = MyDB.getEventTaskList();
            log.info("找到 {} 个需要检查的事件", tasks.size());

            // 并发处理任务
            for (MyDB.EventTask task : tasks) {
                workerPool.submit(() -> processTask(task));
            }

        } catch (Exception e) {
            log.error("轮询处理出错: {}", e.getMessage(), e);
        }
    }

    private void processTask(MyDB.EventTask task) {
        try {
            log.info("开始检查事件风险: id={}, type={}", task.getId(), task.getEventType());

            // 标记为处理中
            task.setStatus(MyDB.EventTask.Status.PROCESSING);

            //todo simulate 调用下游服务进行风险评估
            boolean hasRisk = true;

            // 更新状态
            if (hasRisk) {
                task.setStatus(MyDB.EventTask.Status.FAILED);
                log.warn("事件存在风险: id={}", task.getId());
            } else {
                task.setStatus(MyDB.EventTask.Status.COMPLETED);
                log.info("事件风险检查通过: id={}", task.getId());
            }

            //todo 更新到数据库 status

        } catch (Exception e) {
            log.error("处理事件风险检查出错: id={}, error={}", task.getId(), e.getMessage(), e);
            task.setStatus(MyDB.EventTask.Status.FAILED);
            task.incrementRetryCount();
            //todo 更新到数据库 status
        }
    }
}
