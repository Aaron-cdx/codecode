package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/8 9:04
 * @Motto Keep thinking, keep coding!
 * leetcode 75、颜色分类
 * 即将0 1 2按照顺序排列好即可，不可使用sort函数
 */
public class Leetcode75_SortColors {
    public static void main(String[] args) {
        Leetcode75_SortColors test = new Leetcode75_SortColors();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        test.sortColors(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.print("]");
    }

    /**
     * 第一种方法就是统计0 1 2的个数 然后人为构造有序的数组，比较繁琐
     * 第二种方法利用0 1 2三个指针解决问题，如下代码
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        // 想法就是0向前迭代，2向后迭代
        int l0 = -1;
        int l2 = nums.length;
        // 还是需要三个指针
        for (int i = 0; i < l2; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] > 1) {
                // 跟最后一个交换完之后不能够确保当前i位置的数是0或者1
                swap(nums, i, --l2);
            } else {
                // 否则就是0了
                swap(nums, i++, ++l0);
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
