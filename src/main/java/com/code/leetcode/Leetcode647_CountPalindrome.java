package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/12 16:35
 * leecode 647、回文子串
 * <p>
 * "abc" => a b c => 3
 * "aaa" => a a a aa aa aaa => 6
 * 通过给定的字符串，获取有多少个回文子串
 */
public class Leetcode647_CountPalindrome {
    public static void main(String[] args) {
        Leetcode647_CountPalindrome test = new Leetcode647_CountPalindrome();
        System.out.println(test.countSubstrings_II("abc"));
        System.out.println(test.countSubstrings_II("aaa"));
//        System.out.println(test.isPalindrome("abc"));
//        System.out.println(test.isPalindrome("aaa"));
    }

    /**
     * 第一种方法使用暴力法，就是截取所有的然后获取其中的回文子串的值
     */
    private int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
//                if (isPalindrome(s.substring(j, j + i))) {
//                    count += 1;
//                }
            }
        }
        return count;
    }

    /**
     * 使用动态规划解决当前的问题
     */
    /*
        整体的思路：
        与之前回文子串判断一样，只要是回文串就统计即可

     */
    private int countSubstrings_II(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = 0;
        char[] arr = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (arr[i] == arr[j] && (j - i < 3 || dp[i + 1][j - 1])) {
                    count++;
                    dp[i][j] = true;
                }
            }
        }
        return count;
    }

    /**
     * 利用中心扩散的方法来做
     * 遇到就作为一个回文串加起来
     */
    private int cnt = 0;

    public int countSubstrings_III(String s) {
        if (s == null || s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) {
            isPalindrome(s, i, i);
            isPalindrome(s, i, i + 1);
        }
        return cnt;
    }

    private void isPalindrome(String s, int start, int end) {
        int l = start;
        int r = end;
        while (l >= 0 && r < s.length() && s.charAt(l--) == s.charAt(r++)) {
            cnt++;
        }
    }
}
