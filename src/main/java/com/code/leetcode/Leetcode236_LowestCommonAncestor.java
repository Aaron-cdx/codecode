package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/27 9:23
 * leetcode236、二叉树的最近公共祖先
 * 注意这里是指代的二叉树，并不是二叉搜索树，所以不能够依据值的大小来区分当前的值
 */
public class Leetcode236_LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // 只要左边和右边有值相等，则直接返回
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;
        // 否则的话此时需要判断当前值在左边还是右边
        // 否则查找左边或者右边
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 如果左边不为null且右边也不为null，此时返回root
        if (right != null && left != null) return root;
        // 如果右边不为null，返回right
        if (right != null) return right;
        // 否则返回left
        return left;
    }
}
