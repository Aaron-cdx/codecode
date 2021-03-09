package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/9 9:17
 * @Motto Keep thinking, keep coding!
 * leetcode 1047、删除字符串中所有相邻重复项
 * 题目：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 */
public class Leetcode1047_RemoveDuplicates {
    public static void main(String[] args) {
        Leetcode1047_RemoveDuplicates test = new Leetcode1047_RemoveDuplicates();
        System.out.println(test.removeDuplicates("abbaca"));
    }

    public String removeDuplicates(String S) {
        int n = S.length();
        if (n <= 1) return S;
        char[] chars = S.toCharArray();
        int top = -1;
        // 直接修改本地数组的形式
        for (int i = 0; i < n; i++) {
            if (top == -1 || chars[top] != chars[i]) {
                chars[++top] = chars[i];
            } else {
                top--; // 表示移除操作
            }
        }
        return String.valueOf(chars, 0, top + 1);
    }
    /**
     * 注意相邻两元素相同才移除
     */
    /*public String removeDuplicates(String S) {
        int n = S.length();
        if (n <= 1) return S;
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if(deque.isEmpty() || deque.peek() != S.charAt(i)){
                deque.push(S.charAt(i));
            }else{
                deque.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }*/


    /*public String removeDuplicates(String S) {
        int n = S.length();
        if (n <= 1) return S;
        Deque<Character> deque = new LinkedList<>();
        // abbca // a b - b - b a | c a
        int r = 0;
        while (r < n) {
            if (deque.isEmpty() || deque.peek() != S.charAt(r)) {
                deque.push(S.charAt(r));
            }// 如果相等的话就要找到最后一个相等的位置然后在移除
            else {
                while (r + 1 < n && S.charAt(r + 1) == S.charAt(r)) {
                    r++;
                }
                deque.pop();
            }
            r++;
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }*/
}
