package com.code.leetcode.leetcode2021.March;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/3/8 9:10
 * @Motto Keep thinking, keep coding!
 * leetcode 132、分割回文串II
 * 题目：https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 * 将字符串分割为回文串最少的次数
 */
public class Leetcode132_MinCut {
    public static void main(String[] args) {
        Leetcode132_MinCut test = new Leetcode132_MinCut();
        System.out.println(test.minCut("aaabaa"));
    }

    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], true);
        }
        // 获取回文子串的值，这一趟得出了其中所有是回文子串的bool值
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 这一步判断非常棒
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (g[0][i]) { // 如果到这里的都是则不需要切割，为0
                f[i] = 0;
            } else {
                // 如果到当前字符不是为true，表示需要分割才满足回文要求
                for (int j = 0; j < i; j++) {
                    if (g[j + 1][i]) {
                        // f[j]+1表示是每一次多分割产生的
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }
        return f[n - 1];
    }


    // 动态规划解决131-partition问题
    List<List<String>> res = new ArrayList<>();
    boolean[][] f;
    List<String> ans = new ArrayList<>();

    public List<List<String>> partition(String s) {
        int n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<>(ans));
            return;
        }
        // 开始遍历
        for (int j = index; j < s.length(); j++) {
            // 如果到j是回文串
            if (f[index][j]) {
                // 进行截取
                ans.add(s.substring(index, j + 1));
                // 进行回溯操作
                dfs(s, j + 1);
                // 剪枝操作
                ans.remove(ans.size() - 1);
            }
        }
    }
}
