package com.code.leetcode.leetcode2021.February;

/**
 * @author caoduanxi
 * @Date 2021/2/23 9:26
 * @Motto Keep thinking, keep coding!
 * leetcode 1052、爱生气的书店老板
 * 题目：https://leetcode-cn.com/problems/grumpy-bookstore-owner/
 */
public class Leetcode1052_MaxSatisfied {
    /**
     * 因为这里的选择是不生气的话就是0，此时就可以通过X作为滑动窗口
     * 向前滑动，滑动的时候，将左边的移除(即可能生气的移除)然后将右边的加入到其中
     * 整个过程获取到一个最大的值即可
     * <p>
     * 整个过程，先统计不会生气的满意度，然后对于不会生气的X区间，在此区间内的则算作可以挽回的。
     * 从头计算到结尾，获取最大可以挽回的满意度，最后得出总和即可
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0;
        // 先统计内部的如果不生气的时候的可以获取的满意量
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0) total += customers[i];
        }
        // 然后获取到一个可以挽回的满意量
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        // 最大的可以挽回的量
        int maxIncrease = increase;
        // 这里需要把老板生气的算作不可挽回
        for (int i = X; i < customers.length; i++) {
            // 表示窗口滑动
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            // 这里获取最大可以挽回的满意度
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }
}
