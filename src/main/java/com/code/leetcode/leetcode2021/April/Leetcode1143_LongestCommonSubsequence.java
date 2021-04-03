package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/3 9:43
 * @Motto Keep thinking, keep coding!
 * leetcode 1143、最长公共子序列
 * 题目：https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class Leetcode1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        Leetcode1143_LongestCommonSubsequence test = new Leetcode1143_LongestCommonSubsequence();
//        System.out.println(test.longestCommonSubsequence("abcde", "aec"));
        System.out.println(test.longestCommonSubsequence("bmvcnwrmxcfcxabkxcvgbozmpspsbenazglyxkpibgzq", "bmpmlstotylonkvmhqjyxmnqzctonqtobahcrcbibgzgx"));
//        System.out.println(test.longestCommonSubsequence("bsbininm","jmjkbkjkv"));
    }

    /**
     * 采用压缩空间的方法
     */
    /*public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        //
        int[] dp = new int[m + 1];
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i] = Math.min(dp[i - 1], dp[i]) + 1;
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i]);
                }
            }
        }
        return dp[m];
    }*/

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 如果相等的话，就看前面的最小值为多少即可
//                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 看前面得到的结果是多少。
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        return dp[m][n];
    }
}
