package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/10 21:23
 * @Motto Keep thinking, keep coding!
 * leetcode 860、柠檬水找零
 * 题目：https://leetcode-cn.com/problems/lemonade-change/
 * 题目的意思就是5/10/20,判断每一个依次进入的顾客是否可以完美的找零
 * [5,5,5,10,20]
 * // 注意一开始手上是没有任何零钱的！
 * []所以只要是大于5的开头直接就跳过
 */
public class Leetcode860_LemonadeChange {
    public static void main(String[] args) {
        Leetcode860_LemonadeChange test = new Leetcode860_LemonadeChange();
        System.out.println(test.lemonadeChange(new int[]{5, 5, 5, 10, 20}));
        System.out.println(test.lemonadeChange(new int[]{5, 5, 10}));
        System.out.println(test.lemonadeChange(new int[]{10, 10}));
        System.out.println(test.lemonadeChange(new int[]{5, 5, 10, 10, 20}));
    }

    /**
     * 常规思维，即标记5,10的数量，遇到10只能够用5解决，遇到20可以使用10和5解决
     * 只要判断数量，只要不满足直接返回false
     * 其实这就是贪心算法的思想：
     * 贪心算法是指，在对问题求解时，总是做出在当前看来最好的选择，也就是说不从整体最优上加以考虑
     * 认定所做出的是在某种意义上的局部最优解。
     * 贪心算法不是对所有问题都能得到整体最优解，关键是贪心策略的选择，选择的贪心策略具备无后效性，
     * 即在个状态以前的过程不会影响以后的状态，只与当前状态有关。
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) return false;
        if (bills[0] > 5) return false;
        int five = 0;
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five += 1;
            } else if (bill == 10) {
                if (five >= 1) {
                    ten += 1;
                    five -= 1;
                } else {
                    return false;
                }
            } else {
                if (ten >= 1 && five >= 1) {
                    ten -= 1;
                    five -= 1;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
