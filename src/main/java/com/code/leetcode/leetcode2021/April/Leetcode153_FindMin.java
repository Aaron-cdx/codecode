package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/8 9:41
 * @Motto Keep thinking, keep coding!
 * leetcode 153、寻找旋转排序数组中的最小值
 * 题目：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class Leetcode153_FindMin {
    public static void main(String[] args) {
        Leetcode153_FindMin test = new Leetcode153_FindMin();
        System.out.println(test.findMin(new int[]{2, 1}));
        System.out.println(test.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(test.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(test.findMin(new int[]{11, 13, 15, 17}));
        System.out.println(test.findMin(new int[]{3, 1, 2}));
    }

    /**
     * 最小值一定是旋转过去之后的最大值后面的
     * 或者是未旋转过去的最前面的最小值
     * 就往最小的元素去压缩范围，最后到了l <= r的时候此时结果就是最小的值
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1, mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            // 这里对最小值进行判断，通过mid与l对比，再通过l与r对比，如果mid大于l且l大于r，则最小值一定在后面
            if (nums[mid] >= nums[l] && nums[l] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid; // 因为l大于r，可以确定l一定不在范围之内，而r可能会在最小值之
            }
        }
        return nums[l]; // 如何定义这个最小的元素呢？
    }
}
