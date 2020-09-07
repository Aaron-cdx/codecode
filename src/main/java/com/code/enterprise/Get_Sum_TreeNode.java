package com.code.enterprise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/9/7 17:21
 * 判断二叉树中从根节点到叶子节点是否存在指定和为sum的路径
 * 获取其中的路径
 */
public class Get_Sum_TreeNode {
    public boolean hasPathSum(TreeNode root, int sum) {
        // write code here
        if (root == null) return false;
        return execute(root, sum);
    }

    public boolean execute(TreeNode root, int sum) {
        if (root == null) return false;
        // 因为必须是根节点，所以这样判断
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        int val = root.val;
        return execute(root.left, sum - val) || execute(root.right, sum - val);
    }

    /**
     * 获取所有的指定路径，使用list存储
     */
    private List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return lists;
        execute(root, sum, new ArrayList<Integer>());
        return lists;
    }

    private void execute(TreeNode root, int sum, ArrayList<Integer> list) {
        if (root == null) return;
        int val = root.val;
        list.add(val);
        // 注意这里添加添加的当前节点需要先移除，否则会造成节点重复
        if (root.left == null && root.right == null && sum - val == 0) {
            lists.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        // 否则的话此时需要进行遍历，需要回溯操作
        execute(root.left, sum - val, list);
        execute(root.right, sum - val, list);
        list.remove(list.size() - 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
