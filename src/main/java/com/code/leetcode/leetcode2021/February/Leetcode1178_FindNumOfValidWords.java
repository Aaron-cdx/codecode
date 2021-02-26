package com.code.leetcode.leetcode2021.February;

import sun.text.normalizer.Trie;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/2/26 9:44
 * @Motto Keep thinking, keep coding!
 * leetcode 1178、猜字谜
 * 题目：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle/
 */
public class Leetcode1178_FindNumOfValidWords {
    public static void main(String[] args) {
        Leetcode1178_FindNumOfValidWords test = new Leetcode1178_FindNumOfValidWords();
//        System.out.println(test.isBelongs("gaswxyz","aaaa"));
//        String[] words = new String[]{"aaaa"};
        String[] words = new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
//        String[] puzzles = new String[]{"aboveyz"};
        System.out.println(test.findNumOfValidWords(words, puzzles));
    }

    // 字典树添加操作
    public void add(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            // 一步一步向下放元素
            char ch = word.charAt(i);
            if (cur.child[ch - 'a'] == null) {
                cur.child[ch - 'a'] = new TrieNode();
            }
            // cur向下走
            cur = cur.child[ch - 'a'];
        }
        ++cur.frequency;// 这个主要是统计到底有多少的word到了这个地方
    }

    // 利用回溯查找当前问题
    public int find(String puzzle, char required, TrieNode cur, int pos) {
        // 如果cur为null,直接返回0，表示没找到
        if (cur == null) {
            return 0;
        }
        // 如果到了7表示字符串到底了，直接返回frequency即可
        if (pos == 7) {
            return cur.frequency;
        }
        // 否则继续进行回溯查找，查找的元素为当前位置的子元素，向下走
        int ret = find(puzzle, required, cur.child[puzzle.charAt(pos) - 'a'], pos + 1);
        // 如果当前位置不等于required，此时，pos向下走，然后继续查找
        if (puzzle.charAt(pos) != required) {
            ret += find(puzzle, required, cur, pos + 1);
        }
        return ret;
    }

    TrieNode root;

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        root = new TrieNode();
        for (String word : words) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    sb.append(arr[i]);
                }
            }
            // 加入字典树
            add(root, sb.toString());
        }
        List<Integer> ans = new ArrayList<>();
        // 然后对puzzles进行遍历
        for (String puzzle : puzzles) {
            char required = puzzle.charAt(0);
            char[] arr = puzzle.toCharArray();
            Arrays.sort(arr);
            ans.add(find(new String(arr), required, root, 0));
        }
        return ans;
    }

    /**
     * 非暴力解法
     * 利用位运算+哈希表的方式来处理
     * 这个方式自己没有看懂！怀疑人生啊！
     * 先利用位运算，将所有word中可能存在的组合放入到map中
     * 然后多puzzles进行遍历，得出所有可能的结果，对这些结果记性子集的全部遍历
     * 然后获取所有可能的结果
     */
    /*public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<>();
        // 这里遍历所有的word只是为了找到其具体的二进制位置，然后用map记录出现的次数即可
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            int x = Integer.bitCount(mask);
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        // 这里对puzzles进行遍历，word与puzzle的首字母相同，word中的每一个字母都要在puzzle中查找到
        for (String puzzle : puzzles) {
            int total = 0;
            int mask = 0;
            // 从第一个开始，第0个的话肯定是存在的，这里将当前puzzle的除了第一位的进行mask累加
            for (int i = 1; i < 7; i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            // 最后利用所有的子集获取是否有组合到word中出现的
            int subset = mask;
            do {
                // 这里永远都去异或第一位,因为永远都要包含第一位
                int s = subset | (1 << (puzzle.charAt(0) - 'a'));
                // 找是否有出现了的，有的话则进入
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                // 这一步实质是在遍历所有的子集
                subset = (subset - 1) & mask;// 这一步实质是在用subset%mask求余操作，但是这步操作的目的是什么？
            } while (subset != mask);
            list.add(total);
        }
        return list;
    }*/
    /**
     * 这里主要意思就是判断puzzles中当前字符串属于几个words。
     * 判断属于集合words的一句就是，words中含有的，自己是否含有？
     * gaswxyz 找不到任何的谜底，因为没有谜底含有g
     * 使用暴力一一匹配的操作怎么做呢
     * 这是暴力解法解题
     */
//    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
//        List<Integer> list = new ArrayList<>();
//        for (String puzzle : puzzles) {
//            int cnt = 0;
//            for (String word : words) {
//                if(isBelongs(puzzle,word)){
//                    cnt+=1;
//                }
//            }
//            list.add(cnt);
//        }
//        return list;
//    }
//    public boolean isBelongs(String words,String puzzles){
//        // 先统计puzzles
//        int[] puz = new int[26];
//        for (int i = 0; i < puzzles.length(); i++) {
//            puz[puzzles.charAt(i)-'a']++;
//        }
//        if(puz[words.charAt(0)-'a'] == 0) return false;
//        for (int i = 0; i < words.length(); i++) {
//            if(puz[words.charAt(i)-'a'] != 0){
//                puz[words.charAt(i) - 'a'] = 0;
//            }
//        }
//        // 注意word中包含第一个字符
//        for (int i : puz) {
//            if(i != 0) return false;
//        }
//        return true;
//    }
}

class TrieNode {
    int frequency;
    TrieNode[] child;

    public TrieNode() {
        frequency = 0;
        child = new TrieNode[26];
    }
}
