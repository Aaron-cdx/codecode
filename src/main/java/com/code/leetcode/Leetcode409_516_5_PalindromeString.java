package com.code.leetcode;

/**
 * @author caoduanxi
 * @Date 2020/9/11 10:29
 * <p>
 * leetcode 409、最长回文串
 * 给出包含大小写(区分)的字符串，判断可以组成的最长回文串的大小。
 * "abccccdd" => "dccaccd" => 7
 * <p>
 * leetcode 516、最长回文子序列
 * 子序列的话可以无序，即在一个字符串中找出最长的回文子序列长度
 * "bbbab" => "bbbb" => 4
 * <p>
 * leetcode 5、最长回文子串
 * "babad" => "bab" 或者 "aba"
 */
public class Leetcode409_516_5_PalindromeString {
    public static void main(String[] args) {
        Leetcode409_516_5_PalindromeString test = new Leetcode409_516_5_PalindromeString();
        System.out.println(test.longestPalindrome5_II("aaabccccdd"));
        System.out.println(test.longestPalindrome5_II("babad"));
        System.out.println(test.longestPalindrome5_II("cbbd"));
//        System.out.println(test.longestPalindromeSubseqII("bbbab"));
    }

    /**
     * 419
     * <p>
     * 这个由于是求最长的，偶数肯定是可以保证是回文的
     * 奇数的话就要看个数了，如果奇数大于2则里面有偶数，此时里面的偶数可以单独组成
     * 故只需要看是否有奇数，然后使用偶数+1即可。
     */
    public int longestPalindrome409(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] nums = new int[58];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'A']++;
        }
        int even = 0;
        int odd = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                even += num;
            } else {
                // 如果是奇数的话，此时需要判断当前的数是否大于2
                // 大于2的话此时需要先将2乘上
                int tmp = num / 2;
                odd += 1;
                even += tmp * 2;
            }
        }
        return odd > 0 ? even + 1 : even;
    }

    /**
     * leetcode 5
     * <p>
     * 利用中心发散的思想来解决这道问题
     * <p>
     * 可以使用动态规划算法来解决这个问题
     */
    /*
        这里与回文序列的不同在于，这里所要获取的回文子串必须保证有序
     */
    private String longestPalindrome5(String s) {
        if (s == null || s.length() == 0) return "";
        // 这里有一个动态更新的过程
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 这种情况是当前的数正好就是处于中间的，这样可能获取到最大值
            int len1 = getMaxDiffusionLen(s, i, i);
            // 这种是两边正好是对称的，这样可能获取到最大值
            int len2 = getMaxDiffusionLen(s, i, i + 1);
            // 从中获取到最大值，看是否需要更新
            int len = Math.max(len1, len2);
            if (len > end - start) {
                // 如果是需要更新
                // 则start的位置在i可能是在中间，也可能是在边上
                // 如果是在中间的话，此时前后对等，如果不是总监
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        // 这是为了截取的时候更加方便，所以
        return s.substring(start, end + 1);
    }

    private int getMaxDiffusionLen(String s, int start, int end) {
        int l = start;
        int r = end;
        while (l >= 0 && r <= s.length() - 1) {
            if (s.charAt(l) != s.charAt(r)) {
                break;
            }
            l--;
            r++;
        }
        return r - l - 1;
    }


    private String longestPalindrome5_II(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int maxLen = 1;
        char[] arr = s.toCharArray();
        // 先填充行，再填充列
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (arr[i] != arr[j]) continue;
                if ((j - i < 3)) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j] && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        for (boolean[] booleans : dp) {
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean + " ");
            }
            System.out.println();
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * leetcode 516、最长回文子序列
     * <p>
     * 第一种做法先使用递归操作来做这个题目思路没有问题，但是超时
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        // 没有想法，不知道怎么去做这个
        return recursion(s.toCharArray(), 0, s.length() - 1);
    }

    /*
        这里有四种情况
        1. 如果只有一个字符，此时返回1，即start==end  return 1
        2. 如果有2个字符，且两者相等，即s[start] == s[end] && start + 1 == end return 2
        3. 如果有两个以上字符，两者不等，即s[start] != s[end] 此时 return Math.max(recursion(s,start,end-1),recursion(s,start+1,end))
           即去掉前面一个或者去掉后面一个看谁能够取得更大的回文序列
        4. 表示两个相等，但是字符大于2，此时return 2 + recursion(s,start+1,end-1)
     */
    private int recursion(char[] s, int start, int end) {
        if (start == end) return 1;
        if (s[start] == s[end] && start + 1 == end) {
            return 2;
        }
        if (s[start] != s[end]) {
            return Math.max(recursion(s, start + 1, end), recursion(s, start, end - 1));
        }
        return 2 + recursion(s, start + 1, end - 1);
    }

    /**
     * 动态规划解题
     */
    /*
        dp[i][j] 表示第i个字符到第j个字符中间的回文子序列的长度
        1. if(i == j) dp[i][j] = 1
        2. if(s[i] == s[j] && i+1 == j) dp[i][j] = 2;
        3. if(s[i] != s[j]) max(dp[i+1][j],dp[i][j-1])
        4. 相等且二者中间有很多字符 dp[i][j] = 2 + dp[i-1][j+1]
     */
    public int longestPalindromeSubseqII(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        char[] arr = s.toCharArray();
        // 这种收缩，收到最后就是在右上角
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j] && i + 1 == j) {
                    dp[i][j] = 2;
                } else if (arr[i] != arr[j]) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                } else {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[0][n - 1];
    }
}
