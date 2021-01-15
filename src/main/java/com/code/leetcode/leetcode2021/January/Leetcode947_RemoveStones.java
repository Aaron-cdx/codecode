package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/15 9:32
 * @Motto Keep thinking, keep coding!
 * leetcode 947、移除最多的同行或者同列石头
 * 题目：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/
 * 如果一个石头与其他石头同行同列，则可以移除。
 * 即一个并查集合并同行同列的石头，最后判断父节点是否为自己，如果不是则移除，最后获取最多可以移除的结果
 */
public class Leetcode947_RemoveStones {
    /**
     * 深度优先搜索查找
     */
    /*public int removeStones(int[][] stones) {
        int n = stones.length;
        if (n == 1) return 0;
        List<List<Integer>> edge = new ArrayList<>();
        // 构建一个图的方式
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    edge.get(i).add(j);
                }
            }
        }
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                num++;
                dfs(i, visited, edge);
            }
        }
        // num是不可移除的，类似于根节点
        return n - num;
    }

    // dfs是把那些行列有关联的都标为已经访问
    public void dfs(int index, boolean[] visited, List<List<Integer>> edge) {
        // 先置为true再说，因为已经统计了，如果不置为true会导致重复统计
        visited[index] = true;
        // 以之前查找的继续查找
        for (Integer y : edge.get(index)) {
            if (!visited[y]) {
                dfs(y, visited, edge);
            }
        }
    }*/
    /**
     * stones[x][y]表示一块石头
     * 深度优先遍历也可以解决这个问题，即每次以自己的行列去找，找到的就置为-1
     * 并查集查找
     */
    public int removeStones(int[][] stones) {
        int row = stones.length;
        if (row == 1) return 0;
        int[] parent = new int[row];
        for (int i = 0; i < row; i++) {
            parent[i] = i;
        }
        // 这里开始合并查找
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < row; j++) {
                // 获取两个石头去,两个石头必须行或者列在同一个上面，否则不合并
                if (parent[i] != parent[j]
                        && (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])) {
                    union(parent, i, j);
                }
            }
        }
        int removeStone = 0;
        for (int i = 0; i < row; i++) {
            if (parent[i] != i) {
                removeStone++;
            }
        }
        return removeStone;
    }
    // 合并操作
    public void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
    // 查找父节点操作
    private int find(int[] parent, int index) {
        if (index != parent[index]) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
