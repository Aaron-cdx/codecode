package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/16 16:04
 * @Motto Keep thinking, keep coding!
 * leetcode 87、扰乱字符串
 * 题目：https://leetcode-cn.com/problems/scramble-string/
 */
public class Leetcode87_IsScramble {
    public static void main(String[] args) {
        Leetcode87_IsScramble test = new Leetcode87_IsScramble();
        System.out.println(test.isScramble("eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd"));
    }
    /**
     * 递归的方式是超时的
     * 时间复杂度过高
     * 对s1字符串进行存储记忆，实现记忆化搜索操作
     */
    // 使用map进行存储
    /*Map<String, Map<String, Boolean>> memory = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        if (s1.equals(s2)) return true; // 如果相等直接返回

        int n = s1.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            map.put(c2, map.getOrDefault(c2, 0) - 1);
        }
        for (Character key : map.keySet()) {
            if (map.get(key) != 0) return false;// 表示里面的字符都不等，直接返回false
        }
        // 判断是否遍历过了
        if (memory.containsKey(s1) && memory.get(s1).containsKey(s2)) {
            return memory.get(s1).get(s2);
        }

        // 如果字符相等，此时来具体判断具体的子的序列是否相等
        for (int i = 1; i < n; i++) {
            boolean flag =
                    // S1 => T1 / S2 => T2
                    isScramble(s1.substring(0, i), s2.substring(0, i))
                            && isScramble(s1.substring(i), s2.substring(i))
                            // S1 => T2 / S2 => T1
                            || isScramble(s1.substring(0, i), s2.substring(n - i)) // 交换了则匹配后一段
                            && isScramble(s1.substring(i), s2.substring(0, s2.length() - i));// 交换了后一段匹配前一段
            if (flag) {
                // 先保存，用于实现记忆化搜索操作
                // 这里表示如果不存在s1的键则直接构造一个新的值，然后新的值去存入s1,true操作
                memory.computeIfAbsent(s1, z -> new HashMap<>()).put(s2, true);
                return true;
            }
        }
        memory.computeIfAbsent(s1, z -> new HashMap<>()).put(s2, false);
        return false;
    }*/

    /**
     * 使用递归时间复杂度过高，需要采用记忆化搜索的方式来存储部分重复的子运算
     * 动态规划解题
     */
    int[][][] memo;
    String s1, s2;

    public boolean isScramble(String s1, String s2) {
        int length = s1.length();
        this.memo = new int[length][length][length + 1];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, length);
    }

    private boolean dfs(int i1, int i2, int length) {
        if (memo[i1][i2][length] != 0) {
            return memo[i1][i2][length] == 1;
        }
        // 否则进行判断子串是否相等
        if (s1.substring(i1, i1 + length).equals(s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = 1;
            return true;
        }
        // 直接判断是否符合条件
        if (!check(s1.substring(i1, i1 + length), s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = -1;
            return false;
        }
        // 获取分割位置
        for (int i = 1; i < length; i++) {
            // 最原始的情况
            if (dfs(i1, i2, i) && dfs(i1 + i, i2 + i, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
            // 交换的情况 s1前半段对应s2后半段/s1后半段对应s2前半段
            // i1表示前半段，i2+length-i表示后半段,长度为i，i1+i表示i1+length-length+i = i1+i
            if (dfs(i1, i2 + length - i, i) && dfs(i1 + i, i2, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
        }
        memo[i1][i2][length] = -1;
        return false;
    }

    /**
     * 检查词频是否相同
     */
    public boolean check(String s11, String s22) {
        if (s11.length() != s22.length()) return false;
        int n = s11.length();
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[s11.charAt(i) - 'a']++;
            arr[s22.charAt(i) - 'a']--;
        }
        for (int i : arr) {
            if (i != 0) return false;
        }
        return true;
    }
}
