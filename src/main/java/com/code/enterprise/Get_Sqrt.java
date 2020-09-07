package com.code.enterprise;

/**
 * @author caoduanxi
 * @Date 2020/9/7 16:40
 * 求某一个整数的平方根
 * 标签：二分
 * 思路，采用二分法查找当前值，判断是否太大，太大移动当前标签
 */
public class Get_Sqrt {
    public int sqrt(int x) {
        if (x == 1) return 1;
        int max = x;
        int min = 0;
        while (max - min > 1) {
            int m = (max + min) / 2;
            if (x / m < m) {
                max = m;
            } else {
                min = m;
            }
        }
        return min;
    }
}
