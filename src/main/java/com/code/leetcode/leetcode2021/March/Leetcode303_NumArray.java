package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/1 10:21
 * @Motto Keep thinking, keep coding!
 * leetcode 303、区域和检索-数组不可变
 * 题目：https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
public class Leetcode303_NumArray {

}

class NumArray {
    //    private final int[] nums;
    private final int[] storage;

    /**
     * 给定数组，求出数组的索引范围区间元素和
     * 前缀和：
     * 前缀和数组 sum 的每一位记录的是当前位置距离起点位置，这连续一段的和区间和。
     */
    public NumArray(int[] nums) {
//        this.nums = nums;
        this.storage = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            storage[i + 1] = storage[i] + nums[i];
        }
//        this.storage = new int[nums.length];
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//            storage[i] = sum;
//        }
    }

    /**
     * 采用动态规划的方法来做
     */
    public int sumRange(int i, int j) {
//        return storage[j] + nums[i] - storage[i];
        return storage[j + 1] - storage[i];
    }

    /**
     * 首先暴力法
     * 时间复杂度过高，每一次的时间复杂度为O(mn)
     */
    /*public int sumRange(int i, int j) {
        int sum = 0;
        for (int x = i; x <= j; x++) {
            sum += nums[x];
        }
        return sum;
    }*/
}
