package com.code.leetcode.December;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/12/14 9:10
 * @Motto Keep thinking, keep coding!
 * leetcode 49、字母异位词分组
 * 题目：https://leetcode-cn.com/problems/group-anagrams/
 * 题目意思，就是将字符串数组中异位词分组，存入列表中即可
 */
public class Leetcode49_GroupAnagram {
    public static void main(String[] args) {
        Leetcode49_GroupAnagram test = new Leetcode49_GroupAnagram();
        System.out.println(test.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    /**
     * 时间复杂度：O(nklogk) k为字符串数组中每个字符的长度k
     * 空间复杂度：O(nk) k含义同上
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        if (strs == null || strs.length == 0) return list;
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            char[] tempStr = str.toCharArray();
            Arrays.sort(tempStr);
            String tempString = new String(tempStr);
            if (map.containsKey(tempString)) {
                int index = map.get(tempString);
                list.get(index).add(str);
            } else {
                // 否则就是新的，此时构建list存入，然后将index存入到map中
                List<String> tempList = new ArrayList<>();
                tempList.add(str);
                list.add(tempList);
                map.put(tempString, list.size() - 1);
            }
        }
        return list;
    }
}
