package com.code.enterprise;

/**
 * @author caoduanxi
 * @Date 2020/9/7 15:41
 * 给出0 1的二维矩阵，求最大的正方形面积
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 其实这个就看你有多少个1是连续的了，面积就是连续的1个数相乘即可
 */
public class Max_Square {
    public static void main(String[] args) {
        Max_Square test = new Max_Square();
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(test.solve(matrix));
    }

    public int solve(char[][] matrix) {
        // write code here
        if (matrix == null || matrix.length == 0) return 0;

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int maxLen = 0;
        // 就是这样去走，判断当前的值是否为1，如果
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}
