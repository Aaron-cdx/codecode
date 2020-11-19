package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/19 21:45
 * @Motto Keep thinking, keep coding!
 * leetcode 283、移动零
 * 题目：https://leetcode-cn.com/problems/move-zeroes/
 * 即将所有的0都移动到数组的末尾
 */
public class Leetcode283_MoveZero {
    public static void main(String[] args) {
        Leetcode283_MoveZero test = new Leetcode283_MoveZero();
        int[] nums = new int[]{0, 1, 0, 3, 12};
        test.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        // 一遍过的代码,就是为0的与不为0的交换位置
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, k);
                k++;
            }
        }
        // 这是麻烦了的代码
        /*int i = 0;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[i] != 0) {
                i++;
                j++;
            } else {
                // 否则就是nums[i] = 0, 此时需要启动j去寻找非0,然后交换
                while (j < nums.length - 1 && nums[j] == 0) {
                    j++;
                }
                swap(nums, i, j);
                i++;
            }
        }*/
    }

    public void swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
}
