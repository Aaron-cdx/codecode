package com.code.leetcode.leetcode2021.March;

import javax.smartcardio.CardTerminal;

/**
 * @author caoduanxi
 * @Date 2021/3/30 8:53
 * @Motto Keep thinking, keep coding!
 * leetcode 74、搜索二维矩阵
 * 题目：https://leetcode-cn.com/problems/search-a-2d-matrix/
 */
public class Leetcode74_SearchMatrix {
    public static void main(String[] args) {
        Leetcode74_SearchMatrix test = new Leetcode74_SearchMatrix();
//        int[] arr = new int[]{1,2,4,5,6,7};
//        System.out.println(test.binarySearch(arr,3));
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
//        int[][] matrix = new int[][]{{1, 3}};
        System.out.println(test.searchMatrix(matrix, 3));
        System.out.println(test.searchMatrix(matrix, 16));
//        System.out.println(6 % 4);
    }

    /**
     * 一次二分算法解决战斗
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;
        // 这里涉及行列坐标的转换
        while (low <= high) { // 这里需要注意low和high可以相等
            int mid = low + (high - low) / 2;
            int row = mid / n;// 除以列数，得到行数
            int col = mid % n;// 余上列数，得到列数
            if (matrix[row][col] > target) {
                high = mid - 1;
            } else if (matrix[row][col] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 两次二分算法
     * 每行的第一个元素小于最后一个元素。第一列是升序的
     * 时间复杂度O(logm+logn) = O(logmn)
     * 空间复杂度O(1)
     */
    /*public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = searchMatrixFirstColumn(matrix, target);
        if (rowIndex < 0) return false;
        return binarySearch(matrix[rowIndex], target);
    }

    public int searchMatrixFirstColumn(int[][] matrix, int target) {
        int low = -1;
        int high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean binarySearch(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > num) { // 表示target在前面
                right = mid - 1;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }*/

    /**
     * 这是复杂度最高的算法
     * 采用了rows次二分算法，实际上最多只需要两次二分算法，或者1次（把所有元素归结为一行元素）
     */
    /*public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            // 满足条件才开始查找，否则不查找
            if (matrix[i][0] <= target && target <= matrix[i][cols - 1]) {
                if (binarySearch(matrix[i], target)) return true;
            }
        }
        return false;
    }*/

}
