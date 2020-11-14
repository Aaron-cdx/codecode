package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/14 9:38
 * @Motto Keep thinking, keep coding!
 * leetcode 1122、数组的相对排序
 * 题目：https://leetcode-cn.com/problems/relative-sort-array/
 */
public class Leetcode1122_RelativeSortArray {
    public static void main(String[] args) {
        Leetcode1122_RelativeSortArray test = new Leetcode1122_RelativeSortArray();
        int[] res = test.relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6});
        System.out.println(res);
    }

    /**
     * 看似是桶排序，实质为计数排序。
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 不进行边界判断了
        // 获取最大值进行桶的建立
        int max = Integer.MIN_VALUE;
        for (int i : arr1) {
            max = Math.max(i, max);
        }
        // 利用桶来装，实质为计数排序
        int[] bucket = new int[max + 1];
        for (int i : arr1) {
            bucket[i] += 1;
        }
        // 开始对存在arr2中的元素进行一个arr1的按照顺序的排序
        int index = 0;
        for (int i : arr2) {
            while (bucket[i] > 0) {
                arr1[index++] = i;
                bucket[i] -= 1;
            }
        }
        // 对于arr2中没有出现的数字，在桶中这个是默认升序的，所以在走一趟即可
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                arr1[index++] = i;
                bucket[i]--;
            }
        }
        return arr1;
    }
}
