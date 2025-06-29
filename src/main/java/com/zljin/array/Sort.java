package com.zljin.array;

import java.util.Arrays;

/**
 * 排序算法示例
 * 1. 冒泡
 * 2. 归并
 * 3. 快速
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{38, 27, 43, 3, 9, 82, 10};
        System.out.println(Arrays.toString(arr));
        //bubbleSort(arr);
        //mergeSort(arr);
        quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡排序 稳定
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)  原地排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 归并排序 稳定
     * <p>
     * 时间复杂度：O(n log n) 所有情况
     * 空间复杂度：O(n)的额外空间
     * 稳定性：稳定
     * <p>
     * 归并排序的递归方法
     * 将待排序数组分为两半，分别对两半进行归并排序，然后将两半合并成一个有序数组
     * <p>
     * 递归三要素：
     * 1. 递归终止条件
     * 2. 递归分解：需要解决的问题如何分解为更小的相同问题
     * 3. 递归合并：每一次递归调用都应该向终止条件靠近，并且每次递归调用都会返回结果，最终这些结果会被组合起来得到原问题的解
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        // 检查数组是否为空或只有一个元素
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 创建临时数组用于合并操作（避免在递归中重复创建）
        int[] temp = new int[arr.length];

        // 调用递归排序方法
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    /**
     * 递归排序方法（分治思想）
     *
     * @param arr   原始数组
     * @param left  左边界索引
     * @param right 右边界索引
     * @param temp  临时数组
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        // 递归终止条件：当前区间只有一个元素
        if (left >= right) {
            return;
        }

        // 计算中间位置
        int mid = left + (right - left) / 2;

        // 递归排序左半部分
        mergeSort(arr, left, mid, temp);

        // 递归排序右半部分
        mergeSort(arr, mid + 1, right, temp);

        // 合并两个有序部分
        merge(arr, left, mid, right, temp);
    }

    /**
     * 合并两个有序数组
     *
     * @param arr   原始数组
     * @param left  左边界
     * @param mid   中间位置
     * @param right 右边界
     * @param temp  临时数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;       // 左半部分起始位置
        int j = mid + 1;    // 右半部分起始位置
        int k = 0;          // 临时数组索引

        // 1. 比较两个子数组的元素，按顺序放入临时数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 2. 将左半部分剩余元素放入临时数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 3. 将右半部分剩余元素放入临时数组
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 4. 将临时数组中的有序数据复制回原数组
        k = 0;
        while (left <= right) {
            arr[left++] = temp[k++];
        }
    }

    /**
     * 快速排序
     * <p>
     * 时间复杂度：最佳O(n log n) 平均O(n log n) 最差O(n^2)
     * 空间复杂度：最佳O(log n) 平均O(log n) 最差O(n)  采用递归栈
     * 稳定性：不稳定
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        // 检查数组是否为空或只有一个元素
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 调用递归排序方法
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 递归排序方法（分治思想）
     * @param arr   原始数组
     * @param left  左边界索引
     * @param right 右边界索引
     */
    private static void sort(int[] arr, int left, int right) {
        // 递归终止条件：当前区间只有一个元素或为空
        if (left >= right) {
            return;
        }

        // 分区操作，返回基准位置
        int pivotIndex = partition(arr, left, right);

        // 递归排序左半部分（小于基准的部分）
        sort(arr, left, pivotIndex - 1);

        // 递归排序右半部分（大于基准的部分）
        sort(arr, pivotIndex + 1, right);
    }

    /**
     * 分区操作 - 核心步骤
     * @param arr   原始数组
     * @param left  左边界
     * @param right 右边界
     * @return 基准元素最终位置
     */
    private static int partition(int[] arr, int left, int right) {
        // 1. 选择基准元素（这里选择中间位置的元素）
        int mid = left + (right - left) / 2;
        int pivot = arr[mid];

        // 2. 将基准元素移到最右边（简化后续操作）
        swap(arr, mid, right);

        // 3. 初始化分区指针
        int i = left;  // i 指向小于基准的区域的末尾

        // 4. 遍历数组，将小于基准的元素移到左侧
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;  // 移动分区指针
            }
        }

        // 5. 将基准元素放回正确位置
        swap(arr, i, right);

        // 6. 返回基准位置
        return i;
    }

    /**
     * 交换数组中的两个元素
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
