package com.code.leetcode.leetcode2021.February;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/2/16 9:41
 * @Motto Keep thinking, keep coding!
 * leetcode 561、数组拆分I
 * 题目：https://leetcode-cn.com/problems/array-partition-i/
 * 即将数组组成对子，获取组合对中最小值相加后的最大值为多少
 */
public class Leetcode561_ArrayPairSum {
    /**
     * 长度为2n，组成n对，判断每对中的最小值的相加值最大为多少
     * 这里有点类似博弈的思维，即小的与小的在一起，大的与大的在一起，
     * 如果小的与大的在一起就会损失大的，因为大的可能是大的里面的小的
     * <p>
     * 采用排序的方式可以解决
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);// 先排序，然后取值即可
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
