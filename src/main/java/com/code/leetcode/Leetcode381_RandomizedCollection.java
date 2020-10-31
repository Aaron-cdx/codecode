package com.code.leetcode;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/10/31 8:49
 * @Motto Keep thinking, keep coding!
 * leetcode 381、O(1)时间插入、删除和获取随机元素-允许重复操作
 * 题目：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * 还是没有理解题目的精髓，题目的按照个数的概率返回使用的是当前的ArrayList来实现的
 * 但是
 */
public class Leetcode381_RandomizedCollection {
    Map<Integer, Set<Integer>> idx;
    ArrayList<Integer> nums;

    /**
     * Initialize your data structure here.
     */
    public Leetcode381_RandomizedCollection() {
        idx = new HashMap<>();
        nums = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        // 这里是利用set存放放入的下标
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<>());
        // 因为List的数组是有序的
        set.add(nums.size() - 1);
        idx.put(val, set);
        // 看看是不是第一次插入，如果不是返回false
        return set.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        // 如果不包含的话，直接返回false
        if (!idx.containsKey(val)) {
            return false;
        }
        // 包含的话，此时需要需要找到当前数的下标
        Iterator<Integer> iterator = idx.get(val).iterator();
        // 这就获取到这个元素的下标
        int i = iterator.next();
        int lastNum = nums.get(nums.size() - 1);
        nums.set(i, lastNum);
        // 从map中的val的下标中删除i这个下标
        idx.get(val).remove(i);
        // 将当前lastNum的最后一个的下标删除，因为被替换了
        idx.get(lastNum).remove(nums.size() - 1);
        // 如果i是小于最后一个元素，此时在lastNum的下标中加入当前值。
        // 按照道理i=nums.size()-1,如果不是的话，需要修改最后元素的下标映射
        // 如果i正好是最后的元素就不需要做啥了，如果i不是最后的元素，此时还需要对lastNum进行以此映射
        if (i < nums.size() - 1) {
            idx.get(lastNum).add(i);
        }
        // 如果这个数在删除之后没有了下标记录，可以直接移除，这是一个优化点
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        // 真正移除最后一个元素
        nums.remove(nums.size() - 1);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        // 主要是这里设计如何根据数目大小获取当前返回的随机性，使用arrayList就行了，这样的话即使按照数据个数实现的随机概率返回
        // 蠢到了自己！
        return nums.get((int) (Math.random()*nums.size()));
    }
}
