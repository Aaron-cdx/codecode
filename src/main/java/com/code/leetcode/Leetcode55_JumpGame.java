package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/13 18:58
 * leetcode 55、跳跃游戏
 * 即通过数组中的数字作为跳跃的步数，判断当前数能否跳到最后一个节点
 * 能跳到的话叔叔true，否则输出false
 * <p>
 * 标签：贪心算法
 */
public class Leetcode55_JumpGame {

    public static void main(String[] args) {
        Leetcode55_JumpGame test = new Leetcode55_JumpGame();
        System.out.println(test.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(test.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(test.canJump(new int[]{0, 2, 3}));
        System.out.println(test.canJump(new int[]{2, 5, 0, 0}));
        System.out.println(test.canJump(new int[]{3, 2, 1, 0, 4}));

    }

    /**
     * 首先利用暴力法，但是暴力法有一个坏处，会多出许多重复计算的步骤
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int maxStep = 0;
        // 建立一个可以跳最远的一个值，只要当前值能够跳到最远
        // 则这个最远值一定大于i,一旦出现小于，就知道自己前面所有的数都接触不到自己，直接放弃false
        for (int i = 0; i < n; i++) {
            if (i > maxStep) return false;
            maxStep = Math.max(maxStep, i + nums[i]);
        }
        return true;
    }
}
