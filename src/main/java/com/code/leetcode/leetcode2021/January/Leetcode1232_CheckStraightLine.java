package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/17 9:12
 * @Motto Keep thinking, keep coding!
 * leetcode 1232、缀点成线
 * 题目：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/
 */
public class Leetcode1232_CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        if (len <= 2) return true;
        // 否则获取斜率
        int dx = coordinates[1][0] - coordinates[0][0];
        int dy = coordinates[1][1] - coordinates[0][1];
        // 将斜率转化为乘法dy/dx = (dy')/(dx') => dy*dx' = dx*dy'
        for (int i = 2; i < len; i++) {
            if (dy * (coordinates[i][0] - coordinates[0][0])
                    != dx * (coordinates[i][1] - coordinates[0][1])) {
                return false;
            }
        }
        return true;
    }

    // [[0,1],[2,4],[3,3]]
    // 如果是遇到(0,1),(0,0),(-1,0)这个时候是在一条直线上
    private double getDelta(int[] c1, int[] c2) {
        if (c2[0] - c1[0] == 0) return 0;
        return ((c2[1] - c1[1]) / (c2[0] - c1[0]));
    }
}
