package com.code.leetcode.leetcode2021.February;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2021/2/1 9:56
 * @Motto Keep thinking, keep coding!
 * leetcode 888、公平的糖果交换
 * 题目：https://leetcode-cn.com/problems/fair-candy-swap/
 */
public class Leetcode888_FairCandySwap {
    public static void main(String[] args) {
        Leetcode888_FairCandySwap test = new Leetcode888_FairCandySwap();
        System.out.println(Arrays.toString(test.fairCandySwap(new int[]{1, 1}, new int[]{2, 2})));
    }
    /**
     * 暴力法时间复杂度过高O(n^2)
     * 能通过，但还耗时比较长
     */
    /*public int[] fairCandySwap(int[] A, int[] B) {
        // 两者必须交换糖果
        int aSum = 0;
        int bSum = 0;
        aSum = Arrays.stream(A).sum();
        bSum = Arrays.stream(B).sum();
        // 遍历的时候，[1,2,5] - [2,4] 交换5和4两者才会相等
        // 暴力遍历肯定不行O(n^2)时间复杂度过高
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if(aSum - A[i] + B[j] == bSum - B[j] + A[i]){
                    return new int[]{A[i],B[j]};
                }
            }
        }
        return new int[]{};
    }*/

    /**
     * 根据暴力法推出公式
     * x = y + (bSum - aSum) / 2
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int aSum = 0;
        int bSum = 0;
        aSum = Arrays.stream(A).sum();
        bSum = Arrays.stream(B).sum();
        // 不用暴力法也需要使用总和的值
        int delta = (aSum - bSum) / 2;
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }
        for (int num : B) {
            int x = num + delta;
            if(set.contains(x)){
                return new int[]{x,num};
            }
        }
        return new int[]{};
    }
}
