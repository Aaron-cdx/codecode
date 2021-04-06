package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/6 9:14
 * @Motto Keep thinking, keep coding!
 * leetcode 80、删除有序数组中的重复项II
 * 题目：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class Leetcode80_RemoveDuplicates {
    /**
     * 原地不动修改数组
     * 返回修改后的数组长度
     * 每个元素最多出现两次
     * <p>
     * 利用cnt进行本地赋值操作，从而实现具体的移除元素操作
     */
    /*public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int cnt = 0;
        for (int num : nums) {
            if (cnt < 2 || num != nums[cnt - 2]) {
                nums[cnt] = num;
                cnt++;
            }
        }
        return cnt;
    }*/

    /**
     * 利用双指针进行
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // 双指针操作
        int left = 2, right = 2;
        while (right < n) {
            // 加入left-2与right处值不等，而此时由于left-2的元素与left并不等，因为最多保持两个元素，所以此时需要进行left = right行进
            if (nums[left - 2] != nums[right]) {
                nums[left] = nums[right];
                left++;
            }
            // 如果相等的话，此时表示重复了，left时钟保持在一个不等的临界位置
            right++;
        }
        return left;
    }
}
