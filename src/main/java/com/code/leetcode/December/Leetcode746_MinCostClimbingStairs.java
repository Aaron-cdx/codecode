package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/21 9:23
 * @Motto Keep thinking, keep coding!
 * leetcode 746、使用最小花费爬楼梯
 * 题目：https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 * 题目的意思是爬楼梯，你可以选择爬一个台阶或者两个台阶
 * 但是你只能从0或者1开始，每次爬到的位置的索引值cost[i]代表你爬到这个位置的开销
 * 务必用最小的开销爬到楼顶，问最小的开销是多少？
 * [10,15,20] => 从15开始，爬2个到顶，开销15
 * [1,100,1,1,1,100,1,1,100,1] => 从1开始，爬两个，再爬两个，再爬两个，在爬一个，再爬两个
 * 最小开销6
 */
public class Leetcode746_MinCostClimbingStairs {
    public static void main(String[] args) {
        Leetcode746_MinCostClimbingStairs test = new Leetcode746_MinCostClimbingStairs();
        System.out.println(test.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(test.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    /**
     * 动态规划解题
     * 时间O(n)
     * 空间O(n)
     */
    /*public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (i < cost.length ? cost[i] : 0) + Math.min(dp[i - 1], dp[i - 2]);
        }
        return dp[dp.length - 1];
    }*/

    /**
     * 好理解的方案
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            // 当前状态只能够来自前面1步状态+消耗，或者两步状态+消耗
            dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
        }
        return dp[dp.length - 1];
    }

    /**
     * 优化空间复杂度O(1)
     */
    /*public int minCostClimbingStairs(int[] cost) {
        int prev = 0, curr = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }*/

}
