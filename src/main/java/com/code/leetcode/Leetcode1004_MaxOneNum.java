package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/8 14:17
 * leetcode 1004、最大连续1的个数
 */
public class Leetcode1004_MaxOneNum {
    public static void main(String[] args) {
        Leetcode1004_MaxOneNum test = new Leetcode1004_MaxOneNum();
//        System.out.println(test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
//        System.out.println(test.findMaxConsecutiveOnes(new int[]{1, 0, 0, 0, 0, 0}));
        System.out.println(test.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(test.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    /**
     * 找寻最大连续1的个数
     * [1,1,0,1,1,1] => 3
     * leetcode 485 https://leetcode-cn.com/problems/max-consecutive-ones/
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cnt += 1;
            } else {
                max = Math.max(cnt, max);
                cnt = 0;
            }
        }
        // 如果所有的都是连续的1，此时一定要比较一次cnt，因为他会一直累加
        max = Math.max(cnt, max);
        return max;
    }

    /**
     * 最大连续1的个数，此时可以将k的1用来补充期间遇到的0，作为连续的保证
     * 返回最长的1的连续长度
     * A = [1,1,1,0,0,0,1,1,1,1,0], K = 2  ===> 6  把 后面 0->1 1 1 1 1 1<-0 则输出6个
     * <p>
     * 思路的话一直走，滑动窗口移动的依据是按照k的个数来的，如果K用完了，且再次遇到0，此时结束状态
     */
    private int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;
        while (r < A.length) {
            if (A[r] == 0) {
                if (K > 0) {
                    K -= 1;
                    r++;
                } else {
                    max = Math.max(r - l, max);
                    // 如果之前本来就是0的话，此时需要将这个0还给它
                    if (A[l] == 0) {
                        K += 1;
                    }
                    // 这里r需要保持不变
                    l++;
                }
            } else {
                // 否则就是它为1的情况，为1的话此时直接前进
                r++;
            }
        }
        max = Math.max(r - l, max);
        return max;
    }
}
