package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/18 14:23
 * @Motto Keep thinking, keep coding!
 * leetcode 19、删除链表的倒数第N个节点
 * 题目：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class Leetcode19_RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        ListNode slow = dummyNode;
        // 利用一个虚拟节点，让fast先走n步，这样只要fast到达了终点此时slow的下一个节点就是倒数第n个节点
        // 此时只要将slow的下一个节点的指针指向下一个的下一个即可完成倒数第n个节点的移除操作
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }
}
