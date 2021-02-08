package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/8 13:16
 * @Motto Keep thinking, keep coding!
 * leetcode 978、最长湍流子数组
 * 题目：https://leetcode-cn.com/problems/longest-turbulent-subarray/
 * 题目的意思是返回最长湍流子数组的最长长度
 * 大于小于进行切换
 */
public class Leetcode978_MaxTurbulenceSize {
    /**
     * 寻找最大的湍流子数组
     * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组
     * 只有下面的两种情况
     * > < > <
     * < > < >
     * [9,9]这种如果左边相等的话，需要向前驱动
     * 滑动窗口解法
     */
    /*public int maxTurbulenceSize(int[] arr) {
        int ret = 1;
        int n = arr.length;
        int left = 0;
        int right = 0;
        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                // 两种方式的判断
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    // 进行重置，表示不能继续计算
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }*/

    /**
     * 动态规划解决
     * 一个记录升序，一个记录降序，升序交替切换
     */
    /*public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            // 表示降序
            if (arr[i - 1] > arr[i]) {
                // 当前降序的前提是上一个保持升序
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] < arr[i]) {
                // 当前升序是上一个保持降序
                dp[i][1] = dp[i - 1][0] + 1;
            }
            // 否则表示相等，此时直接略过即可
        }
        int ret = 1;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret,dp[i][0]);
            ret = Math.max(ret,dp[i][1]);
        }
        return ret;
    }*/

    /**
     * 动态规划压缩空间
     * 因为下一个状态都是需要依赖上一个状态的变化来的
     * 所以在这里需要进行空间的压缩操作
     */
    public int maxTurbulenceSize(int[] arr) {
        int down = 1;
        int up = 1;
        int n = arr.length;
        int ret = 1;
        for (int i = 1; i < n; i++) {
            // 如果是准确的上升下降，则一直计算，否则的话值就变为1
            if (arr[i - 1] > arr[i]) {
                down = up + 1;
                up = 1;
            } else if (arr[i - 1] < arr[i]) {
                up = down + 1;
                down = 1;
            } else {
                // 两个相等直接跳过
                up = down = 1;
            }
            // 每一次计算最大值
            ret = Math.max(ret, Math.max(up, down));
        }
        return ret;
    }

}
