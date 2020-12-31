package com.code.leetcode.December;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author caoduanxi
 * @Date 2020/12/30 8:50
 * @Motto Keep thinking, keep coding!
 * leetcode 1046、最后一块石头的重量
 * 题目：https://leetcode-cn.com/problems/last-stone-weight/
 * 题目就是两块石头相等，粉碎就为0，否则粉碎为1,还需要再次粉碎
 */
public class Leetcode1046_LastStoneWeight {
    public static void main(String[] args) {
        Leetcode1046_LastStoneWeight test = new Leetcode1046_LastStoneWeight();
        System.out.println(test.lastStoneWeight(new int[]{1, 3}));
        System.out.println(test.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }

    /**
     * 使用优先队列实现
     * 优先队列内部也是排序，如果自己使用数组的话则每次遍历进行一次排序即可
     */
   /* public int lastStoneWeight(int[] stones) {
        if(stones.length == 0) return 0;
        // 默认是小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int stone : stones) {
            queue.add(stone);
        }
        while(!queue.isEmpty()){
            if(queue.size() <= 1){
                return queue.poll();
            }
            // 否则的话弹出两个
            int one = queue.poll();
            int two = queue.poll();
            if(one != two){
                // 一块石头完全粉碎，一块石头y-x
                queue.add(Math.abs(one-two));
            }
        }
        return 0;
    }*/
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 0) return 0;
        int index = stones.length - 1;
        // 一定不会到最后一个，因为是两两合并机制
        for (int i = 0; i < stones.length - 1; i++) {
            Arrays.sort(stones);
            if (stones[index - 1] == 0) break;
            stones[index] -= stones[index - 1];
            stones[index - 1] = 0;
        }
        return stones[index];
    }
}
