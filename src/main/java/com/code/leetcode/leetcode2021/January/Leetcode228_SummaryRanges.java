package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/10 9:23
 * @Motto Keep thinking, keep coding!
 * leetcode 228、汇总区间
 * 题目：https://leetcode-cn.com/problems/summary-ranges/
 */
public class Leetcode228_SummaryRanges {
    public static void main(String[] args) {
        Leetcode228_SummaryRanges test = new Leetcode228_SummaryRanges();
        System.out.println(test.summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(test.summaryRanges(new int[]{0,2,3,4,6,8,9}));
        System.out.println(test.summaryRanges(new int[]{0}));
    }
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums.length == 0) return res;
        int pre = 0;
        int post = 1;
        while(pre < nums.length){
            if(post < nums.length && nums[post] == nums[post-1]+1){
                post++;
            }else{
                if(post-1 == pre){
                    res.add(String.valueOf(nums[pre]));
                }else{
                    String str = nums[pre]+"->"+nums[post-1];
                    res.add(str);
                }
                pre = post;
                post += 1;
            }
        }
        return res;
    }
}
