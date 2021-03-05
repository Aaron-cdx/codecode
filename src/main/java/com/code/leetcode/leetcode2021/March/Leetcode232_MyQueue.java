package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2021/3/5 10:17
 * @Motto Keep thinking, keep coding!
 * leetcode 232、用栈实现队列
 * 题目：https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * 使用栈实现队列
 * 2 1 => 1 2
 *
 */
public class Leetcode232_MyQueue {
}
/*
class MyQueue{
    // 使用两个栈
    // 队列先进先出，栈先进后出，所以使用stack1去接纳新增的数据，取出的时候使用stack2去吐出来
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    */
/** Initialize your data structure here. *//*

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        // 1 2 3 4 5
        // 5 4 3 2 1
    }

    */
/** Push element x to the back of queue. *//*

    // 比如 1 2  => 1 2  但是栈的话是 2 1
    // 这样是每次都要倒放一次
    public void push(int x) {
        while(!stack2.isEmpty()){
            stack1.add(stack2.pop());
        }
        stack1.add(x);
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    */
/** Removes the element from in front of queue and returns that element. *//*

    public int pop() {
        return stack2.pop();
    }

    */
/** Get the front element. *//*

    public int peek() {
        return stack2.peek();
    }

    */
/** Returns whether the queue is empty. *//*

    public boolean empty() {
        return stack2.isEmpty();
    }
}
*/

class MyQueue{
    private Deque<Integer> inStack;
    private Deque<Integer> outStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(outStack.isEmpty()){
            in2out();
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(outStack.isEmpty()){
            in2out();
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void in2out(){
        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }
}
