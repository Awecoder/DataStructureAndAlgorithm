package com.lzp.algorithm.sort;

/**
 * 希尔排序代码实现
 *
 * @author lzp
 * @version v1.0 at 2019/1/17
 */
public class ShellSort {
    private ShellSort() {

    }

    /**
     * 排序算法--nlogn复杂度
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int n = arr.length;

        // 设置增量
        int h = 1;
        while (h < n / 3) {
            // 设定初始增量大小
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // 根据增量进行分组，对每个分组进行插入排序
            for (int i = h; i < n; i++) {
                Comparable e = arr[i];
                int j = i;
                // 以h为尺度
                for (; j >= h && arr[j - h].compareTo(e) > 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = e;
            }
            // 所有分组排序完后，调整增量大小
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = Sorts.generateRandomArray(10000, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.ShellSort", arr);
    }
}
