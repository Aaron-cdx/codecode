package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/15 10:07
 * @Motto Keep thinking, keep coding!
 * leetcode 213、打家劫舍II
 * 题目：https://leetcode-cn.com/problems/house-robber-ii/
 */
public class Leetcode213_Rob {
    public static void main(String[] args) {
        Leetcode213_Rob test = new Leetcode213_Rob();
//        System.out.println(test.rob(new int[]{2}));
//        System.out.println(test.rob(new int[]{2, 1, 2, 4}));
        System.out.println(test.rob(new int[]{1,2}));
//        System.out.println(test.rob(new int[]{0, 0}));
//        System.out.println(test.rob(new int[]{1,2,3,1}));
//        System.out.println(test.rob(new int[]{2,3,2}));
//        System.out.println(test.robI(new int[]{1, 2, 3, 1}));
//        System.out.println(test.robI(new int[]{2, 7, 9, 3, 1}));
    }

    /**
     * 这里构成环状的话则是不偷头或者不偷尾部
     * 偷头部则不偷尾部
     * 偷尾部则不偷头部
     * 然后判断谁取值更大，最后输出结果
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        if (len == 1) return dp[0];
        dp[1] = Math.max(nums[0],nums[1]);
        if (len == 2) return dp[1]; // 如果长度仅为2个，直接返回即可
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        int max1 = dp[len - 2]; // 从头偷到倒数第二个
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return Math.max(max1, dp[len - 1]);
    }


    public int robI(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = nums[0];
        if (n <= 1) return dp[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

}
