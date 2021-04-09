package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/9 8:12
 * @Motto Keep thinking, keep coding!
 * leetcode 154、寻找旋转排序数组中的最小值II
 * 题目：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class Leetcode154_FindMin {
    public static void main(String[] args) {
        Leetcode154_FindMin test = new Leetcode154_FindMin();
        System.out.println(test.findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(test.findMin(new int[]{1, 3, 5}));
    }

    public int findMin(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1, mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            // 如果相等的话则两边收缩即可
            if (nums[mid] == nums[l] && nums[mid] == nums[r]) {
                l++;
                r--;
            } else if (nums[mid] >= nums[l] && nums[mid] > nums[r]) {
                // mid同时大于两边的话，此时最小值一定在后面
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[r];
    }
}
