package com.code.leetcode.leetcode2021.March;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/3/31 9:24
 * @Motto Keep thinking, keep coding!
 * leetcode 90、子集II
 * 题目：https://leetcode-cn.com/problems/subsets-ii/
 */
public class Leetcode90_SubsetWithDup {
    public static void main(String[] args) {
        Leetcode90_SubsetWithDup test = new Leetcode90_SubsetWithDup();
        int[] nums = new int[]{1, 2, 2};
//        int[] nums = new int[]{0};
        System.out.println(test.subsetsWithDup(nums));
//        test.subsetsWithDup(nums);
    }

    /**
     * 可以包含重复元素的子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backTrace(res, nums, 0, new ArrayList<>());
        return res;
    }

    /**
     * 回溯剪枝的话没办法到第一个元素
     */
    public void backTrace(List<List<Integer>> res, int[] nums, int index, List<Integer> list) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            // 这里不能包含相同的元素，如果是nums[i-1]==nums[i] continue
            // 是不能包含相同的子集，1,2会出现两次，这样的话会造成错误出现 2 2也会出现两次，这种情况要直接避免掉
            // 这里需要判断，如果元素相等，可能会造成子集重复的话，即
            // 如果在添加过程中与前一个元素相等没事，即i-index不止一个且num[i]=nums[i-1]这个意思是
            if (i - index > 0 && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            backTrace(res, nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
