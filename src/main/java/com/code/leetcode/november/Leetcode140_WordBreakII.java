package com.code.leetcode.november;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/11/1 9:39
 * @Motto Keep thinking, keep coding!
 * leetcode 140、单词拆分II
 * 题目：https://leetcode-cn.com/problems/word-break-ii/
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 */
public class Leetcode140_WordBreakII {
    /**
     * 利用动态规划+回溯法解决这个问题
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<List<String>>> map = new HashMap<>();
        List<List<String>> wordBreaks = bachTrack(s, s.length(), new HashSet<>(wordDict), 0, map);
        List<String> breakList = new ArrayList<>();
        // 后面将结果里面的数取出
        for (List<String> wordBreak : wordBreaks) {
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    private List<List<String>> bachTrack(String s, int length, HashSet<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        // 如果不包含才需要开始计算
        if (!map.containsKey(index)) {
            List<List<String>> wordBreaks = new LinkedList<>();
            if (index == length) {
                wordBreaks.add(new LinkedList<>());
            }
            for (int i = index + 1; i <= length; i++) {
                String word = s.substring(index, i);
                if (wordSet.contains(word)) {
                    List<List<String>> newWordBreaks = bachTrack(s, length, wordSet, i, map);
                    // 这里表示当前第一个可以组成的，和后续的结合可以组成多少对
                    for (List<String> newWordBreak : newWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<>(newWordBreak);
                        // 将它放在第一个
                        wordBreak.offerFirst(word);
                        // 给总的添加进去
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);
    }
}
