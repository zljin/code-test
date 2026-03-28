package com.zljin.poc.eventdrivenv2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * simulate DB
 */
public class MyDB {

    //其他进程会导数据
    private static final List<EventTask> EVENT_TASK_LIST = new ArrayList<>();

    public static List<EventTask> getEventTaskList() {
        return EVENT_TASK_LIST;
    }

    //design db field
    @Data
    static class EventTask {
        public enum Status {
            PENDING,     // 待处理
            PROCESSING,  // 处理中
            COMPLETED,   // 已完成
            FAILED       // 处理失败
        }

        private Integer id;
        private String eventType;
        private String data;
        private Status status = Status.PENDING;
        private int retryCount = 0;
        public void incrementRetryCount() {
            this.retryCount++;
        }
    }
}
