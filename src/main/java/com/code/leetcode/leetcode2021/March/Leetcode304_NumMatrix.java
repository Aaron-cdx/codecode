package com.code.leetcode.leetcode2021.March;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/3/2 9:36
 * @Motto Keep thinking, keep coding!
 * leetcode 304、二维区域和检索-矩阵不可变
 * 题目：https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 */
public class Leetcode304_NumMatrix {
    public static void main(String[] args) {
        Leetcode304_NumMatrix test = new Leetcode304_NumMatrix();
        NumMatrix numMatrix = new NumMatrix(new int[][]{{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
    }

}

class NumMatrix {
    private int[][] storage;

    public NumMatrix(int[][] matrix) {
        // 一维检索
        /*int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            this.storage = new int[m][n+1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    this.storage[i][j+1] = storage[i][j]+matrix[i][j];
                }
            }
        }*/
        // 二维检索
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            this.storage = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    storage[i + 1][j + 1] = storage[i][j + 1] + storage[i + 1][j] - storage[i][j] + matrix[i][j];
                }
            }
        }
    }

    /**
     * 第一种最简单的暴力法
     * -|直接遍历矩阵行列对应的矩形即可
     * -|但时间复杂度过高，选择放弃
     * <p>
     * 第二种方法建议采用昨天作用的前缀和
     * 一维前缀和
     * 意图很明显了，就是按照对应的列来排布的，列是标准准确的，此时因为是一维的，所以要计算多少行的话，此时需要遍历所有行即可
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 一维检索和
        /*int sum = 0;
        for (int i = row1; i <=row2 ; i++) {
            sum += storage[i][col2+1]-storage[i][col1];
        }
        return sum;*/
        // 二维检索和
        return storage[row2 + 1][col2 + 1] - storage[row1][col2 + 1] - storage[row2 + 1][col1] + storage[row1][col1];
    }
}
