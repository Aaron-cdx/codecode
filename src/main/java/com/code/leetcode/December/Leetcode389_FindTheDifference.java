package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/18 9:18
 * @Motto Keep thinking, keep coding!
 * leetcode 389、找不同
 * 题目：https://leetcode-cn.com/problems/find-the-difference/
 * 题目意思是两个字符串，在随机位置添加一个字母，找出被添加的字母是哪一个
 */
public class Leetcode389_FindTheDifference {
    public static void main(String[] args) {
        Leetcode389_FindTheDifference test = new Leetcode389_FindTheDifference();
        System.out.println(test.findTheDifference("a", "aa"));
    }

    /**
     * 常规思路
     */
    /*public char findTheDifference(String s, String t) {
        int[] chars = new int[26];
        for (int i = 0; i < t.length(); i++) {
            chars[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']--;

        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) return (char) ('a' + i);
        }
        return ' ';
    }*/
    /**
     * 使用位运算
     */
    /*public char findTheDifference(String s, String t) {
        // 使用位运算
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            x ^= (s.charAt(i) - 'a');
        }
        for (int i = 0; i < t.length(); i++) {
            x ^= (t.charAt(i) - 'a');
        }
       return (char) ('a' + x);
    }*/
    /**
     * 使用和相加相减即可
     */
    public char findTheDifference(String s, String t) {
        //和相减
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int sum1 = 0;
        int sum2 = 0;
        for (char c : arr1) {
            sum1 += c - 'a';
        }
        for (char c : arr2) {
            sum2 += c - 'a';
        }
        return (char)(sum2 - sum1 + 'a');
    }

}
