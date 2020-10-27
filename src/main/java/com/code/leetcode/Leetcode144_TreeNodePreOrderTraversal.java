package com.code.leetcode;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/10/27 9:29
 * @Motto Keep thinking, keep coding!
 * leetcode 144、二叉树的前序遍历
 * 题目：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 自己在非递归的时候居然出现了小问题，这个确实是不应该啊，这个明显使用栈的问题，右边先进
 * 然后后续都是处理完左边的再说，这个居然出现了问题，真的不应该，这个自己需要深刻反省自己了！
 */
public class Leetcode144_TreeNodePreOrderTraversal {
    /**
     * 非递归方式实现前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        // 直接先处理右边然后处理左边即可
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 递归实现树的前序遍历
     */
    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        executePreOrder(root, list);
        return list;
    }

    public void executePreOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        executePreOrder(root.left, list);
        executePreOrder(root.right, list);
    }
}
