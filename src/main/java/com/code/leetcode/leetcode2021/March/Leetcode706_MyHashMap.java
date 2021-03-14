package com.code.leetcode.leetcode2021.March;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/14 9:19
 * @Motto Keep thinking, keep coding!
 */
public class Leetcode706_MyHashMap {
}

/**
 * 如何设计一个HashMap的集合
 * 实现put,get,remove方法
 * 如果是链地址法，找到key之后，判断value是否相等，如果相等的话，此时获取val
 * 如果移除key的话，直接吧key找到
 * 可能有多个val共享一个key，即找到key之后需要判断val是否相等，不等的话返回false
 * <p>
 * 报错并发修改错误 Line 62: java.util.ConcurrentModificationException
 * 这里报错的原因是因为修改了之后还在执行循环，所以一定会报错！
 */
class MyHashMap {
    private static final int BASE = 769;
    private static LinkedList[] data;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            data[i] = new LinkedList<KeyValue>();
        }
    }

    private int hash(int key) {
        return key % BASE;
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int h = hash(key);
        // 然后获取遍历的集合
        Iterator iterator = data[h].iterator();
        while (iterator.hasNext()) {
            KeyValue keyValue = (KeyValue) iterator.next();
            if (keyValue.key == key) {
                // 如果存在则替换这个对象的value
                keyValue.setValue(value);
                return;
            }
        }
        // 否则的话重新放入即可
        data[h].offerLast(new KeyValue(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int h = hash(key);
        // 然后获取遍历的集合
        Iterator iterator = data[h].iterator();
        while (iterator.hasNext()) {
            KeyValue keyValue = (KeyValue) iterator.next();
            if (keyValue.key == key) {
                return keyValue.value;
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int h = hash(key);
        // 然后获取遍历的集合
        Iterator<KeyValue> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            KeyValue keyValue = (KeyValue) iterator.next();
            if (keyValue.key == key) {
                data[h].remove(keyValue);
                return;
            }
        }
    }
}

class KeyValue {
    int key;
    int value;

    public KeyValue(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
