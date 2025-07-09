package com.zljin.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点类
 * @param <E>
 */
public class GraphNode<E> {
    private E value;

    private List<GraphNode<E>> neighbors;

    public GraphNode(E value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    // 添加邻居节点 （无向图）
    public void addNeighbor(GraphNode<E> neighbor) {
        if (!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
            neighbor.neighbors.add(this); // 无向图需要双向连接
        }
    }

    public E getValue() {
        return value;
    }

    public List<GraphNode<E>> getNeighbors() {
        return neighbors;
    }
}
