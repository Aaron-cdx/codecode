package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/25 9:10
 * @Motto Keep thinking, keep coding!
 * leetcode 82、删除排序链表中的重复元素II
 * 题目：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class Leetcode82_DeleteDuplicates {
    /**
     * 这里是借用head自身作为一个指针，避免自己多构建一个指针
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        while (head != null && head.next != null) {
            if (head.val != head.next.val) {
                cur = head;
                head = head.next;
            } else {
                // 相等的话
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // 后面的部分需要记录下
                cur.next = head.next;
                head = head.next;
            }
        }
        return dummyNode.next;
    }

    public ListNode deleteDuplicatesI(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 首先需要找到具体的相等的开头到相等的结尾，然后吧链表切断
        ListNode pre = dummyNode;
        ListNode start = pre.next;
        ListNode end = start.next;
        while (end != null) {
            if (end.val == start.val) {
                while (end != null && end.val == start.val) {
                    end = end.next;
                }
                if (end == null) {
                    pre.next = end;
                    break;
                }
                start = end;
                end = start.next;
                pre.next = start;
            } else {
                pre = pre.next;
                end = end.next;
                start = start.next;
            }
        }
        return dummyNode.next;
    }
}
