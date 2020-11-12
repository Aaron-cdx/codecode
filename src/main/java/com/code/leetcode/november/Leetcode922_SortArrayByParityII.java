package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/12 22:09
 * @Motto Keep thinking, keep coding!
 * leetcode 922、按奇偶排序数组II
 * 题目：https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 */
public class Leetcode922_SortArrayByParityII {
    /**
     * 简单来说就是进行奇数的位置排列奇数，偶数的位置排列偶数
     */
    public int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length == 0) return null;
        int i = 0;
        int j = 1;
        while (i < A.length) {
            if ((A[i] & 1) != 0) {
                // 如果这个位置是奇数，就让这个奇数位置找到一个偶数之后再交换
                while ((A[j] & 1) != 0) j += 2;
                swap(A, i, j);
            }
            i += 2;
        }
        return A;
//        int j = 1;
//        for (int i = 0; i < A.length - 1; i = i + 2) {
//            // 表明是偶数
//            if ((A[i] & 1) != 0) {
//                while ((A[j] & 1) != 0) {
//                    j = j + 2;
//                }
//                swap(A, i, j);
//            }
//        }
//        return A;
    }

    public void swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

}
