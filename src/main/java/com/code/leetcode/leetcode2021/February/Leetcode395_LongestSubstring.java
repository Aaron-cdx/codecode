package com.code.leetcode.leetcode2021.February;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2021/2/27 9:37
 * @Motto Keep thinking, keep coding!
 * leetcode 395、至少有K个重复字符的最长子串
 * 题目：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class Leetcode395_LongestSubstring {
    public static void main(String[] args) {
        Leetcode395_LongestSubstring test = new Leetcode395_LongestSubstring();
        System.out.println(test.longestSubstring("acbbdda", 2));
    }

    /**
     * 利用递归结合HashMap实现
     */
    public int longestSubstring(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        // 然后这里开始判断当前出现的数有哪些不满足
        for (Character c : map.keySet()) {
            // 表示不满足，此时需要向左去查找
            if (map.get(c) > 0 && map.get(c) < k) {
                int ret = 0;
                // 标准的对前后进行查找，因为这个字符是不满足的
                for (String str : s.split(String.valueOf(c))) {
                    ret = Math.max(ret, longestSubstring(str, k));
                }
                return ret;
            }
        }
        // 表示都满足，此时表示没有不满足的
        return s.length();
    }

    /**
     * 利用递归的方法实现
     */
    /*public int longestSubstring(String s, int k) {
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }

    private int dfs(String s, int l, int r, int k) {
        int ret = 0;
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        char split = 0;
        // 遍历是为了获取所有的一个结果
        for (int i = 0; i < 26; i++) {
            // 表示这一段不符合
            if (cnt[i] > 0 && cnt[i] < k) {
                // 记录当前的字符
                split = (char) (i + 'a');
            }
        }
        // 表示所有的都是满足的，此时将区间长度返回即可
        if (split == 0) {
            return r - l + 1;
        }
        // 否则就是有不满足的
        // 此时要进行边界收缩寻找不满足条件的左边界了
        int i = l;
        while (i <= r) {
            // 如果不满足的字符出现在第一个位置，这是为了避免出现重复，要直接杀到其右边界，这样才可以获取到左边界
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            // 表示全都不满足，直接break
            if (i > r) {
                break;
            }
            int start = i;// 记录左边界开始的位置
            // 否则的话，表示这个字符在非第一个字符
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            // 此时找到了split,对齐左边界进行遍历即可
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(length, ret);
        }
        return ret;
    }*/

    /**
     * 这里递归的总的含义就如下所示
     * 主要是通过一个计数器，计算所有字符出出现的次数
     * 然后26个字符分别遍历。找到不符合要求的
     * 然后找到不符合要求的左边界，对左边进行遍历，然后对右边界进行遍历
     * 最后通过递归找到符合条件的最大值即可。
     */
    /*private int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {// 这是为了获取26个字母分别的次数
            // 表示这一段是有字母，但是不符合k的条件
            // 所以后面的查找要从split这位置往后找
            // 或者从split这个位置往前找
            // 这是一个重要的条件
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        // 符合条件即满足k，这里就会进行计算
        if (split == 0) {
            return r - l + 1;
        }
        // 不符合条件的往下走，在下面进行计算
        int i = l;
        int ret = 0;
        while (i <= r) {
            // 查找split的位置，定位到这个i
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }*/
    /**
     * 使用Map来获取具体存储的次数
     * 还是用数组吧
     * 找到给定字符串（由小写字符组成）中的最长子串 T
     * 要求 T 中的每一字符出现次数都不少于 k
     * <p>
     * 利用滑动窗口解题
     */
   /* public int longestSubstring(String s, int k) {
        // 表示存储当前的单词
        int ret = 0;
        int n = s.length();
        for (int t = 1; t <= 26; t++) {
            int l = 0, r = 0;
            int[] cnt = new int[26];
            int total = 0;
            int less = 0;
            while (r < n) {
                int x = s.charAt(r) - 'a';
                cnt[x]++;
                if (cnt[x] == 1) {
                    total++;
                    less++;
                }
                if (cnt[x] == k) {
                    less--;
                }
                // total > t的时候，表示出现了多个此时需要进行收缩
                while (total > t) {
                    // 左边界的收缩
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        total--;
                        less--;
                    }
                    l++;
                }
                // 表示在里面都是符合条件的，此时计算长度
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }*/
}
