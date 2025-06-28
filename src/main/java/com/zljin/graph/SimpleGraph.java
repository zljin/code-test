package com.zljin.graph;

import java.util.*;

/**
 * 构建无向图
 *
 * BFS and DFS
 *
 * @param <E>
 */
public class SimpleGraph<E> {

    private Map<E, GraphNode<E>> nodes = new HashMap<>();

    public void addNode(E name) {
        nodes.putIfAbsent(name, new GraphNode<>(name));
    }

    //添加无向边
    public void addEdge(String node1, String node2) {
        GraphNode<E> n1 = nodes.get(node1);
        GraphNode<E> n2 = nodes.get(node2);

        if (n1 != null && n2 != null) {
            n1.addNeighbor(n2);
        }
    }

    public GraphNode<E> getNode(String name) {
        return nodes.get(name);
    }

    public void printGraph() {
        nodes.forEach((name, nodes) -> {
            System.out.print(name + "-->");
            nodes.getNeighbors().forEach(neighbor -> {
                System.out.print(neighbor.getValue() + " ");
            });
            System.out.println();
        });
    }

    /**
     * 广度优先搜索（BFS）遍历图
     * 依赖queue, 先进先出
     * @param start
     */
    public void bfs(E start) {

        GraphNode<E> startNode = nodes.get(start);

        if (startNode == null) return;
        System.out.println("BFS遍历结果 (" + startNode.getValue() + "开始):");


        Queue<GraphNode<E>> queue = new LinkedList<>();
        Set<GraphNode<E>> visited = new HashSet<>();

        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            GraphNode<E> current = queue.poll();
            System.out.print(current.getValue() + "  ");
            current.getNeighbors().forEach(neighbor -> {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            });
        }
        System.out.println();
    }

    /**
     * 广度优先搜索（DFS）遍历图
     * 依赖Stack, 先进后出
     * @param startName
     */
    public void dfs(E startName) {
        GraphNode<E> startNode = nodes.get(startName);

        if (startNode == null) return;
        System.out.println("DFS遍历结果 (" + startNode.getValue() + "开始):");

        Deque<GraphNode<E>> stack = new LinkedList<>();
        Set<GraphNode<E>> visited = new HashSet<>();

        stack.push(startNode);
        visited.add(startNode);

        while (!stack.isEmpty()) {
            GraphNode<E> current = stack.pop();
            System.out.print(current.getValue() + "  ");

            // 3. 将邻居按反向顺序入栈（保证访问顺序）
            List<GraphNode> neighbors = new ArrayList<>(current.getNeighbors());
            // 反转邻居列表，确保先访问第一个邻居
            Collections.reverse(neighbors);

            neighbors.forEach(neighbor -> {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            });
        }
        System.out.println();
    }

    /**
     * 采用递归方式实现深度优先搜索（DFS）遍历图
     * @param startName
     */
    public void dfs2(E startName) {
        GraphNode start = nodes.get(startName);
        if (start == null) return;

        System.out.println("DFS遍历结果 (" + startName + "开始):");
        Set<GraphNode<E>> visited = new HashSet<>();
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(GraphNode<E> node, Set<GraphNode<E>> visited) {
        if (visited.contains(node)) return;

        System.out.print(node.getValue() + "  ");
        visited.add(node);

        for (GraphNode<E> neighbor : node.getNeighbors()) {
            dfsRecursive(neighbor, visited);
        }
    }

    public static void main(String[] args) {
        SimpleGraph<String> cityGraph = new SimpleGraph<>();
        cityGraph.addNode("北京");
        cityGraph.addNode("上海");
        cityGraph.addNode("广州");
        cityGraph.addNode("深圳");
        cityGraph.addNode("杭州");
        cityGraph.addNode("成都");

        // 3. 添加边（城市之间的连接）
        cityGraph.addEdge("北京", "上海");
        cityGraph.addEdge("北京", "广州");
        cityGraph.addEdge("上海", "杭州");
        cityGraph.addEdge("广州", "深圳");
        cityGraph.addEdge("广州", "成都");
        cityGraph.addEdge("深圳", "成都");
        cityGraph.addEdge("杭州", "成都"); // 添加一个跨区域连接

        // 4. 打印图结构
        cityGraph.printGraph();

        // 5. 从北京开始BFS遍历
        cityGraph.bfs("北京");

        // 6. 从北京开始DFS遍历
        cityGraph.dfs("北京");
        cityGraph.dfs2("北京");
    }


}
