package com.code.leetcode.December;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2020/12/26 9:16
 * @Motto Keep thinking, keep coding!
 * leetocde 85、最大矩形
 * 题目：https://leetcode-cn.com/problems/maximal-rectangle/
 * 找出仅包含1的最大的矩形，并返回其面积
 */
public class Leetcode85_MaximalRectangle {
    public static void main(String[] args) {
        Leetcode85_MaximalRectangle test = new Leetcode85_MaximalRectangle();
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(test.maximalRectangle(matrix));
    }

    /**
     * 使用单调栈的方法解题
     */
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        int[][] left = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 为'0'的不用统计
                if (matrix[i][j] == '1') {
                    left[i][j] = 1 + (j >= 1 ? left[i][j - 1] : 0);
                }
            }
        }
        int ret = 0;
        for (int j = 0; j < col; j++) {
            int[] up = new int[row];
            int[] down = new int[row];
            Deque<Integer> stack = new LinkedList<>();// 定义单调栈目的就是为了求高度
            for (int i = 0; i < row; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = row - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? row : stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < row; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * 利用矩形构造
     * 时间复杂度O(nm^2)
     * 空间复杂度O(nm)
     */
    /*public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        int[][] left = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 为'0'的不用统计
                if (matrix[i][j] == '1') {
                    left[i][j] = 1 + (j >= 1 ? left[i][j - 1] : 0);
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') continue;
                // 否则的话需要开始计算了
                int width = left[i][j];
                int area = width;// 这是初始的面积，可能在后续的计算高度过程中，向上兼容的面积并没有从左边累加过来的大！
                // 此时需要向上遍历，获取当前柱状图可以到多高
                for (int k = i - 1; k >= 0; k--) {
                    // 宽度因为向上兼容，应该取最小的
                    width = Math.min(width, left[k][j]);
                    // 当前面积计算
                    area = Math.max(area, width * (i - k + 1));
                }
                ret = Math.max(area,ret);
            }
        }
        return ret;
    }*/

    /**
     * 一个矩形的构造，可以来自于其上面，左边，此时如果上面和左边同时存在且不为1，此时可以断定当前
     * FAILED自己的思路错了
     */
/*    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        // 初始化第一行和第一列
        int[][] dp = new int[rows][cols];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        int maxCount = dp[0][0];
        for (int i = 1; i < rows; i++) {
            if (dp[i - 1][0] == 0) {
                dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            } else {
                dp[i][0] = matrix[i][0] == '1' ? dp[i - 1][0] + 1 : 0;
            }
            maxCount = Math.max(maxCount, dp[i][0]);
        }
        for (int i = 1; i < cols; i++) {
            if (dp[0][i - 1] == 0) {
                dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            } else {
                dp[0][i] = matrix[0][i] == '1' ? dp[0][i - 1] + 1 : 0;
            }
            maxCount = Math.max(maxCount, dp[0][i]);
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '0') continue;
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];
                int diag = dp[i - 1][j - 1];
                // 如果对角线上为0,则上面的和左边的必定只能取最大的
                if (diag == 0) {
                    dp[i][j] = Math.max(left, up) + 1;
                } else {
                    // 如果对角线不等于0,则左边和上面的值可能来自对角线，也可能不来自对角线
                    dp[i][j] = left + up - 1;
                }
                maxCount = Math.max(dp[i][j], maxCount);
            }
        }
        return maxCount;
    }*/
}
