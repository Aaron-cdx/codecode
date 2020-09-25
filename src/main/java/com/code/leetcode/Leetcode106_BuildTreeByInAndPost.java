package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/25 21:41
 * leetcode106、从中序与后序遍历序列构造二叉树
 * 即给出中序遍历的结果与后续遍历的结果，构造这颗二叉树然后输出即可
 * 中：[9,3,15,20,7]
 * 后：[9,15,7,20,3]
 */
public class Leetcode106_BuildTreeByInAndPost {
    public static void main(String[] args) {
        Leetcode106_BuildTreeByInAndPost test = new Leetcode106_BuildTreeByInAndPost();
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode treeNode = test.buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;
        return executeBuild(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode executeBuild(int[] inorder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        // 否则就要寻找当前中序与后序的对应元素位置。
        // 这就是根节点
        TreeNode root = new TreeNode(postOrder[postEnd]);
        // 然后需要寻找左边元素
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == postOrder[postEnd]) {
                index = i - inStart;
                break;
            }
        }
        // 这里主要是为了确定左右子树的节点个数
        // 中序这里的元素主要为 [inStart,inStart+index-1]  而此时属于自己的后序遍历元素为[postStart,postStart+index-1]
        root.left = executeBuild(inorder, inStart, inStart + index - 1, postOrder, postStart, postStart + index - 1);
        // 确定右子树节点个数
        // 右边的话 中序元素为[inStart+index+1,inEnd] 属于自己的后序元素为[postStart+index,postEnd-1]
        root.right = executeBuild(inorder, inStart + index + 1, inEnd, postOrder, postStart + index, postEnd - 1);
        return root;
    }
}
