package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/7 9:11
 * @Motto Keep thinking, keep coding!
 * leetcode 547、省份数量
 * 题目：https://leetcode-cn.com/problems/number-of-provinces/
 * isConnected[i][j]=1表示相连，而等于0表示不相连
 */
public class Leetcode547_FindCircleNum {
    public static void main(String[] args) {
        Leetcode547_FindCircleNum test = new Leetcode547_FindCircleNum();
        System.out.println(test.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }
    /**
     * 这种直接暴力求解的话是没有答案的，这个答案肯定也是不对的
     */
    /*public int findCircleNum(int[][] isConnected) {
        int rows = isConnected.length;
        if(rows == 0) return 0;
        int cols = isConnected[0].length;
        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(i != j && isConnected[i][j] == 1 && isConnected[j][i] == 1){
                    cnt += 1;
                }
                isConnected[i][j]=0;
                isConnected[j][i]=0;
            }
        }
        return cnt;
    }*/

    /**
     * 标签是并查集和深度优先搜索
     * 间接相连也是连接！a->b->c => a->c
     * 这里的省份是连接的才能够算一个省份，如果只有其一个人，则其一个人算作一个省份
     * <p>
     * 深度优先搜索解题
     */
    /*public int findCircleNum(int[][] isConnected) {
        int rows = isConnected.length;
        if (rows == 0) return 0;
        int cnt = 0;
        boolean[] visited = new boolean[rows];
        for (int i = 0; i < rows; i++) {
            if (!visited[i]) {
                dfs(isConnected, i, visited);
                cnt++;
            }
        }
        return cnt;
    }*/
    private void dfs(int[][] isConnected, int i, boolean[] visited) {
        // 访问自己的列，如果列有了，此时以列为行，访问当前行的列
        for (int j = 0; j < isConnected.length; j++) {
            // 如果自己这一行中的列有为1的，则下到这一列大小的行里面，且这一列没有被访问过
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, j, visited);
            }
        }
    }

    /**
     * 使用并查集解题
     */
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        // 初始化，每一个都是自己的父节点
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        // 处理自己不遍历，遍历本行中剩余的列
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                // 如果它俩联通，则表示一定有一个一致的父节点
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            // 最后只要是自己的父节点的话就没有任何问题，否则的话则表明自己的父节点已经被合并了
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    /**
     * dfs获取
     */
    private int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        // 如果自己就是自己的父节点，此时则找到了最初的父节点，直接返回
        return parent[index];
    }

}
