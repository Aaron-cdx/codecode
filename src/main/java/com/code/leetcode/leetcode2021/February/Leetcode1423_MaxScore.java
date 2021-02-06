package com.code.leetcode.leetcode2021.February;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/2/6 9:33
 * @Motto Keep thinking, keep coding!
 * leetcode 1423、可获得的最大点数
 * 题目：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/
 * 思想：通过移除最小的点数从而反向获取可以获得的最大的点数，妙啊！
 */
public class Leetcode1423_MaxScore {
    public static void main(String[] args) {
        Leetcode1423_MaxScore test = new Leetcode1423_MaxScore();
        System.out.println(test.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 6));
    }

    /**
     * 这里要获得最大的点数，则就意味着需要移除最小的点数即可
     * 构建n-k大小的窗口，窗口移动过程中能够获得的最大值即为最大点数
     */
    /*public int maxScore(int[] cardPoints, int k) {
        int left = 0;
        int right = 0;
        int n = cardPoints.length;
        int sum = Arrays.stream(cardPoints).sum();
        if (n == k) return sum;
        int maxPoint = Integer.MIN_VALUE;
        int tempSum = 0;
        while (right < n) {
            // 这里表示需要移除的元素
            tempSum += cardPoints[right];
            // 到了具体的位置的时候，此时左边收缩，右边继续前进
            if (right - left + 1 == n - k) {
                // 计算完然后前进即可
                maxPoint = Math.max(maxPoint, sum - tempSum);
                tempSum -= cardPoints[left++];
            }
            right++;
        }
        return maxPoint;
    }*/

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int len = n-k;
        int sum = Arrays.stream(cardPoints).sum();
        if(len == 0) return sum;
        int minSum = 0;
        // 直接固定窗口的高度大小
        for (int i = 0; i < len; i++) {
            minSum += cardPoints[i];
        }
        int min = minSum;
        // 进出获取最小的可移除项目即可
        for (int i = len; i < n; i++) {
            minSum += cardPoints[i] - cardPoints[i-len];
            min = Math.min(minSum,min);
        }
        return sum - min;
    }
}
