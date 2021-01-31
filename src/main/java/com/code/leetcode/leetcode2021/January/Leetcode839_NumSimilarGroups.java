package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/31 9:59
 * @Motto Keep thinking, keep coding!
 * leetcode 839、相似字符串组
 * 题目：https://leetcode-cn.com/problems/similar-string-groups/
 * 找出相似字符串组的个数
 * 相似字符串，即字符串X交换其中两个字符，如果可以和其他字符串相等，则它们属于相似字符串组中
 */
public class Leetcode839_NumSimilarGroups {
    /**
     * 注意这里问的是存在多少个字符串组，相似字符串为一个组
     * 所以这里剩下的组是相似的为一个组，剩下的不能联通的也算作一个组，故存在sizeCount个组
     */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        SimilarUnion similarUnion = new SimilarUnion(n);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                // 两者相似才能合并为一个组
                if (!isSimilar(strs[i], strs[j])) {
                    continue;
                }
                // 如果合并成功
                similarUnion.union(i, j);
            }
        }
        return similarUnion.sizeCount;
    }

    /**
     * 判断两个字符串是否是相似字符串，
     * 即判断两个是否不同的只有2个以内，
     * 如果是的话则表示可以交换为组
     */
    public boolean isSimilar(String s1, String s2){
        int index = 0;
        int count = 0;
        while (index < s1.length()){
            if(s1.charAt(index) != s2.charAt(index)){
                count ++;
                if(count > 2) return false;
            }
            index++;
        }
        return true;
    }
}

class SimilarUnion {
    int[] parent;
    int[] rank;
    int sizeCount;

    public SimilarUnion(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        sizeCount = n;
    }

    public int find(int index) {
        if (index != parent[index]) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
    // 联通规则需要做减法，不能联通则要减去一个联通分量
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        if (rank[rootX] < rank[rootY]) {
            int tmp = rank[rootX];
            rank[rootX] = rank[rootY];
            rank[rootY] = tmp;
        }
        parent[rootY] = rootX;
        rank[rootX] += rank[rootY];
        sizeCount--;
        return true;
    }
}
