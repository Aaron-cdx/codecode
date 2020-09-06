package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/5 19:48
 */
public class Solution_14 {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while(k > 0){
            fast = fast.next;
        }

        return null;
    }
}

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }
}
