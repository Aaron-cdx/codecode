package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/27 9:05
 * leetcode235、二叉搜索树的最近公共祖先
 */
public class Leetcode235_LowestCommonAncestor {
    /**
     * 这是一棵二叉搜索树，主要是有值大小区分
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        // 这里判断是否在左边
        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        // 这里判断是否在右边
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        // 否则就是root在中间，p，q左右各一个，此时直接返回root即可
        return root;
    }
}
