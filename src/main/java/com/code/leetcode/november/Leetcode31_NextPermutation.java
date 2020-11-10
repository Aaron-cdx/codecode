package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/10 8:11
 * @Motto Keep thinking, keep coding!
 * leetcode 31、下一个排列
 * 题目：https://leetcode-cn.com/problems/next-permutation/
 * 即找出下一个最大的数
 */
public class Leetcode31_NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        // 先找到非降序的
        int l = nums.length -2;
        while(l >= 0 && nums[l+1] <= nums[l]){
            l--;
        }
        // 交换的数要最大，而未交换的数要最小，即先交换需要交换的，然后交换后面部分大的。
        if(l >= 0){
            int j = nums.length - 1;
            // 找到第一个比当前数大的
            while(j>=0 && nums[j]<= nums[l]){
                j--;
            }
            swap(nums,l,j);
        }
        rotateReverse(nums,l+1,nums.length-1);
    }

    public void swap(int[] num, int i, int j){
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    public void rotateReverse(int[] num, int i, int j){
        while(i < j){
            swap(num,i++,j--);
        }
    }
}
