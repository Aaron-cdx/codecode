package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/14 9:17
 * @Motto Keep thinking, keep coding!
 * leetcode 1018、可被5整除的二进制前缀
 * 题目：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5/
 * 即数组的二进制转化为10进制数能否被5整除
 */
public class Leetcode1018_PrefixesDivBy5 {
    public static void main(String[] args) {
        Leetcode1018_PrefixesDivBy5 test = new Leetcode1018_PrefixesDivBy5();
//        System.out.println(test.prefixesDivBy5(new int[]{0, 1, 1, 1, 1, 1}));
//        System.out.println(test.prefixesDivBy5(new int[]{0, 1, 1}));
//        System.out.println(test.prefixesDivBy5(new int[]{1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1}));
        System.out.println(test.prefixesDivBy5(new int[]{
                1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0}));
    }

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        if (A.length == 0) return list;
        int res = 0;
        // 数值太大了，罩不住，后面一个数是前面1个数乘以2+自身，如果满足取余的规则，
        // 则前面是0自己重新计算即可，因为后面如果加上了5的倍数还是有效
        for (int j : A) {
            res = (res * 2 + j) % 5;
            if (res == 0) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;
    }
}
