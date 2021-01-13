package com.code.leetcode.leetcode2021.January;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2021/1/13 9:33
 * @Motto Keep thinking, keep coding!
 * leetcode 684、冗余连接
 * 题目：https://leetcode-cn.com/problems/redundant-connection/
 * 找出无向图中的一条边，去除此边使得当前的数保持n个节点。如果有多条边随意返回一个，边可能为空
 * 边为空的情况即表示所有的点都没有重合
 */
public class Leetcode684_FindRedundantConnection {
    public static void main(String[] args) {
        Leetcode684_FindRedundantConnection test = new Leetcode684_FindRedundantConnection();
//        System.out.println(Arrays.toString(test.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
        System.out.println(Arrays.toString(test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}})));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        // 并查集初始化
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int x = edge[0];
            int y = edge[1];
            // 如果不等，进行合并操作
            if (find(parent, x) != find(parent, y)) {
                union(parent, x, y);
            } else {
                // 否则返回这条边
                return edge;
            }
        }
        return new int[]{};
    }

    public void union(int[] parent, int x, int y) {
        // 合并的话此时是将两个index查找的父节点做比对
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX == rootY) {
            // 如果两者相等，即具有相同的初始父节点，直接结束合并操作
            return;
        }
        // 否则则对父节点进行设置
        parent[rootX] = rootY;
    }

    /**
     * 寻找当前节点的父节点
     * 如果自己等于自己则终止，否则自己的父节点为自己的父节点的父节点
     */
    public int find(int[] parent, int index) {
        // 如果找到了最后的自己则直接返回
        if (index != parent[index]) {
            // 否则需要继续查找自己的父节点,继续向上溯源
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
