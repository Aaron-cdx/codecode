package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/4 21:51
 * 有序数组旋转之后，找出最小的那个输出
 */
public class Solution_6 {
    public static void main(String[] args) {
        Solution_6 s = new Solution_6();
        System.out.println(s.minNumberInRotateArray(new int[]{1, 2, 3, 4, 5}));
    }

    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (array[mid] == array[l] && array[mid] == array[r]) {
                int min = Integer.MAX_VALUE;
                for (int i : array) {
                    min = Math.min(min, i);
                }
                return min;
            }
            // 否则的话采用二分法
            if (array[mid] > array[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return array[r];
    }
}
