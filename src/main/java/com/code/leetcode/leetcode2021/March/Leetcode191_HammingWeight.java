package com.code.leetcode.leetcode2021.March;

/**
 * @author caoduanxi
 * @Date 2021/3/22 8:52
 * @Motto Keep thinking, keep coding!
 * leetcode 191、位1的个数
 * 题目：https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class Leetcode191_HammingWeight {
    public static void main(String[] args) {
        Leetcode191_HammingWeight test = new Leetcode191_HammingWeight();
//        System.out.println(test.hammingWeight(11111111111111111111111111111101));
    }
    // you need to treat n as an unsigned value

    /**
     * 如果数字二进制过大 则会超过Integer的范围，此时会呈现报错：过大的整数
     * 如何解决数字过大的问题呢？
     */
    /*public int hammingWeight(int n) {
        int cnt = 0;
        int index = 0;
        while (index < 31) {
            if ((n & 1) == 1) cnt++;
            n >>>= 1;
            index++;
        }
        return cnt;
    }*/

    // 这是计算1的个数，只要不为0表示内部还有1，需要继续下去
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            n &= n - 1;
            cnt++;
        }
        return cnt;
    }

}
