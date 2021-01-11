package com.code.leetcode.leetcode2021.January;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/1/11 10:44
 * @Motto Keep thinking, keep coding!
 * leetcode 1202、交换字符串中的元素
 * 题目：https://leetcode-cn.com/problems/smallest-string-with-swaps/
 */
public class Leetcode1202_SmallestStringWithSwaps {
    public static void main(String[] args) {
        Leetcode1202_SmallestStringWithSwaps test = new Leetcode1202_SmallestStringWithSwaps();
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0,3));
        pairs.add(Arrays.asList(1,2));
        System.out.println(test.smallestStringWithSwaps("dcab",pairs));
    }
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // 编写流程
        if(pairs.size() == 0) return s;
        // 并查集构建
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int x = pair.get(0);
            int y = pair.get(1);
            // 进行合并
            unionFind.union(x,y);
        }
        // 构建与字符的映射关系
        char[] chars = s.toCharArray();
        // 利用优先队列将结果中的字符按照字典序排好即可，而优先队列默认是小顶堆的
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if(map.containsKey(root)){
                map.get(root).offer(chars[i]);
            }else{
                PriorityQueue<Character> queue = new PriorityQueue<>();
                queue.offer(chars[i]);
                map.put(root,queue);
            }
        }
        // 重组字符串
        StringBuilder sb = new StringBuilder();
        // 这里是单个吐出来的，所以可以接收到正确的结果
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();
    }
}

/**
 * 定义并查集
 */
class UnionFind {
    // 这里使用的是秩合并
    private int[] rank;
    private int[] parent;

    public UnionFind(int len) {
        this.parent = new int[len];
        this.rank = new  int[len];
        // 初始化
        for (int i = 0; i < len; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
        }
    }

    /**
     * 进行两个节点的合并操作
     */
    public void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            // 如果相等则表示两个已经拥有一个父节点了
            return;
        }
        // 如果两个秩相等，但是父节点不同，此时需要指定某个节点为其它节点的父节点了
        if(rank[rootX] == rank[rootY]){
            // 这是指定当前节点为另一个节点的父节点，则当前y则随之递增秩
            parent[rootX] = rootY;
            rank[y]++;
        }else if(rank[rootX] > rank[rootY]){
            // 如果秩大，则自己并入到秩小的作为父节点,此时自己本来就小，其他节点本来就大，所以不会产生秩传递现象
            parent[rootY] = rootX;
        }else{
            parent[rootX] = rootY;
        }
    }

    public int find(int x) {
        if(x != parent[x]){
            // 如果自己不是自己的父节点，则一直向上溯源自己的父节点
            parent[x] = find(parent[x]);
        }
        // 否则表示找到了直接返回即可
        return parent[x];
    }
}