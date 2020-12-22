package com.code.leetcode.December;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/12/22 9:15
 * @Motto Keep thinking, keep coding!
 * leetcode 103、二叉树的锯齿形层序遍历
 * 题目：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * 题意：按照锯齿形状将当前的二叉树进行打印
 */
public class Leetcode103_ZigzagLevelOrder {
    public static void main(String[] args) {
        Leetcode103_ZigzagLevelOrder test = new Leetcode103_ZigzagLevelOrder();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        System.out.println(test.zigzagLevelOrder(root));
    }
    /**
     * 第一种方法，利用集合反差，利用标志表示交替反插入实现
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    /*public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        boolean yes = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (yes) {
                    tempList.add(poll.val);
                } else {
                    tempList.add(0, poll.val);
                }
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            list.add(tempList);
            yes = !yes;
        }
        return list;
    }*/

    /**
     * 使用单个栈实现
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        boolean yes = true;
        // 如何使用栈实现呢？
        while (!stack.isEmpty()) {
            int size = stack.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 这里遍历的话主要是移除,size-i-1则是完全按照stack的顺序来的,且不会受到中途加入新的数据影响
                TreeNode pop = stack.remove(size - i - 1);
                tempList.add(pop.val);
                if (yes) {
                    // 先左后右
                    if (pop.left != null) stack.add(pop.left);
                    if (pop.right != null) stack.add(pop.right);
                } else {
                    if (pop.right != null) stack.add(pop.right);
                    if (pop.left != null) stack.add(pop.left);
                }
            }
            yes = !yes;
            list.add(tempList);
        }
        return list;
    }
}
