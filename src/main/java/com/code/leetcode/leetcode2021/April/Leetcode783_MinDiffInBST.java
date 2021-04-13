package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/13 17:10
 * @Motto Keep thinking, keep coding!
 * leetcode 783、二叉搜索树节点最小距离
 * 题目：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 */
public class Leetcode783_MinDiffInBST {
    /**
     * 最小值应该是在上一级和下一级中产生，或者是左边最后一个与更节点之间产生
     */
    int min = Integer.MAX_VALUE;
    int res = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        helper(root);
        return min;
    }

    public void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (res != Integer.MAX_VALUE) {
            min = Math.min(min, Math.abs(root.val - res));
        }
        res = root.val;
        helper(root.right);
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
