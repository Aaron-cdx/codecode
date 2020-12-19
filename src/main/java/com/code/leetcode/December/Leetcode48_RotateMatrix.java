package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/19 9:36
 * @Motto Keep thinking, keep coding!
 * leetcode 48、旋转图像
 * 题目：https://leetcode-cn.com/problems/rotate-image/
 * 题目意思：nxn的规范矩阵，将当前二维矩阵旋转90°，原地修改当前数组，不使用额外的空间。
 */
public class Leetcode48_RotateMatrix {
    public static void main(String[] args) {
        Leetcode48_RotateMatrix test = new Leetcode48_RotateMatrix();
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        test.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用额外空间
     */
    /*public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        // 这里使用行列变换
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }*/

    /**
     * 因为是二维矩阵，必定存在四条边，则同时交换四条边即可
     * 太复杂了。。。公式推导 == 不做
     *
     * 采用先水平翻转再对角线翻转
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int row = 0; row < n/2; row++) {
            for (int col = 0; col < n; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[n-row-1][col];
                matrix[n-row-1][col] = temp;
            }
        }
        // 对角线翻转
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
    }
}
