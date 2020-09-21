package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/21 18:58
 * leetcode 538、把二叉搜索树转换为累加树
 */
public class Leetcode538_TreeNodeToAddTree {
    public TreeNode convertBST1(TreeNode root) {
        if (root == null) return null;
        executeConvertBst(root);
        return root;
    }

    // 中序遍历反序即可 右中左，但是为了保持值的连续性，需要使用一个sum来代替这个值
    int sum = 0;

    public void executeConvertBst(TreeNode root) {
        if (root == null) return;
        executeConvertBst(root.right);
        sum += root.val;
        root.val = sum;
        executeConvertBst(root.left);
    }

    /**
     * 第二种方法：Mirrors遍历算法
     * 这种方法的核心思想：
     * 1. 如果当前节点的右边节点为null则处理当前节点
     * 2. 如果不为null，此时寻找最左边的节点，然后将当前节点和根节点构造一个succ.left = node的关系
     * 从而将整棵树连起来，注意需要切断连接
     * 利用node节点和succ节点构造关系，然后达到常数空间遍历整个树
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left; // 其实是向上走
            } else {
                TreeNode succ = getSuccessor(node);
                // 表示没有左边的节点了，此时需要连接到根节点
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                } else {
                    // 如果succ.left != null，表示这是人为构造的关系达成了，此时需要解除关系计算值了
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }

    public TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        // succ.left != node的判断主要是因为在建立了连接关系之后，此时是成立的。所以不能去重复建立关系
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

    public static void main(String[] args) {
        Leetcode538_TreeNodeToAddTree test = new Leetcode538_TreeNodeToAddTree();
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(13);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.left.left = new TreeNode(6);
        TreeNode res = test.convertBST(root);
        System.out.println(res);
    }
}
