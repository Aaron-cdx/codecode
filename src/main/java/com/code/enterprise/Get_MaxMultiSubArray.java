package com.code.enterprise;

/**
 * @author caoduanxi
 * @Date 2020/9/7 19:35
 * 子数组最大乘积
 * 即获取当前的数组的最大子数组的最大乘积
 * [-2.5, 4, 0, 3, 0.5, 8, -1] => [3, 0.5, 8] => 12
 * 类似题目最大子序和
 */
public class Get_MaxMultiSubArray {
    public static void main(String[] args) {
        Get_MaxMultiSubArray test = new Get_MaxMultiSubArray();
        System.out.println(test.maxProduct(new double[]{-2.5, 4, 0, 3, 0.5, 8, -1}));
    }

    /**
     * 获取最大乘积
     * 标签：动态规划
     */
    public double maxProduct(double[] arr) {
        if (arr == null || arr.length == 0) return 0;
        double min = arr[0];
        double max = min;
        double res = max;
        for (int i = 1; i < arr.length; i++) {
            // 前一个最大值
            double pre_max = max;
            // 保持一个最大值，最大值可以从arr[i] arr[i]*max arr[i]*min中产生
            max = Math.max(arr[i], Math.max(arr[i] * max, arr[i] * min));
            // 保持一个最小值，因为最小值乘以负数可能会变成一个正整数
            min = Math.min(arr[i], Math.min(arr[i] * pre_max, arr[i] * min));

            res = Math.max(res, max);
        }

        return res;
    }

    /**
     * 与此相似，获取最大子序列和
     * 这里是利用ans作为一个标准，小于等于0表示加上去没有价值，大于0的话表示才有价值
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (ans <= 0) {
                ans = nums[i];
            } else {
                ans += nums[i];
            }
            max = Math.max(max, ans);
        }
        return max;
    }

    /**
     * 使用动态规划解题
     */
    public int maxSubArrayII(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (ans <= 0) {
                ans = nums[i];
            } else {
                ans += nums[i];
            }
            dp[i + 1] = Math.max(dp[i], ans);
        }
        return dp[nums.length];
    }

}
