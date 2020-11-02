package com.code.leetcode.november;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2020/11/2 8:47
 * @Motto Keep thinking, keep coding!
 * leetcode 349、两个数组的交集
 * 题目：https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class Leetcode349_ArrayIntersection {
    public static void main(String[] args) {
        Leetcode349_ArrayIntersection test = new Leetcode349_ArrayIntersection();
        test.intersection(new int[]{4, 7, 9, 7, 6, 7}, new int[]{5, 0, 0, 6, 1, 6, 2, 2, 4});
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[]{};
        // 使用nums1存储吧
        int cnt = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> s1 = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                s1.add(i);
            }
        }
        int[] res = new int[s1.size()];
        for (Integer integer : s1) {
            res[cnt++] = integer;
        }
        return res;
    }
}
