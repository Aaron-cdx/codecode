package com.code.leetcode;

import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/10/19 8:52
 * @Motto Keep thinking, keep coding!
 * leetcode 844、比较含退格的字符串
 * 题目：https://leetcode-cn.com/problems/backspace-string-compare/
 * S = "ab#c", T = "ad#c" => ture S和T最后的答案都是ac == ac => true
 */
public class Leetcode844_BackSpaceCompare {
    public static void main(String[] args) {
        Leetcode844_BackSpaceCompare test = new Leetcode844_BackSpaceCompare();
    }

    /**
     * 题目要求的时间复杂度是O(N)，空间复杂度是O(1),表示不可以使用额外的空间
     */
    public boolean backspaceCompare(String S, String T) {
        // 只需要比较最后是否相同即可
        int i = S.length() - 1;
        int j = T.length() - 1;
        int back = 0;
        while (true) {
            back = 0;
            // 这里指代主要包含退位的符号就一直进行下去
            while (i >= 0 && (back > 0 || S.charAt(i) == '#')) {
                // 这里用退位符号的加减来表示需要移动的位置！
                back += S.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            back = 0;
            while (j >= 0 && (back > 0 || T.charAt(j) == '#')) {
                back += T.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else {
                break;
            }
        }
        return i == -1 && j == -1;
    }

    /**
     * 这里的时间复杂度是O(N),空间复杂度是O(N)
     */
    public boolean backspaceCompareII(String S, String T) {
        if (S == null || T == null) return false;

        return solveSpace(S).equals(solveSpace(T));
    }

    public String solveSpace(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            // 如果为空了还遇到退格，直接跳过
            if (S.charAt(i) == '#') {
                if (stack.isEmpty()) continue;
                stack.pop();
            } else {
                stack.add(S.charAt(i));
            }
        }
        return stack.toString();
    }
}
