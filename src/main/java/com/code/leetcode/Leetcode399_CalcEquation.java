package com.code.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/10/10 8:43
 * @Motto Keep thinking, keep coding!
 * leetcode 399、除法求值
 * 给出部分方程式的值，然后根据给出的方程式的值
 * 求解给出的需要计算的方程
 * 如果有结果返回结果，无结果返回-1.0
 */
public class Leetcode399_CalcEquation {
    /**
     * 第二种方法构建加权有向图
     */
    Map<String, List<Edge>> mEdge = new HashMap<>();
    // res
    double[] mRes;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 第一步需要构建有向加权图
        int index = 0;
        for (List<String> equation : equations) {
            String a = equation.get(0);
            String b = equation.get(1);
            // 判断当前的值是否存在
            List<Edge> aEdge = mEdge.get(a);
            if (aEdge == null) {
                // 此时需要构建一个边
                aEdge = new ArrayList<>();
                // 然后将当前的a含有的list的放入其中
                mEdge.put(a, aEdge);
            }
            // 然后将当前的边添加即可
            aEdge.add(new Edge(a, b, values[index]));
            // b也是一样
            List<Edge> bEdge = mEdge.get(b);
            if (bEdge == null) {
                bEdge = new ArrayList<>();
                mEdge.put(b, bEdge);
            }
            bEdge.add(new Edge(b, a, 1 / values[index++]));
        }
        // 第二步遍历获取结果即可
        mRes = new double[queries.size()];
        List<String> visited = new ArrayList<>();
        for (int i = 0; i < mRes.length; i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);
            // 每一次访问之后都要清除
            visited.clear();
            mRes[i] = dfs(a, b, visited);
        }
        return mRes;
    }

    private double dfs(String edgeFrom, String edgeTo, List<String> visited) {
        // 都不含有的话直接返回-1.0
        if (!mEdge.containsKey(edgeFrom) || !mEdge.containsKey(edgeTo)) {
            return -1.0;
        }
        visited.add(edgeFrom);
        // 这表明两者相同
        if (edgeFrom.equals(edgeTo)) {
            return 1.0;
        }
        // 否则的话看看当前值是否有Edge，即是否与其他的边连接起来了
        List<Edge> edges = mEdge.get(edgeFrom);
        if (edges == null || edges.isEmpty()) {
            return -1.0;
        }
        // 否则的话对当前的边界进行遍历
        for (Edge edge : edges) {
            // 如果已经访问过则直接忽略
            if (visited.contains(edge.to)) {
                continue;
            }
            // 否则继续查找
            double r = dfs(edge.to, edgeTo, visited);
            if (r != -1.0) {
                return edge.val * r;
            }
        }
        return -1.0;
    }


    /**
     * 参考别人的答案，利用矩阵构造图的方式获取当前解
     */
    public double[] calcEquation_II(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        // 主要将所有元素都放入，并统计所有元素的个数
        for (List<String> equation : equations) {
            for (String s : equation) {
                if (!map.containsKey(s)) {
                    map.put(s, count++);
                }
            }
        }
        int n = count + 1;
        double[][] graph = new double[n][n];
        // 进行初始化
        for (String s : map.keySet()) {
            int x = map.get(s);
            graph[x][x] = 1.0;
        }

        int index = 0;
        for (List<String> equation : equations) {
            String a = equation.get(0);
            String b = equation.get(1);
            // 这里是固定每一个单词的位置
            int aa = map.get(a);
            int bb = map.get(b);
            double value = values[index++];
            graph[aa][bb] = value;
            graph[bb][aa] = 1 / value;
        }
        // 利用floyd算法将所有的图中没有计算到的数据都补齐
        // 走n遍可以将所有的点与点之间的数据计算完毕
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // 这里表名当前是有值的即1.0,如果没有值表示之前没有计算过
                    if (j == k || graph[j][k] != 0) continue;
                    // 否则计算当前的值
                    if (graph[i][k] != 0 && graph[j][i] != 0) {
                        graph[j][k] = graph[j][i] * graph[i][k];
                    }
                }
            }
        }

        // 最后进行赋值即可
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            List<String> query = queries.get(i);
            String a = query.get(0);
            String b = query.get(1);
            if (map.containsKey(a) && map.containsKey(b)) {
                double ans = graph[map.get(a)][map.get(b)];
                res[i] = (ans == 0 ? -1.0 : ans);
            } else {
                res[i] = -1.0;
            }
        }
        return res;
    }
}

class Edge {
    String from;
    String to;
    double val;

    // 人为构造加权的边即可
    public Edge(String from, String to, double val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}