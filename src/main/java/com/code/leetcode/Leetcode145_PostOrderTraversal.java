package com.code.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/9/29 18:55
 * leetcode 145、二叉树的后序遍历
 */
public class Leetcode145_PostOrderTraversal {
    public List<Integer> postorderTraversalII(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        recurseExe(root, list);
        return list;
    }

    /**
     * 利用递归操作进行二叉树的后序遍历
     */
    public void recurseExe(TreeNode root, List<Integer> list) {
        if (root == null) return;
        // 标准的左右中顺序即可
        recurseExe(root.left, list);
        recurseExe(root.right, list);
        list.add(root.val);
    }

    /**
     * 非递归操作：广度优先遍历操作
     * 利用栈，但是注意需要切断相应的连接否则在处理过程中会出现数据冲突
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 需要一直向左边行进
            // 注意使用continue
            TreeNode node = stack.peek();
            if (node.left != null) {
                stack.add(node.left);
                node.left = null;
                continue;
            }
            // 右边的话继续continue
            if (node.right != null) {
                stack.add(node.right);
                node.right = null;
                continue;
            }
            list.add(stack.pop().val);
        }
        return list;
    }
}
