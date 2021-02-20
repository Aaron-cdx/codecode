package com.code.leetcode.leetcode2021.February;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2021/2/20 9:56
 * @Motto Keep thinking, keep coding!
 * leetcode 697、数组的度
 * 题目：https://leetcode-cn.com/problems/degree-of-an-array/
 * 数组的度就是数组中出现次数最多的某个数字的出现次数
 */
public class Leetcode697_FindShortestSubArray {
    /**
     * 找寻最短的度子数组，表示从出现次数最多的数组中找寻到最短的子数组
     * 输出它的长度
     */
    public int findShortestSubArray(int[] nums) {
        // 数组里面存放当前次数，开始开始下标，结束下标
        Map<Integer, int[]> map = new HashMap<>();
        // tmp[0][1][2] => 三个依次存放 个数，长度，上一次的下标
        for (int i = 0; i < nums.length; i++) {
            // 如果包含这个键的话，此时需要对键进行操作
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;// 只记录最后一次出现的下标
            } else {
                // 放入其中，并放入第一个坐标
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        // 遍历完之后，此时获取
        Set<Map.Entry<Integer, int[]>> entries = map.entrySet();
        int maxCnt = 0;
        int minLen = 0;
        // 获取次数最多的且长度最小的
        // 首先确保次数最多
        for (Map.Entry<Integer, int[]> entry : entries) {
            int[] value = entry.getValue();
            // 第一次赋值
            if (value[0] > maxCnt) {
                maxCnt = value[0];
                minLen = value[2] - value[1] + 1;
            }else if(value[0] == maxCnt){
                int len = value[2] - value[1] + 1;
                if(minLen > len){
                    minLen = len;
                }
            }
        }
        return minLen;
    }
}
