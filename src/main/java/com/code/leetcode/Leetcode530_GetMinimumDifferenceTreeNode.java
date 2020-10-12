package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/12 8:38
 * @Motto Keep thinking, keep coding!
 * leetcode 530、二叉搜索树的最小绝对差
 * 即求解二叉树中任意两个节点的差的绝对值的最小值：注意是任意节点
 * 不过有前提 二叉搜索树
 */
public class Leetcode530_GetMinimumDifferenceTreeNode {
    public static void main(String[] args) {
        Leetcode530_GetMinimumDifferenceTreeNode test = new Leetcode530_GetMinimumDifferenceTreeNode();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(3);
        System.out.println(test.getMinimumDifference(root));
    }

    /**
     * 这里由于是二叉搜索树
     * 可以得到
     * 左边：最小的绝对值出现在自己的左边最右侧
     * 右边：最小的绝对值出现在自己的右边最左侧
     * 利用中序遍历可以解决当前问题
     */
    int min = Integer.MAX_VALUE;
    int preVal = -1;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        getMin(root);
        return min;
    }

    private void getMin(TreeNode root) {
        // 左中右 中间是处理环节
        if (root == null) return;
        getMin(root.left);
        // 主要是一个初始值的位置的限定
        if (preVal != -1) {
            min = Math.min(root.val - preVal, min);
        }
        preVal = root.val;
        getMin(root.right);
    }
}
