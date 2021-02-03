package com.code.leetcode.leetcode2021.February;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2021/2/3 9:08
 * @Motto Keep thinking, keep coding!
 * leetcode 480、滑动窗口中位数
 * 题目：https://leetcode-cn.com/problems/sliding-window-median/
 */
public class Leetcode480_MedianSlidingWindow {
    public static void main(String[] args) {
        Leetcode480_MedianSlidingWindow test = new Leetcode480_MedianSlidingWindow();

//        System.out.println(Arrays.toString(test.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(test.medianSlidingWindow(new int[]{2147483647,2147483647}, 2)));
//        System.out.println(2147483647+2147483647);

    }

    /**
     * 这里求的是中位数,不是平均数,根据大小堆可以解决
     * 这里的操作是定义一个大顶堆维持小元素部分，定义小顶堆维持大元素部分
     * 这里定义大顶堆则是维护当前的小元素里面的最大值，大顶堆则是维护大元素里面的最小值，正好衔接操作
     * 刚开始插入，此时先进入小顶堆操作，弹出分奇偶k值弹出操作。
     * 每一次进入新的数据则需要产生擦除操作，擦除操作的话利用map记录需要擦除的数据，实现延迟擦除操作。
     * 每次擦除完毕对堆顶元素进行移除操作，随后需要进行平衡操作。平衡操作的话一定要确保small元素比large元素多一个或者相等
     * 一旦不满足则需要互相分配元素即可。
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n-k+1];
        DualHeap heap = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            heap.insert(nums[i]);
        }
        res[0] = heap.getMedian();
        for (int i = k; i < n; i++) {
            heap.insert(nums[i]);
            heap.erase(nums[i-k]);
            res[i-k+1] = heap.getMedian();
        }
        return res;
    }
}

class DualHeap{
    // 大根堆
    PriorityQueue<Integer> small;
    // 小根堆
    PriorityQueue<Integer> large;
    // 延迟删除策略，存储某个元素需要被删除多少次
    private Map<Integer,Integer> delayed;

    private int k;
    private int smallSize,largeSize;

    public DualHeap(int k){
        // 大根堆
        small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        // 小跟堆
        large = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        this.delayed = new HashMap<>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }
    // 获取中位数操作，奇数直接去中间的，偶数取中间两个数的平均值
    public double getMedian(){
        return (k&1) == 1 ? small.peek() : ((double)small.peek() + large.peek())/2;
    }

    /**
     * 元素的插入操作
     */
    public void insert(int num){
        // 插入元素，首先从小顶堆开始
        if(small.isEmpty() || num <= small.peek()){
            ++smallSize;
            small.add(num);
        }else{
            ++largeSize;
            large.add(num);
        }
        // 这里需要平衡操作，因为最大的元素可能在最后面导致所有元素全都到small中去了,
        // 但这里元素最多相差两个，因为是每一次insert都会进行以此平衡操作(最多积累上一次和本次的两个)
        makeBalance();
    }
    // 保持平衡操作

    /**
     * 这里是为了对保持平衡后面的元素进行一个删除操作，主要的目的是为了清除内部还没有删除的元素
     * 这些元素是非堆顶的元素，在删除堆顶的元素的时候没有机会被删除的元素！
     */
    public void makeBalance(){
        // 多2个元素
        if(smallSize > largeSize + 1){
            --smallSize;
            ++largeSize;
            large.add(small.poll());
            // 需要对堆顶元素进行移除操作
            prune(small);
            // 这里可能的是刚开始加入了最小的元素后面两个都是最大的。导致large比small大一个元素
        }else if(largeSize > smallSize){
            ++smallSize;
            --largeSize;
            // 弹出了元素则需要移除操作
            small.add(large.poll());
            prune(large);
        }
        // 否则就是一样多，不需要做处理或者small比large多一个元素
    }
    // 对元素进行延迟删除操作
    public void prune(PriorityQueue<Integer> heap){
        while(!heap.isEmpty()){
            int num = heap.peek();
            if(delayed.containsKey(num)){
                delayed.put(num,delayed.get(num)-1);
                if(delayed.get(num) == 0){
                    delayed.remove(num);
                }
                heap.poll();
            }else{
                break;
            }
        }
    }

    /**
     * 对元素进行擦除操作
     */
    public void erase(int num){
        delayed.put(num,delayed.getOrDefault(num,0)+1);
        // 擦除当前元素,判断是否在small这边
        if(num <= small.peek()){
            // 是的话直接擦除
            --smallSize;
            if(num == small.peek()){
                // 如果正好是堆顶元素
                prune(small);
            }
        }else{
            --largeSize;
            if(num == large.peek()){
                prune(large);
            }
        }
        // 移除元素之后需要做平衡操作
        makeBalance();
    }
}
