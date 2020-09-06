package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/6 15:39
 * 二叉树的镜像
 * 即通过一颗二叉树，获取当前二叉树的镜像
 */
public class Solution_18 {
    public void Mirror(TreeNode root) {
        if(root == null) return;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;

        Mirror(root.left);
        Mirror(root.right);
    }
}
