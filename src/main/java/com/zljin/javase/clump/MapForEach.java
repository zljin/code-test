package com.zljin.javase.clump;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Map的遍历
 */
public class MapForEach {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);


        //性能最优,EntrySet一次遍历直接拿到key和value，没有额外查找
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("安全删除");

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getKey().equals("A")) {
                iterator.remove(); // 安全删除
            }
        }
//        map.entrySet().removeIf(entry -> entry.getKey().equals("A"));
        map.forEach((k, v) -> System.out.println(k + ":" + v));

    }
}
