package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/28 10:31
 * @Motto Keep thinking, keep coding!
 * leetcode 724、寻找数组的中心索引
 * 题目：https://leetcode-cn.com/problems/find-pivot-index/
 * 即找到一个二分点将前后分割为两个和相等的结果，如果有多个结果返回最左侧的结果
 * 如果不存在则返回-1
 */
public class Leetcode724_PivotIndex {
    /**
     * 暴力法解决
     * 时间复杂度O(n^2)
     */
//    public int pivotIndex(int[] nums) {
//        int left = 0;
//        while(left < nums.length){
//            int leftSum = 0;
//            int rightSum = 0;
//            int l = 0;
//            while(l < left){
//                leftSum += nums[l++];
//            }
//            int r = left+1;
//            while(r < nums.length){
//                rightSum += nums[r++];
//            }
//            if(leftSum == rightSum){
//                return left;
//            }
//            left++;
//        }
//        return -1;
//    }

    /**
     * 暴力法时间复杂度过高，只能思考空间换时间
     */
    public int pivotIndex(int[] nums) {
        // 记录总的结果
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if(2*sum + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
