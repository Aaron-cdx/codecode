package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/21 10:51
 * @Motto Keep thinking, keep coding!
 * leetcode 1489、找到最小生成树里的关键边和伪关键边
 * 题目：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 * 找到关键边和伪关键边
 * 关键边即所有最小生成树中必出现的
 * 伪关键边则是非所有最小生成树出现的边
 */
public class Leetcode1489_FindCriticalAndPseudoCriticalEdges {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        // 这里最后一个位置[m][3]存放下标大小
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 按照权重来排序
                return o1[2] - o2[2];
            }
        });
        // 构建并查集开始计算value权值大小
        UnionFindTwo unionFind = new UnionFindTwo(n);
        int value = 0;// 权值大小
        for (int i = 0; i < m; i++) {
            // 看看自己的from to是否可以合并，可以合并计入权值
            // 这里在构造最小生成树
            if(unionFind.unite(newEdges[i][0],newEdges[i][1])){
                value += newEdges[i][2];
            }
        }
        // 计算完之后，此时需要计算结果了
        List<List<Integer>> ans = new ArrayList<>();
        // 初始化关键边和伪关键边的集合
        for (int i = 0; i < 2; i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            // 判断是否是关键边，去掉关键边总的权重值会增加
            UnionFindTwo findTwo = new UnionFindTwo(n);
            int v = 0;// 即不把当前的边的权重值加入进去
            for (int j = 0; j < m; j++) {
                if(i != j && findTwo.unite(newEdges[j][0],newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            // 如果没有这条边无法连通，或者连通这条边之后的权值大于最小生成树权值
            if(findTwo.sizeCount != 1 || (findTwo.sizeCount == 1 && v > value)){
                ans.get(0).add(newEdges[i][3]);
                continue;
            }
            // 判断是否是伪关键边
            findTwo = new UnionFindTwo(n);
            findTwo.unite(newEdges[i][0],newEdges[i][1]);
            v = newEdges[i][2];// 关键边的话则表示权重值包括当前，结果仍旧为最小生成树的值
            for (int j = 0; j < m; j++) {
                if(i != j && findTwo.unite(newEdges[j][0],newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(v == value){
                ans.get(1).add(newEdges[i][3]);
            }
        }
        return ans;
    }

}

class UnionFindTwo {
    int[] parent;
    int[] rank;
    //    int n;
    // 当前联通分量数目
    int sizeCount = 0;

    public UnionFindTwo(int n) {
        this.parent = new int[n];
        this.sizeCount = n;
        this.rank = new int[n];
        Arrays.fill(rank, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int findSet(int index) {
        return parent[index] == index ? parent[index] : findSet(parent[index]);
    }

    public boolean unite(int x, int y){
        int rootX = findSet(x);
        int rootY = findSet(y);
        if(rootX == rootY) return false;
        if(rank[rootX] < rank[rootY]){
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        rank[rootX]+=rank[rootY];
        // 发生合并的话联通分量就减一
        --sizeCount;
        return true;
    }
    // 判断是否联通
    public boolean connected(int x, int y){
        x = findSet(x);
        y = findSet(y);
        return x == y;
    }
}
