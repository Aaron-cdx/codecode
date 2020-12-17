package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/17 9:28
 * @Motto Keep thinking, keep coding!
 * leetcode 714、买卖股票最佳时机含手续费
 * 题目：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 意思就是可以任意买卖但是每一笔卖出都需要交手续费fee
 * 动态规划的题目一定要先定义好转移方程，否则根本没法做出题目！！！
 */
public class Leetcode714_MaxProfit {
    public static void main(String[] args) {
        Leetcode714_MaxProfit test = new Leetcode714_MaxProfit();
        System.out.println(test.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
    /**
     * 动态规划三个步骤：
     * 1. 定义状态转移方程
     * 2. 给定转移方程初始值
     * 3. 写代码递推实现转移方程
     */
    /*public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        // 不持有
        dp[0][0] = 0;
        // 持有
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 表示今天不持有，即昨天不持有或者昨天持有今天卖出
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            // 表示今天持有，即昨天也持有或者昨天未持有今天买入
            dp[i][1] = Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
        }
        return dp[prices.length-1][0];
    }*/

    /**
     * 因为上面的数组只有i-1的数据会进行转移，所以只需要关注i-1时刻的数据即可
     */
    public int maxProfit(int[] prices, int fee) {
        int[] dp = new int[2];
        dp[0] = 0;// 未持有
        dp[1] = -prices[0];// 持有
        // 用两个变量记录即可
        for (int i = 1; i < prices.length; i++) {
            int temp = dp[0];
            dp[0] = Math.max(temp, dp[1] + prices[i] - fee);
            dp[1] = Math.max(temp - prices[i], dp[1]);
        }
        return dp[0];
    }

    /**
     * 不含有手续费的股票交易
     */
    public int maxProfit(int[] prices) {
        int[] dp = new int[2];
        dp[1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int temp = dp[0];
            dp[0] = Math.max(temp, dp[1] + prices[i]);
            dp[1] = Math.max(temp - prices[i], dp[1]);
        }
        return dp[0];
    }
}
