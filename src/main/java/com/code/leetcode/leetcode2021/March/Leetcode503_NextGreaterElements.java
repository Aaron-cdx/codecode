package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/6 9:31
 * @Motto Keep thinking, keep coding!
 * leetcode 503、下一个更大的元素II
 * 题目：https://leetcode-cn.com/problems/next-greater-element-ii/
 */
public class Leetcode503_NextGreaterElements {
    /**
     * 使用单调栈解题
     * 只保留比当前元素大的下标，实际上遍历两趟完全即可
     * 没想明白这一题的单调栈解法是怎么个思想。
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        // 从后往前
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 这里表示值保留比自己大的，由于所有的位置都会经历两次赋值，所以结果一定是准确的
            while (!deque.isEmpty() && nums[deque.peek()] <= nums[i % n]) {
                deque.pop();
            }
            arr[i % n] = deque.isEmpty() ? -1 : nums[deque.peek()];
            deque.push(i % n);
        }
        return arr;
    }
    /**
     * 1 2 1
     * 下一个更大的元素从1 2 1开始循环找
     * 2 -1 2 => 只要能够循环找到的就是自己最大的
     * 使用deque双向队列作为栈的替代
     * 这里使用栈的移除和插入可以实现队列的循环操作
     * <p>
     * 纯暴力法解题
     */
    /*public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i+1;
            // 循环遍历的方法
            while((j % n) != i ){
                if(nums[j%n] > nums[i]){
                    arr[i] = nums[j%n];
                    break;
                }
                j++;
            }
            // 否则就是到了自己找不到
            if((j%n) == i){
                arr[i] = -1;
            }
        }
        return arr;
    }*/
}
