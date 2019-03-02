package com.lzp.algorithm.heap;

import com.lzp.algorithm.sort.Sorts;

/**
 * 对整个数组进行堆排序
 *
 * @author lzp
 * @version v1.0 at 2019/2/28
 */
public class HeapSort {
    private HeapSort() {
    }

    /**
     * 堆排序1
     * <pre>
     * 排序思想：将所有元素依次添加到堆中，然后将元素取出，即完成排序
     * 两个动作时间复杂度为0(nlogn），整个排序时间复杂度为0(nlogn）
     * </pre>
     *
     * @param arr 待排序数组
     */
    public static void sort1(Comparable[] arr) {
        int n = arr.length;
        // 构建堆
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);
        // 依次添加入堆
        for (int i = 0; i < n; i++) {
            maxHeap.add(arr[i]);
        }
        // 依次取出
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    /**
     * 利用heapify创建堆，依次取出，时间复杂度0(nlogn)
     * 创建堆过程是0(n)，元素取出过程为0(nlogn)
     *
     * @param arr 待排序数组
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 利用heapif过程构建堆
        MaxHeap<Comparable> maxHeap = new MaxHeap<>(arr);
        // 依次取出
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        int N = 100000;
        Integer[] arr = Sorts.generateRandomArray(N, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.heap.HeapSort", arr);
    }
}
