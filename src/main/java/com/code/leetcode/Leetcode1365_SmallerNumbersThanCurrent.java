package com.code.leetcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/10/26 9:05
 * @Motto Keep thinking, keep coding!
 * leetcode 1365、有多少小于当前数字的数字
 * 题目：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class Leetcode1365_SmallerNumbersThanCurrent {
    public static void main(String[] args) {
        Leetcode1365_SmallerNumbersThanCurrent test = new Leetcode1365_SmallerNumbersThanCurrent();
        int[] nums = new int[]{8, 1, 2, 2, 3};
        int[] ints = test.smallerNumbersThanCurrent(nums);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    /**
     * 提示里面说可以使用
     * 参照别人的思路，数组最大为100个元素，用数组存放的话，这样的话，依次递加下去，可以得出结果
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int[] arr = new int[101];
        for (int num : nums) {
            arr[num] += 1;
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            // 这里要确保nums[i] != 0 否则会nums[i]-1=-1造成下标越界
            nums[i] = nums[i] != 0 ? arr[nums[i] - 1] : 0;
        }
        return nums;
    }

    /**
     * 就是需要找出每个位置上的数整个数组中有多少个数比自己小
     * 先用暴力法解决看看
     * 暴力解法：时间复杂度O(n^2),空间复杂度O(n)
     */
    public int[] smallerNumbersThanCurrentII(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int smaller = 0;
            for (int num : nums) {
                if (nums[i] > num) {
                    smaller++;
                }
            }
            dp[i] = smaller;
        }
        return dp;
    }
}
