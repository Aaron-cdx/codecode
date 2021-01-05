package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/5 18:53
 * @Motto Keep thinking, keep coding!
 * leetcode 803、较大分组的位置
 * 题目：https://leetcode-cn.com/problems/positions-of-large-groups/
 * 包含大于或者等于三个的连续字符的分组称为较大分组！
 */
public class Leetcode830_LargeGroupPositions {
    public static void main(String[] args) {
        Leetcode830_LargeGroupPositions test = new Leetcode830_LargeGroupPositions();
        System.out.println(test.largeGroupPositions("abcdddeeeeaabbbcd"));
        System.out.println(test.largeGroupPositions("abc"));
        System.out.println(test.largeGroupPositions("abbxxxxzzy"));
    }

    /**
     * 使用双指针的技术实现
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        if (s == null || s.length() < 3) return res;
        int l = 0;
        int r = 0;
        char[] chars = s.toCharArray();
        while (r < chars.length) {
            while (r < chars.length && chars[l] == chars[r]) {
                r++;
            }
            if (r - l >= 3) {
                res.add(Arrays.asList(l, r - 1));
            }
            l = r;

        }
        return res;
    }
}
