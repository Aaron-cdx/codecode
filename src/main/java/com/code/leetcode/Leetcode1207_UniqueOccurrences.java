package com.code.leetcode;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/10/28 15:03
 * @Motto Keep thinking, keep coding!
 * leetcode 1207、独一无二的出现次数
 * 题目：https://leetcode-cn.com/problems/unique-number-of-occurrences/
 */
public class Leetcode1207_UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        // 利用set去重复
        for (Integer value : map.values()) {
            if (!set.add(value)) {
                return false;
            }
        }
        // 或者直接使用set将当前的value放入，如果长度小于之前的值为false
        return true;
    }
}
