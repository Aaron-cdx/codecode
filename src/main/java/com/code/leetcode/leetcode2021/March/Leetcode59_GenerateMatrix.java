package com.code.leetcode.leetcode2021.March;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/3/16 9:09
 * @Motto Keep thinking, keep coding!
 * leetcode 59、螺旋矩阵II
 * 题目：https://leetcode-cn.com/problems/spiral-matrix-ii/
 */
public class Leetcode59_GenerateMatrix {
    public static void main(String[] args) {
        Leetcode59_GenerateMatrix test = new Leetcode59_GenerateMatrix();
        System.out.println(Arrays.deepToString(test.generateMatrix(5)));
    }
    public int[][] generateMatrix(int n) {
        // 1. 构造具体的结果矩阵
        int[][] matrix = new int[n][n];
        // 生产的总数
        dfs(matrix, 1, 0, 0, n - 1, n - 1);
        return matrix;
    }

    public void dfs(int[][] matrix, int num, int row, int col, int rows, int cols) {
        // 超出限制直接返回
        if (row > rows || col > cols) return;
        // 如果是一行的情况,这里不存在一行的情况,因为是构成n*n的矩阵
        if (col == cols) {
            // 多行一列
            for (int i = row; i <= rows; i++) {
                matrix[i][col] = num++;
            }
        } else if (row == rows) {
            // 多列一行
            for (int i = col; i <= cols; i++) {
                matrix[row][i] = num++;
            }
        } else {
            // 否则就要进行上下左右的循环遍历了
            int r = row;
            int c = col;
            // 从左到右
            while (col < cols) {
                matrix[r][col++] = num++;
            }
            // 从上到下
            while (row < rows) {
                matrix[row++][cols] = num++;
            }
            // 从左到右
            while (col > c) {
                matrix[rows][col--] = num++;
            }
            // 从下到上
            while (row > r) {
                matrix[row--][c] = num++;
            }
            dfs(matrix, num, row + 1, col + 1, rows - 1, cols - 1);
        }
    }
}
