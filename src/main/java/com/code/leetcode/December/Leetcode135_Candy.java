package com.code.leetcode.December;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/12/24 9:19
 * @Motto Keep thinking, keep coding!
 * leetcode 135、分发糖果
 * 题目：https://leetcode-cn.com/problems/candy/
 * 题目意思是每个孩子至少分到一个糖果，但是最大分值的孩子必须分到最多的糖果
 * 1 0 2 => 如果0获得1个，则1一定2个，1两个的话则2在0后面则必定两个
 * 两个条件：
 * 1. 每个孩子至少得到1个糖果
 * 2. 相邻的孩子中，评分高的孩子必须获得更多的糖果
 */
public class Leetcode135_Candy {
    public static void main(String[] args) {
        Leetcode135_Candy test = new Leetcode135_Candy();
//        System.out.println(test.candy(new int[]{0, 1, 2}));
//        System.out.println(test.candy(new int[]{1, 0, 2}));
//        System.out.println(test.candy(new int[]{1, 2, 2}));
//        System.out.println(test.candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
        System.out.println(test.candy(new int[]{1, 3, 5, 3, 2, 1}));
    }

    /**
     * 时间O(n)
     * 空间O(n)
     */
    /*public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        Arrays.fill(dp, 1);
        // 从前往后
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        // 从后往前，为了兼容从前往后，所以用max
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                dp[i] = Math.max(dp[i], dp[i + 1] + 1);
            }
        }
        int sum = 0;
        for (int i : dp) {
            sum += i;
        }
        return sum;
    }*/
    public int candy(int[] ratings) {
        int ret = 1;
        int n = ratings.length;
        int dec = 0, inc = 1, pre = 1;
        for (int i = 1; i < n; i++) {
            // 大于等于
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0; // 递减长度归0
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1; // 表示如果相等，实质自己不变，如果大于的话需要+1
                ret += pre;
                inc = pre; // 递增序列长度
            } else {
                // 递减在某种程度上也是递增，但是在交界处的话，递减会导致连环效应，会让递增的最后一个+1
                // 否则就是小于需要递减了
                dec++;
                // 这是因为如果递增和递减正好一样，则正好处于交界处，由于当前递减的存在会导致递增的那一个新增1
                if (dec == inc) {   // 这一步就是为了把递增的1弥补回来
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}
