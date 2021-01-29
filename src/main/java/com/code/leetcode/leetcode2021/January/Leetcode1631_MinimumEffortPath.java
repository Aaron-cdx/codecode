package com.code.leetcode.leetcode2021.January;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/1/29 9:45
 * @Motto Keep thinking, keep coding!
 * leetcode 1631、最小体力消耗路径
 * 题目：https://leetcode-cn.com/problems/path-with-minimum-effort/
 */
public class Leetcode1631_MinimumEffortPath {
    public static void main(String[] args) {
        Leetcode1631_MinimumEffortPath test = new Leetcode1631_MinimumEffortPath();
        System.out.println(test.minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}}));
    }

    /**
     * Dijkstra算法求最短路径
     */
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        queue.offer(new int[]{0, 0, 0});
        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];
        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            int x = edge[0], y = edge[1], v = edge[2];
            int idx = x * n + y;
            if (seen[idx]) {
                continue;
            }
            // 判断是否到了最后
            if (x == m - 1 && y == n - 1) break;
            // 否则表示访问过
            seen[idx] = true;
            // 按照四个方向开始查找
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                        && Math.max(v,Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(v, Math.abs(heights[x][y] - heights[nx][ny]));
                    queue.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }
        return dist[m * n - 1];
    }

    /**
     * 利用并查集算法
     */
    /*public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                // 表示是第一行之外的
                if (i > 0) {
                    // 这里是为了将之前的下标重置为0开始
                    edges.add(new int[]{idx - n, idx, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    // 这是为了让j从0开始！行-1的话则要减去n个元素，列-1的话直接减去1个就好
                    edges.add(new int[]{idx - 1, idx, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        // 给边排序，按照权值大小排序
        edges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        NewUnionFind unionFind = new NewUnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            // 合并x，y的时候判断0和m*n-1即左上角和右下角是否可以连接起来，如果可以连接起来直接返回就好了
            unionFind.unite(x, y);
            if (unionFind.connected(0, m * n - 1)) {
                ans = v;
                return ans;
            }
        }
        return 0;
    }*/

    /**
     * 广度优先遍历+二分查找
     */


   /* public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0;
        int right = 999999;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n
                            && !seen[nx * n + ny]
                            && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                // 即到达了最后一个的位置
                ans = mid;
                right = mid - 1;// 则最大的值应该在前面就出现了
            } else {
                // 否则继续向后进行查找
                left = mid + 1;
            }
        }
        return ans;
    }*/

    /**
     * 利用dfs深度优先搜索来解决问题
     * 高度最大差即为耗费的体力值，即高度最大差最小的路径即为最小体力消耗路径
     */
//    public int dfs(int row, int rows, int col, int cols, boolean[][] visited, int[][] heights, int preHeight, int maxHeight) {
//        if ((row == rows && col == cols) || (row < 0 || col < 0)) return maxHeight;
//        int up = 0;
//        int down = 0;
//        int left = 0;
//        int right = 0;
//        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col]) {
//            visited[row][col] = true;
//            maxHeight = Math.max(maxHeight, heights[row][col] - preHeight);
//            // 上下左右
//            up = dfs(row - 1, rows, col, cols, visited, heights, heights[row][col], maxHeight);
//            down = dfs(row + 1, rows, col, cols, visited, heights, heights[row][col], maxHeight);
//            left = dfs(row, rows, col - 1, cols, visited, heights, heights[row][col], maxHeight);
//            right = dfs(row, rows, col + 1, cols, visited, heights, heights[row][col], maxHeight);
//            visited[row][col] = false;
//        }
//        return Math.min(Math.min(up, down), Math.min(left, right));
//    }
    private int getMin(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
}

// 并查集模板
class NewUnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public NewUnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }

    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }

    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }
}
