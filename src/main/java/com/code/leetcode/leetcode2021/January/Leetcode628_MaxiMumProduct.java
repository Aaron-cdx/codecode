package com.code.leetcode.leetcode2021.January;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/1/20 9:46
 * @Motto Keep thinking, keep coding!
 * leetcode 628、三个数的最大乘积
 * 题目：https://leetcode-cn.com/problems/maximum-product-of-three-numbers/
 * 找出数组中三个数的最大乘积，注意会包含负数，负负得正
 */
public class Leetcode628_MaxiMumProduct {
    /**
     * 结果限定不会超过32位整型整数
     * 实际上就是
     * nums[n-1]*nums[n-2]*nums[n-3] 与 nums[n-1]*nums[0]*nums[1]的大小比较
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // 表示全都是正数,此时直接去后面三个数的乘积即可
        if (nums[0] >= 0 || nums[n - 1] < 0) {
            return nums[n - 1] * nums[n - 2] * nums[n - 3];
        }
        // 否则的话表示存在负数,此时存在负数，即判断负数
        // 整数必取一个，负数可能取两个或者一个都不取
        int res = nums[n - 1];
        if (nums[0] * nums[1] > nums[n - 2] * nums[n - 3]) {
            res *= nums[0] * nums[1];
        } else {
            res *= nums[n - 2] * nums[n - 3];
        }
        return res;
    }
}
