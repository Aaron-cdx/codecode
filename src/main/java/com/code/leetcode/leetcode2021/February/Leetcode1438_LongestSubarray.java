package com.code.leetcode.leetcode2021.February;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author caoduanxi
 * @Date 2021/2/21 9:14
 * @Motto Keep thinking, keep coding!
 * leetcode 1438、绝对差不超过限制的最长连续子数组
 * 题目：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
public class Leetcode1438_LongestSubarray {
    public static void main(String[] args) {
        Leetcode1438_LongestSubarray test = new Leetcode1438_LongestSubarray();
        System.out.println(test.longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
        System.out.println(test.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2, 2, 2, 2}, 0));
    }

    /**
     * 使用优先队列解题，但是现在推荐使用Deque双端队列来解题
     */
    /*public int longestSubarray(int[] nums, int limit) {
        // 保持两个优先队列，一个存储最大值，一个存储最小值
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int n = nums.length;
        int left = 0;
        int right = 0;
        // 最长的长度
        int maxLen = 0;
        while (right < n) {
            min.add(nums[right]);
            max.add(nums[right]);
            // 此时判断minVal的值是否符合要求limit的限制
            if (Math.abs(max.peek() - min.peek()) <= limit) {
                maxLen = Math.max(maxLen, right - left + 1);
            } else {
                // 否则表示不满足limit限制，需要移动左边的值
                // 移动左边的值
                while (left <= right && Math.abs(max.peek() - min.peek()) > limit) {
                    max.remove(nums[left]);
                    min.remove(nums[left]);
                    left++;
                }
            }
            right++;
        }
        return maxLen;
    }*/

    /**
     * 使用TreeMap解题
     */
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (!map.isEmpty() && (map.lastKey() - map.firstKey() > limit)) {
                // 判断个数，如果只有一个，直接移除，否则-1
                int cnt = map.get(nums[left]);
                if (cnt <= 1) {
                    map.remove(nums[left]);
                } else {
                    map.put(nums[left], cnt - 1);
                }
                left++;// 左边向前移动
            }
            // 计算长度
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
