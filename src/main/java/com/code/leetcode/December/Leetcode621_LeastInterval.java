package com.code.leetcode.December;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/12/5 22:22
 * @Motto Keep thinking, keep coding!
 * leetcode 621、任务调度器
 * 即每次运行任务需要交替运行，n为冷却时间的长度，表示两个相同种类的一起执行需要有长度为n的冷却时间
 * ABCDA可以
 * ABCA可以
 * AB => 冷却 => A才可以
 */
public class Leetcode621_LeastInterval {
    public int leastInterval(char[] tasks, int n) {
        if (tasks.length <= 1 || n == 1) return tasks.length;
        // 构建数组存储任务
        int[] counts = new int[26];
        for (char task : tasks) {
            counts[task - 'A']++;
        }
        // 排序找最大值出现的位置
        Arrays.sort(counts);
        int maxCount = counts[25];
        // 这里用最大来，小的都去填空就完事了。
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        return Math.max(retCount, tasks.length);
    }
}
