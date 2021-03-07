package com.code.leetcode.leetcode2021.March;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/3/7 9:50
 * @Motto Keep thinking, keep coding!
 * leetcode 131、分割回文串
 * 题目：https://leetcode-cn.com/problems/palindrome-partitioning/
 */
public class Leetcode131_Partition {
    public static void main(String[] args) {
        Leetcode131_Partition test = new Leetcode131_Partition();
        System.out.println(test.partition("efe"));
//        System.out.println(test.isTrue("aab"));
//        System.out.println(test.isTrue("a"));
//        System.out.println(test.isTrue("b"));
    }

    /**
     * 对字符串，分割为子串，把每个子串都变成回文串
     */
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        dfs(new ArrayList<String>(), s, 0);
        return res;
    }

    public void dfs(List<String> list, String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<>(list));// 把结果添加进去即可
            return;
        }
        // 否则的话，需要展开进行计算，刚开始一个一个，然后一次两个，然后一次三个依次下去即可
        for (int i = index; i < s.length(); i++) {
            // 这里需要思考怎么去截取子串,index和i+1，i是跟index绑定的，是动态增大的
            String str = s.substring(index, i + 1);
            if (isTrue(str)) { // 是回文串才往下，否则不往下，直接循环
                // 否则表示是回文串
                list.add(str);
                // i+1表示往下一个目标行进
                dfs(list, s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isTrue(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l < r) {
            if (chars[l++] != chars[r--]) return false;
        }
        return true;
    }
}
