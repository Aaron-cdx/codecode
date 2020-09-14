package com.code.leetcode;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/9/14 10:29
 * leetcode 144、二叉树的前序遍历
 * <p>
 * 非递归版本
 */
public class Leetcode144_TreeNodePreOrder {
    /**
     * 递归版本实现
     */
    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        recursion(root, list);
        return list;
    }

    public void recursion(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        recursion(root.left, list);
        recursion(root.right, list);
    }

    /**
     * 非递归版本
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode poll = stack.pop();
            list.add(poll.val);
            // 使用栈，让左边的后入即可，后入先出
            if (poll.right != null) {
                stack.push(poll.right);
            }
            if (poll.left != null) {
                stack.push(poll.left);
            }
        }
        return list;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
