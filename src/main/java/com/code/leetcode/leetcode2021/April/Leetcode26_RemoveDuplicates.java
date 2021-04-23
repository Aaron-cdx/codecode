package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/23 9:52
 * @Motto Keep thinking, keep coding!
 * leetcode 26、删除有序数组中的重复项
 * 题目：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class Leetcode26_RemoveDuplicates {
    public static void main(String[] args) {
        Leetcode26_RemoveDuplicates test = new Leetcode26_RemoveDuplicates();
        System.out.println(test.removeElement(new int[]{0,1,2,2,3,0,4,2},2));
    }
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int b = 0;
        nums[b] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[b]) {
                nums[++b] = nums[i];
            }
        }
        return b + 1;
    }

    /**
     * leetcode 27、移除元素
     * 题目：https://leetcode-cn.com/problems/remove-element/
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[b++] = nums[i];
            }
        }
        return b;
    }
}
