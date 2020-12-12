package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/12 18:45
 * @Motto Keep thinking, keep coding!
 * leetcode 376、摆动序列
 * 题目：https://leetcode-cn.com/problems/wiggle-subsequence/
 * 题目意思是找到数组中为摆动序列的最长长度为多少。
 */
public class Leetcode376_WiggleMaxLength {
    public static void main(String[] args) {
        Leetcode376_WiggleMaxLength test = new Leetcode376_WiggleMaxLength();
        System.out.println(test.wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(test.wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(test.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(test.wiggleMaxLength(new int[]{3, 2, 5}));
    }

    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len == 0 || len == 1) return len;
        // 对数组中的数进行归纳
        int up = 1;
        int down = 1;
        // 后面依靠前面，前面依靠后面，上依靠下，下依靠上
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
