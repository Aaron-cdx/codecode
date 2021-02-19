package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/19 8:55
 * @Motto Keep thinking, keep coding!
 * leetcode 1004、最大连续1的个数III
 * 题目：https://leetcode-cn.com/problems/max-consecutive-ones-iii/
 */
public class Leetcode1004_LongestOnes {
    public static void main(String[] args) {
        Leetcode1004_LongestOnes test = new Leetcode1004_LongestOnes();
        System.out.println(test.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(test.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }

    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int right = 0;
        int left = 0;
        // 如果是遇到0，此时K<=0的话，此时需要让左边界跳过第一个0然后开始计数，否则得不到最大的值
        int max = 0;
        while (right < n) {
            if (A[right] == 1) {
                right++;
                continue;
            }
            max = Math.max(max, right - left);
            // 否则为0
            if (K > 0) {
                K--;
                right++;
            } else {
                // 否则K<=0，此时就会进入到左边界的收缩
                while (A[left] == 1) {// 遇到第一个0的时候
                    left++;
                }
                left++;// 左边界跳过第一个0，此时K++
                K++;
            }
        }
        max = Math.max(max, right - left);
        return max;
    }
}
