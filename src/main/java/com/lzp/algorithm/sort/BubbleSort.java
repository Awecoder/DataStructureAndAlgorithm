package com.lzp.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author lzp
 * @version v1.0 at 2019/1/16
 */
public class BubbleSort {
    private BubbleSort() {

    }

    /**
     * 排序方法
     * 算法思路：当前位置元素与下一位置元素两两比较，比较到最后，剩下最大的元素在末位。循环往复~~
     *
     * @param arr 输入数组
     */
    public static void sort0(Comparable[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 改进的排序方法
     * 主要是针对近乎有序的数组的情况，这样使用标志位一次循环下来没有改变，说明已经排好序
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        boolean flag = false;
        do {
            flag = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    flag = true;
                }
            }
            n--;
        } while (flag);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = Sorts.generateRandomArray(10000, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.BubbleSort", arr);
    }
}
