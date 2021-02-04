package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/4 9:04
 * @Motto Keep thinking, keep coding!
 * leetcode 643、子数组最大平均数
 * 题目：https://leetcode-cn.com/problems/maximum-average-subarray-i/
 * 找出连续k个元素最大的平均数
 */
public class Leetcode643_FindMaxAverage {
    public static void main(String[] args) {
        Leetcode643_FindMaxAverage test = new Leetcode643_FindMaxAverage();
//        System.out.println(test.findMaxAverage(new int[]{1,12,-5,-6,50,3},4));
        System.out.println(test.findMaxAverage(new int[]{-1},1));
    }
    public double findMaxAverage(int[] nums, int k) {
        double maxAvg = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        double sum = 0;
        while(right < nums.length){
            sum += nums[right];
            if(right-left+1 == k){
                // 计算平均值
                maxAvg = Math.max(maxAvg,sum/k);
                sum -= nums[left++];
            }
            right++;
        }
        return maxAvg;
    }
}
