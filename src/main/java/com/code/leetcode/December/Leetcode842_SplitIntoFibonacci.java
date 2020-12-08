package com.code.leetcode.December;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/12/8 21:45
 * @Motto Keep thinking, keep coding!
 * leetcode 842、将数组拆分成斐波那契序列
 * 题目：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/
 * 主要的意思就是对一串字符串，将其拆分为满足斐波那契数列的数组
 */
public class Leetcode842_SplitIntoFibonacci {
    public static void main(String[] args) {
        Leetcode842_SplitIntoFibonacci test = new Leetcode842_SplitIntoFibonacci();
        System.out.println(test.splitIntoFibonacci("123456579"));
    }
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;
        bfs(res, S, 0);
        return res;
    }

    public boolean bfs(List<Integer> res, String s, int index) {
        if (res.size() >= 3 && index == s.length()) {
            return true;
        }
        // 否则的话开始遍历计算每一次的值
        long number = 0;
        for (int i = index; i < s.length(); i++) {
            // 表明0开头，直接跳过
            if (i > index && s.charAt(index) == '0') return false;
            number = 10 * number + (s.charAt(i) - '0');
            // 太大直接放弃
            if(number > Integer.MAX_VALUE) return false;
            // 这里要避免多求了值
            if(res.size() >= 2 && (number > res.get(res.size()-1)+res.get(res.size()-2))) return false;
            if(res.size() <= 1 || (number == res.get(res.size()-1)+res.get(res.size()-2))){
                res.add((int) number);
                if (bfs(res,s,i+1)){
                    return true;
                }
                System.out.println("res="+res);
                res.remove(res.size()-1);
            }
        }
        return false;
    }
}
