package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/27 9:28
 * @Motto Keep thinking, keep coding!
 * leetcode 61、旋转链表
 * 题目：https://leetcode-cn.com/problems/rotate-list/
 */
public class Leetcode61_RotateRight {
    public static void main(String[] args) {
        Leetcode61_RotateRight test = new Leetcode61_RotateRight();
        ListNode head = new ListNode(0);
        int[] arr = new int[]{0,1,2};
        ListNode cur = head;
        for (int i : arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        System.out.println(test.rotateRight(head.next,4));
    }

    /**
     * 构成环状，然后沿着环状走，最后切断环状即可
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int cnt = 1;
        ListNode cur = head;
        while(cur.next != null){
            cnt += 1;
            cur = cur.next;
        }
        // 如果是节点的倍数，直接返回即可
        k %= cnt;
        if(k == 0) return head;
        cur.next = head; // 构成环状
        ListNode pre = head;
        int i = 0;
        while(i < (cnt - k - 1)){
            pre = pre.next;
            i++;
        }
        ListNode temp = pre.next;
        pre.next = null;
        return temp;
    }

    /**
     * 第一种方法是利用双指针交替变换得到旋转
     */
   /* public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int cnt = 0;
        ListNode cur = head;
        while(cur != null){
            cnt += 1;
            cur = cur.next;
        }
        // 如果是节点的倍数，直接返回即可
        k %= cnt;
        if(k == 0) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 利用双指针进行操作
        ListNode p1 = dummyNode;
        ListNode p2 = head;
        // 双指针依次滚动即可
        // 定位到最后一个非null节点
        while(p2.next != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        // 开始交替
        while(k != 0){
            p1.next = null;
            p2.next = dummyNode.next;
            dummyNode.next = p2;
            // 然后对p2进行交替
            while(p2.next != p1){
                p2 = p2.next;
            }
            // 这里进行p2和p1的交换
            ListNode temp = p2;
            p2 = p1;
            p1 = temp;
            k--;
        }
        return dummyNode.next;
    }*/
}
