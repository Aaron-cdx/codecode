package com.code.leetcode.leetcode2021.February;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/2/17 14:37
 * @Motto Keep thinking, keep coding!
 * leetcode 566、重塑矩阵
 * 题目：https://leetcode-cn.com/problems/reshape-the-matrix/
 */
public class Leetcode566_MatrixReshape {
    public static void main(String[] args) {
        Leetcode566_MatrixReshape test = new Leetcode566_MatrixReshape();
        int[][] ints = test.matrixReshape(new int[][]{{1, 2, 3, 4}, {3, 4, 5, 6}}, 2, 4);
        System.out.println(Arrays.deepToString(ints));
    }

    /**
     * 第二种方法，直接赋值
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        // 不可行，元素过少不能够填充满
        if (row * col != r * c) {
            return nums;
        }
        // 开始直接赋值
        int[][] res = new int[r][c];
        // 行转化为一维则为 i*row+col => 转换回来的话 =>
        for (int i = 0; i < row * col; i++) {
            // i/c表示第几行，i%c表示第几列
            res[i / c][i % c] = nums[i / col][i % col];
        }
        return res;
    }

    /**
     * 第一种方法：将矩阵转化为一维矩阵，然后重塑具体的r*c矩阵
     */
    /*public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        // 不可行，元素过少不能够填充满
        if (row * col < r * c) {
            return nums;
        }
        // 否则表示合理或者是元素多，直接填充即可
        int[][] res = new int[r][c];
        // 转化为一维数组看看
        int[] tmp = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tmp[i * col + j] = nums[i][j];
            }
        }
        int k = 0;
        // 开始赋值进入
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = tmp[k++];
            }
        }
        return res;
    }*/
}
