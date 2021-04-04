package com.code.leetcode.leetcode2021.April;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2021/4/4 10:10
 * @Motto Keep thinking, keep coding!
 * leetcode 781、森林中的兔子
 * 题目：https://leetcode-cn.com/problems/rabbits-in-forest/
 */
public class Leetcode781_NumRabbits {
    /**
     * 森林中的兔子
     */
    /*public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        if (answers.length == 0) return 0;
        int ans = 0;
        int n = answers.length;
        for (int i = 0; i < n; i++) {
            int num = answers[i];
            // 如果遇到一个，则当前颜色的兔子共有n+1只，但需要跳过后续说同样数目的兔子
            ans += num + 1;
            while (num-- > 0 && i + 1 < n && answers[i + 1] == answers[i]) {
                i++;
            }
        }
        return ans;
    }*/

    /**
     * 根据数学推出兔子个数
     * 2 2 2 当前兔子数为 3   (3+2)/(2+1)=1
     * 2 2 2 2 当前兔子数为6  (4+2)/(2+1)=2
     * 2 2 2 2 2 2 当前兔子数为6 (6+2)/(2+1)=2
     * 假设说n的兔子有x+1只
     * 则 n%(x+1) == 0 表示此时兔子颜色种类为n/(x+1)
     * 则 n%(x+1) != 0 表示此时兔子颜色种类为n/(x+1)+1  表示一定存在多出来的一种颜色
     * <p>
     * 实际上碰到多1和少1的现象，基本除法都可以解决这个问题
     */
    public int numRabbits(int[] answers) {
        int n = answers.length;
        int res = 0;
        if (n == 0) return res;
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int first = entry.getKey();
            int second = entry.getValue();
            res += (first + second) / (first + 1) * (first + 1);
        }
        return res;
    }
}
