package com.code.leetcode.leetcode2021.April;

/**
 * @author caoduanxi
 * @Date 2021/4/14 16:33
 * @Motto Keep thinking, keep coding!
 * leetcode 208、实现前缀树
 * 题目：https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Leetcode208_Trie {

}

class Trie {
    // 这两个属性是针对每一个Trie实现的
    boolean is_string = false;
    Trie[] next = new Trie[26];

    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie root = this;// 当前为根root
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.next[chars[i] - 'a'] == null) {
                root.next[chars[i] - 'a'] = new Trie();// 如果下一个为null的话，此时给下一个赋值，然后root下移
            }
            root = root.next[chars[i] - 'a'];
        }
        root.is_string = true;// 这个表示前缀连接成功
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            // 如果遇到null直接返回，表示没有插入过这样的元素
            if (root.next[aChar - 'a'] == null) return false;
            // 否则的话一直向下
            root = root.next[aChar - 'a'];
        }
        return root.is_string;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] chars = prefix.toCharArray();
        for (char aChar : chars) {
            // 如果遇到null直接返回，表示没有插入过这样的元素
            if (root.next[aChar - 'a'] == null) return false;
            // 否则的话一直向下
            root = root.next[aChar - 'a'];// 这里是前缀所以，如果没有返回false，表示有这个前缀
        }
        return true;// 这个地方不是很懂
    }
}
