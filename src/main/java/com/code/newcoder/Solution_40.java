package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/11 10:04
 * 剑指offer 40、数组中出现一次的数字
 * 即数组中除了两个数字出现一次，其余数字都出现两次，找出这两个出现1次的数字
 */
public class Solution_40 {
    public static void main(String[] args) {
        Solution_40 test = new Solution_40();
//        System.out.println(test.getOneIndex(2));
//        System.out.println(2 ^ 3);
        test.FindNumsAppearOnce(new int[]{1, 1, 2, 99, 4, 4}, new int[1], new int[1]);
    }

    private void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length == 0) return;
        int num = array[0];
        for (int i = 1; i < array.length; i++) {
            num ^= array[i];
        }
        // 获取到结果的1出现的位置
        int index = getOneIndex(num);
        // 因为能出现1的，必定是两个出现1次的数这一位不同
        // 所以只需要将这一位不同的放在一起异或，最终就会得出这两个结果
        for (int arr : array) {
            if (((arr >> index) & 1) == 1) {
                num1[0] ^= arr;
            } else {
                num2[0] ^= arr;
            }
        }
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }

    private int getOneIndex(int num) {
        int index = 0;
        while ((num & 1) != 1) {
            index++;
            num >>= 1;
        }
        return index;
    }
}
