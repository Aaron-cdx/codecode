package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/3 9:48
 * @Motto Keep thinking, keep coding!
 * leetcode 86、分隔链表
 * 题目：https://leetcode-cn.com/problems/partition-list/
 * 给定一个特定x将链表分隔开
 */
public class Leetcode86_PartitionLIstNode {
    // 保留相对位置,但这不使用的双指针的方法实现的
    // 但实际官方题解也是这么写的
    /*public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode sm = new ListNode(-1);
        ListNode s = sm;
        ListNode lg = new ListNode(-1);
        ListNode l = lg;
        while(head != null){
            if(head.val < x){
                s.next = new ListNode(head.val);
                s= s.next;
            }else{
                l.next = new ListNode(head.val);
                l = l.next;
            }
            head = head.next;
        }
        s.next = lg.next;
        return sm.next;
    }*/
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode sm = new ListNode(-1);
        ListNode s = sm;
        ListNode lg = new ListNode(-1);
        ListNode l = lg;
        while(head != null){
            if(head.val < x){
                s.next = head;
                s= s.next;
            }else{
                l.next = head;
                l = l.next;
            }
            head = head.next;
        }
        // 这里必须切换 因为是两个指针交替引用如果不切换会造成死循环
        l.next = null;
        s.next = lg.next;
        return sm.next;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
