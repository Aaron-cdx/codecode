package com.code.leetcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/9/10 20:55
 * leetcode 34、在排序数组中查找元素的第一个和最后一个位置索引
 */
public class Leetcode34_SearchOneAndLastOneIndex {
    public static void main(String[] args) {
        Leetcode34_SearchOneAndLastOneIndex test = new Leetcode34_SearchOneAndLastOneIndex();
        System.out.println((test.binarySearch(new int[]{5, 7, 7, 8, 8, 8, 8, 10, 11, 12}, 8)));
    }

    /**
     * 由于此时需要查找当前的数的第一个出现的位置和最后一个出现的位置
     * 即需要往前查找,和往后查找，如果前面是0后面也是0的话，此时就直接返回[i,i]
     * 向前逼近和向后逼近的不同！
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        int l = 0;
        int r = nums.length - 1;
        // 从后往前逼近
        while (l < r) {
            int mid = (l + r) >> 1;
            if (target <= nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l] != target) return res;
        // 找左右边界的时候逼近的方向不同则可找到当前的边界点
        res[0] = l;
        r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (target >= nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        res[1] = l - 1;
        return res;
    }

    /**
     * 普通的二分查找
     */
    public int binarySearchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0;
        int r = nums.length;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[l]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return nums[l] == target ? l : -1;
    }

    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0;
        int r = nums.length;
        // 从后往前逼近
        while (l < r) {
            int mid = (l + r) >> 1;
            if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
