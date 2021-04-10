package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/10 9:30
 * @Motto Keep thinking, keep coding!
 * leetcode 263、丑数
 * 题目：https://leetcode-cn.com/problems/ugly-number/
 */
public class Leetcode263_IsUgly {
    public static void main(String[] args) {
        Leetcode263_IsUgly test = new Leetcode263_IsUgly();
        System.out.println(test.isUgly(55));
    }

    public boolean isUgly(int n) {
        if (n == 0) return false;
        if (n == 1) return true;
        if (n % 2 == 0) {
            return isUgly(n / 2);
        }
        if (n % 3 == 0) {
            return isUgly(n / 3);
        }
        if (n % 5 == 0) {
            return isUgly(n / 5);
        }
        return false;
    }
}
