package com.code.leetcode.December;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/12/6 21:57
 * @Motto Keep thinking, keep coding!
 * leetcode 118、杨辉三角
 * 题目：https://leetcode-cn.com/problems/pascals-triangle/
 * 题意：
 * 给定一个非负整数n，生成杨辉三角的前n行
 */
public class Leetcode118_Generate {
    public static void main(String[] args) {
        Leetcode118_Generate test = new Leetcode118_Generate();
        System.out.println(test.generate(10));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows == 0) return list;
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            if (i == 0) {
                temp.add(1);
            } else {
                // 下一行需要基于上一次的结果
                List<Integer> preList = list.get(i - 1);
                temp.add(preList.get(0));
                for (int j = 1; j < preList.size(); j++) {
                    temp.add(preList.get(j - 1) + preList.get(j));
                }
                temp.add(preList.get(preList.size() - 1));
            }
            list.add(temp);
        }
        return list;
    }
}
