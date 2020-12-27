package com.code.leetcode.December;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/12/27 9:27
 * @Motto Keep thinking, keep coding!
 * leetcode 205、同构字符串
 * 题目：https://leetcode-cn.com/problems/isomorphic-strings/
 * 题目意思
 * egg => add => true
 * foo => bar => false
 * paper => title => true
 */
public class Leetcode305_IsIsomorphic {
    public static void main(String[] args) {
        Leetcode305_IsIsomorphic test = new Leetcode305_IsIsomorphic();
        System.out.println(test.isIsomorphic("egg","add"));
        System.out.println(test.isIsomorphic("foo","bar"));
        System.out.println(test.isIsomorphic("paper","title"));
    }
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        int index = 0;
        while(index < s.length()){
            char ss = s.charAt(index);
            char tt = t.charAt(index);
            if(map.containsKey(ss)){
                if(map.get(ss) != tt) return false;
            }else{
                // 添加进去的时候先判断当前的tt值是否存在，存在的话表示之前映射了
                if(map.containsValue(tt)) return false;
                map.put(ss,tt);
            }
            index++;
        }
        return true;
    }
}
