package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/23 8:47
 * @Motto Keep thinking, keep coding!
 * leetcode 234、回文链表
 * 题目：https://leetcode-cn.com/problems/palindrome-linked-list/
 * 判断链表是否是回文链表，时间复杂度O(n),空间复杂度O(1)
 */
public class Leetcode234_IsPalindromeListNode {
    public static void main(String[] args) {
        Leetcode234_IsPalindromeListNode test = new Leetcode234_IsPalindromeListNode();
        ListNode head = new ListNode(0);
        int[] nums = new int[]{1, 0, 3, 4, 0, 1};
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        System.out.println(test.isPalindrome(head.next));
    }

    /**
     * 采取先翻转然后再判断的原则。
     * 注意：这里的翻转条件有所改变，不再是fast != null && fast.next != null
     * 而是采用fast.next != null && fast.next.next != null，这是为什么，这是因为你要翻转的时候
     * 必须确保被翻转的值是不为null的，否则没法翻转！
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head;
        ListNode slow = head;
        // 这里只需要将fast节点的停止条件前移即可
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 翻转后部链表
        fast = slow.next;
        slow.next = null;
        slow = head;
        fast = reverseNode(fast);
        // 这里的话偶数的话两者一起到结束位置，奇数的话此时slow会多出一个
        while (fast != null) {
            if (slow.val != fast.val) return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = p;
            p = head;
            head = temp;
        }
        return p;
    }
}
