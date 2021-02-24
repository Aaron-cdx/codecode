package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/24 9:05
 * @Motto Keep thinking, keep coding!
 * leetcode 832、翻转图像
 * 题目：https://leetcode-cn.com/problems/flipping-an-image/
 */
public class Leetcode832_FlipAndInvertImage {
    /**
     * 采用简便的方法来写
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            // 每个都是一重循环
            while (left < right) {
                // 如果两者相等，则需要进行翻转
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                // 如果不想等，因为在异或的下面，加入为1 0交换之后为0 1然后异或还是为1 0,所以不做改变
                left++;
                right--;
            }
            // 最后处理一下如果left == right的情况，直接异或
            if (left == right) A[i][left] ^= 1;
        }
        return A;
    }
    /**
     * 题目的意思是先实现水平翻转，然后实现全局的翻转
     * 水平翻转即为逆序，反转图片意思是0变为1,1变为0
     * <p>
     * 也可以采用简便的方法来写
     */
    /*public int[][] flipAndInvertImage(int[][] A) {
        for (int[] ints : A) {
            reversePic(ints);
        }
        return A;
    }

    public void reversePic(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            swap(arr, left++, right--);
        }
        // 同时遍历异或操作
        for (int i = 0; i < arr.length; i++) {
            arr[i] ^= 1;
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }*/
}
