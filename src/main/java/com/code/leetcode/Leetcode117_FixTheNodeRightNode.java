package com.code.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoduanxi
 * @Date 2020/9/28 9:26
 * leetcode117、填充每个节点的下一个右侧节点指针II
 */
public class Leetcode117_FixTheNodeRightNode {
    public static void main(String[] args) {
        Queue<Node> queue = new LinkedList<>();
        System.out.println(queue.peek());
    }

    /**
     * 先利用有空间的情况做题
     * 需要再本次空间之内解决当前的所有的情况
     */
    public Node connectII(Node root) {
        if (root == null) return null;
        // 这里如果没有next节点使用#填充，即
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 这一行内需要解决这个问题
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                // 这个要把本批次处理完全才可以
                if (size > 0) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 利用递归，递归利用的栈空间可以忽略
     * 通过已经建立好的next指针去寻找下一个next的元素即可
     */
    public Node connect(Node root) {
        if (root == null) return null;
        // 利用递归实现
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        if (root.left != null && root.right == null) {
            root.left.next = nextNode(root.next);
        }
        // root.left == null =>
        if (root.right != null) {
            root.right.next = nextNode(root.next);
        }
        // 因为计算左边节点的next时需要知道right右边的依赖关系，所以需要先处理右边的
        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node nextNode(Node node) {
        if (node == null) return null;
        if (node.left != null) return node.left;
        if (node.right != null) return node.right;
        // 这里主要是为了防止如果层级太高导致找不到下一个
        if(node.next != null){
            return nextNode(node.next);
        }
        return null;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
