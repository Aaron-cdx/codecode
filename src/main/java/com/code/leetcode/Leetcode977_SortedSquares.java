package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/16 9:31
 * @Motto Keep thinking, keep coding!
 * leetcode 977、有序数组的平方
 * 取了平方之后还需要按照有序的方式排列平方后的数组
 * [-4,-1,0,3,10] => [0,1,9,16,100]
 */
public class Leetcode977_SortedSquares {
    public static void main(String[] args) {
        Leetcode977_SortedSquares test = new Leetcode977_SortedSquares();
        test.print(test.sortedSquares(new int[]{-3, -3, -2, 1}));
        test.print(test.sortedSquares(new int[]{-4, -1, 0, 3, 10}));
        test.print(test.sortedSquares(new int[]{-7, -3, 2, 3, 11}));

    }

    public void print(int[] A) {
        for (int i : A) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) return null;
        int[] res = new int[A.length];
        int start = 0;
        int end = A.length - 1;
        int i = end;
        // 利用一个O(n)的空间来解决当前的问题！
        while (start <= end) {
            int s = A[start] * A[start];
            int e = A[end] * A[end];
            if (s > e) {
                res[i] = s;
                start++;
            } else {
                // 如果后面更大就向前走
                res[i] = e;
                end--;
            }
            i--;
        }
        return res;
    }
}
