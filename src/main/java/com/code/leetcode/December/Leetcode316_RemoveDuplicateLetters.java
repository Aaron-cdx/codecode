package com.code.leetcode.December;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/12/20 9:37
 * @Motto Keep thinking, keep coding!
 * leetcode 316、去除重复字母
 * 题目：https://leetcode-cn.com/problems/remove-duplicate-letters/
 * 题目的意思就是将字符串中重复的字母去除，然后将其组成最小的字典序的字符串输出
 * 注意这个最小的字典序是要看当前字符串的字母排列的顺序来的，不是自己重排序。要求不能打乱其他字符的相对位置
 */
public class Leetcode316_RemoveDuplicateLetters {
    public static void main(String[] args) {
        Leetcode316_RemoveDuplicateLetters test = new Leetcode316_RemoveDuplicateLetters();
        System.out.println(test.removeDuplicateLetters("cbacdcbc"));
        System.out.println(test.removeDuplicateLetters("bcabc"));
    }

    public String removeDuplicateLetters(String s) {
        // 题目要求 1 <= s.length <= 10000
        int[] arr = new int[26];
        // 统计字符个数
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        // 判断当前字符在内部是否存在
        boolean[] exist = new boolean[26];
        char[] res = new char[26];
        int index = 0; // 字符插入的位置
        for (char c : s.toCharArray()) {
            // 如果不存在其中，才需要判断位置是否要更改，如果存在其中直接跳过
            if (!exist[c - 'a']) {
                // 此时判断之前是否存在比自己大的，且数量大于1的,则弹出
                while (index > 0 && (res[index - 1] > c) && (arr[res[index - 1] - 'a'] > 0)) {
                    // 则弹出当前
                    exist[res[--index] - 'a'] = false;
                }
                res[index++] = c;
                exist[c - 'a'] = true;
            }
            // 表示消耗一个
            arr[c - 'a']--;
        }
        return String.valueOf(Arrays.copyOf(res, index));
    }
}
