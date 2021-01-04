package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/4 9:18
 * @Motto Keep thinking, keep coding!
 * Leetcde 509、斐波那契数
 * 题目：https://leetcode-cn.com/problems/fibonacci-number/
 * 题目的意思是说输出n的斐波那切数
 */
public class Leetcode509_Fibonacci {
    public static void main(String[] args) {
        Leetcode509_Fibonacci test = new Leetcode509_Fibonacci();
        System.out.println(test.fib(3));
    }

    public int fib(int n) {
        int pre = 0;
        int next = 1;
        if(n == 0) return 0;
        if(n == 1) return 1;
        for (int i = 2; i <= n; i++) {
            int temp = next;
            next = pre + next;
            pre = temp;
        }
        return next;
    }
}
