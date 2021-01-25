package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/25 10:10
 * @Motto Keep thinking, keep coding!
 * leetcode 959、由斜杠划分区域
 * 题目：https://leetcode-cn.com/problems/regions-cut-by-slashes/
 * 根据/、\划分具体的区域，判断划分出来的区域个数有多少
 */
public class Leetcode959_RegionsBySlashes {
    public int regionsBySlashes(String[] grid) {
        // 首先将grid拆分成N*N个小的区域
        int N = grid.length;
        RegionUnion findSet = new RegionUnion(4 * N * N);
        // 开始遍历
        for (int i = 0; i < N; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                // 将index转化为一行的
                int index = 4 * (i * N + j);
                // 此处根据slash进行合并操作
                char c = chars[j];
                // 如果为/此时0-3/1-2合并
                if (c == '/') {
                    findSet.union(index, index + 3);
                    findSet.union(index + 1, index + 2);
                    // 如果为\，此时0-1/2-3合并
                } else if (c == '\\') {
                    findSet.union(index, index + 1);
                    findSet.union(index + 2, index + 3);
                } else {
                    // 否则只能为空格''，此时0-1-2-3一起合并
                    findSet.union(index, index + 1);
                    findSet.union(index + 1, index + 2);
                    findSet.union(index + 2, index + 3);
                }
                // 合并完一个单元格区域之后，此时需要考虑单元格间的合并操作
                // 单元格区域的话可以按照右下，即自己的右边和下边，这个要注意边界问题
                if (i < N - 1) {
                    // 表示不是最后一行，则需要向下合并，此时合并的是自己的2和下面的0
                    int down = 4 * ((i + 1) * N + j);
                    findSet.union(index + 2, down);
                }
                if (j < N - 1) {
                    // 表示不是最后一列，向右合并，1与右边的3合并
                    int right = 4 * (i * N + j + 1);// 变成了右边的index
                    findSet.union(index + 1, right + 3);
                }
            }
        }
        return findSet.getSizeCount();
    }
}

class RegionUnion {
    int[] parent;
    int[] rank;
    int sizeCount;

    public RegionUnion(int n) {
        parent = new int[n];
        rank = new int[n];
        sizeCount = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;
        if (rootX < rootY) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        sizeCount--;
    }

    public int find(int index) {
        if (index != parent[index]) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

    public int getSizeCount() {
        return this.sizeCount;
    }
}
