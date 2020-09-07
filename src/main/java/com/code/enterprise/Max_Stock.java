package com.code.enterprise;

/**
 * @author caoduanxi
 * @Date 2020/9/7 17:01
 * 买卖股票的最佳时机
 * 动态规划
 */
public class Max_Stock {
    /**
     * 暴力解法，因为只能买一次卖出一次，所以只要查找当前数字之前的最小数即可，然后依次判断
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                max = Math.max(max, prices[i] - minPrice);
            }
        }
        return max;
    }
}
