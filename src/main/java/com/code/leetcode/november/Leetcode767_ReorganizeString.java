package com.code.leetcode.november;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/11/30 9:20
 * @Motto Keep thinking, keep coding!
 * Leetcode 767、重构字符串
 * 题目：https://leetcode-cn.com/problems/reorganize-string/
 * 题目含义：一个字符串可能含有多个字符，如何保证相同的两个字符不在相邻位置上
 * 如果在相邻位置上直接输出""
 * 题目表达：检查是否能重新排布其中的字母，使得两个相邻的字符不同
 * S="aab" => "aba"
 * S="aaab" => "" => 因为这里必须出现边上出现自己相同元素
 */
public class Leetcode767_ReorganizeString {
    public static void main(String[] args) {
        Leetcode767_ReorganizeString test = new Leetcode767_ReorganizeString();
        System.out.println(test.reorganizeString("aab"));
        System.out.println(test.reorganizeString("aaab"));
    }

    /**
     * 题解的主旨思想确实很棒，通过分析最大的数量问题，因为只要存在字符数量大于(n+1)/2
     * 就会导致这个数无论怎么插空都会产生相邻元素相同，所以只需要统计
     * 然后根据奇偶分配来将所有的字符打散，这样就实现了我们所想的插空问题。
     */
    public String reorganizeString(String S) {
        if (S.length() < 2) return S;
        // 只包含小写字母，且长度在[1,500]
        int[] alpha = new int[26];
        int maxCount = 0;
        int n = S.length();
        int maxIndex = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            alpha[c - 'a']++;
            maxCount = Math.max(maxCount, alpha[c - 'a']);
        }
        // 如果大于(n+1)/2则表示太多了，其他元素没办法补足差距，一定会出现重复的现象
        if (maxCount > (n + 1) / 2) return "";
        // 这里需要插入具体的元素
        char[] reorganizeArr = new char[n];
        // 这里只要用总元素个数的一半即可限制住最多的那个元素，否则大家都比较少，直接插入，就好了，几乎没有空缺
        // 这里是解决插空问题的关键
        int evenIndex = 0, oddIndex = 1, halfOfArr = n / 2;
        for (int i = 0; i < alpha.length; i++) {
            char c = (char) (i + 'a');
            // 如果说奇数的分配完了，才会往偶数那边去;或者当前数大于一半则直接往偶数位置填空即可，但是貌似不会出现大于一半的情况发生
            while (alpha[i] > 0 && alpha[i] <= halfOfArr && oddIndex < n) {
                alpha[i]--;
                reorganizeArr[oddIndex] = c;
                oddIndex += 2;
            }
            while (alpha[i] > 0) {
                alpha[i]--;
                reorganizeArr[evenIndex] = c;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArr);
    }
}
