package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/1 6:09
 * @Motto Keep thinking, keep coding!
 * leetcode 19、秋叶收藏集
 */
public class Leetcode19_LCPCollectLeaves {
    public int minimumOperations(String leaves) {
        if (leaves == null || leaves.length() == 0) return 0;
        // 将所有的树叶排列成红黄红，问至少需要多少次数
        // 这里需要利用动态规划解决问题
        int n = leaves.length();
        char[] chars = leaves.toCharArray();
        // 3个状态，即红黄红的操作
        int[][] dp = new int[n][3];
        dp[0][0] = chars[0] == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int isRed = chars[i] == 'r' ? 1 : 0;
            int isYellow = chars[i] == 'y' ? 1 : 0;
            // 即此位为红色的操作次数
            dp[i][0] = dp[i - 1][0] + isYellow;
            // 这里的话就要判断前面是红色，这里是红色步数更小，还是前面是黄色，这里从黄色编程红色步数更小
            dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + isRed;
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }
        return dp[n-1][2];
    }
}
