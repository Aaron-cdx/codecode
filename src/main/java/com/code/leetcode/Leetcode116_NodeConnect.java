package com.code.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoduanxi
 * @Date 2020/10/15 10:17
 * @Motto Keep thinking, keep coding!
 * leetcode 116、填充每个节点的下一个右侧节点指针
 * 题目：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * 思路：
 * 第一种方法比较简单，通过queue队列的形式来将当前的节点放入到其中的，然后构建节点之间的关系
 * 第二种方法通过递归，利用人为构建的左右节点来实现
 * 最重要的就是要区分好当前的节点的next怎么去查找！
 */
public class Leetcode116_NodeConnect {
    public Node connectII(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                // 先进先出
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                // size就是判断条件，这个需要注意
                if (size > 0) {
                    node.next = queue.peek();
                }
            }
        }
        return root;
    }

    /**
     * 方法就是人为的衔接最右节点
     */
    public Node connect(Node root) {
        if (root == null) return null;
        // 这里主要是需要分清楚情况，分清楚了情况问题就好解决了
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        if (root.left != null && root.right == null) {
            root.left.next = getNextNode(root.next);
        }
        if (root.right != null) {
            root.right.next = getNextNode(root.next);
        }
        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node getNextNode(Node root) {
        if (root == null) return null;
        // 左边不为null返回左边的节点，否则看右边，不为null返回右边的节点
        if (root.left != null) return root.left;
        if (root.right != null) return root.right;
        // 两个都为null，这个一般是层级比较好，所以只能从next节点下手
        if (root.next != null) {
            return getNextNode(root.next);
        }
        return null;
    }
}
