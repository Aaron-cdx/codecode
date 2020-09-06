package com.code.newcoder;

import java.util.ArrayList;

/**
 * @author caoduanxi
 * @Date 2020/9/6 15:48
 * 顺时针打印矩阵
 */
public class Solution_19 {
    public static void main(String[] args) {
        Solution_19 solution = new Solution_19();
//        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix = new int[][]{{1, 2, 3}};
//        int[][] matrix = new int[][]{{1},{2},{3}};
        ArrayList<Integer> integers = solution.printMatrix(matrix);
        System.out.println(integers);
    }

    private ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return list;
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;
        execute(matrix, rows, 0, cols, 0);
        return list;
    }

    /**
     * 需要考虑只有一行或者一列的情况
     */
    public void execute(int[][] matrix, int rows, int row, int cols, int col) {
        if (row > rows || col > cols) return;
        // 判断一行的情况
        if (row == rows && col != cols) {
            // 一行
            int c = col;
            while (c <= cols) {
                list.add(matrix[rows][c++]);
            }
            return;
        }
        // 判断一列的情况
        if (row != rows && col == cols) {
            // 一列
            int r = row;
            while (r <= rows) {
                list.add(matrix[r++][cols]);
            }
            return;
        }
        if (row == rows) {
            list.add(matrix[row][col]);
            return;
        }
        int r = row;
        int c = col;
        // 从左往右
        while (col != cols) {
            list.add(matrix[row][col++]);
        }
        // 从上到小
        while (row != rows) {
            list.add(matrix[row++][col]);
        }
        // 从左到右
        while (col != c) {
            list.add(matrix[row][col--]);
        }
        // 从下往上
        while (row != r) {
            list.add(matrix[row--][col]);
        }
        execute(matrix, rows - 1, row + 1, cols - 1, col + 1);
    }
}
