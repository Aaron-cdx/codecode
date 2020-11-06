package com.code.leetcode.november;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author caoduanxi
 * @Date 2020/11/6 16:16
 * @Motto Keep thinking, keep coding!
 * leetcode 1356、根据数字二进制下1的数目排序
 * 题目：https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/
 */
public class Leetcode1356_SortByBitsHasOne {
    public static void main(String[] args) {
        Leetcode1356_SortByBitsHasOne test = new Leetcode1356_SortByBitsHasOne();
    }
    /**
     * 最简单的做法是对其中的1的数量进行排序，计算
     * 即组成对象进行排序，如果出现数目相等按照大小排序
     */
    public int[] sortByBits(int[] arr) {
        // 组成对象进行排序
        if(arr == null || arr.length == 0) return new int[]{};
        Integer[] nums = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }
        Arrays.sort(nums,(o1,o2)->{
            int bitCountA = getOne(o1);
            int bitCountB = getOne(o2);
            // 相同按原数，不同按位数
            return bitCountA == bitCountB ? o1 - o2 : bitCountA - bitCountB;
        });
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        return arr;
    }

    public int getOne(int num){
        int one = 0;
        while(num != 0){
            one += 1;
            num &= num-1;
        }
        return one;
    }
}
class Num implements Comparator<Num> {
    int value;
    int cnt;

    public Num(int value, int cnt) {
        this.value = value;
        this.cnt = cnt;
    }

    @Override
    public int compare(Num o1, Num o2) {
        if(o1.cnt > o2.cnt) return 1;
        if(o1.value > o2.value) return 1;
        return 0;
    }
}