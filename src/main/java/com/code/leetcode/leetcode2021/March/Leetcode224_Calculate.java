package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/10 9:40
 * @Motto Keep thinking, keep coding!
 * leetcode 224、基本计算器
 * 题目：https://leetcode-cn.com/problems/basic-calculator/
 */
public class Leetcode224_Calculate {
    /**
     * 整体的思路就是对于运算符来说，进入括号时候的运算符需要注意
     * 同时保持一个ret变量计算结果，对于进入括号与不进入括号时候的符号需要记录
     * 如果是+号进入括号的话，则不影响计算，如果是-号进入括号的话，需要标记为减法操作
     */
    public int calculate(String s) {
        Deque<Integer> number = new LinkedList<>();
        int n = s.length();
        number.push(1); // 最开始默认为1
        int sign = 1;

        int i = 0;
        int ret = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                // 如果是+号，此时sign重新赋值为1
                sign = number.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                // sign = -1
                sign = -number.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                // 表示此时进入到括号内，此时需要插入前面的sign，以确保进入到括号内的符号问题
                number.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                // 表示出括号，此时需要把进括号插入的sign弹出
                number.pop();
                i++;
            } else {
                // 否则就是表示当前是数字了，需要开始进行计算
                long num = 0;
                // 防止越界同时保证为数字才继续遍历下去可能是2345这种数字
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                // 计算结果
                ret += sign * num;// sign表示前面的符号
            }
        }
        return ret;
    }
}
