package com.lzp.structure.heap;

import java.util.Random;

/**
 * 自定义最大堆性能测试
 *
 * @author lzp
 * @version v1.0 at 2018/12/4
 */
public class Main {
    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            /**
             * 采用heapify方法构建堆
             */
            maxHeap = new MaxHeap<>(testData);
        } else {
            /**
             * 逐一添加元素的方法构建堆
             */
            maxHeap = new MaxHeap<>();
            for (int data : testData) {
                maxHeap.add(data);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test mapheap completed.");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");
        System.out.println();
        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
