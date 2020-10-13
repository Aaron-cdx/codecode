package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/13 8:30
 * @Motto Keep thinking, keep coding!
 * leetcode 24、两两交换链表中的节点
 * 即将链表中的节点两两交换
 * 1->2->3->4 ==> 2->1->4->3
 */
public class Leetcode24_SwapPairsListNode {
    public static void main(String[] args) {
        Leetcode24_SwapPairsListNode test = new Leetcode24_SwapPairsListNode();
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i <= 100; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        ListNode listNode = test.swapPairs(head.next);
        while (listNode != null) {
            System.out.println(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    /**
     * 递归翻转链表
     */
    public ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode node = reverseNode(head.next);
        head.next.next = head;
        head.next = null;

        return node;
    }

    /**
     * 利用递归解决问题
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        // node作为第一个节点先拿出来
        ListNode node = head.next;
        // 这也是一样的只是利用递归
        head.next = swapPairs(node.next);
        // 利用node作为连接节点，然后返回node即可
        node.next = head;
        return node;
    }


    /**
     * 非递归方式解决问题
     */
    public ListNode swapPairsII(ListNode head) {
        if (head == null || head.next == null) return head;
        // 否则需要使用双指针
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 利用当前节点作为连接下一个节点的前一个节点
        ListNode pre = dummyNode;
        // 作为交换的节点
        ListNode a = pre.next;
        ListNode b = a.next;
        while (b != null) {
            // 记录后续的节点
            ListNode temp = b.next;
            // 改变指针指向
            b.next = a;
            a.next = temp;
            pre.next = b;
            // 这里注意如果是null，后续就没必要继续交换了
            if (temp == null) {
                break;
            }
            // 如果不是null，这里需要重新排布位置
            pre = a;
            a = a.next;
            b = a.next;
        }
        return dummyNode.next;
    }
}
