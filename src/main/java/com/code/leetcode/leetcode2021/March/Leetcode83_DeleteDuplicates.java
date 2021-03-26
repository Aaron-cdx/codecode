package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/26 9:12
 * @Motto Keep thinking, keep coding!
 * leetcode 83、删除排序链表中的重复元素
 * 题目：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class Leetcode83_DeleteDuplicates {
    public static void main(String[] args) {
        Leetcode83_DeleteDuplicates test = new Leetcode83_DeleteDuplicates();
        ListNode head = new ListNode(0);
        int[] arr = new int[]{1,1,2,3,3,3};
        ListNode cur = head;
        for (int i : arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        System.out.println(test.deleteDuplicates(head.next));
    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while(cur.next != null){
            // 1 1 2 3 3 3
            if(cur.val == cur.next.val){
                // 采用依次切断每一个相同元素节点实现的
                ListNode temp = cur.next;
                cur.next = temp.next;
                temp.next = null; // 依次切断每一个相同元素节点
            }else{
                cur = cur.next;
            }
        }
        return head;
    }
    /*public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        while(head != null && head.next != null){
            if(head.val == head.next.val){
                ListNode node = head.next;
                // 这里是找到相同元素最后一个节点，然后切断
                while(node != null && node.val == head.val){
                    node = node.next;
                }
                if(node == null){
                    head.next = null;
                    break;
                }
                head.next = null;
                head.next = node;
                head = node;
            }else{
                head = head.next;
            }
        }
        return dummyNode.next;
    }*/
}
