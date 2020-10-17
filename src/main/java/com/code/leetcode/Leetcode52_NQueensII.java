package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/17 9:48
 * @Motto Keep thinking, keep coding!
 * leetcode 52、N皇后II
 * 返回N皇后的解决方案数
 * 题目：https://leetcode-cn.com/problems/n-queens-ii/
 * 思路：主要还是围绕放置的位置不能够再同一行，不能够在同一列，不能够再同一个对角线(对角线有正反对角线)
 * 主要是将对角线量化，变成一维矩阵，然后通过当前的行和列获取当前一维矩阵是否被占用
 */
public class Leetcode52_NQueensII {
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private int res = 0;

    public int totalNQueens(int n) {
        col = new boolean[n];
        // 看对角线有几条线段
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        putQueens(n, 0);
        return res;
    }

    private void putQueens(int n, int row) {
        // 表示执行到最后一行了，可以直接终止了
        if (row == n) {
            res += 1;
            return;
        }
        // 对列进行遍历
        for (int i = 0; i < n; i++) {
            // row表示行
            if (!col[i] && !dia1[row + i] && !dia2[row - i + n - 1]) {
                col[i] = true;
                dia1[row + i] = true;
                dia2[row - i + n - 1] = true;
                putQueens(n, row + 1);
                col[i] = false;
                dia1[row + i] = false;
                dia2[row - i + n - 1] = false;
            }
        }
    }
}
