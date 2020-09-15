package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/15 21:04
 * leetcode 240、搜索二维矩阵II
 * 行元素从到右升序排列，列元素从上到下升序排列
 */
public class Leetcode240_SearchMatrix {
    /**
     * 整体思路就是从右上角开始
     * 如果target偏大则往下，否则往左即可
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;
        // 卡住边界即可
        while (col >= 0 && row < rows && row >= 0) {
            if (target == matrix[row][col]) return true;
            if (target > matrix[row][col]) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }

}
