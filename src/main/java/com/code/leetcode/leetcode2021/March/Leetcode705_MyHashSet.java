package com.code.leetcode.leetcode2021.March;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/13 19:25
 * @Motto Keep thinking, keep coding!
 */
public class Leetcode705_MyHashSet {
}

/**
 * 实现自定义HashSet要求实现以下方法
 * 使用链地址法
 */
class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashSet() {
        data = new LinkedList[BASE];
        // 每一个里面放一个链表
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            // 存在就返回
            if (iterator.next() == key) {
                return;
            }
        }
        // 否则在尾部插入
        data[h].offerLast(key);
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void remove(int key) {
        // 查找看看有没有
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            int elem = iterator.next();
            // 存在就返回
            if (elem == key) {
                data[h].remove(elem);
            }
        }
    }

    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            int elem = iterator.next();
            // 存在就返回
            if (elem == key) {
                return true;
            }
        }
        return false;
    }
}
/*
class MyHashSet {
    boolean[] hash;

    */
/**
 * Initialize your data structure here.
 * <p>
 * Returns true if this set contains the specified element
 * <p>
 * Returns true if this set contains the specified element
 * <p>
 * Returns true if this set contains the specified element
 *//*

    public MyHashSet() {
        hash = new boolean[1000000 + 1];
    }

    public void add(int key) {
        hash[key] = true;
    }

    public void remove(int key) {
        hash[key] = false;
    }

    */
/**
 * Returns true if this set contains the specified element
 *//*

    public boolean contains(int key) {
        return hash[key];
    }
}*/
