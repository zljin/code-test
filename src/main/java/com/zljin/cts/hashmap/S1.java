package com.zljin.cts.hashmap;

import java.util.*;

/**
 * 两数之和
 */
public class S1 {
    public static void main(String[] args) {
        S1 s = new S1();
        int[] nums = new int[]{2, 3, 4, 5};
        int[] result = s.twoSum(nums, 7);
        Arrays.stream(result).forEach(System.out::println);
    }

    public int[] twoSum(int[] nums, int target) {
        /*for(int i = 0;i<nums.length;i++){
            for(int j = i;j<nums.length;j++){
                if(target-nums[i] == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};*/

        Map<Integer, Integer> ret = new HashMap<>();//v->i
        for (int i = 0; i < nums.length; i++) {
            if (ret.containsKey(target - nums[i])) {
                return new int[]{ret.get(target - nums[i]), i};
            }
            ret.put(nums[i], i);
        }
        return new int[]{};
    }
}
