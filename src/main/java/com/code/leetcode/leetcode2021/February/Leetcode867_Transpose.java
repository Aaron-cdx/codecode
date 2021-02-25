package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/25 9:21
 * @Motto Keep thinking, keep coding!
 * leetcode 867、转置矩阵
 * 题目：https://leetcode-cn.com/problems/transpose-matrix/
 * 实现矩阵的转置操作
 */
public class Leetcode867_Transpose {
    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] res = new int[col][row];
        // 2*3转置之后会变成3*2此时行会增加，列会减小，所以res[j][i]
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
