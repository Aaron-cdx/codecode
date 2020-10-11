package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/10/11 8:55
 * @Motto Keep thinking, keep coding!
 * leetcode 416、分割等和子集
 * 即判断当前的数组是否可以分为两个子集，这两个子集的和为整个数组的和
 */
public class Leetcode416_CanPartition {
    public static void main(String[] args) {
        Leetcode416_CanPartition test = new Leetcode416_CanPartition();
//        test.canPartition(new int[]{1, 5, 11, 5});
        test.canPartition(new int[]{1, 2, 5});
    }

    /**
     * 参考其他人的答案
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果不可以分为两份，直接返回false
        if (sum % 2 != 0) return false;

        int v = sum / 2;
        boolean[] dp = new boolean[v + 1];
        for (int i = 0; i <= v; i++) {
            // 因为从小到大，只要遇到相等，后面不可能再有相等的了
            if (nums[0] == i) {
                dp[i] = true;
                break;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = v; j >= 0 && j >= nums[i]; j--) {
                dp[j] = dp[j] | dp[j - nums[i]];
            }
        }
        return dp[v];
    }


    /**
     * 使用一维数组实现当前的动态规划
     */
    public boolean canPartition_III(int[] nums) {
        if (nums == null || nums.length < 1) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果不可以分为两份，直接返回false
        if (sum % 2 != 0) return false;

        int v = sum / 2;
        boolean[] dp = new boolean[v + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = v; j >= 0; j--) {
                if (nums[i] == j) {
                    dp[j] = true;
                } else if (j - nums[i] >= 0) {
                    dp[j] = dp[j] | dp[j - nums[i]];
                }
            }
        }
        return dp[v];
    }

    /**
     * 典型的背包问题，即0 1背包问题
     * 利用二维数组做的动态规划，这里实际应该使用一维数组
     */
    public boolean canPartition_II(int[] nums) {
        if (nums == null || nums.length < 1) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果不可以分为两份，直接返回false
        if (sum % 2 != 0) return false;

        int v = sum / 2;
        // 先用二维的，待会转化为一维
        boolean[][] dp = new boolean[nums.length][v + 1];
        // 初始化 给第一行与第一列赋值
        for (int i = 0; i <= v; i++) {
            dp[0][i] = nums[0] == i;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= v; j++) {
                // 答案来自前一个或者来自上一个
                if (nums[i] == j) {
                    dp[i][j] = true;
                } else {
                    // 结果来自与两个位置，一个上面直接继承，另外一个来自自己与前面一个的差值
                    dp[i][j] = dp[i - 1][j];
                    if (j - nums[i] >= 0) {
                        dp[i][j] |= dp[i - 1][j - nums[i]];
                    }
                }
            }
        }
        for (boolean[] booleans : dp) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(booleans[j] + " ");
            }
            System.out.println();
        }
        return dp[nums.length - 1][v];
    }
}
