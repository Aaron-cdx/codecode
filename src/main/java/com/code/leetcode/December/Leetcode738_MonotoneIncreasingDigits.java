package com.code.leetcode.December;

/**
 * @author caoduanxi
 * @Date 2020/12/15 9:16
 * @Motto Keep thinking, keep coding!
 * leetcode 738、单调递增的数字
 * 题目：https://leetcode-cn.com/problems/monotone-increasing-digits/
 * 给一个数N，找出小于或者等于这个数的整数，且这个数需要满足，单调递增
 * 即xy  x <= y
 * 10 => 9
 * 1234 => 1234
 * 332 => 299
 * 10000 => 9999
 * <p>
 * 10
 * 1234 => 1 2 3 4
 * 3 3 2 => 2 9 9
 * 10000 => 9 9 9 9
 */
public class Leetcode738_MonotoneIncreasingDigits {
    public static void main(String[] args) {
        Leetcode738_MonotoneIncreasingDigits test = new Leetcode738_MonotoneIncreasingDigits();
//        System.out.println(test.isUp(332));
        System.out.println(test.monotoneIncreasingDigits(332));
//        System.out.println(test.monotoneIncreasingDigits(1234));
    }

    /**
     * 第一种做法，采用字符数组的方式来做
     * 只要后面比前面大，则前面-1，后面所有的都变为9即可
     */
    /*public int monotoneIncreasingDigits(int N) {
        // 否则表明当前数根本不是一直递增的。比如10、332
        // 10的话 我怎么知道是把9取出来呢？
        char[] chars = String.valueOf(N).toCharArray();
        int len = chars.length;
        for (int i = len - 1; i > 0; i--) {
            if (chars[i] < chars[i - 1]) {
                chars[i - 1]--;
                for (int j = i; j < len; j++) {
                    chars[j] = '9';
                }
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }*/

    /**
     * 第二种解法：则知道某一位后面全是补9，则一直找到第一位一定是递增的，然后-1则直接补9了
     */
    /*public int monotoneIncreasingDigits(int N) {
        if (isUp(N)) {
            return N;
        }
        int base = 1;
        // 这里的思想是，其实就是给后面补1，那么我就一直向前递归，直到直到第一个，这样的话只要乘以位数就可
        while (N > 10) {
            if (isUp(N - 1)) {
                return N * base - 1;
            }
            N /= 10;
            base *= 10;
        }
        return N * base - 1;
    }


    public boolean isUp(int n) {
        // 判断当前数是不是递增的
        // 即从后往前是否是递减的
        int last = n % 10;
        n /= 10;
        while (n != 0) {
            int temp = n % 10;
            n /= 10;
            if (temp > last) return false;
            last = temp;
        }
        return true;
    }*/

    /**
     * 第三种解法：题解里面的
     * 时间复杂度O(lngN) N表示位数
     * 空间复杂度O(logN)
     *
     */
    public int monotoneIncreasingDigits(int N) {
        char[] chars = String.valueOf(N).toCharArray();
        int i = 1;
        int len = chars.length;
        while (i < len && chars[i - 1] <= chars[i]) {
            i++;
        }
        // 当前i的位置即为下一位比自己小的位置
        if (i < len) {
            // 则前面的都要-1，后面的都置为9
            // 自身位数减一之后还是保持递增关系，则停止
            while (i > 0 && chars[i - 1] > chars[i]) {
                chars[i - 1]--;// 直到i为1,或者第一个是
                i--;
            }
            for (i += 1; i < len; i++) {
                chars[i] = '9';
            }
        }
        // 如果i不小于len，直接返回了，因为遍历到底了
        return Integer.parseInt(String.valueOf(chars));
    }
}
