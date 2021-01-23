package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/23 9:57
 * @Motto Keep thinking, keep coding!
 * leetcode 1319、连通网络的操作次数
 * 题目：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/
 * 即将所有的网络通过最少的连接次数连接
 */
public class Leetcode1319_MakeConnected {
    public static void main(String[] args) {
        Leetcode1319_MakeConnected test = new Leetcode1319_MakeConnected();
        System.out.println(test.makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println(test.makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println(test.makeConnected(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}));
    }

    boolean[] visited;
    List<Integer>[] edges;

    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (len < n - 1) return -1;
        // 初始化
        visited = new boolean[n];
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        // 开始将互通的边都添加进去
        for (int[] connection : connections) {
            int x1 = connection[0];
            int x2 = connection[1];
            edges[x1].add(x2);
            edges[x2].add(x1);
        }
        // 开始遍历获取联通分量
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }
        // 减去最初的一个衔接所有的联通分量
        return cnt - 1;
    }

    public void dfs(int index) {
        visited[index] = true;
        // 对当前连接的所有边的分量做处理
        for (int edge : edges[index]) {
            if(!visited[edge]){
                dfs(edge);
            }
        }
    }

    /**
     * 并查集版本
     * 每联通一次则联通分量少1
     */
    public int makeConnected_1(int n, int[][] connections) {
        int len = connections.length;
        // 如果线缆少于n-1则不可能联通所有机器
        if (len < n - 1) return -1;
        UnionMachine unionSet = new UnionMachine(n);
        for (int[] connection : connections) {
            int x = connection[0];
            int y = connection[1];
            unionSet.union(x, y);
        }
        // 联通分量比实际分量要少1
        return unionSet.sizeCount - 1;
    }
}

class UnionMachine {
    int[] parent;
    int[] rank;
    // 表示联通分量
    int sizeCount;

    public UnionMachine(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
        this.sizeCount = n;
    }

    public int find(int index) {
        if (index != parent[index]) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;
        // 判断秩大小，然后合并，统一采用x合并y
        if (rank[rootX] < rank[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        this.sizeCount--;
    }
}
