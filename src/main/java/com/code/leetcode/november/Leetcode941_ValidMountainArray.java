package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/3 19:53
 * @Motto Keep thinking, keep coding!
 * leetcode 941、有效的山脉数组
 * 题目：https://leetcode-cn.com/problems/valid-mountain-array/
 */
public class Leetcode941_ValidMountainArray {
    /**
     * 上坡下坡法
     */
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length == 0) return false;
        // 最简单的上山下山方法
        int l = 0;
        int r = A.length - 1;
        // 上升成立
        while (l < r && A[l] < A[l + 1]) {
            l += 1;
        }
        // 下降成立
        while (l < r && A[r] < A[r - 1]) {
            r -= 1;
        }
        // 但凡上升下降如果均成立必相等，不等则表示不成立，直接返回false
        return l == r && l != 0 && r != A.length - 1;
    }
}
