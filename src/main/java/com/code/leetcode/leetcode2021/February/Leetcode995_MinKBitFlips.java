package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/18 9:31
 * @Motto Keep thinking, keep coding!
 * leetcode 995、K连续位的最小翻转次数
 * 题目：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/
 */
public class Leetcode995_MinKBitFlips {
    public static void main(String[] args) {
        Leetcode995_MinKBitFlips test = new Leetcode995_MinKBitFlips();
//        System.out.println(test.minKBitFlips(new int[]{0,1,0},1));
        System.out.println(test.minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }
    /**
     * 设定K位翻转，0->1;1->0，判断在翻转的条件下是否可行
     * 差分数组解题
     */
    /*public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0;
        int revCnt = 0;
        for (int i = 0; i < n; i++) {
            revCnt += diff[i];
            // 这里的翻转表示+1操作，实际上并不翻转，只是对翻转做一个必要的加法
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) return -1;
                ++ans;
                ++revCnt;
                --diff[i + K];
            }
        }
        return ans;
    }*/

    /**
     * 利用滑动窗口解决问题
     */
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int ans = 0;
        int revCnt = 0;
        for (int i = 0; i < n; i++) {
            // 表示进行了翻转，此时还原
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                A[i - K] -= 2;
            }
            // 如果为0此时表示需要进行翻转
            if (A[i] == revCnt) {
                if (i + K > n) return -1;
                // 否则的话表示进行了翻转，此时revCnt进行翻转
                ++ans;
                revCnt ^= 1;// 表示进行了翻转
                A[i] += 2;// 利用+2进行是否翻转的判定
            }
        }
        return ans;
    }
}
