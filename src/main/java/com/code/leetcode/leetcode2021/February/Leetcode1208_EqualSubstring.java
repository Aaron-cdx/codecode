package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/5 9:33
 * @Motto Keep thinking, keep coding!
 * leetcode 1208、尽可能使字符串相等
 * 题目：https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
 * 即通过ASCII的转化代表消耗，给定消耗判断可以转换的最大的长度。
 */
public class Leetcode1208_EqualSubstring {
    public static void main(String[] args) {
        Leetcode1208_EqualSubstring test = new Leetcode1208_EqualSubstring();
        System.out.println(test.equalSubstring("abcd", "bcdf", 3));
        System.out.println(test.equalSubstring("abcd", "cdef", 3));
        System.out.println(test.equalSubstring("abcd", "acde", 0));
        System.out.println(test.equalSubstring("ujteygggjwxnfl", "nstsenrzttikoy", 43));
    }

    /**
     * 滑动窗口就是用maxCost作为一个窗口滑动即可，超过了收缩左边界，没有超过扩张右边界即可
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        int len = s.length();
        int maxLen = 0;
        int cost = 0;
        while (right < len) {
            // 计算的开销
            cost += Math.abs(s.charAt(right) - t.charAt(right++));
            // 如果小于，此时可以进行消耗递减同时计算长度
            while (cost > maxCost) {
                // 收缩左边界
                cost -= Math.abs(s.charAt(left) - t.charAt(left++));
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}

