package com.code.leetcode.December;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author caoduanxi
 * @Date 2020/12/31 9:29
 * @Motto Keep thinking, keep coding!
 * leetcode 435、无重叠区间
 * 题目：https://leetcode-cn.com/problems/non-overlapping-intervals/
 * 移除重复区间最小个数，使得当前数组没有重叠区间
 */
public class Leetcode435_EraseOverlapIntervals {
    public static void main(String[] args) {
        Leetcode435_EraseOverlapIntervals test = new Leetcode435_EraseOverlapIntervals();
        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//        int[][] intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] != o2[0]) {
//                    return o1[0] - o2[0];
//                } else {
//                    return o1[1] - o2[1];
//                }
//            }
//        });
//        System.out.println(intervals);
        System.out.println(test.eraseOverlapIntervals(intervals));
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        // 使用动态规划解题
        int[] dp = new int[intervals.length];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        Arrays.fill(dp,1);// 至少为1个
        int n = intervals.length;
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 如果前面的大的小于等于后面的小的则不重复
                if(intervals[j][1] <=intervals[i][0]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    cnt = Math.max(dp[i],cnt);
                }
            }
        }
        return n - cnt;
    }


    /**
     * 贪心算法
     * 排序O(nlogn)+O(n) => O(nlogn)
     * 空间O(logn)排序时候使用的栈空间
     */
    /*public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int cnt = 1;// 表示不重复的有多少,至少为1
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= end){
                cnt++;
                end = intervals[i][1];
            }
        }
        return intervals.length-cnt;
    }*/

}
