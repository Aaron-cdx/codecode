package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/22 9:30
 * @Motto Keep thinking, keep coding!
 * leetcode 242、有效的字母异位词
 * 题目：https://leetcode-cn.com/problems/valid-anagram/
 */
public class Leetcode242_IsAnagram {
    /**
     * 主要思路是通过一个数组存储，然后过两遍，如果真的完全相等，所有位都是0，否则就是不等
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() == 0 && t.length() == 0) return true;
        if (s.length() == 0 || t.length() == 0) return false;
        int[] alpha = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            alpha[t.charAt(i) - 'a'] -= 1;
        }
        for (int i : alpha) {
            if (i != 0) return false;
        }
        return true;
    }
}
