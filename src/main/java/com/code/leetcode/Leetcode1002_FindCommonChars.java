package com.code.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/10/14 8:55
 * @Motto Keep thinking, keep coding!
 * leetcode 1002、查找常用字符
 * 返回列表中每个字符串中都显示的全部字符（包含重复字符）组成的列表
 * ["bella","label","roller"] => ["e","l","l"]
 * ["cool","lock","cook"] => ["c","o"]
 *
 * 2020-10-14 10:45:45 这一题初次写的时候写出来了，二次刷题居然写不出来!真是戏谑！
 * 自己要好好加油了，感觉最近一直在退步！
 */
public class Leetcode1002_FindCommonChars {
    public static void main(String[] args) {
        Leetcode1002_FindCommonChars test = new Leetcode1002_FindCommonChars();
        System.out.println(test.commonChars(new String[]{"bella", "label", "roller"}));
        System.out.println(test.commonChars(new String[]{"cool", "lock", "cook"}));
    }

    /**
     * 先使用暴力法解决问题才是王道
     */
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        // 这里需要做处理
        int[] arr = new int[26];
        for (char c : A[0].toCharArray()) {
            arr[c - 'a']++;
        }
        // 对比每一个单词，然后一个一个比对
        for (int i = 1; i < A.length; i++) {
            int[] temp = new int[26];
            for (char c : A[i].toCharArray()) {
                temp[c - 'a']++;
            }
            for (int j = 0; j < temp.length; j++) {
                arr[j] = Math.min(arr[j], temp[j]);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }
}
