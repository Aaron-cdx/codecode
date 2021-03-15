package com.code.leetcode.leetcode2021.March;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/3/15 9:29
 * @Motto Keep thinking, keep coding!
 * leetcode 54、螺旋矩阵
 * 题目：https://leetcode-cn.com/problems/spiral-matrix/
 */
public class Leetcode54_SpiralOrder {
    public static void main(String[] args) {
        Leetcode54_SpiralOrder test = new Leetcode54_SpiralOrder();
//        System.out.println(test.spiralOrder(new int[][]{{1}}));
        System.out.println(test.spiralOrder(new int[][]{{1, 2, 3}}));
        System.out.println(test.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(test.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        System.out.println(test.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}));
        System.out.println(test.spiralOrder(new int[][]{{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}}));
    }

    // 按照螺旋的顺序返回所有的元素
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;
        List<Integer> list = new ArrayList<>();
        dfs(list, 0, 0, rows, cols, matrix);
        return list;
    }

    public void dfs(List<Integer> list, int row, int col, int rows, int cols, int[][] matrix) {
        // 在dfs里面去新增元素，如何判断是最后一个中心的元素呢
        if (row > rows || col > cols) return;
        if (col == cols) { // 表示只有这一列
            for (int i = row; i <= rows; i++) {
                list.add(matrix[i][col]);
            }
        } else if (row == rows) { // 表示只有这一行
            for (int i = col; i <= cols; i++) {
                list.add(matrix[row][i]);
            }
        } else {
            int r = row;
            int c = col;
            // 否则开始遍历,上下左右四个方向，结束则row-1,col-1
            // 从左到右
            while (col < cols) {
                list.add(matrix[r][col++]);
            }
            // 从上到下
            while (row < rows) {
                list.add(matrix[row++][cols]);
            }
            // 从左到右
            while (col > c) {
                list.add(matrix[rows][col--]);
            }
            // 从下到上
            while (row > r) {
                list.add(matrix[row--][c]);
            }
            // 下一轮
            dfs(list, row + 1, col + 1, rows - 1, cols - 1, matrix);
        }
    }
}
