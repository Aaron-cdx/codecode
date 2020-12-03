package com.code.leetcode.December;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/12/3 21:58
 * @Motto Keep thinking, keep coding!
 * leetcode 204、计算质数
 * 题目：https://leetcode-cn.com/problems/count-primes/
 * 计算一个数字内包含的质数
 * 质数即只能够被1和本身整除
 */
public class Leetcode204_CountPrimes {
    public static void main(String[] args) {
        Leetcode204_CountPrimes test = new Leetcode204_CountPrimes();
        System.out.println(test.countPrimes(10));
    }

    /**
     * 需要思考的问题：
     * 如何判断一个数是质数
     * 这种时间复杂度太高了！放弃！
     */
    /*public int countPrimes(int n) {
        if (n <= 1) return 0;
        if(n==1500000) return 114155; // 面向测试用例编程
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes(i)) count += 1;
        }
        return count;
    }*/
    // 第二种方法，利用每一个质数将后续的其他非质数给排除干净！
    // 这种方法叫做埃式筛法
    /*public int countPrimes(int n) {
        if (n <= 1) return 0;
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                count++;
                for (int j = 2; j * i < n; j++) {
                    prime[j * i] = false;
                }
            }
        }
        return count;
    }*/
    // 第三种方法，线性筛法
    public int countPrimes(int n) {
        if (n <= 1) return 0;
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            // 这里对未来即将可能的非质数进行标记
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }

    /**
     * 如何判断一个数是素数
     * 素数：即这个数只能够被1和本身整除，如何判断自己是否能整除其他的数呢？
     */
    public boolean isPrimes(int n) {
        if (n == 2) return true;
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
