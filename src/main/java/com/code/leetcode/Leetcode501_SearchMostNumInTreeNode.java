package com.code.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/9/24
 */
public class Leetcode501_SearchMostNumInTreeNode {
    /**
     * 这里查找的是出现频率最高的元素，可能有多个，如果存在多个的话需要输出多个值
     * 比如2 3 4都出现了三次，此时需要输出[2,3,4]
     * 对list中数进行动态更新即可
     * <p>
     * 思路：这里的主要是思路就是使用一个list存储所有的，只要是最大的绝对是唯一的
     * 动态更新的操作是因为如果有统计数大于当前最大的，则清楚list中所有的数据即可
     * <p>
     * 采用中序遍历即可，当前值即代表
     */
    int cnt = 0;
    int max = 0;
    int current = 0;
    List<Integer> mList = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        executeFineMode(root);
        int[] res = new int[mList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = mList.get(i);
        }
        return res;
    }

    public void executeFineMode(TreeNode root) {
        if (root == null) return;
        // 采用中序遍历
        executeFineMode(root.left);
        // 判断当前值是否需要累加
        int nodeVal = root.val;
        // 如果当前值相等，则++
        if (current == nodeVal) {
            cnt++;
        } else {
            current = nodeVal;
            cnt = 1;
        }
        // 这里的话只要抓住众数这个标签即出现次数最多即可，因为只要你出现次数最多，此时的话你肯定不会等于max
        // 而且就算这里提前放入，后面一旦右边还有自己相等的数据，mList会直接清空，情况还是一样
        if (cnt == max) {
            mList.add(nodeVal);
        }
        if (cnt > max) {
            mList.clear();
            mList.add(nodeVal);
            max = cnt;
        }
        executeFineMode(root.right);
    }
}
