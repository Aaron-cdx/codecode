package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/4 19:36
 */
public class Solution_4 {
    public static void main(String[] args) {
        Solution_4 s = new Solution_4();
        TreeNode treeNode = s.reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        System.out.println(treeNode);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return rebuildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode rebuildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        // 表明没有节点了
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        // 需要找到当前中序遍历的节点在前序遍历数组中的位置
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                index = i - inStart;// 这是换取一个大小的区间
                break;
            }
        }
        root.left = rebuildTree(pre, preStart + 1, preStart + index, in, inStart, inStart + index - 1);
        root.right = rebuildTree(pre, preStart + index + 1, preEnd, in, inStart + index + 1, inEnd);
        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int x) {
        this.val = x;
    }
}
