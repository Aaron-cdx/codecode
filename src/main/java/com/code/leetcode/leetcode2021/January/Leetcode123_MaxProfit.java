package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/9 9:22
 * @Motto Keep thinking, keep coding!
 * leetcode 123、买卖股票的最佳时机III
 * 题目：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * 题目的意思限定交易两次
 */
public class Leetcode123_MaxProfit {
    public static void main(String[] args) {
        Leetcode123_MaxProfit test = new Leetcode123_MaxProfit();
        System.out.println(test.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    /**
     * 限定只能完成两笔交易
     * 即只能买卖各两次
     */
    /*public int maxProfit(int[] prices) {
        // 只有两笔交易
        int n = prices.length;
        if (n < 2) return 0;
        int[][] dp = new int[2][2];
        // 这里需要初始化，每一次的持有都是极小值
        for (int i = 0; i < 2; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        for (int price : prices) {
            dp[0][1] = Math.max(dp[0][1],-price);
            dp[0][0] = Math.max(dp[0][0],dp[0][1]+price);
            for (int i = 1; i < 2; i++) {
                // 但前持有=>1.之前持有 2.之前不持有，现在持有
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] - price);
                // 当前不持有=>1.之前不持有 2.之前持有，现在不持有
                dp[i][0] = Math.max(dp[i][0], dp[i][1] + price);
            }
        }
        return dp[1][0];
    }*/

    /**
     * 通过直观的两笔交易
     * 交易主要是在初始化的时候，因为只要是买入的操作，在初始化买入的时候都要初始化为最小值
     * 这样可以保证买入的时候是真正的买入，如果不初始化，默认0，则很多买入操作不会进行！
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int[][] dp = new int[3][2];
        // 对第一次交易和第二次交易持有的初始化
        dp[1][1] = -prices[0];
        dp[2][1] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 第一次持有,之前就持有,或者现在买入
            dp[1][1] = Math.max(dp[1][1], -prices[i]);
            // 第一次不持有
            dp[1][0] = Math.max(dp[1][0], dp[1][1] + prices[i]);
            // 第二次持有
            dp[2][1] = Math.max(dp[2][1], dp[1][0] - prices[i]);
            // 第二次不持有
            dp[2][0] = Math.max(dp[2][0], dp[2][1] + prices[i]);
        }
        return Math.max(dp[1][0], Math.max(dp[2][0], 0));
    }
}
