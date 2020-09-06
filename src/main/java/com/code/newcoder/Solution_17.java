package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/6 14:45
 * 二叉树的子结构
 * 思路：
 * 首先需要明确子结构，即当前节点不行就左节点，不行就右节点
 * 然后开始利用子查询来查找是否有符合其中条件的
 * 终止条件的话：第一个主查询的话主要是当A或者B为null的时候，此时不可进行下去了，因为null不是任一树的子结构
 * 第二个终止条件的话，由于B是子结构，是可以满足为A的子结构的，因为现在判断的是全等，但是如果A为null的时候B
 * 不为null的话，此时表明非全等，直接返回false
 */
public class Solution_17 {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return isTrue(root1, root2) || HasSubtree(root1.right, root2) || HasSubtree(root1.left, root2);
    }

    /**
     * 明确意图，需要找A的子结构是否为B
     * 即让B在A中去匹配，看看自己是否存在A中
     * 即B可能会先为null，因为它是子结构，会先结束
     * 但是在刚开始的时候，如果B == null return false因为空树不是任一树的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        return isTrue(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 这里只需要判断是否全等即可
     */
    public boolean isTrue(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && isTrue(A.right, B.right) && isTrue(A.left, B.left);
    }
}
