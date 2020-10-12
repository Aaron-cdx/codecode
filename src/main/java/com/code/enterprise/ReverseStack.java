package com.code.enterprise;

import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/10/12 17:11
 * @Motto Keep thinking, keep coding!
 * 原地反转栈
 * 递归用的栈空间忽略不计
 */
public class ReverseStack {
    public static void main(String[] args) {
        ReverseStack test = new ReverseStack();
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
//        while (!stack.isEmpty()){
//            System.out.print(stack.pop()+" ");
//        }
        test.reverseStack(stack);
        System.out.println();
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * 这里采取的翻转手段是
     * 获取所有的最后的值，利用递归的特性，因为在局部犯法中有局部变量栈空间
     */
    public void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        // 否则的话获取响应的值
        int last = getLast(stack);
        reverseStack(stack);
        stack.push(last);
    }

    private int getLast(Stack<Integer> stack) {
        // 先吐出来第一个
        int val = stack.pop();
        if (stack.isEmpty()) {
            // 如果上面值已经是最后一个的话
            return val;
        }
        // 否则的话此时需要继续获取最后一个值
        int last = getLast(stack);
        stack.push(val);// 然后吧上一个的val插入其中
        return last;// 将最后一个值返回
    }

//    public void reverseStack(Stack<Integer> stack) {
//        if (stack.isEmpty()) return;
//        int last = getLast(stack);
//        reverseStack(stack);
//        stack.push(last);
//    }
//
//    public int getLast(Stack<Integer> stack) {
//        int val = stack.pop();
//        if(stack.isEmpty()){
//            return val;
//        }else{
//            int value = getLast(stack);
//            stack.push(val);
//            return value;
//        }
//    }

}
