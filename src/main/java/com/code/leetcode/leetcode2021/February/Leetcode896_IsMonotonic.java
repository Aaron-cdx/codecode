package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/28 10:01
 * @Motto Keep thinking, keep coding!
 * leetcode 896、单调数列
 * 题目：https://leetcode-cn.com/problems/monotonic-array/
 * easy难度的题目
 */
public class Leetcode896_IsMonotonic {
    /**
     * 6 5 4 4 => true
     * 1 1 2 3 => false
     */
    public boolean isMonotonic(int[] A) {
        // 首先要判断是单调递增还是单调递减
        // 利用双指针，从后往前走，如果左右边界到最后有为0的，则一定是某一个方向的，否则就是不是一个方向的
        int n = A.length;
        if (n == 1) return true;
        int up = 0;// 上升
        int down = 0;// 下降
        for (int i = 1; i < n; i++) {
            // 如果在上升中也出现了下降，反之亦然
            if (A[i] > A[i - 1]) up = 1;
            else if (A[i] < A[i - 1]) down = 1;
        }
        // 此时表示这个是毫无意义的
        return (up + down) != 2;
    }
}
