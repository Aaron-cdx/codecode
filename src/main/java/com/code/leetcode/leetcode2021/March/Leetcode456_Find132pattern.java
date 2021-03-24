package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/24 9:07
 * @Motto Keep thinking, keep coding!
 * leetcode 456、132模式
 * 题目：https://leetcode-cn.com/problems/132-pattern/
 */
public class Leetcode456_Find132pattern {
    public static void main(String[] args) {
        Leetcode456_Find132pattern test = new Leetcode456_Find132pattern();
        System.out.println(test.find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(test.find132pattern(new int[]{3, 1, 4, 2}));
        System.out.println(test.find132pattern(new int[]{-1, 3, 2, 0}));
    }

    /**
     * 提示使用栈实现，但是栈怎么实现132呢？
     * 对所有的数保留最小值，然后遍历利用栈来对后面两个数查找即可
     */
    public boolean find132pattern(int[] nums) {
        // 这里的
        int n = nums.length;
        if (n < 3) return false;
        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        // 使用栈 min[i] < stack.peek() < nums[i] 则输出true
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            // 判断当前栈中的元素，如果小于min[i]则表示不符合要求，需要弹出所有不符合要求的元素
            while(!stack.isEmpty() && stack.peek() <= min[i]) stack.pop();
            // 然后判断stack.peek()与nums[i],因为上一步可以确保stack.peek()大于min[i]
            if(!stack.isEmpty() && stack.peek() < nums[i]) return true;
            // 否则的话直接入栈
            stack.push(nums[i]);
        }
        return false;
    }
}
