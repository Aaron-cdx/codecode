package com.code.leetcode.leetcode2021.April;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/4/5 9:43
 * @Motto Keep thinking, keep coding!
 * leetcode 88、合并两个有序数组
 * 题目：https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class Leetcode88_Merge {
    public static void main(String[] args) {
        Leetcode88_Merge test = new Leetcode88_Merge();
        int[] nums1 = new int[]{0};
        int[] nums2 = new int[]{1};
        int m = 0;
        int n = 1;
        test.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * [0] 1 [1] 1
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        if (len == m) return; // m是真实元素，len是实际长度
        len -= 1;
        int a = m - 1;
        int b = n - 1;
        while (a >= 0 && b >= 0) {
            if (nums1[a] > nums2[b]) {
                nums1[len--] = nums1[a--];
            } else {
                nums1[len--] = nums2[b--];
            }
        }
        // 即b没到位，此时需要拷贝了，如果a没到位此时结果是正确的
        if (b != -1) {
            System.arraycopy(nums2, 0, nums1, 0, b + 1);
        }
    }
}
