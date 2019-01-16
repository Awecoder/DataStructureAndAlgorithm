package com.lzp.algorithm.sort;

/**
 * 选择排序代码实现
 * 算法复杂度0(n^2)
 *
 * @author lzp
 * @version v1.0 at 2019/1/15
 */
public class SelectionSort {

    /**
     * 不允许产生任何实例
     */
    private SelectionSort() {
    }

    /**
     * <pre>
     * 实现思想：
     * 假设对数组从小到大排序，从数组0位置开始，
     * 不断寻找当前位置到length-1位置最小值替换当前位置的值
     *
     * 选择排序的优化思路：
     * 遍历一次可以同时找到当前未处理元素的最大值和最小值。
     * </pre>
     *
     * @param arr 要进行排序的数组
     */
    public static void sort(Comparable[] arr) {
        // 数组元素个数
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间内最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // j位置值小于minIndex位置值时，j设为minIndex
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 交换当前位置和最小值位置的值
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
//        // 1
////        Integer[] arr = Sorts.generateRandomArray(100, 0, 10000);
////        SelectionSort.sort(arr);
////        Sorts.print(arr);

        // 2
        Integer[] arr = Sorts.generateRandomArray(10000, 0, 10000);
        Sorts.isSorted("com.lzp.algorithm.sort.SelectionSort", arr);
    }
}
