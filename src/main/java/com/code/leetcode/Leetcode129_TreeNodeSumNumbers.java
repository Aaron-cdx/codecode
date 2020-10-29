package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/29 9:00
 * @Motto Keep thinking, keep coding!
 * leetcode 129、求根到叶子节点之和
 * 题目：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 */
public class Leetcode129_TreeNodeSumNumbers {
    public static void main(String[] args) {
        Leetcode129_TreeNodeSumNumbers test = new Leetcode129_TreeNodeSumNumbers();
        System.out.println(test.sumNumbers(null));
    }

    int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        execute(root, 0);
        return sum;
    }

    public void execute(TreeNode root, int val) {
        if (root == null) return;
        // 最开始开始乘法
        int x = val * 10 + root.val;
        // 遇到根节点直接返回当前的，值然后相加上去
        if (root.left == null && root.right == null) {
            sum += x;
            return;
        }
        // 左右顺序
        execute(root.left, x);
        execute(root.right, x);
    }
}
