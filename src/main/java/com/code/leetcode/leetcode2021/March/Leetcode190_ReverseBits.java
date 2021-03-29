package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/29 9:42
 * @Motto Keep thinking, keep coding!
 * leetcode 190、颠倒二进制位
 * 题目：https://leetcode-cn.com/problems/reverse-bits/
 */
public class Leetcode190_ReverseBits {
    /**
     * 输入: 00000010100101000001111010011100
     * 输出: 00111001011110000010100101000000
     * 直接反过来求值即可
     */
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 获取当前值并向左移动31-i位 表示第31位不需要移动
            ans += (1 & (n >> i)) << (31 - i);
        }
        return ans;
    }
}
