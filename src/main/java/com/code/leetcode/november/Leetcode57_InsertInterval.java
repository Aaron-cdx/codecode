package com.code.leetcode.november;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/11/4 22:19
 * @Motto Keep thinking, keep coding!
 * leetcode 57、插入区间
 * 题目：https://leetcode-cn.com/problems/insert-interval/
 * 类似于区间合并的感觉，区间合并只需要考虑存在的区间即可，而插入区间的话
 * 需要先考虑区间的插入，然后再进行区间的合并操作。
 */
public class Leetcode57_InsertInterval {
    /**
     * 整体的思路就是：定义左右的变量
     * 如果遍历到区间，看能否合并，不能合并的话看能够将当前区间插入，看插入位置在前面还是后面
     * 主要有三种情况：
     * 1. 区间前面大于right，此时就将newInterval插入在前面，并标注已插入
     * 2. 区间后面小于left，此时表示interval元素插入在前面，newInterval没有找到位置插入
     * 3. 表示区间需要合并，此时找到前后的值，并将left、right更新
     * 注意可能存在一直在合并区间导致没有进行插入的操作
     * 所以最后需要判断一下是否进行了插入的操作，如果没有就进行插入操作结束
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if(interval[0] > right){
                // 如果左边都大于右边则吧当前值插在前面了
                // 这是还没放入的前提，放入了就算了
                if(!placed){
                    ansList.add(new int[]{left,right});
                    placed = true;
                }
                ansList.add(interval);
                // 如果右边都小于人家的左边
            }else if(interval[1] < left){
                // 表示还没有找到位置插入
                ansList.add(interval);
            }else{
                // 否则的话肯定是要进行区间合并操作
                left = Math.min(interval[0],left);
                right = Math.max(interval[1],right);
            }
        }

        // 最后还是要判断当前的数是否放置了
        if(!placed){
            // 表示一直在合并
            ansList.add(new int[]{left,right});
        }
        int[][] res = new int[ansList.size()][];
        for (int i = 0; i < ansList.size(); i++) {
            res[i] = ansList.get(i);
        }
        return res;
    }
}
