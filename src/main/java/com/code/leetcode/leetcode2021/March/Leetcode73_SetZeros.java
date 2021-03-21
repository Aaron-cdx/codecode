package com.code.leetcode.leetcode2021.March;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2021/3/21 8:56
 * @Motto Keep thinking, keep coding!
 * leetcode 73、矩阵置零
 * 题目：https://leetcode-cn.com/problems/set-matrix-zeroes/
 */
public class Leetcode73_SetZeros {
    public static void main(String[] args) {
        Leetcode73_SetZeros test = new Leetcode73_SetZeros();
//        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        test.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 使用常数级别的空间即O(1)空间复杂度来实现
     * 即实现本地修改为0
     * <p>
     * 这里方法实现：是对每一个初始行列为0的元素，进行row与col的状态记录
     * 如果非第一行，第一列的元素为0 此时通过设置元素初始行为0或者初始列为0
     * 然后对非第一行第一列的元素通过判断行初始或者列初始是否为0来赋值0
     * 最后对第一行为0或者第一列为0进行赋值即可
     * <p>
     * 这个方法妙啊！
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return;
        int cols = matrix[0].length;

        boolean rowZero = false;
        boolean colZero = false; // 这两个是记录行或者列为0的情况的指标
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果当前为0，则需要判断是否第一行或者第一列的
                if (matrix[i][j] == 0) {
                    if (i == 0) rowZero = true; // 即出现在第一行
                    if (j == 0) colZero = true; // 即出现在第一列，如果两个都有则是行列全为0
                    // 如果不是在第一行或者第一列中，此时需要将当前元素行开始置为0，列开始置为0
                    // 提示后面遍历的元素，这一行与这一列需要全部进行置零操作
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 由于在上面对非第一行第一列的元素的行开始与列开始置为0了，这里即在内部进行置零操作
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // 这里就是表示当前列为0或者当前行开头为0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后对于首行与首列元素进行判断
        if (rowZero) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    /**
     * 先使用额外空间来做
     * 即使用额外空间记录为0的行列值，然后对行列值进行赋0操作即可
     */
    /*public void setZeroes(int[][] matrix) {
        Set<Zeros> set = new HashSet<>();
        // 使用数组记录行和列
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    set.add(new Zeros(i, j));
                }
            }
        }
        // 得到了所有的为0的行和列
        for (Zeros zeros : set) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[zeros.row][j] = 0;
            }
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][zeros.col] = 0;
            }
        }
    }*/
}

class Zeros {
    int row;
    int col;

    public Zeros(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
