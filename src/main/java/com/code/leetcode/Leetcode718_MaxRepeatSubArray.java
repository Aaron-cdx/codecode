package com.code.leetcode;

import java.lang.reflect.Field;

/**
 * @author caoduanxi
 * @Date 2020/9/8 14:52
 * leetcode 718、最长重复子数组
 */
public class Leetcode718_MaxRepeatSubArray {
    /**
     * 主要是利用动态规划来解决当前的一个题目
     * 主要是利用如果当前相等，就看看之前是否有值，有的话继续累加即可
     * 动态判断一个最大值即可
     */
    public int findLength(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;
        int la = A.length;
        int lb = B.length;
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 0; i < la; i++) {
            for (int j = 0; j < lb; j++) {
                if (A[i] == B[j]) {
                    // 对角线相加即可
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                max = Math.max(dp[i + 1][j + 1], max);
            }
        }
        return max;
    }
}
