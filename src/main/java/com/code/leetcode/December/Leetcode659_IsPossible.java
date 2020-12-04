package com.code.leetcode.December;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/12/4 9:22
 * @Motto Keep thinking, keep coding!
 * leetcode659、分割数组为连续子序列
 * 题目：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/
 * 自己写的题解：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/solution/fen-ge-shu-zu-wei-lian-xu-zi-xu-lie-by-x-hqye/
 * 注意题目的条件，长度至少为3，这个是个非常关键的点！
 * 1 2 3 3 4 4 5 5
 * => 1 2 3 4 5
 * => 3 4 5
 * ===============
 * 1 2 3 3 4 5
 * => 1 2 3
 * => 3 4 5
 */
public class Leetcode659_IsPossible {
    public static void main(String[] args) {
        Leetcode659_IsPossible test = new Leetcode659_IsPossible();
        System.out.println(test.isPossible(new int[]{1, 2, 3, 3, 4, 4, 5, 5}));
    }

    /**
     * 如何将数组分割为两个连续的子序列
     * 官方题解第一种答案：
     * 采用Hash+最小堆实现
     * 利用Hash里面放置最小堆，在行进的过程当中，由于最小堆的存在每次先弹出的都是最小的那个
     * 就可以使得后续的数组变得连续起来.
     * 时间复杂度: O(nlogn)  空间复杂度：O(n) => 主要是哈希表中以及PriorityQueue中的数组长度，底层都是数组实现，
     * 总长度不会超过n
     * 存在的问题：每次遇到num-1时都会新建一个PriorityQueue。
     */
    /*public boolean isPossible(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new PriorityQueue<>());
            }
            // 否则的话判断之前是否有，即判断是否开始在连续的节点处
            if (map.containsKey(num - 1)) {
                // 如果存在num-1的话，表明没有到连续节点处或者过了连续节点处
                int preLen = map.get(num - 1).poll();// 吐出来
                // 如果没有到连续节点处，需要将当前的num-1节点移除，以保持
                if (map.get(num - 1).isEmpty()) {// 如果里面只有一个数的话，则需要移除，防止在下一轮迭代中又遇到
                    map.remove(num - 1);
                }
                map.get(num).add(preLen + 1);
            } else {
                map.get(num).add(1);// 这是过了连续节点，或者节点没有连续起来导致的为1
            }
        }
        // 因为一个数字有两次出现的话，每次后面用的话都是用最小堆的顶部点，即最小，故一定不会出现重复
        Set<Map.Entry<Integer, PriorityQueue<Integer>>> entries = map.entrySet();
        System.out.println(entries);
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : entries) {
            PriorityQueue<Integer> value = entry.getValue();
            if (value.peek() != null && value.peek() < 3) {
                return false;
            }
        }
        return true;
    }*/

    /**
     * 为避免每次遇到num-1的时候新建短的子序列
     * 利用两个map来操作，一个记录出现的次数，一个能够构成的长度，只要满足条件就一直往后，不满足直接返回false
     * 利用贪心思想
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {// 如果被用了，直接略过
                // 这里判断结算的map里面是否有前一个
                int preEndLen = endMap.getOrDefault(num - 1, 0);
                if (preEndLen > 0) {
                    // 如果有的话，表名之前的值是存入了，此时为了保持最长数组,需要将后面的续上，续上需要付出代价的，代价就是数量-1
                    countMap.put(num, count - 1);
                    // 同时因为要将num-1续上，此时endMap中的num-1需要进行减一操作
                    endMap.put(num - 1, preEndLen - 1);
                    // 然后续了更长，此时需要按照num更新操作
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                } else {
                    // 如果没有的话，表明自己是第一个，如果自己是第一个，则看看最少能组成长度为3自己是否满足
                    int cnt1 = countMap.getOrDefault(num + 1, 0);
                    int cnt2 = countMap.getOrDefault(num + 2, 0);
                    if (cnt1 > 0 && cnt2 > 0) {
                        // 如果两者都是大于0
                        // 此时可以组成，则更新map
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, cnt1 - 1);
                        countMap.put(num + 2, cnt2 - 1);
                        // 同时结算的map里面需要新增
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        // 表示不满足3，直接返回false
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
