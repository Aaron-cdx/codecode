package com.code.leetcode.leetcode2021.February;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/2/10 15:40
 * @Motto Keep thinking, keep coding!
 * leetcode 567、字符串的排列
 * 题目：https://leetcode-cn.com/problems/permutation-in-string/
 * 即判断s1字符串是否在s2字符串(异构也算)里面
 */
public class Leetcode567_CheckInclusion {
    public static void main(String[] args) {
        Leetcode567_CheckInclusion test = new Leetcode567_CheckInclusion();
        System.out.println(test.checkInclusion("adc", "dcda"));
    }

    /**
     * 暴力解法通过时间复杂度过高
     */
    /*public boolean checkInclusion(String s1, String s2) {
        int[] arr = new int[26];
        int n1 = s1.length();
        int n2 = s2.length();
        if (n2 < n1) return false;
        for (int i = 0; i < n1; i++) {
            arr[s1.charAt(i) - 'a']++;
        }
        int[] brr = new int[26];
        // 两轮遍历，边遍历边判断是否是子序列字符
        for (int i = 0; i <= n2 - n1; i++) {
            for (int j = i; j < i + n1; j++) {
                brr[s2.charAt(j) - 'a']++;
            }
            if (compareSame(arr, brr)) return true;
            Arrays.fill(brr, 0);
        }
        return false;
    }*/

    /**
     * 利用滑动窗口实现
     */
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n2 < n1) return false;
        int[] arr = new int[26];
        int[] brr = new int[26];
        // 前n1个判断
        for (int i = 0; i < n1; i++) {
            arr[s1.charAt(i)-'a']++;
            brr[s2.charAt(i)-'a']++;
        }
        if(compareSame(arr,brr)) return true;
        // 此时开启滑动窗口判断
        int l = 0;
        int r = n1;
        while(r < n2){
            // 左边进来的记得还原，右边出去的记得加入
            brr[s2.charAt(l++)-'a']--;
            brr[s2.charAt(r++)-'a']++;
            if(compareSame(arr,brr)) return true;
        }
        return false;
    }

    public boolean compareSame(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
}

