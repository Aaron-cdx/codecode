package com.code.leetcode.pratice;

/**
 * @author caoduanxi
 * @Date 2021/2/23 10:42
 * @Motto Keep thinking, keep coding!
 * leetcode 258、各位相加
 * 题目：https://leetcode-cn.com/problems/add-digits/
 * 即各个位上相加直到结果为一位数则输出
 */
public class Leetcode258_AddDigits {
    public static void main(String[] args) {
        Leetcode258_AddDigits test = new Leetcode258_AddDigits();
        System.out.println(test.addDigits(38));
    }

    /**
     * 原则上是不允许使用递归或者循环来解题
     * 先通过递归来解题
     * 递归完美实现题目解决
     */
    /*public int addDigits(int num) {
        if (num / 10 == 0) return num;
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        return addDigits(total);
    }*/

    /**
     * 如果不使用递归的方式解决这个问题
     * 这里根据分析可以得到
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
     * 1 2 3 4 5 6 7 8 9 1  2  3  4  5  6  7  8
     * 这里可以看出来9个一轮询，交替变化
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
