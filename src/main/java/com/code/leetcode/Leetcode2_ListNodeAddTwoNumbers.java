package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/4 19:45
 * @Motto Keep thinking, keep coding!
 * leetcode 2、两数相加
 * 利用链表进行两数相加的求解
 * 2->4->3 + 5->6->4 = 7->0->8
 */
public class Leetcode2_ListNodeAddTwoNumbers {
    /**
     * 主要是通过保存进位来实现元素的累加机制！
     * 注意最后一位也可能产生进位，所以可以使用curry来保存
     * 最后一位进位的情况！
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // 进位操作，判断是否有进位操作
        int curry = 0;
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        // 主要就是保存进位即可！
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            int sum = v1 + v2 + curry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            curry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + curry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            curry = sum / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + curry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            curry = sum / 10;
            l2 = l2.next;
        }
        if (curry != 0) {
            cur.next = new ListNode(curry);
            cur = cur.next;
        }
        return res.next;
    }
}

class ListNode {
    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }
}
