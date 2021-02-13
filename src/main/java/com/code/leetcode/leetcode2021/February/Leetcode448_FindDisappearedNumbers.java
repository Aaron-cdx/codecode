package com.code.leetcode.leetcode2021.February;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/2/13 9:04
 * @Motto Keep thinking, keep coding!
 * leetcode 448、找到所有数组中消失的数字
 * 题目：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 * 即找到[1,n]之间缺少的所有的数字
 * easy
 */
public class Leetcode448_FindDisappearedNumbers {
    /**
     * 不借助额外的空间结构来实现这个问题
     * 1. 使用额外空间解决
     */
    /*public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // 不能够通过排序解决这个问题
        int[] counters = new int[nums.length];
        // n为数组大小
        for (int num : nums) {
            counters[num - 1]++;
        }
        for (int i = 0; i < counters.length; i++) {
            if(counters[i] == 0) res.add(i+1);
        }
        return res;
    }*/

    /**
     * 2. 不使用额外空间
     * 空间复杂度O(n)实际上是限制了使用排序的方法，
     * 因为快速排序时间复杂度为O(nlogn)
     * 希望原地修改数组的方式解决这个问题
     * 原地修改的想法是因为整个数组一定是满足n个，所以只需要对那些存在的数位置进行处理即可
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        // 这里是对存在的数的位置进行处理，遍历完之后，存在的数的位置及发生了变化只有那些不存在的
        // 数的位置上才不会发生变化，数值一定是小于等于n的。其余的都是大于n的
        for (int num : nums) {
            // (num-1)%n表示找到具体的位置
            nums[(num - 1) % n] += n;
        }
        for (int i = 0; i < n; i++) {
            // 这里小于等于n是因为数组中的数字均是[1,n]的，即最小的就是1了，所以无论如何都是会大于n的
            if(nums[i] <= n) res.add(i+1);
        }
        return res;
    }
}
