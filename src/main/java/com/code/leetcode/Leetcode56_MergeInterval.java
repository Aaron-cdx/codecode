package com.code.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author caoduanxi
 * @Date 2020/9/9 10:14
 * <p>
 * leetcode 56、合并区间
 * 给出一个区间的集合，请合并所有重叠的区间
 * [[1,3],[2,6],[8,10],[15,18]]
 * 标签：排序、数组
 */
public class Leetcode56_MergeInterval {
    public static void main(String[] args) {
        Leetcode56_MergeInterval test = new Leetcode56_MergeInterval();
        int[][] merge1 = test.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        int[][] merge = test.merge(new int[][]{{1, 4}, {1, 4}});
        int[][] merge2 = test.merge(new int[][]{{1, 4}, {5, 6}});
        System.out.println(Arrays.deepToString(merge1));
        System.out.println(Arrays.deepToString(merge));
        System.out.println(Arrays.deepToString(merge2));
    }

    /**
     * 第一种方法尝试成功，通过区间人为合并，不过代码有点繁琐
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[][]{};
        if (intervals.length == 1) return intervals;
        // 注意有区间重叠才可合并，否则不可合并[1,3][4,6]这里没有任何的重叠
        // 需要定义什么叫做重叠，首先如果前一个第一个数和第二个数都小于后面的第一个数，则不能合并
        // 否则如果第二个数大于后面的第一个数则可以选择合并。
        int[][] res = new int[intervals.length][intervals[0].length];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        int cnt = 0;
        res[cnt++] = intervals[0];// 使用res来保存原始区间，然后合并
        for (int i = 1; i < intervals.length; i++) {
            int[] interval1 = res[cnt - 1];
            int[] interval2 = intervals[i];
            // 如果不能合并
            if (interval1[0] < interval2[0] && interval1[1] < interval2[0]) {
                res[cnt++] = intervals[i];
                continue;
            }
            // 如果可以合并
            if (interval1[0] <= interval2[0] && interval1[1] <= interval2[1]) {
                res[--cnt] = new int[]{interval1[0], interval2[1]};// 此时需要替换intervals[i-1]的区间，防止后面不能合并
                intervals[i] = res[cnt];// 这里需要填充合并后的区间
                cnt++;
            }
        }
        return Arrays.copyOf(res, cnt);// 拷贝特定长度
    }

    /**
     * 第二种解法：参照题解
     */
    public int[][] mergeII(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            // 后面的起始区间都大于前面的结束区间，表明不能合并
            if (index == -1 || interval[0] > res[index][1]) {
                // 这里包含最初的填充即index=-1时的样子
                res[++index] = interval;
            } else {
                // 否则表示可以合并
                // 利用前面一个的区间，把后面的区间填充，但是同时需要判断谁更大[1,5][1,4]此时需要合并为[1,5]
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
}
