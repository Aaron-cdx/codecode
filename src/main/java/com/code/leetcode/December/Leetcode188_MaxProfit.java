package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/28 8:47
 * @Motto Keep thinking, keep coding!
 * leetcode 188、买卖股票的最佳时机IV
 * 题目：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * 题目的意思是，获取最大利润，但是限制了交易次数K，问在k次交易之内，能够获取到的最大的利润是多少？
 */
public class Leetcode188_MaxProfit {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) return 0;
        // 如果超过了一半，则演变为可以任意次交易，利润最大即可
        if (k >= prices.length / 2) return greedy(prices);
        // 否则就演变为类似于买卖股票III的情况，不过III只让交易2次
        // 此处交易k次
        int[][] dp = new int[k+1][2];
        for (int i = 0; i < k; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        // dp[i][0]表示第i次不持有，dp[i][1]表示第i次持有
        for (int price : prices) {
            // 初始化持有，保持原始的最小，或者当前买入
            dp[0][1] = Math.max(dp[0][1], -price);
            // 初始化不持有，保持原始不持有，或者当前持有卖出
            dp[0][0] = Math.max(dp[0][0], dp[0][1] + price);
            for (int i = 1; i <= k; i++) {
                // 第i次持有，可能是之前一直持有的，或者之前不持有当前买入
                // 这里注意，为什么dp[i][1]是dp[i][1]的前一个状态，这是因为每次都要过k循环，所以是这样
                // 但是对于卖出则要使用i-1了
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] - price);
                // 第i次不持有，可能是之前一直不持有的，或者之前持有现在卖出
                dp[i][0] = Math.max(dp[i][0], dp[i][1] + price);
            }
        }
        // 返回的是最后的不持有的结果
        return dp[k - 1][0];
    }

    public int greedy(int[] prices) {
        if (prices.length < 2) return 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        // 表示本次持有
        dp[1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 如果之前没有持有，而现在持有，则当前未持有状态的来源为
            // 如果现在持有，则状态可能是之前一直持有，或者之前未持有，现在买了
            dp[1] = Math.max(dp[1], dp[0] - prices[i]);
            // 之前未持有，或者之前持有现在卖了
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
        }
        return dp[0];
    }

    /**
     * 买卖股票的最佳时机III
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
     * 即限定交易次数只能够为两笔，问取得的最大利润可以为多少
     */
    /*public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        int[][][] dp = new int[len][3][2];
        // 初始化，一开始持有
        dp[0][1][1] = -prices[0];
        // 第二次持有
        dp[0][2][1] = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            // 保持之前的状态，或者买入
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            // 这里表示不持股，保持之前不持股，或者现在卖出去了
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            // 表示第二次持股,保持第二次之前的持股，或者第一次不持股之后的买入
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
            // 表示第二次不持股，保持第二次的不持股，或者第二次持股后的卖出
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
        }
        // 最后肯定不持股的利润是最大的
        return Math.max(Math.max(dp[len - 1][2][0], dp[len - 1][1][0]), 0);
    }*/

    /**
     * 优化，由于今天只参考了昨天的状态，与i是无关的，所以可以优化第一维度的空间
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int[][] dp = new int[3][2];
        dp[1][1] = -prices[0];
        dp[2][1] = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            // 第一次持有，可能被是最开始就持有，或者现在买入
            dp[1][1] = Math.max(dp[1][1], -prices[i]);
            // 第一次不持有，可能前一状态不持有，或者现有状态卖出
            dp[1][0] = Math.max(dp[1][0], dp[1][1] + prices[i]);
            // 第二次持有，可能前一状态持有，或者第一次不持有之后的买入
            dp[2][1] = Math.max(dp[2][1], dp[1][0] - prices[i]);
            // 第二次不持有，可能前一状态不持有，或者持有状态卖出
            dp[2][0] = Math.max(dp[2][0], dp[2][1] + prices[i]);
        }
        return Math.max(Math.max(dp[1][0], dp[2][0]), 0);
    }
}
