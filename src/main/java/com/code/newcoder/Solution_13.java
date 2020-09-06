package com.code.newcoder;

/**
 * @author caoduanxi
 * @Date 2020/9/5 11:39
 * 调整数组顺序使得奇数位于偶数前面
 * 1 2 3 4 5 => 2 4 1 3 5
 */
public class Solution_13 {
    public static void main(String[] args) {
        Solution_13 s = new Solution_13();
        int[] nums = new int[]{1, 2, 4, 6, 3, 5, 6, 8, 10};
        s.reOrderArray(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }


    /**
     * 利用双指针来操作
     * 如果是奇数就跳过，偶数才开始双指针走
     */
    public void reOrderArray(int[] array) {
        int odd = 0;
        int even = 0;
        while (even < array.length) {
            // 奇数就直接跳过
            if (array[even] % 2 != 0) {
                even++;
                odd++;
                continue;
            }
            // 只有偶数的时候才处理
            while (even < array.length && array[even] % 2 == 0) {
                even++;
            }
            if (even >= array.length) break;
            // 到了第一个偶数位置
            swap(array, odd++, even);
            // 然后交换之前到这一段区间的数字
            swapRange(array, odd, even);
        }
    }

    /**
     * 交换区间
     *
     * @param nums
     * @param l
     * @param r
     */
    public void swapRange(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r);
        }
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

}
