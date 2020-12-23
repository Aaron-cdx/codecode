package com.code.leetcode.December;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/12/23 9:20
 * @Motto Keep thinking, keep coding!
 * leetcode 387、字符串中的第一个唯一字符
 * 题目：https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 * 题目的意思是找出第一个唯一出现的字符，如果有多个返回第一个的下标即可
 */
public class Leetcode387_FirstUniqChar {
    public static void main(String[] args) {
        Leetcode387_FirstUniqChar test = new Leetcode387_FirstUniqChar();
        System.out.println(test.firstUniqChar("leetcode"));
        System.out.println(test.firstUniqChar("loveleetcode"));
        System.out.println(test.firstUniqChar("ccaabb"));
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 利用map实现
     */
    /*public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        // 统计了个数之后呢
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int index = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                index = Math.min(index, s.indexOf(entry.getKey()));
            }
        }
        return index == Integer.MAX_VALUE ? -1 : index;
    }*/
    public int firstUniqChar(String s) {
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (num[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}
