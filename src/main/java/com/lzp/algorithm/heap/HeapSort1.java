package com.lzp.algorithm.heap;

import com.lzp.algorithm.sort.Sorts;

/**
 * 不使用额外的堆，直接在原数组上进行原地堆排序
 *
 * @author lzp
 * @version v1.0 at 2019/3/2
 */
public class HeapSort1 {
    private HeapSort1() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        // 原地堆排序是从0索引位置开始的
        // 最后一个元素索引为n-1
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) {
            // 在当前数组中做heapfiy操作
            siftDown(arr, n, i);
        }

        // 取出最大值后，交换到最后，首元素做下沉操作
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    /**
     * 元素下沉操作
     *
     * @param arr 原始数组
     * @param n   arr中堆最后一个元素索引
     * @param k   arr中要下沉的元素索引
     */
    private static void siftDown(Comparable[] arr, int n, int k) {
        // 下沉元素值
        Comparable e = arr[k];
        while (2 * k + 1 < n) {
            int child = 2 * k + 1;
            if (child + 1 < n && arr[child + 1].compareTo(arr[child]) > 0) {
                child++;
            }

            if (e.compareTo(arr[child]) >= 0) {
                break;
            }

            // 可优化点--该点和插入排序处的优化是相同的!!!
//            swap(arr, k, child);
//            k = child;

            arr[k] = arr[child];
            k = child;
        }
    }

    /**
     * 交换两索引位置元素
     *
     * @param arr 原始数组
     * @param i   左索引
     * @param j   右索引
     */
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = Sorts.generateRandomArray(N, 0, 10000);
        Sorts.isSorted("com.lzp.algorithm.heap.HeapSort1", arr);
    }
}
