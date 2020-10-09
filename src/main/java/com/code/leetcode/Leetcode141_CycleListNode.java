package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/9 8:40
 * @Motto Keep thinking, keep coding!
 * leetcode 141、环形链表
 * 判断链表中是否有环
 */
public class Leetcode141_CycleListNode {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode fast = head;
        ListNode slow = head;
        // 利用快慢指针，只要在前进的过程中两个指针相遇则表示有环
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}
