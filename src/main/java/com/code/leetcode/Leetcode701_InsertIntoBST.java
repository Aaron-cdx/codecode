package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/30 9:15
 * leetcode 701、二叉搜索树的插入操作
 */
public class Leetcode701_InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 如果到了null，表示当前节点需要进行落地操作
        if (root == null) return new TreeNode(val);
        // 否则依据左边还是右边，继续下一步操作
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
