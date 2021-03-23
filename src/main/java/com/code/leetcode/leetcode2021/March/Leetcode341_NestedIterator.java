package com.code.leetcode.leetcode2021.March;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/3/23 9:39
 * @Motto Keep thinking, keep coding!
 * leetcode 341、扁平化嵌套列表迭代器
 * 题目：https://leetcode-cn.com/problems/flatten-nested-list-iterator/
 */
public class Leetcode341_NestedIterator {
    public static void main(String[] args) {
    }
}

class NestedIterator implements Iterator<Integer> {
    List<Integer> list = new ArrayList<>();
    int index;

    /**
     * [1,[1,1]] => [1,1,1]
     */
    public NestedIterator(List<NestedInteger> nestedList) {
        add(nestedList);
    }

    public void add(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            // 如果是数字的话则直接进添加即可
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                // 这里需要进行递归
                add(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index != list.size();
    }
}

interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

