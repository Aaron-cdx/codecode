package com.code.leetcode.leetcode2021.January;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2021/1/6 9:04
 * @Motto Keep thinking, keep coding!
 * leetcode 399、除法求值
 * 题目：https://leetcode-cn.com/problems/evaluate-division/
 * 题目的意思是除法，给出几个变量的除法和值，求解需要查询的除法值
 * 这一道题是并查集和图的联合问题
 */
public class Leetcode399_CalcEquation {
    /**
     * 求解queries中的值
     *
     * @param equations 等式
     * @param values    等式求除法的值
     * @param queries   查询的变量除法的值
     * @return queries中的求除法之后的余值
     * dfs+bfs
     * 图的话在java中就要使用HashMap来构建
     */
    double[][] graph;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 首先构造并查集
        Map<String, Integer> map = new HashMap<>();
        int cnt = 0;
        for (List<String> equation : equations) {
            for (String s : equation) {
                // 如果不包含的话此时加入到map中，并统计不重复的字符数
                if (!map.containsKey(s)) {
                    map.put(s, cnt++);
                }
            }
        }
        // 此时cnt是所有存在字符的个数,此时可以按照个数获取到所有的边的相应的关系
        graph = new double[cnt + 1][cnt + 1];
        // graph[a][b]=a/b,反之亦然,这是构建给出的条件中包含的有条件的加权的图的边
        for (int i = 0; i < equations.size(); i++) {
            int a = map.get(equations.get(i).get(0));
            int b = map.get(equations.get(i).get(1));
            graph[a][b] = values[i];
            graph[b][a] = 1 / values[i];
        }
        // 现在求解整个queries中的值的答案
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            // 首先判断如果存在未知的数，结果直接使用-1代替
            String aKey = queries.get(i).get(0);
            String bKey = queries.get(i).get(1);
            if (!map.containsKey(aKey) || !map.containsKey(bKey)) {
                res[i] = -1;
                continue;
            }
            // 否则的话则表示两者都存在，此时需要使用深度优先遍历去寻找边与边之间的关系了
            int a = map.get(aKey);
            int b = map.get(bKey);
            res[i] = dfs(a, b, graph.length, new boolean[graph.length]);
            // 得到的结果需要添加到图graph中
            if (res[i] != -1 && graph[a][b] != 0) {
                graph[a][b] = res[i];
                graph[b][a] = 1 / res[i];
            }
        }
        return res;
    }

    private double dfs(int a, int b, int graphLen, boolean[] visited) {
        // 表示当前的图连接关系已经建立了，可以直接返回
        if (graph[a][b] != 0) {
            return graph[a][b];
        }
        // 遍历过的顶点需要记录,否则会无限循环
        visited[a] = true;
        // 否则的话可能和[a/b ~ b/c]这样,[a/c]并没有建立连接关系
        for (int i = 0; i < graphLen; i++) {
            // 表明a/i建立了关系,只要i/b建立关系,相乘答案就出来了,这里判断从顶点出发的就免了，保证了会一直向后查找不会对找不到的查询多次
            if (!visited[i] && graph[a][i] != 0) {
                double ib = dfs(i, b, graphLen, visited);
                if (ib != -1) {
                    return graph[a][i] * ib;
                }
            }
        }
        return -1;
    }
}
