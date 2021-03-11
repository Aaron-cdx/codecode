package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/11 9:41
 * @Motto Keep thinking, keep coding!
 * leetcode 227、基本计算器II
 * 题目：https://leetcode-cn.com/problems/basic-calculator-ii/
 */
public class Leetcode227_Calculate {
    /**
     * 思路就是利用一个栈，把做+-都按照符号直接放入栈中
     * 做乘除的手动出栈计算即可
     */
    public int calculate(String s) {
        Deque<Integer> number = new LinkedList<>();
        int n = s.length();
        int num = 0;
        // 记录字符的作用，默认是+
        char sign = '+';
        // 这里完全就是思路的问题了
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 如果是数字的话
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if((c !=' ' && !Character.isDigit(c)) || i == n-1 ){
                switch (sign){
                    case '+':
                        number.push(num);// +的话直接放入即可，即首字母放入
                        break;
                    case '-':
                        number.push(-num);
                        break;
                    case '*':// 乘法的话，此处需要将前面的数字取出来做运算之后再放入
                        number.push(number.pop()*num);
                        break;
                    default:
                        number.push(number.pop()/num);
                }
                // 处理完之后，需要记录前面的符号是
                sign = c;
                num = 0;// num需要置零
            }
        }
        int ans = 0;// 统计其中的和就好
        while(!number.isEmpty()){
            ans += number.pop();
        }
        return ans;
    }
}
