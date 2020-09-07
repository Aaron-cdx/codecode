package com.code.enterprise;

/**
 * @author caoduanxi
 * @Date 2020/9/7 19:03
 * 获取环的入口点
 * 首先需要判断当前的链表有环
 * 首先快慢指针，一旦两者相遇，此时快指针回到原点，慢指针在原来位置
 * 然后两者一起运动，再次相遇则是入环扣
 */
public class Get_ListNodeCycleDot {
    public static void main(String[] args) {
        Get_ListNodeCycleDot dot = new Get_ListNodeCycleDot();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 3};
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        ListNode listNode = dot.detectCycle(head.next);
        System.out.println(listNode);
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 一直走，如果遇到了环，则直接break
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        // 这是判断是否有环的判断
        if (fast == null || fast.next == null) return null;
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 更为直接的方式来判断当前是否有环
     */
    public ListNode detectCycleII(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 表明遇到了环,此时回到快指针回到了原点，与慢指针一起，一旦再次相遇，当前节点就是入环口
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

}


