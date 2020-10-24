package com.code.leetcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/10/24 16:44
 * @Motto Keep thinking, keep coding!
 * leetcode 1024、视频拼接
 * 即通过不同的片段来拼接当前的视频，判断需要至少几个片段可以将当前的视频拼接完毕，如果不能够完成拼接直接输出-1
 * 题目：https://leetcode-cn.com/problems/video-stitching/
 */
public class Leetcode1024_VideoStitching {
    /**
     * 采用动态规划的方式解题定义dp[i]为0-i的覆盖需要的片段个数，然后每次遍历所有的符合条件的片段进行筛选
     */
    public int videoStitchingI(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, 101);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            // 这里是枚举每一个空间一遍
            for (int[] clip : clips) {
                if (clip[0] < i && clip[1] >= i) {
                    // 这里是判断0-i这个区间能否直接覆盖，不能的话，采用两个区间的合并覆盖即可
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == 101 ? -1 : dp[T];
    }

    /**
     * 贪心算法实现的当前的内容
     * 首先使用数组记录当前元素出现时，最大的结束位置
     * 然后使用两个指针pre和last交替连接各个区间，将cnt计算出来
     * 注意每次到达上一个pre的时候需要进行cnt++，因为多个了一个区间，同时也要将上一个区间的pre=last，这样可以保证
     * 连接上，一旦出现i=last，则表示当前值的区间已经是最大了，则永远到不了一个视频的结尾！
     */
    public int videoStitching(int[][] clips, int T) {
        int[] maxEnd = new int[T];
        for (int[] clip : clips) {
            if (clip[0] < T) {
                // 比较上一次的最大和本次最大
                maxEnd[clip[0]] = Math.max(maxEnd[clip[0]], clip[1]);
            }
        }
        int pre = 0;
        int last = 0;
        int count = 0;
        for (int i = 0; i < T; i++) {
            last = Math.max(maxEnd[i], last);
            //如果当前元素等于本区间最大元素，则直接返回-1，因为不可能连接起来了！
            if (i == last) return -1;
            if (i == pre) {
                count++;
                pre = last;
            }
        }
        return count;
    }

}
