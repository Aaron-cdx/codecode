package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/12 12:27
 * @Motto Keep thinking, keep coding!
 * leetcode 264、丑数II
 * 题目：https://leetcode-cn.com/problems/ugly-number-ii/
 */
public class Leetcode264_NthUglyNumber {
    public static void main(String[] args) {
        Leetcode264_NthUglyNumber test = new Leetcode264_NthUglyNumber();
        System.out.println(test.nthUglyNumber(12));
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        if (n == 1) return 1;
        dp[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        // 会出现两个6的问题
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(2 * dp[two], Math.min(3 * dp[three], 5 * dp[five]));
            if (dp[i] >= dp[two] * 2) two++;
            if (dp[i] >= dp[three] * 3) three++;
            if (dp[i] >= dp[five] * 5) five++;
        }
        return dp[n - 1];
    }
}
