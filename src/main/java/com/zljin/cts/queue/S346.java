package com.zljin.cts.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一个移动平均的数据结构，计算数据流中最近 size 个元素的平均值
 */
public class S346 {

    private Deque<Integer> queue;

    private double sum;

    private int size;

    public S346(Deque<Integer> queue, double sum, int size) {
        this.queue = new LinkedList<>();
        this.sum = sum;
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        queue.offer(val);
        sum += val;
        return sum / queue.size();
    }


}
