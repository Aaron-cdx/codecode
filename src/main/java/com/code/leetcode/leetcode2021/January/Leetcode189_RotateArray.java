package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/8 9:09
 * @Motto Keep thinking, keep coding!
 * leetcode 189、旋转数组
 * 题目：https://leetcode-cn.com/problems/rotate-array/
 * 给定k值旋转对应的数组
 * [1,2,3,4,5,6,7] => k=3 ==> [5,6,7,1,2,3,4]
 */
public class Leetcode189_RotateArray {
    public static void main(String[] args) {
        Leetcode189_RotateArray test = new Leetcode189_RotateArray();
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] nums = new int[]{-1, -100, 3, 99};// 3 99 -1 -100
//        int[] nums = new int[]{-1};
        test.rotate(nums, 2);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    /**
     * 最简单的就是直接通过一个数组存放解决
     * 但题目要求使用原地解法O(1)空间复杂度
     * 先解题在说
     */
    /*public void rotate(int[] nums, int k) {
        if(nums.length == 1) return;
        // k值需要经过处理
        k %= nums.length;;
        rotate(nums,0,nums.length-1);
        rotate(nums,0,k-1);
        rotate(nums,k,nums.length-1);
    }
    public void rotate(int[] nums, int start, int end){
        while(start < end){
            swap(nums,start++,end--);
        }
    }
    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }*/

    /**
     * 只适用于奇数个数元素，偶数个数的话就会被破坏，此时只有部分元素被替换！
     * 自己的做法会导致元素替换不完全，此时需要通过最大公约数，如果到了，但是还没有遍历完全的话，此时
     */
    public void rotate(int[] nums, int k) {
        if (nums.length == 1) return;
        // k值需要经过处理
        k %= nums.length;
        // 利用辗转变换法
        int n = nums.length;
        int count = gcd(k,n);
        for (int start = 0; start < count; start++) {
            int current = start;
            int pre = nums[start];
            do{
                int next = (current+k)%n;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                current = next;
            }while(start != current);
        }
    }

    private int gcd(int x, int y) {
        // 辗转相除法
        return y > 0 ? gcd(y, x % y) : x;
    }
}
