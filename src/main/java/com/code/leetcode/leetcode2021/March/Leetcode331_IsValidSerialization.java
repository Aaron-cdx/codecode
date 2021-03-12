package com.code.leetcode.leetcode2021.March;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2021/3/12 9:16
 * @Motto Keep thinking, keep coding!
 * leetcode 331、验证二叉树的前序序列化
 * 题目：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class Leetcode331_IsValidSerialization {
    /**
     * 第一种思路：
     * 如果遇到空节点，则栈消耗一个节点
     * 如果遇到非空节点，则栈除了消耗一个节点，还要继续补充两个节点
     */
    /*public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        while(i < n){
            if(stack.isEmpty()){
                return false;
            }
            if(preorder.charAt(i) == ','){
                i++;
            }else if(preorder.charAt(i) == '#'){
                int top = stack.pop() - 1; // 消耗一个槽位
                if(top > 0){
                    stack.push(top);
                }
                i++;
            }else{
                // 只剩下数字了,可能是多位数，这里采取位读取法，遇到,则停止
                while(i < n  && preorder.charAt(i) != ','){
                    i++;
                }
                // 除了消耗一个槽位，还需要补充两个槽位
                int top = stack.pop()-1;
                if(top > 0){
                    stack.push(top);
                }
                // 补充的槽位要先进行消耗，所以push进2
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }*/

    /**
     * 直接使用slot记录槽位变化，如果在某一时刻槽位为0直接返回false
     * 直到最后判断槽位是否为0
     */
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                slots -= 1;
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
//                slots = slots - 1 + 2;
                slots++;
            }
        }
        return slots == 0;
    }
}
// go语言版本
/*
func isValidSerialization(preorder string) bool {
    // 仅有一个nil
    if preorder == "#" {
        return true
    }
    // 使用入度出度
    indegree, outdegree := 0, 0
    nodes := strings.Split(preorder, ",")

    for i := 0; i < len(nodes); i++ {
        if i == 0{
            // 初始化,如果不止一个nil，开头就是nil，直接返回false
            if nodes[i] == "#"{
                return false
            }
            // 否则初始化
            outdegree = 2
            continue
        }
        if nodes[i] == "#"{
            // #话入度为1，出度为0
            indegree += 1
        }else{
            // 为数字的话，入度为1，出度为2
            indegree +=1
            outdegree +=2
        }
        // 除了在最后，在中间入度是不可能大于出度或者等于出度的
        // 入度肯定是小于出度的，因为入1个需要出2个
        if i != len(nodes) - 1 && indegree >= outdegree{
            return false
        }
    }
    return indegree == outdegree
}*/
