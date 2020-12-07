package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/7 19:00
 * @Motto Keep thinking, keep coding!
 * leetcode 861、翻转矩阵后的得分
 * 题目：https://leetcode-cn.com/problems/score-after-flipping-matrix/
 * 题目意思是对二维矩阵中的元素可以执行行翻转或者列翻转以保证值最大，问最大值为多少
 */
public class Leetcode861_MatrixScore {
    public static void main(String[] args) {
        Leetcode861_MatrixScore test = new Leetcode861_MatrixScore();
        int[][] A = new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        System.out.println(test.matrixScore(A));
        System.out.println(1 << 3);
    }
    /**
     * 题目理解：
     * 第一种做法
     * 由于要保证最大值，即每一行需要保持最大值，则由二进制相关的理论可得，首部为1才可能取到最大值
     * 故首先需要保证每一行的第一个是值最大，然后在对列进行翻转
     * 对列进行翻转需要考虑其他列的想法，如果0的数大于1的数，此时必须翻转以获取最大值
     */
    /*public int matrixScore(int[][] A) {
        if(A == null || A.length == 0) return 0;
        // 先将不为1的那一行进行翻转
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++) {
            if((A[i][0] & 1) == 0){
                for (int j = 0; j < n; j++) {
                    A[i][j] ^= 1;
                }
            }
        }
        // 然后对列进行翻转,对所有的列进行遍历
        for (int i = 0; i < n; i++) {
            int zeros = 0;
            for (int[] ints : A) {
                zeros += ints[i] == 0 ? 1 : 0;
            }
            if(zeros > m/2){
                for (int k = 0; k < m; k++) {
                    A[k][i] ^= 1;
                }
            }
        }
        int sum = 0;
        for (int[] a : A) {
            sum += calculate(a);
        }
        // 最后计算结果
        return sum;
    }
    private int calculate(int[] A){
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += (A[i] << A.length-1-i);
        }
        return sum;
    }*/

    /**
     * 第二种方法：
     * 利用贪心的思想来做
     * 先按照左边第一列必为1计算，然后对于在运行过程中左边第一列如果为0的，则表明此时的是翻转的需要计算为0
     */
    public int matrixScore(int[][] A) {
        if (A == null || A.length == 0) return 0;
        int m = A.length, n = A[0].length;
        // 左边第一列为1的贡献值m*2^(n-1)
        int sum = m * (1 << n - 1);
        // 然后开始计算其他列带来的贡献
        // 注意这里从第二列开始，第一列计算完毕了
        for (int j = 1; j < n; j++) {
            int ones = 0;
            for (int[] ints : A) {
                // 如果是第一列为0的话，表明这个位置是经过翻转了的
                if (ints[0] == 0) {
                    ones += (1 - ints[j]);
                } else {
                    ones += ints[j];
                }
            }
            // 统计1的个数,按照多的取，因为只有1才会带来贡献
            int k = Math.max(ones, m - ones);
            sum += k * (1 << (n - j - 1));
        }
        return sum;
    }
}
