package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/28 9:51
 * @Motto Keep thinking, keep coding!
 * leetcode 173、二叉搜索树迭代器
 * 题目：https://leetcode-cn.com/problems/binary-search-tree-iterator/
 */
public class Leetcode173_BSTIterator {
}

class BSTIterator {
    // 使用栈
    Deque<TreeNode> stack;
    TreeNode cur;

    public BSTIterator(TreeNode root) {
        stack = new LinkedList<>();
        cur = root;
    }

    /**
     * 数据弹出
     * 利用一个额外节点保存当前节点的状态cur
     */
    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left; // 一直向左，保存所有需要弹出的左边
        }
        // 此时返回当前元素即可
        cur = stack.pop();
        int ret = cur.val;
        // 返回之后，此时的cur需要定位到当前的有元素，因为中序遍历左中右
        cur = cur.right;
        return ret;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}