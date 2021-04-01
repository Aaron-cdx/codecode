package com.code.leetcode.leetcode2021.April;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/4/1 8:54
 * @Motto Keep thinking, keep coding!
 * leetcode 1006、笨阶乘
 * 题目：https://leetcode-cn.com/problems/clumsy-factorial/
 * <p>
 * 思路：这题的思路可以使用递归直接求值，还有就是通过数学的规律求出最简洁的表达式
 * 或者使用栈的思路，模拟计算，对不同的运算符采用不同的入栈出栈计算，最后对栈中的
 * 值进行求和操作即可。
 */
public class Leetcode1006_Clumsy {
    /**
     * 通过 * / + - 来把每一个阶乘做一个决断
     * 乘除必须放在一起做运算操作，怎么处理多运算符的问题呢？
     * 10
     * 10*9/8+7 - 6*5/4+3 - 2*1 = 12
     */
   /* public int clumsy(int N) {
        if (N <= 2) return N;
        if (N == 3) return 6;
        int sum = N * (N - 1) / (N - 2) + N - 3;
        N -= 4;
        while (N >= 4) {
            sum += -N * (N - 1) / (N - 2) + N - 3;
            N -= 4;
        }
        return sum - clumsy(N);
    }*/

    // 使用栈模拟

    /**
     * 如果是-号则不出栈，进行计算在出栈
     */
    public int clumsy(int N) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N);
        N--;

        int index = 0; // 控制加减乘除
        while (N > 0) {
            // *
            if (index % 4 == 0) {
                stack.push(stack.pop() * (N));
            } else if (index % 4 == 1) {
                // /
                stack.push(stack.pop() / (N));
            } else if (index % 4 == 2) {
                // +
                stack.push(N);
            } else {
                // -,此时不用弹出,给数字加一个负号放入即可
                stack.push(-(N));
            }
            index++;
            N--;
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
