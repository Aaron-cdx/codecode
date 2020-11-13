package com.code.leetcode.november;

/**
 * @author caoduanxi
 * @Date 2020/11/13 14:59
 * @Motto Keep thinking, keep coding!
 * leetcode 328、奇偶链表
 * 题目：https://leetcode-cn.com/problems/odd-even-linked-list/
 */
public class Leetcode328_OddEvenListNode {
    public static void main(String[] args) {
        Leetcode328_OddEvenListNode test = new Leetcode328_OddEvenListNode();
//        int[] nums = new int[]{1,2,3,4,5};
//        int[] nums = new int[]{2,1,3,5,6,4,7};
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        ListNode listNode = test.oddEvenList(head.next);
        System.out.println(listNode);
    }

    /**
     * 按照节点的奇偶属性将节点按照前面奇数后面偶数的排列
     * 2->1->3->5->6->4->7
     * ==>
     * 2->3->6->7->1->5->4
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 设立奇偶节点
        ListNode odd = head;
        ListNode even = odd.next;
        // 这里主要是注意链表节点连接的时机问题
        while (even != null && even.next != null) {
            ListNode temp = odd.next; // 保存临时节点
            odd.next = even.next; // odd开始修改指向
            odd = odd.next; // odd开始向后行进
            even.next = odd.next; // even开始修改指向
            odd.next = temp; // 为了确保奇数后面是偶数节点，此时需要将temp连接上
            even = even.next; // 最后even向前行进
        }
        return head;
    }
}

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }
}