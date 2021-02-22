package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/22 14:40
 * @Motto Keep thinking, keep coding!
 * leetcode 766、托普利茨矩阵
 * 题目：https://leetcode-cn.com/problems/toeplitz-matrix/
 * 左上角到右下角的所有的元素都相等表示符合输出true
 * <p>
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 *  |- 将每一行复制到一个连续数组中，随后在读取下一行时，就与内存中存在的之前的数组比较即可
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 *  |- 将矩阵竖直分割为若干个子矩阵，并保证两个相邻的矩阵至少有一列或者一行是重合的，然后判断每个子矩阵是否符合
 */
public class Leetcode766_IsToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) continue;
                if (matrix[i][j] != matrix[i - 1][j - 1]) return false;
            }
        }
        return true;
    }
}
