package com.code.leetcode.leetcode2021.January;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/1/24 7:59
 * @Motto Keep thinking, keep coding!
 * leetcode 674、最长连续递增序列
 * 题目：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 * 找出数组中连续递增序列最长的
 */
public class Leetcode674_FindLengthOfLCIS {
    public static void main(String[] args) {
        Leetcode674_FindLengthOfLCIS test = new Leetcode674_FindLengthOfLCIS();
        System.out.println(test.findLengthOfLCIS(new int[]{1, 3, 5, 7}));
        System.out.println(test.findLengthOfLCIS(new int[]{1, 3, 5, 4, 2, 3, 4, 5}));
    }

    /**
     * 双指针解决连续子序列的问题
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int l = 0;
        int r = 1;
        int max = Integer.MIN_VALUE;
        while (r < nums.length) {
            if (nums[r] <= nums[r - 1]) {
                max = Math.max(max, r - l);
                l = r;
            }
            r++;
        }
        // 最后还要计算一遍
        max = Math.max(max, r - l);
        return max;
    }

    /**
     * leetcode 673、最长递增子序列的个数
     * 题目：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
     * 找出最长递增子序列的个数
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int n = nums.length;
        // 采用暴力求解
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(count, 1);
        Arrays.fill(dp, 1);
        int maxCount = 0;
        for (int i = 1; i < n; i++) {
            // 以i结尾才可以知道前面总共的大小，这样每一次才有机会获取到最长的那个值
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
                if (dp[i] > maxCount) maxCount = dp[i];
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (maxCount == dp[i]) res += count[i];
        }
        return res;
    }
}
