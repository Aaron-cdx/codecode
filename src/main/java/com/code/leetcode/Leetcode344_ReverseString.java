package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/8 8:59
 * @Motto Keep thinking, keep coding!
 * leetcode 344、反转字符串
 * hello => olleh
 * 原地修改，双指针
 */
public class Leetcode344_ReverseString {
    public void reverseString(char[] s) {
        if(s==null || s.length == 1) return;
        int l = 0;
        int r = s.length-1;
        while(l < r){
            swap(s,l++,r--);
        }
    }

    public void swap(char[] s, int a, int b){
        char tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;
    }
}
