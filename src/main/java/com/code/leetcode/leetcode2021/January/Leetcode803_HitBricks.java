package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/16 9:20
 * @Motto Keep thinking, keep coding!
 * leetcode 803、打砖块
 * 题目：https://leetcode-cn.com/problems/bricks-falling-when-hit/
 * 打掉的位置砖块消失
 */
public class Leetcode803_HitBricks {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 将行列直接转换为具体的个数即可
        UnionFindSet unionFind = new UnionFindSet(rows * cols + 1);
        int[][] status = new int[rows][cols];
        // 对数组进行拷贝
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                status[i][j] = grid[i][j];
            }
        }
        // 对数组进行hit砖块的去除操作
        for (int i = 0; i < hits.length; i++) {
            status[hits[i][0]][hits[i][1]] = 0;
        }

        // 进行砖块的逆向合并操作
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                // 表示当前有砖块，不是被击碎的
                if (status[i][j] == 1) {
                    // 如果是在第一行的话
                    if (i == 0) {
                        // 这个意思是从最后一个合并到最前面一个(j依次遍历)
                        unionFind.union(rows * cols, i * cols + j);
                    }
                    // 如果上面一个为1
                    if (i > 0 && status[i - 1][j] == 1) {
                        unionFind.union(i * cols + j, (i - 1) * cols + j);
                    }
                    // 如果前面一个为1
                    if (j > 0 && status[i][j - 1] == 1) {
                        unionFind.union(i * cols + j, i * cols + j - 1);
                    }
                }
            }
        }
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] ret = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0];
            int c = hits[i][1];
            if (grid[r][c] == 0) continue;
            // 否则表示有砖块
            int prev = unionFind.size(rows * cols);
            if (r == 0) {
                // 如果是顶部的
                unionFind.union(c, rows * cols);
            }
            // 对上下左右进行判断
            for (int[] direction : directions) {
                // 上 右 下 左
                int dr = direction[0];
                int dc = direction[1];
                int nr = r + dr;
                int nc = c + dc;
                // 不越界
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (status[nr][nc] == 1) {
                        unionFind.union(r * cols + c, nr * cols + nc);
                    }
                }
            }
            int size = unionFind.size(rows * cols);
            ret[i] = Math.max(0, size - prev - 1);
            status[r][c] = 1;
        }
        return ret;
    }
}

class UnionFindSet {
    int[] parent;
    int[] size;

    public UnionFindSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
            // 同时需要维护size大小
            size[rootY] += size[rootX];
        }
    }

    public int size(int x) {
        return size[x];
    }
}
