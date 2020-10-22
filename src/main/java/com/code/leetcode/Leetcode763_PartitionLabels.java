package com.code.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/10/22 9:07
 * @Motto Keep thinking, keep coding!
 * leetcode 763、划分数字区间
 * 题目：https://leetcode-cn.com/problems/partition-labels/
 */
public class Leetcode763_PartitionLabels {
    public static void main(String[] args) {
        Leetcode763_PartitionLabels test = new Leetcode763_PartitionLabels();
        System.out.println(test.partitionLabels("ababcbacadefegdehijhklij"));
    }

    /**
     * 总体的思路先通过遍历一遍获取每个元素的最后的位置，然后通过当前遍历找到最大的结束当前片段的
     * 字母的下标，则在同一段中的字母结束肯定是在一起结束的
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;
        int[] alpha = new int[26];
        char[] chars = S.toCharArray();
        // 这个才是重点，重点在这里！
        for (int i = 0; i < chars.length; i++) {
            // 这里需要存储当前字母的下标
            alpha[chars[i] - 'a'] = i;
        }
        int start = 0, end = 0;
        for (int i = 0; i < chars.length; i++) {
            // 这个end需要维持一个区间的最大
            int endc = alpha[chars[i] - 'a'];
            end = Math.max(endc, end);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}
