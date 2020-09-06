package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/5 19:48
 * 链表中倒数第k个节点
 */
public class Solution_14 {
    public static void main(String[] args) {
        Solution_14 solution = new Solution_14();
//        int[] nums = new int[]{1, 2, 3, 4, 5};
        int[] nums = new int[]{1};
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        ListNode listNode = solution.FindKthToTail(head.next, 1);
        System.out.println(listNode);
    }

    /**
     * 先让第一个节点先走k步，然后在一起走，如果第一个节点到了尾部
     * 则第二个节点就是倒数第k个节点
     * 注意：
     *     面试中会夹杂这比如链表有环怎么判断？
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (k > 0 && fast != null) {
            fast = fast.next;
            k--;
        }
        if (k > 0) return null;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }
}
