package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/29 9:11
 * @Motto Keep thinking, keep coding!
 * leetcode 330、按要求补齐数组
 * 题目：https://leetcode-cn.com/problems/patching-array/
 * 补齐数组使得从1-n的数都可以使用数组内元素表示。
 */
public class Leetcode330_MinPatches {
    /**
     * 原理的话为如果要表示当前数，则需要展示出其之前所有的因子
     * 1 2 4 6 => 8
     * 1 2 4 8 => 16
     * 所有数字的表示只要前面包含其所有的因子即可
     * 如果数字 1 2 4 5 10 => 20
     * 1 5 10 => 20 需要补充 2 4
     * 从1开始，如果小于等于自己可以将其加上往后，如果是大于自己，则表明一定缺少因子，需要累加！
     */
    public int minPatches(int[] nums, int n) {
        // 可以覆盖更多的元素 1 2 4 => [1,8)
        int index = 0;
        int count = 0;
        long add = 1;
        // 模拟[1,1]=>[1,8]
        while(add <= n){
            if(index < nums.length && nums[index] <= add){
                add += nums[index++];// 能够表示的话，则add开始进行扩容[add,add+nums[i]]
            }else{
                // 否则表示无法扩容，此时扩成两倍
                add += add;
                count++;
            }
        }
        return count;
    }
}
