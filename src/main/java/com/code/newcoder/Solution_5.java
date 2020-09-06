package com.code.newcoder;

import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/9/4 21:45
 * 用两个栈来实现一个队列
 */
public class Solution_5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        // 永远保持插入数据的时候，2要清空所有的数据，这样1再插入的时候就是倒序，再放入到2中就是正序
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public int pop() {
        return stack2.pop();
    }
}
