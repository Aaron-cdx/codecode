package com.code.leetcode.leetcode2021.January;

/**
 * @author caoduanxi
 * @Date 2021/1/1 16:23
 * @Motto Keep thinking, keep coding!
 * leetcode 605、种花问题
 * 题目：https://leetcode-cn.com/problems/can-place-flowers/
 * 题目是1是花但两朵花不能够一起中，中间需要隔开一朵，判断在现有的
 * 花种植的基础之上能否种植n朵花
 *
 * 奇偶得到接 如果是偶数p
 * 则(p+1)/2 == p/2  如果是奇数,则(p-1)/2 == p/2
 * 所以无论就只要是p/2就可得到之间的结果
 */
public class Leetcode605_CanPlaceFlowers {
    public static void main(String[] args) {
        Leetcode605_CanPlaceFlowers test = new Leetcode605_CanPlaceFlowers();
//        System.out.println(test.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
//        System.out.println(test.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
//        System.out.println(test.canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 1}, 1));
//        System.out.println(test.canPlaceFlowers(new int[]{0, 0, 1, 0, 1}, 1));
//        System.out.println(test.canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 0}, 2));
        System.out.println(test.canPlaceFlowers(new int[]{1, 0}, 1));
    }

    /*public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int m = flowerbed.length;
        if (m <= 0) return true;
        // 允许插入的花朵数量
        int cnt = 0;
        int pre = -1;
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                // 这个的话pre是从0开始,而i需要从i-2开始
                // p = (i-2-0+1)/2 = (i-1)/2 => 如果i是奇数呢，如果i是偶数呢？
                // 如果是i-1为奇数则i-1/2与i/2相等，
                // 如果是i-1为偶数i-1/2与i/2相等，所以为了无视奇偶，直接采取加1即可
                // p = i / 2
                if (pre < 0) {
                    cnt += i / 2;
                } else {
                    // 此时计算可以插入花的数量，现在的pre是有值只能够从pre+2开始计算而此时的i也是有值，i-2开始计算
                    // 所以此时p = (i-2-pre-2+1)/2 = (i-pre-3)/2 => (i-pre-2)/2
                    cnt += (i-pre-2) / 2;
                }
                pre = i;
            }
        }
        // 注意这里m本身是长度，实际上最后一个下标应该为m-1，所以所有的值在原有基础上-1
        if(pre < 0){
            cnt += (m + 1)/2;
        }else{
            cnt += (m - pre - 1)/2;
        }
        return cnt >= n;
    }*/
    /**
     * 不经过数学思维思考的解答都是毫无灵魂的逻辑！
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0) return true;
        int m = flowerbed.length;
        // 这个跟自己想法一样
        // 只不过自己没有做出来
        // 如果是第一个，则判断后面是否为0
        // 如果是最后一个，则判断前面是否为0
        // 如果是中间的，则判断自己前面和后面是否为0
        for (int i = 0; i < m; i++) {
            if((flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0)) && (i == m-1 || flowerbed[i+1] == 0)){
                n--;
                if(n <= 0) return true;
                flowerbed[i] = 1;
            }
        }
        // 如果都不满足提偶见，此时
        return false;
    }
}
