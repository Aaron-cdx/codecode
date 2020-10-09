package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/9 8:45
 * @Motto Keep thinking, keep coding!
 * leetcode 142、环形链表II
 * 如果有环获取环入口的第一个节点，无环返回null
 */
public class Leetcode142_CycleListNodeII {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        // 此时需要找到入环的地方，一旦两者相遇则终止
        ListNode fast = head;
        ListNode slow = head;
        // 这里主要是一旦遇到了环，即两者遇到了相等，此时快指针到头部，然后与慢指针一样的速度，再次相遇即是入环节点
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
