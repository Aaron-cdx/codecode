package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/18 9:24
 * @Motto Keep thinking, keep coding!
 * leetcode 92、反转链表II
 * 题目：https://leetcode-cn.com/problems/reverse-linked-list-ii/
 */
public class Leetcode92_ReverseBetween {
    public static void main(String[] args) {
        Leetcode92_ReverseBetween test = new Leetcode92_ReverseBetween();
        ListNode list = new ListNode(0);
//        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr = new int[]{3, 5};
        ListNode head = list;
        for (int i : arr) {
            head.next = new ListNode(i);
            head = head.next;
        }
//        System.out.println(test.reverseBetween(list.next, 2, 4));
        System.out.println(test.reverseBetween(list.next, 1, 2));
    }

    /**
     * 反转m -> n处的链表
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 从left开始，right截止
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        ListNode node = cur.next;
        // 首先到left点，node表示开始的点
        int i = 0;
        while (i + 1 < left && node.next != null) {
            cur = cur.next;
            node = node.next;
            i++;
        }
        // 现在cur到了left的前一个点，node到了left当前点
        while (i + 1 < right && node.next != null) {
            // 开始执行插入操作
            ListNode temp = node.next;
            node.next = temp.next;
            temp.next = cur.next;
            cur.next = temp;
            i++;
        }
        return dummyNode.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
