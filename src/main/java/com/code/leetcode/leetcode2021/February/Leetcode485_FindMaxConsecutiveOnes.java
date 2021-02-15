package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/15 9:36
 * @Motto Keep thinking, keep coding!
 * leetcode 485、最大连续1的个数
 * 题目：https://leetcode-cn.com/problems/max-consecutive-ones/
 * 判断最大连续1的个数是多少
 */
public class Leetcode485_FindMaxConsecutiveOnes {
    /**
     * 采用滑动窗口实现
     */
    /*public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            if (nums[r] != 1) {
                res = Math.max(res, r - l);
                // 需要跳过当前
                r++;
                l = r;
            } else {
                res = Math.max(res, r - l + 1);
                r++;
            }
        }
        return res;
    }*/

    /**
     * 直接通过1的值判断会比较简单
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int cnt = 0;
        for (int num : nums) {
            if (num == 1) {
                cnt++;
            } else {
                // 表示遇到了0,此时计算结果,并实现对cnt的重置操作
                res = Math.max(res, cnt);
                cnt = 0;
            }
        }
        // 最后结束之前需要单独判断一次，可能最大的出现在最后
        res = Math.max(res, cnt);
        return res;
    }

}
