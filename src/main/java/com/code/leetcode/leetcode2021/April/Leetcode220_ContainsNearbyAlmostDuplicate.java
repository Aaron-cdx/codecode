package com.code.leetcode.leetcode2021.April;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author caoduanxi
 * @Date 2021/4/23 9:10
 * @Motto Keep thinking, keep coding!
 * leetcode 220、存在重复元素
 * 题目：https://leetcode-cn.com/problems/contains-duplicate-iii/
 */
public class Leetcode220_ContainsNearbyAlmostDuplicate {
    /**
     * 需要保证abs(nums[i]-nums[j])<=t && abs(i-j) <= k
     * <p>
     * 因为这里情况可能为nums[i]-nums[j]<=t 或者 nums[j]-nums[i] <= t
     * nums[j] >= nums[i]-t
     * nums[j] <= nums[i]+t
     * <p>
     * 利用滑动窗口&二分
     */
    /*public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 如果少于两个数或者区间k小于0的话，直接返回false
        if (nums.length < 2 || k < 0) return false;
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long temp = treeSet.ceiling((long) nums[i] - (long) t);
            if (temp != null && temp <= ((long) nums[i] + (long) t)) {
                return true;
            }
            treeSet.add((long) nums[i]);
            if (treeSet.size() == k + 1) {
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }*/

    /**
     * 利用桶排序解决这个问题
     */
    private long size;

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 如果少于两个数或者区间k小于0的话，直接返回false
        if (nums.length < 2 || k < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        size = t + 1L;// 确保小于等于t的都被分入到同一个桶

        for (int i = 0; i < nums.length; i++) {
            long u = nums[i];
            long idx = getIdx(u);
            // 如果目标桶存在，则一定满足条件，因为size就是t的大小，能分到一个桶肯定是满足的
            if (map.containsKey(idx)) return true;
            // 否则表示当前桶不存在，此时检查相邻的桶
            long l = idx - 1, r = idx + 1;
            // 区间满足条件了，此时需要确保值满足条件
            if (map.containsKey(l) && u - map.get(l) <= t) return true;
            if (map.containsKey(r) && map.get(r) - u <= t) return true;
            // 否则建立桶
            map.put(idx, u); // 多个数在一个桶，只会保存最后一个入桶的数
            // 移除下标范围超出的桶的值
            if (i >= k) {
                map.remove(getIdx(nums[i - k]));
            }
        }
        return false;
    }

    private long getIdx(long u) {
        // u/size可以对正数直接求出桶的位置，但是对于负数来说u+1/size表示正常分割，
        // 但由于0号桶被使用了，此时需要在此基础上进行减一操作
        return u >= 0 ? u / size : ((u + 1) / size) - 1;
    }
}
