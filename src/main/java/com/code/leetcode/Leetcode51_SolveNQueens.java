package com.code.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/10/17 9:09
 * @Motto Keep thinking, keep coding!
 * leetcode 51、N皇后
 * 题目：https://leetcode-cn.com/problems/n-queens/
 */
public class Leetcode51_SolveNQueens {
    /**
     * N皇后问题是表示当前的放置的节点不能够再同一行，也不能够在同一列
     * 肯定是使用回溯法解决，这个肯定是没毛病的
     */
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        LinkedList<Integer> row = new LinkedList<>();
        putQueen(n, 0, row);
        return res;
    }

    private void putQueen(int n, int index, LinkedList<Integer> row) {
        if (index == n) {
            res.add(generateString(n, row));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                row.addLast(i);
                putQueen(n, index + 1, row);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.removeLast();
            }
        }
    }

    private List<String> generateString(int n, LinkedList<Integer> row) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 这是个正方形行和列相同
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[row.get(i)] = 'Q';
            list.add(new String(chars));
        }
        return list;
    }

    public static void main(String[] args) {
        Leetcode51_SolveNQueens queens = new Leetcode51_SolveNQueens();
        System.out.println(queens.solveNQueens(4));
    }

}
