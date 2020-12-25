package com.code.leetcode.December;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/12/25 9:06
 * @Motto Keep thinking, keep coding!
 * leetcode 455、分发饼干
 * 题目：https://leetcode-cn.com/problems/assign-cookies/
 * 题目意思根据小朋友的胃口判断最多可以满足多少小朋友
 */
public class Leetcode455_FindContentChildren {
    public static void main(String[] args) {
        Leetcode455_FindContentChildren test = new Leetcode455_FindContentChildren();
        System.out.println(test.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(test.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
        System.out.println(test.findContentChildren(new int[]{10, 9, 8, 7}, new int[]{5, 6, 7, 8}));
    }

    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        // 如果最小的都满足不了,直接返回0即可
        if (g[0] > s[s.length - 1]) {
            return 0;
        }
        int cnt = 0;
        int i = 0;
        int j = 0;
        while (i < s.length && j < g.length) {
            if (s[i] >= g[j]) {
                i++;
                j++;
                cnt++;
            } else {
                i++;
            }
        }
        return cnt;
    }
}
