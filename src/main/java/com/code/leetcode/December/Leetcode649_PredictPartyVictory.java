package com.code.leetcode.December;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoduanxi
 * @Date 2020/12/11 21:35
 * @Motto Keep thinking, keep coding!
 * leetcode 649、Dota2参议院
 * 题目：https://leetcode-cn.com/problems/dota2-senate/
 * 题目的意思是两个阵营的人同时投票，排在前面的可以将排在后面的对手的投票权利禁止
 * 循环比较下去，判断最后只剩下的发言权的人是哪边的就算哪边获胜！
 */
public class Leetcode649_PredictPartyVictory {
    public static void main(String[] args) {
        Leetcode649_PredictPartyVictory test = new Leetcode649_PredictPartyVictory();
        System.out.println(test.predictPartyVictory("RDRDRDRDDRR"));
        System.out.println(test.predictPartyVictory("RDD"));
    }

    public String predictPartyVictory(String senate) {
        if (senate.length() == 1) {
            return senate.charAt(0) == 'R' ? "Radiant" : "Dire";
        }
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') radiant.add(i);
            else dire.add(i);
        }
        int len = senate.length();
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            // 这里要做的就是循环比较，一直抗衡下去
            int r = radiant.remove();
            int d = dire.remove();
            if (r < d) {
                radiant.offer(r + len);
            } else {
                dire.offer(d + len);
            }
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
