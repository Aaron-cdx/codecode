package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/27 10:09
 * @Motto Keep thinking, keep coding!
 * leetcode 1579、保证图可完全遍历
 * 题目：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
 * 即三种类型的边保证Alice和Bob可以完全遍历的情况下最多可以移除的边数
 * 如果二者不能够完全遍历，直接返回-1
 */
public class Leetcode1579_MaxNumEdgesToRemove {
    /**
     * 这里的公共边两个人一起合并，如果出现两个人同时合并错误，则表示此边需要移除！
     * 后续的独占边则是采用的两个并查集按照不同的标准合并
     * 如果是Alice的，合并如果失败，则表示可以移除
     * 如果是Bob的，合并如果失败，则表示可以移除
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionEdge alice = new UnionEdge(n);
        UnionEdge bob = new UnionEdge(n);
        int ans = 0;
        // 为了让节点从0开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }
        // 先处理公共的边
        for (int[] edge : edges) {
            // 表示公共的边
            if (edge[0] == 3) {
                // 两个人分别进行合并，如果两个人合并都false，则表示之前连接过
                boolean a = alice.union(edge[1],edge[2]);
                boolean b = bob.union(edge[1],edge[2]);
                if( !a && !b){
                    ans++;
                }
            }
        }
        // 处理独占边
        for (int[] edge : edges) {
            // alice独占
            if (edge[0] == 1) {
                // 表示之前合并过
                if (!alice.union(edge[1], edge[2])) {
                    ++ans;
                }
            } else if (edge[0] == 2) {
                // bob独占
                if (!bob.union(edge[1], edge[2])) {
                    ++ans;
                }
            }
        }
        // 最后如果顺利的话分量应该是为1的即全部联通
        if (alice.sizeCount != 1 || bob.sizeCount != 1) {
            return -1;
        }
        return ans;
    }
}

/**
 * 并查集模板
 */
class UnionEdge {
    int[] parent;
    int[] rank;
    int sizeCount;

    public UnionEdge(int n) {
        parent = new int[n];
        rank = new int[n];
        sizeCount = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int index) {
        if (index != parent[index]) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        if (rootX < rootY) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        sizeCount--;
        return true;
    }
}
