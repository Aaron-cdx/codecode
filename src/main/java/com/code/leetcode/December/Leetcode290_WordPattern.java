package com.code.leetcode.December;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/12/16 9:23
 * @Motto Keep thinking, keep coding!
 * leetcode 290、单词规律
 * 题目：https://leetcode-cn.com/problems/word-pattern/
 * 题意表示给出一个模式，abba，则在str中出现的单词需要符合abba，即dog cat cat dog是符合的
 * 即在pattern中相等的，则在str中变成单词也要相等
 */
public class Leetcode290_WordPattern {
    public static void main(String[] args) {
        Leetcode290_WordPattern test = new Leetcode290_WordPattern();
        System.out.println(test.wordPattern("abba", "dog cat cat dog"));
        System.out.println(test.wordPattern("abba", "dog cat cat fish"));
        System.out.println(test.wordPattern("abba", "dog dog dog dog"));
        System.out.println(test.wordPattern("abc", "dog cat dog"));
        System.out.println(test.wordPattern("aaa", "aa aa aa"));
    }

    /*public boolean wordPattern(String pattern, String s) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new HashMap<>();
        // 建立双向映射的a->dog dog->a
        String[] patterns = pattern.split("");
        String[] ss = s.split(" ");
        if(patterns.length != ss.length) return false;
        int len = patterns.length;
        for (int i = 0; i < len; i++) {
            // 如果包含a,则判断a->dog
            if (map.containsKey(patterns[i])) {
                if (!map.get(patterns[i]).equals(ss[i])) {
                    return false;
                }
            } else {
                // 如果不存在正的映射，则放入正的映射
                map.put(patterns[i], ss[i]);
                // 同时判断反的映射是否存在，存在的话，判断是否等，不等的话则返回false
                if (map1.containsKey(ss[i])) {
                    if (!map1.get(ss[i]).equals(patterns[i])) return false;
                } else {
                    map1.put(ss[i], patterns[i]);
                }
            }
        }
        return true;
    }*/

    public boolean wordPattern(String pattern, String s) {
        if(pattern == null || s == null) return false;
        String[] pp = pattern.split("");
        String[] ss = s.split(" ");
        if(pp.length != ss.length) return false;
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < pp.length; i++) {
            if(map.containsKey(pp[i])){
                // 包含的话，若不等直接返回false
                if(!map.get(pp[i]).equals(ss[i])) return false;
            }else{
                // 不包含的话需要判断值是否曾经出现，防止一对多
                if(map.containsValue(ss[i])) return false;
                map.put(pp[i],ss[i]);
            }
        }
        return true;
    }
}
