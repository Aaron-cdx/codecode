package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/21 9:08
 * @Motto Keep thinking, keep coding!
 * leetcode 925、长按键入
 * name = "alex", typed = "aaleex" => true
 * 即发现当前的元素重复了n次，所以可以使用当前的元素
 * 题目：https://leetcode-cn.com/problems/long-pressed-name/
 */
public class Leetcode925_IsLongPressedName {
    public static void main(String[] args) {
        Leetcode925_IsLongPressedName test = new Leetcode925_IsLongPressedName();
        System.out.println(test.isLongPressedName("alex", "aaleex"));
        System.out.println(test.isLongPressedName("saeed", "ssaaedd"));
        System.out.println(test.isLongPressedName("ppyplrza", "pyypllrza"));
        System.out.println(test.isLongPressedName("pyplrz", "ppyypllr"));
        System.out.println(test.isLongPressedName("zlexya", "aazlllllllllllllleexxxxxxxxxxxxxxxya"));
    }

    public boolean isLongPressedNameII(String name, String typed) {
        int i = 0;
        int j = 0;
        // 让j先行，i随其后
        while (j < typed.length()) {
            // 这里限制i的大小
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                // 如果不相等且i到了中点后面还有不同的，直接输出false
                return false;
            }
        }
        // 否则就看是否是相同的将typed字符串耗尽此时判断i是否等于name.length
        return i == name.length();
    }


    public boolean isLongPressedName(String name, String typed) {
        int n = name.length()-1;
        int t = typed.length() - 1;
        while(t >= 0){
            if(n >= 0 && name.charAt(n) == typed.charAt(t)){
                n--;
                t--;
            }else if(t < typed.length() - 1 && typed.charAt(t) == typed.charAt(t+1)){
                t--;
            }else{
                return false;
            }
        }
        return n == -1;
    }

    /**
     * 从后往前扫描的步骤比较麻烦，自己写的比较繁琐，其实可以参照从前往后的步骤写
     */
    public boolean isLongPressedNameI(String name, String typed) {
        int n = name.length() - 1;
        int t = typed.length() - 1;
        while (n >= 0 && t >= 0) {
            // 如果两个正好相等，则一直向前
            if (name.charAt(n) == typed.charAt(t)) {
                n--;
                t--;
            } else {
                // 否则则是遇到了不等的情况此时需要，先把重复的抹零
                while (t + 1 < typed.length() && typed.charAt(t) == typed.charAt(t + 1)) {
                    t--;
                }
                // 抹零之后判断是否等，不等的话就直接输出false了
                if (name.charAt(n) == typed.charAt(t)) {
                    n--;
                    t--;
                } else {
                    return false;
                }
            }
        }
        // 这里是做一个收尾的工作
        if (n >= 0) {
            return false;
        }
        if (t >= 0) {
            while (t >= 0 && t + 1 < typed.length() && typed.charAt(t) == typed.charAt(t + 1)) {
                t--;
            }
            return t == -1;
        }
        return true;
    }
}
