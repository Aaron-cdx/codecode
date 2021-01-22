package com.code.leetcode.leetcode2021.January;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/1/22 9:36
 * @Motto Keep thinking, keep coding!
 * leetcode 989、数组形式的整数加法
 * 题目：https://leetcode-cn.com/problems/add-to-array-form-of-integer/
 * 即通过数组与一个整数进行相加，获取最后的结果，存入到list中
 */
public class Leetcode989_AddToArrayForm {
    public static void main(String[] args) {
        Leetcode989_AddToArrayForm test = new Leetcode989_AddToArrayForm();
        System.out.println(test.addToArrayForm(new int[]{1, 0, 0, 0}, 34));
        System.out.println(test.addToArrayForm(new int[]{2, 7, 4}, 181));
        System.out.println(test.addToArrayForm(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, 1));
    }

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        int n = A.length;
        for (int i = n - 1; i >= 0; i--) {
            int sum = A[i] + K % 10;
            K /= 10;
            // 此时把进位留给前面
            if (sum >= 10) {
                K++;
                sum -= 10;
            }
            list.add(sum);
        }
        // 这里可能是K没有了，这个不用处理，如果是数组没有了，则K需要继续计数
        for (; K > 0; K /= 10) {
            list.add(K % 10);
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 第一种方法比较直白，一位一位相加包含进位
     */
    /*public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        // 表示进位
        int carry = 0;
        // A=[1,2,0,0] K=34
        int l = A.length - 1;
        while (l >= 0 && K != 0) {
            int sum = A[l--] + K % 10 + carry;
            K /= 10;
            carry = sum / 10;
            int res = sum % 10;
            list.add(0, res);
        }
        // 如果因为数组到头了，此时K肯定还不等于0，此时对K进行运算即可
        while (K != 0) {
            int sum = K % 10 + carry;
            K /= 10;
            carry = sum / 10;
            int res = sum % 10;
            list.add(0, res);
        }
        // 如果因为K为0了，此时对数组进行运算即可
        while (l >= 0) {
            int sum = A[l--] + carry;
            carry = sum / 10;
            int res = sum % 10;
            list.add(0, res);
        }
        if (carry != 0) {
            list.add(0, carry);
        }
        return list;
    }*/
}
