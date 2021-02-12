package com.code.leetcode.leetcode2021.February;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2021/2/12 9:35
 * @Motto Keep thinking, keep coding!
 * leetcode 119、杨辉三角II
 * 题目：https://leetcode-cn.com/problems/pascals-triangle-ii/
 * 返回杨辉三角的第k行
 */
public class Leetcode119_GetRow {
    public static void main(String[] args) {
        Leetcode119_GetRow test = new Leetcode119_GetRow();
        System.out.println(test.getRow(5));
    }
    /**
     * 获取杨辉三角
     * O(k)的空间复杂度
     */
    /*public List<Integer> getRow(int rowIndex) {
        // 利用list作为上一层的记录模板
        List<Integer> list = new ArrayList<>();
        list.add(1);
        // 第一行即返回第0行
        if(rowIndex == 0) return list;
        // 利用时间换空间
        // 直接从k==1开始
        for (int i = 1; i <= rowIndex; i++) {
            int j = list.size();
            // 这里并不能够对上面的数据进行记录
            // 从后往前插入即可完成数据记录
            for (int l = j; l >= 0; l--) {
                if(l == 0){
                    list.set(l,list.get(l));
                }else if(l == j){
                    // 只有最后一个需要add，其他位置只需要set即可实现
                    list.add(l,list.get(l-1));
                }else{
                    list.set(l,list.get(l)+list.get(l-1));
                }
            }
        }
        return list;
    }*/

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        // 这里直接先添加0从后往前即可，因为第一个是永远不变的，所以只需要到下标为1的即可停止
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }
}
