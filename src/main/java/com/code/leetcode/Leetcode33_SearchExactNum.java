package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/10 19:42
 * leetcode 33、搜索旋转排序数组
 * 就是在排序的数组中选一段进行旋转，给定一个数字找到当前数字在数组中的下标为多少
 * 要求时间复杂度为O(logn)
 * <p>
 * 这里的思路就是每次限定当前数的一个边界，中点mid正好可以来做边界
 */
public class Leetcode33_SearchExactNum {
    public static void main(String[] args) {
        Leetcode33_SearchExactNum searchExactNum = new Leetcode33_SearchExactNum();
//        System.out.println(searchExactNum.searchMin(new int[]{2, 3, 4, 5, 0, 1}));
        System.out.println(searchExactNum.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(searchExactNum.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(searchExactNum.search(new int[]{3, 5, 1}, 3));
        System.out.println(searchExactNum.search(new int[]{4, 5, 6, 0, 1, 2, 3}, 5));
    }

    private int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (target == nums[mid]) return mid;
            // 需要使用0位置上的判断当前序列是否是处于一个递增
            if (nums[0] <= nums[mid]) {
                // 如果是的话，此时判断target是否处于这其中
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 如果不是递增则表示其大于mid位置的数，此时的话，以mid和最后一个作为边界判断
                // 只要判断它是否在后面即可
                // 这个表明如果的值出现在前面此时mid后半部分是有序的
                if (target > nums[mid] && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 寻找旋转数组中的最小值
     */
    private int searchMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == nums[l] && nums[mid] == nums[r]) {
                int min = Integer.MAX_VALUE;
                for (int num : nums) {
                    min = Math.min(num, min);
                }
                return min;
            }
            // 只需要看是否比后面的值大，比后面的值大，由于是翻转过来的，所以小的肯定在后面
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
}
