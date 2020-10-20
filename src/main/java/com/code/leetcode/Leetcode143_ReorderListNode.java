package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/20 8:29
 * @Motto Keep thinking, keep coding!
 * leetcode 143、重排链表
 * 题目：https://leetcode-cn.com/problems/reorder-list/
 * 1->2->3->4 => 1->4->2->3
 * l0 ln l1 ln-1 l2 ln-2 ......
 */
public class Leetcode143_ReorderListNode {
    public static void main(String[] args) {
        Leetcode143_ReorderListNode test = new Leetcode143_ReorderListNode();
        int[] nums = new int[]{1, 2, 3, 4};
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        test.reorderList(head.next);
        while (head.next != null) {
            System.out.println(head.next.val);
            head.next = head.next.next;
        }
    }

    /**
     * 这里主要采用的就是先利用快慢指针找终点，然后切断链表，利用翻转将后半段翻转
     * 然后合并链表即可
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // 利用快慢指针找到中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        slow = head;
        // 这里翻转fast链表
        fast = reverse(fast);
        while (slow != null && fast != null) {
            ListNode slowTemp = slow.next;
            ListNode fastTemp = fast.next;
            slow.next = fast;
            fast.next = slowTemp;
            slow = slowTemp;
            fast = fastTemp;
        }
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
