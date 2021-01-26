package com.code.leetcode.leetcode2021.January;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2021/1/26 10:32
 * @Motto Keep thinking, keep coding!
 * leetcode 1128、等价多米诺骨牌对的数量
 * 题目：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/
 */
public class Leetcode1128_NumEquivDominoPairs {
    public static void main(String[] args) {
        Leetcode1128_NumEquivDominoPairs test = new Leetcode1128_NumEquivDominoPairs();
        System.out.println(test.numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
    }

    /**
     * 时间复杂度过高
     * O(n^2)的时间复杂度
     */
    /*public int numEquivDominoPairs(int[][] dominoes) {
        Arrays.sort(dominoes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int pair = 0;
        for (int i = 0; i + 1 < dominoes.length; i++) {
            for (int j = i+1; j < dominoes.length; j++) {
                if ((dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1])
                        || (dominoes[i][1] == dominoes[j][0] && dominoes[i][0] == dominoes[j][1])) {
                    pair++;
                }
            }
        }
        return pair;
    }*/

    /**
     * 利用空间换时间
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] dominoe : dominoes) {
            // 与你成为一对的也会和其他人成为一对,所以要加上之前可以成对的数目
            int val = dominoe[0] < dominoe[1] ? dominoe[0] * 10 + dominoe[1] : dominoe[1] * 10 + dominoe[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }
}
