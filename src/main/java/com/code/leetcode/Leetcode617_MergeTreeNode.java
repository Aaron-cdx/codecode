package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/23 19:49
 * leetcode 617、合并二叉树
 * 就是将两棵树合并，都有节点进行节点值合并，若有无节点则进行节点覆盖操作
 */
public class Leetcode617_MergeTreeNode {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 都为null，则返回null
        if (t1 == null && t2 == null) return null;
        // 一方为null，则返回另一方
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        // 都不为null，则构建节点
        TreeNode root = new TreeNode(t1.val + t2.val);
        // 左右构造节点
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }
}
