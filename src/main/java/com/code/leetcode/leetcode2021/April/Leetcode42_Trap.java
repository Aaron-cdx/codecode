package com.code.leetcode.leetcode2021.April;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/4/2 9:26
 * @Motto Keep thinking, keep coding!
 * leetcode 面试题 17.21 / 42、直方图的水量/接雨水
 * 题目：https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 */
public class Leetcode42_Trap {
    /**
     * 使用动态规划解题
     * 妙啊
     * 左边求最大值
     * 右边求最大值
     * 两者最小值减去height实际高度即为可以得到的雨水
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int ans = 0;
        // 最后只需要通过两者的最小值即可求出
        for (int i = 0; i < n; i++) {
            ans += Math.max(rightMax[i], leftMax[i]) - height[i];
        }

        return ans;
    }
    /**
     * 采用单调栈方法处理
     * 利用栈在行进过程中判断是否可能形成凹陷，如果能形成凹陷，则进入处理，否则继续行进
     */
    /*public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            // 栈不为空，且当前数的行进加入可以对凹陷进行雨水的计算
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 进入处理
                int top = stack.pop(); // 这是弹出可能为凹陷的位置
                if (stack.isEmpty()) { // 如果凹陷左边没有元素了，则接不住雨水，直接break
                    break;
                }
                // 否则表示可以接雨水，此时先处理一个
                int left = stack.peek();// 使用peek是为了保留左边元素，可能未来会出现更高的从而接住更多的雨水
                // 接雨水的长度
                int currentLen = i - left - 1;
                // 接雨水的高度,这里的高度是用当前元素和其左边元素最小的高度减去凹陷处的高度
                int currentHeight = Math.min(height[left], height[i]) - height[top];
                ans += currentHeight * currentLen;
            }
            // 否则这里只有一直变小才有机会形成凹陷
            stack.push(i);
        }
        return ans;
    }*/
    /**
     * 采用双指针的办法
     * 左边处理左边的右边处理右边的，但是右边具有先行权利，即最后是右边到达最高定点
     */
    /*public int trap(int[] height) {
        if (height == null) return 0;
        int n = height.length;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = n - 1;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // 如果小于右边的值，表示现在有雨水可以承接
            if (height[left] < height[right]) {
                ans += leftMax - height[left]; // 用左边最大值减去左边现在获取的值，因为右边已经比自己大了，现在一定会有凹陷
                left++;
            } else {
                // 否则就是左边值更大，右边可能出现没有凹陷，可能接收不到雨水，此时需要看右边最大值与当前right位置是否可以组成凹陷
                // 因为左边更大，右边相对来说最大值可以组成凹陷
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }*/
}
