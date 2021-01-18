package com.code.leetcode.leetcode2021.January;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/1/18 21:04
 * @Motto Keep thinking, keep coding!
 * leetcode 721、账户合并
 * 题目：https://leetcode-cn.com/problems/accounts-merge/
 */
public class Leetcode721_AccountsMerge {
    /**
     * 这里是合并相同账户的一个邮箱地址
     * 简历每一个账户与账户名的关联，最后只需要判断有哪些账户的账户名相同，则将其加入到统一的账户中即可
     * 名称相同的账户不一定相同，但是账户相同的则是同一个人了
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 注意这里有一个条件，需要按照ASCII码的顺序排列
        // 构建两个Map
        Map<String, Integer> indexMap = new HashMap<>();
        Map<String, String> emailMap = new HashMap<>();
        // 获取总的账号个数
        int emailAccount = 0;
        // 首先遍历去重
        for (List<String> account : accounts) {
            // 获取名称
            String name = account.get(0);
            // 开始遍历邮箱名称
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailMap.containsKey(email)) {
                    // 包含的话就跳过
                    indexMap.put(email, emailAccount++);
                    emailMap.put(email, name);
                }
            }
        }
        // 获取到了(邮箱->下标) (邮箱->名称)
        // 到这里邮箱的个数就准备好了,开始进行并查集的构造
        Union findSet = new Union(emailAccount);
        // 开始进行合并操作
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                // 合并操作同一个邮箱内部的
                int index = indexMap.get(account.get(i));
                findSet.union(indexMap.get(firstEmail), index);
            }
        }
        Map<Integer,List<String>> indexToEmails = new HashMap<>();
        // 合并完了之后,此时需要取数据进行账户的合并操作,先要构造结果集合,将父节点相同的都放入到一个Map集合中
        // 利用父节点相同的原理合并到一个集合
        for (String email : indexMap.keySet()) {
            // 找出父节点
            int emailIndex = findSet.find(indexMap.get(email));
            // 根据父节点获取具体的生活
            List<String> list = indexToEmails.getOrDefault(emailIndex, new ArrayList<>());
            list.add(email);
            indexToEmails.put(emailIndex,list);
        }
        // 最后进行结果合并
        List<List<String>> res = new ArrayList<>();
        // 这里利用之前的emailMap中已经存了账户和名称的对应关系，加上已经合并的结果即可完成映射操作
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailMap.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            res.add(account);
        }
        return res;
    }
}

class Union {
    int[] parent;

    public Union(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public int find(int index) {
        if (index != parent[index]) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
}
