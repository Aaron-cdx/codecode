package com.code.leetcode.week;

/**
 * @author caoduanxi
 * @Date 2021/1/10 10:31
 * @Motto Keep thinking, keep coding!
 * 20200110周赛
 */
public class Week20200110 {
    /*
    [55,60,78,53,93,37,31,4,61,11,13,51,34,83,24,96,5,77,1,67]
11
     */
    public static void main(String[] args) {
//        int[] decode = decode(new int[]{1, 2, 3}, 1);
//        for (int i : decode) {
//            System.out.print(i + " ");
//        }
//        int[] nums = new int[]{1};
//        int[] nums = new int[]{7,9,6,6,7,8,3,0,9};
//        int[] nums = new int[]{100,90};
        int[] nums = new int[]{55, 60, 78, 53, 93, 37, 31, 4, 61, 11, 13, 51, 34, 83, 24, 96, 5, 77, 1, 67};
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        ListNode listNode = swapNodes(pre.next, 11);
//        System.out.println(swapNodes(pre.next, 2));
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.print("null");

    }

    /**
     * 交换链表的节点
     * 先找到这两个节点，然后进行值的交换即可
     * 可以直接修改值
     * 131/132 艹
     */
    public static ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        // 正数第k即k-1，倒数第k则为count-k
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode.next;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        if(k == count){
            k = 1;
        }
        // 获得count，第k则为第三个count-k-1 k-1
        cur = dummyNode;
        for (int i = 0; i < k-1; i++) {
            cur = cur.next;
        }
        ListNode post = dummyNode;
        for (int i = 0; i < count - k; i++) {
            post = post.next;
        }
        int tmp = cur.next.val;
        cur.next.val = post.next.val;
        post.next.val = tmp;

        return dummyNode.next;
    }

    public static int[] decode(int[] encoded, int first) {
        int[] res = new int[encoded.length + 1];
        res[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            res[i + 1] = res[i] ^ encoded[i];
        }
        return res;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
