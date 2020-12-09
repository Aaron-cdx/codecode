package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/9 22:11
 * @Motto Keep thinking, keep coding!
 * leetcode 62、不同路径
 * 题目：https://leetcode-cn.com/problems/unique-paths/
 * 题目意思就是从左上角往右下角走，有多少中不同的路径可以走
 */
public class Leetcode62_UniquePaths {
    public static void main(String[] args) {
        Leetcode62_UniquePaths test = new Leetcode62_UniquePaths();
        System.out.println(test.uniquePaths(1, 1));
    }

    /**
     * 标准的动态规划，先执行初始化操作，然后下一次的每一格的数据就是左边和上面的数据
     * 因为只能向右移和向下移动
     */
    public int uniquePaths(int m, int n) {
        if (m == 0) return 0;
        int[][] dp = new int[m][n];
        // 初始化第一行第一列
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
