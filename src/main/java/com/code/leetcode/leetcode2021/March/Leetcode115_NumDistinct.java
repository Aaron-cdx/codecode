package com.code.leetcode.leetcode2021.March;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/3/17 9:26
 * @Motto Keep thinking, keep coding!
 * leetcode 115、不同的子序列
 * 题目：https://leetcode-cn.com/problems/distinct-subsequences/
 * 题解：https://leetcode-cn.com/problems/distinct-subsequences/solution/shou-hua-tu-jie-xiang-jie-liang-chong-ji-4r2y/
 */
public class Leetcode115_NumDistinct {
    public static void main(String[] args) {
        Leetcode115_NumDistinct test = new Leetcode115_NumDistinct();
        System.out.println(test.numDistinct("rabbbit", "rabbit"));
    }

    /**
     * 采用动态规划解决问题
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // 初始化问题 j为0的时候，即t到了结尾，一定是匹配成功了，所以dp[i][0]=1;
        // i为0的时候，即s到了结尾，一定是匹配失败了，所以dp[0][j] = 0
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                // 同时为0的时候需要先判断j然后在判断i
                if (j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    // 如果相等 则沿袭继续的递归子问题，继续判断或者重新开启一个子的判断
                    if (cs[i - 1] == ct[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {// 否则继续缩略问题
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    /*int[][] visited;
    public int numDistinct(String s, String t) {
        int lenS = s.length() - 1;
        int lenT = t.length() - 1;
        visited = new int[s.length()][t.length()];
        for (int[] ints : visited) {
            Arrays.fill(ints,-1);
        }
        return dfs(s.toCharArray(), t.toCharArray(), lenS, lenT);
    }

    *//**
     * 注意涉及到零值问题的记忆化数组需要初始化为全-1，否则也会产生问题。
     *//*
    public int dfs(char[] s, char[] t, int i, int j) {
        if (j < 0) {
            return 1;
        }
        if (i < 0) {
            return 0;
        }
        if (visited[i][j] != -1) {
            return visited[i][j];
        }
        // 表示末尾相同
        if (s[i] == t[j]) {
            // 递归子问题，如果末尾相同的话，一个递归是继续判断下一位，另一个递归是要继续找当前相同的字符串
            visited[i][j] = dfs(s, t, i - 1, j) + dfs(s, t, i - 1, j - 1);
        } else {
            // 否则就是表示不同，则前面匹配的向前走
            visited[i][j] = dfs(s, t, i - 1, j);
        }
        return visited[i][j];
    }
*/
    /**
     * 递归解决，没有记忆化，多出许多重复子问题
     */
    /*public int dfs(char[] s, char[] t, int i, int j) {
        if (j < 0) {
            return 1;
        }
        if (i < 0) {
            return 0;
        }
        // 表示末尾相同
        if (s[i] == t[j]) {
            // 递归子问题，如果末尾相同的话，一个递归是继续判断下一位，另一个递归是要继续找当前相同的字符串
            return dfs(s, t, i - 1, j) + dfs(s, t, i - 1, j - 1);
        } else {
            // 否则就是表示不同，则前面匹配的向前走
            return dfs(s, t, i - 1, j);
        }
    }*/
}
