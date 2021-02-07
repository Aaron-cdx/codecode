package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/7 9:13
 * @Motto Keep thinking, keep coding!
 * leetcode 665、非递减序列
 * 题目：https://leetcode-cn.com/problems/non-decreasing-array/
 * 通过改变其中的一个元素从而获取到非递减序列，如果可以置为true，否则为false
 */
public class Leetcode665_CheckPossibility {
    public static void main(String[] args) {
        Leetcode665_CheckPossibility test = new Leetcode665_CheckPossibility();
        System.out.println(test.checkPossibility(new int[]{3,4,2,3}));
        System.out.println(test.checkPossibility(new int[]{5,7,1,8}));
        System.out.println(test.checkPossibility(new int[]{4,2,3}));
    }
    /**
     * 3 4 2 3是一个特例
     * 5 7 1 8是一个特例
     * 只需要统计下降的次数就好，只要下降次数超过1次，则表示false
     */
    /*public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        int down = 0;
        for (int i = 0; i + 1 < n; i++) {
            if(nums[i+1] < nums[i]) {
                down++;
                // 出现了两次递减
                if (down > 1) return false;
                // 否则的话，此时需要隔空判断一下i+1与i-1之间的关系
                // i==0的时候直接略过就好，
                if(i > 0 && nums[i-1] > nums[i+1]){
                    nums[i+1] = nums[i];
                }
            }
        }
        return true;
    }*/

    /**
     * 第二种方法，一直改造求解真实递减的次数
     */
    public boolean checkPossibility(int[] nums) {
       int n = nums.length;
       int down = 0;
        for (int i = 0; i < n-1; i++) {
            // 递减出现了
            if(nums[i+1] < nums[i]){
                down++;
                if(down > 1) return false;
                // 否则的话此时判断应该怎么修正，此时修正的机会只有两个，一个是修正i，一个是修正i+1
                // 修正参考都是以i-1做参考的，如果i-1小于i+1，此时的机会只有修正i；如果i-1大于i+1,此时只有修正i+1
                if(i > 0 && nums[i-1] > nums[i+1]){
                    nums[i+1] = nums[i];
                }
            }
        }
        return true;
    }
}
