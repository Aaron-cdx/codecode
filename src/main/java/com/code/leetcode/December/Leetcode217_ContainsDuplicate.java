package com.code.leetcode.December;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2020/12/13 17:11
 * @Motto Keep thinking, keep coding!
 * leetcode 217、存在重复元素
 * 题目：https://leetcode-cn.com/problems/contains-duplicate/
 * 题目的意思就是判断是否存在重复元素
 */
public class Leetcode217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length <= 1) return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)) return true;
        }
        return false;
    }
}
