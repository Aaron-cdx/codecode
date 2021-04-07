package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/7 9:42
 * @Motto Keep thinking, keep coding!
 * leetcode 81、搜索旋转排序数组II
 * 题目：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * 思路：
 * 对于这种旋转数组(不含重复元素),先判断是在前面还是后面，如果是前面(这个需要二次判断),则收缩右边界，否则左边界
 * 而含有重复元素的，则需要对nums[mid]=nums[l]=nums[r]此时无法判断，只能够同时收缩左右边界继续判断了
 */
public class Leetcode81_Search {
    public static void main(String[] args) {
        Leetcode81_Search test = new Leetcode81_Search();
//        System.out.println(test.search_33(new int[]{1},0));
        System.out.println(test.search(new int[]{1}, 0));
    }

    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return false;
        int l = 0, r = n - 1, mid = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target) return true;
            // 如果左右边界相等，此时只能进行区间缩减
            if (nums[mid] == nums[l] && nums[mid] == nums[r]) {
                l++;
                r--;
            }
            // 否则根据前后判断
            else if (nums[mid] >= nums[l]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 否则表示不再前半部分，在后半部分
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }

    public int search_33(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 这里要用二分
            /*if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } */

            if (nums[mid] >= nums[l]) {
                // 判断是否是在左边
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 否则表示此时的数组是进行了旋转的
                if (target > nums[mid] && target <= nums[n - 1]) { // 表示后半部分有序
                    l = mid + 1;
                } else { // 否则就向前
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
