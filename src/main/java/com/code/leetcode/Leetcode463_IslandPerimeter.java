package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/30 8:55
 * @Motto Keep thinking, keep coding!
 * leetcode 463、岛屿的周长
 * 题目：https://leetcode-cn.com/problems/island-perimeter/
 */
public class Leetcode463_IslandPerimeter {
    /**
     * 利用深度优先搜索来实现
     */
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    /**
     * dfs最直观的解释就是当前的一个岛屿流向的问题
     * 如果是岛屿流向水域+1
     * 如果是岛屿流向边界+1
     * 如果不是的话以当前的岛屿为开始开始递归操作，求出后续的岛屿边界问题
     */
    private int dfs(int[][] grid, int row, int col) {
        // 走向边界的话此时要多加1
        if (!(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length)) {
            return 1;
        }
        if (grid[row][col] == 0) {
            return 1;
        }
        if (grid[row][col] != 1) {
            return 0;
        }
        grid[row][col] = 2;
        return dfs(grid, row - 1, col)
                + dfs(grid, row + 1, col)
                + dfs(grid, row, col - 1)
                + dfs(grid, row, col + 1);
    }


    /**
     * 只要有相邻的方格就-2
     * 最直观的解法
     */
    public int islandPerimeterII(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 如果当前值是1，则要看上和前是否有岛屿
                if (grid[i][j] == 1) {
                    res += 4;
                    // 这里主要是当前的与上面的占据相同的边直接就减去2即可
                    if (i > 0 && grid[i - 1][j] == 1) res -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) res -= 2;
                }
            }
        }
        return res;
    }
}
