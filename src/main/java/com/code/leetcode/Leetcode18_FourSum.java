package com.code.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/10/5 9:27
 * @Motto Keep thinking, keep coding!
 * leetcode 18、四数之和
 */
public class Leetcode18_FourSum {
    public static void main(String[] args) {
        Leetcode18_FourSum test = new Leetcode18_FourSum();
        System.out.println(test.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(test.fourSum(new int[]{-2, -1, -1, 1, 1, 2, 2}, 0));
        // -2 -1 0 0 1 2
        // -2,-1,-1,1,1,2,2

    }

    /**
     * 思路：
     *      具体的思路就是通过先通过遍历的方式，将最前面两个值固定
     *      然后再通过二分查找的方法来解决问题，注意边界条件值的判定以及重复结果的跳过
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // 先进行一个快速排序
        Arrays.sort(nums);
        // 双指针,同一个数字不可以使用两次，否则会造成重复的结果出现
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < nums.length - 1; j++) {
                // 这里如果直接使用j-1会直接覆盖此前的值，造成结果错误
                // 所以这里需要使用j-1>i防止此时跳过答案
                if (j - 1 > i && nums[j - 1] == nums[j]) continue;
                // 这里使用双指针
                int l3 = j + 1;
                int l4 = nums.length - 1;
                int sum = target - nums[i] - nums[j];
                while (l3 < l4) {
                    if (nums[l3] + nums[l4] > sum) {
                        l4--;
                    } else if (nums[l3] + nums[l4] < sum) {
                        l3++;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l3], nums[l4]));
                        // 这里先进行去重，然后再进行++和--操作
                        while (l3 < l4 && nums[l3 + 1] == nums[l3]) {
                            l3++;
                        }
                        while (l3 < l4 && nums[l4 - 1] == nums[l4]) {
                            l4--;
                        }
                        l3++;
                        l4--;
                    }
                }
            }
        }
        return res;
    }
}
