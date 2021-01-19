package com.code.leetcode.leetcode2021.January;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/1/19 10:27
 * @Motto Keep thinking, keep coding!
 * leetcode 1584、连接所有点最小连接费用
 * 题目：https://leetcode-cn.com/problems/min-cost-to-connect-all-points/
 * 即按照曼哈顿距离计算所有点连接起来的最小连接距离
 */
public class Leetcode1584_MinCostConnectPoints {

    /**
     * 第一种方法
     * 利用连接图的方式，将所有边的距离都结合起来，然后利用并查集的同一个父节点的问题
     * 利用秩合并的操作，合并秩更的，即可求得距离
     */
//    public int minCostConnectPoints(int[][] points) {
//        int n = points.length;
//        DisJoinSetUnion joinSetUnion = new DisJoinSetUnion(n);
//        List<Edge> edges = new ArrayList<>();
//        // 遍历所有点构建边
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                edges.add(new Edge(i, j, getDist(points, i, j)));
//            }
//        }
//        Collections.sort(edges, new Comparator<Edge>() {
//            @Override
//            public int compare(Edge o1, Edge o2) {
//                return o1.len - o2.len;
//            }
//        });
//        // 这里开始计算结果
//        int ret = 0;
//        int num = 1;
//        for (Edge edge : edges) {
//            int len = edge.len;
//            int x = edge.x;
//            int y = edge.y;
//            if(joinSetUnion.union(x,y)){
//                ret += len;
//                num++;
//                if(num == n) break;
//            }
//        }
//        return ret;
//    }
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n < 2) return 0;
        // 否则的话即求所有点到自己点最小的距离的那个点即可
        int checkPoint = 0;
        int distance = 0;
        int[] minCost = new int[n];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[0] = 0;
        // 这里获取的就是到每个点的最小的距离即可
        for (int i = 0; i < n; i++) {
            int minDist = Integer.MAX_VALUE;
            int temp = checkPoint;
            // 这里控制n-1是因为到了n-1个点其实就遍历完了，最后一个n-1位置的点不需要遍历求解
            for (int j = 0; j < n-1; j++) {
                // 如果等于0表示是最开始的一个或者是已经访问过
                if (minCost[j] > 0) {
                    // 获取到当前点最小的距离
                    // 最小距离为当前原始的距离，对比获取到的检查点和自己的最小距离
                    minCost[j] = Math.min(minCost[j], getDist(points, j, checkPoint));
                    // 判断是否需要更新最小距离
                    if (minCost[j] < minDist) {
                        minDist = minCost[j];
                        temp = j;// 需要记录下一个检查点，以达到寻找最小的一个距离的连续性
                    }
                }
            }
            // 这里找到了最小值就需要开始计算距离了
            distance += minDist;
            // 获取下一个最小点
            checkPoint = temp;
            minCost[checkPoint] = 0;
        }
        return distance;
    }

    public int getDist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }
}

class DisJoinSetUnion {
    int[] parent;
    int[] rank;

    public DisJoinSetUnion(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        // 这里需要比较秩的大小,如果两个是同一个父节点，则不用合并
        if (rootX == rootY) return false;
        // 如果y更大则交换y和x让x更大
        if (rank[rootY] > rank[rootX]) {
            // 如果x更大，表示x之前合并过其他的
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        // 否则x更大直接合并就好
        rank[rootX] += rank[rootY];
        parent[rootY] = rootX;
        return true;
    }

    public int find(int index) {
        if (index != parent[index]) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
}

// 构建边大小
class Edge {
    int x;
    int y;
    int len;

    public Edge(int x, int y, int len) {
        this.x = x;
        this.y = y;
        this.len = len;
    }
}
