package com.zljin.codes;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/description/
 */
public class TwoSumDemo {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        //(value,index)
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (resultMap.containsKey(target - nums[i])) {
                return new int[]{resultMap.get(target - nums[i]), i};
            }
            resultMap.put(nums[i], i);
        }
        return new int[]{};
    }
}
