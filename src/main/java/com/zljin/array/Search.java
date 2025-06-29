package com.zljin.array;

public class Search {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println(binarySearch(arr, 12)); // 输出：3
    }

    /**
     * 二分查找
     *
     * @param arr
     * @param target
     * @return
     */
    private static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // 找中间点
            if (arr[mid] == target) {
                System.out.println("Found at index: " + mid);
                return mid; // 找到目标，返回索引
            } else if (arr[mid] < target) {
                low = mid + 1; // 目标在右半边，砍掉左半边
            } else {
                high = mid - 1; // 目标在左半边，砍掉右半边
            }
        }
        return -1;
    }


}
