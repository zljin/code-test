package com.zljin.heap;

/**
 * todo study
 */
public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int i) { return (i-1)/2; }
    private int leftChild(int i) { return 2*i + 1; }
    private int rightChild(int i) { return 2*i + 2; }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 插入元素
    public void insert(int value) {
        if (size >= capacity) {
            System.out.println("堆已满");
            return;
        }

        int index = size;
        heap[size] = value;
        size++;

        // 向上堆化
        while (index != 0 && heap[parent(index)] < heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // 删除堆顶元素
    public int extractMax() {
        if (size <= 0) {
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size-1];
        size--;
        maxHeapify(0);

        return root;
    }

    // 向下堆化
    private void maxHeapify(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    // 构建堆
    public void buildHeap(int[] arr) {
        if (arr.length > capacity) {
            System.out.println("输入数组太大");
            return;
        }

        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;

        // 从最后一个非叶子节点开始堆化
        for (int i = (size/2)-1; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    // 堆排序
    public void heapSort() {
        int originalSize = size;

        for (int i = size-1; i > 0; i--) {
            swap(0, i);
            size--;
            maxHeapify(0);
        }

        size = originalSize; // 恢复堆大小
    }

    // 打印堆内容
    public void printHeap() {
        System.out.print("堆内容: ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(10);

        // 插入元素
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(20);
        maxHeap.insert(15);
        maxHeap.insert(30);

        maxHeap.printHeap(); // 输出: 30 20 10 5 15

        // 删除堆顶
        System.out.println("删除的元素: " + maxHeap.extractMax()); // 30
        maxHeap.printHeap(); // 输出: 20 15 10 5

        // 堆排序
        maxHeap.heapSort();
        System.out.print("堆排序结果: ");
        maxHeap.printHeap(); // 输出: 5 10 15 20
    }
}
