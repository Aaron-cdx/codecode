package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/20 9:28
 * @Motto Keep thinking, keep coding!
 * leetcode 150、逆波兰表达式求值
 * 题目：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class Leetcode150_EvalRPN {
    /**
     * 求解逆波兰表达式的值
     * 实质很简单，遇到数字就入栈，遇到运算符则出栈进行计算，然后将计算好的数据入栈即可
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        // 如何判断一个字符是数字还是操作运算符
        for (String token : tokens) {
            if (isOperator(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(calculate(a, b, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    /**
     * 计算具体的表达式
     */
    public int calculate(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return b + a;
            case "-":
                return b - a;
            case "*":
                return b * a;
            default:
                return b / a;
        }
    }
}
