package com.code.leetcode.leetcode2021.January;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/1/2 9:58
 * @Motto Keep thinking, keep coding!
 * leetcode 239、滑动窗口最大值
 * 题目：https://leetcode-cn.com/problems/sliding-window-maximum/
 * 给定一个k值大小的滑动窗口，判断滑动窗口的最大值是多少？
 */
public class Leetcode239_MaxSlidingWindow {
    public static void main(String[] args) {
        Leetcode239_MaxSlidingWindow test = new Leetcode239_MaxSlidingWindow();
//        test.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        test.maxSlidingWindow(new int[]{4,-2}, 2);
//        test.test();
    }

    public void test(){
        int[] nums = new int[]{1,2,3,4,5};
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });// 默认小顶堆
        for (int num : nums) {
            queue.add(num);
        }
        while(!queue.isEmpty()){
//            System.out.println(queue.peek()+" ");
//            System.out.println(queue.remove(queue.element())+" ");
            System.out.print(queue.poll()+" ");
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 如果窗口大小为1,则返回原始的大小即可
        if (k == 1) return nums;
        // 双端队列
        Deque<Integer> deque = new LinkedList<>();
        // 从后面插入保证大的一定在最前面
        for (int i = 0; i < k; i++) {
            // 如果进来了更大的就一直推进
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        res[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; i++) {
            while(!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            // 现在需要判断之前的那些最大值。因为可能存在队首无法被弹出
            // 防止之前的最大值污染当前窗口最大值
            while(deque.peekFirst() <= i-k){
                deque.pollFirst();
            }
            res[i-k+1] = nums[deque.peekFirst()];
        }
        return res;
    }

    /**
     * 超出时间限制！！！！！
     * 时间复杂度O(nlogn)
     * 空间复杂度O(n)
     */
    // 无论怎么滑动 一定是进一个出一个
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        // 如果窗口大小为1,则返回原始的大小即可
        if (k == 1) return nums;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });// 默认小顶堆
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        list.add(queue.peek());
        // 每次需要移除一个元素
        for (int i = 1; i < nums.length && i + k - 1 < nums.length; i++) {
            // 弹出i-1位置的数
            queue.remove(nums[i - 1]);
            queue.add(nums[i + k - 1]);
            list.add(queue.peek());
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }*/
}
