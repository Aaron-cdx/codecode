package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/2 13:15
 * @Motto Keep thinking, keep coding!
 * leetcode 424、替换后的最长重复字符
 * 题目：https://leetcode-cn.com/problems/longest-repeating-character-replacement/
 */
public class Leetcode424_CharacterReplacement {
    public static void main(String[] args) {
        Leetcode424_CharacterReplacement test = new Leetcode424_CharacterReplacement();
        System.out.println(test.characterReplacement("ABBB", 2));
        System.out.println(test.characterReplacement("AABABBA", 1));
    }

    /**
     * 滑动窗口一般时间复杂度为O(n)
     * 所以需要利用时间换空间的思路来解决
     */
    public int characterReplacement(String s, int k) {
        int[] arr = new int[26];
        int n = s.length();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int res = 0;
        while (right < n) {
            arr[s.charAt(right) - 'A']++;
            // 获取哪一个字母出现的次数最多
            maxLen = Math.max(maxLen, arr[s.charAt(right) - 'A']);

            if (right - left + 1 - maxLen > k) {
                // 此时表示K不够用了，缩减左边窗口
                arr[s.charAt(left) - 'A']--;
                left++;
            }
            // 这里统计最大的长度，这里right已经++了
            res = Math.max(maxLen, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 思路就是按照双指针的思路去走，即双指针往后走，直到k为0则停止，判断最长的长度是多少。
     * 这种思路对于正反判断的对象不生效
     * 即 ABBBA k=2无论正反结果都只能是4不能精确的计算出5
     * 且时间复杂度为O(n^2)
     */
    /*public int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            // 第一个指针为i，第二个指针为j
            int j = i + 1;
            int tmp = k;
            while (j < chars.length && (s.charAt(j) == chars[i] || tmp > 0)) {
                if (s.charAt(j) != chars[i]) {
                    tmp--;
                }
                j++;
            }
            // 没有了的话，此时计算长度
            maxLen = Math.max(maxLen, j - i);
        }
        for (int i = chars.length-1; i >= 0; i--) {
            // 第一个指针为i，第二个指针为j
            int j = i - 1;
            int tmp = k;
            while (j >= 0 && (s.charAt(j) == chars[i] || tmp > 0)) {
                if (s.charAt(j) != chars[i]) {
                    tmp--;
                }
                j--;
            }
            // 没有了的话，此时计算长度
            maxLen = Math.max(maxLen,i - j);
        }
        return maxLen;
    }*/
}
