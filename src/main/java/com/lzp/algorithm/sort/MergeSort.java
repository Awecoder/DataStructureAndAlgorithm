package com.lzp.algorithm.sort;

/**
 * 归并排序代码实现
 * <pre>
 * 归并排序基于分治的思想，将数组区间分成两个子数组，分别排序，再将有序的子数组归并为完整数组。
 * 适用于稳定性高，空间复杂度要求不苛刻的情况 时间复杂度O(nlogn)
 *
 * 在对归并排序进行优化后，归并排序快于希尔排序.
 *
 * 归并排序实现的难点，在于索引的维护。
 * </pre>
 *
 * @author lzp
 * @version v1.0 at 2019/1/21
 */
public class MergeSort {
    private MergeSort() {

    }

    // 归并merge过程需要的赋值数组
    private static Comparable[] aux;

    /**
     * 排序主函数
     *
     * @param arr 要排序的数组
     */
    public static void sort(Comparable[] arr) {
        aux = new Comparable[arr.length]; // 指定aux大小范围
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 递归排序
     *
     * @param arr   原始数组
     * @param left  当前排序的数组区间左边界
     * @param right 当前排序的数组区间右边界
     */
    private static void sort(Comparable[] arr, int left, int right) {
        // 当右边界小于等于左边界
//        if (right <= left) {
//            return;
//        }
        // 优化点1
        // 对于小规模数组，使用插入排序更加有效
        if (right - left <= 15) {
            insertSort(arr, left, right);
            return;
        }
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        // 优化点2
        // 对arr[mid] <= arr[mid+1]的情况，不进行merge
        // 对于近乎有序的数组有效，但对于一般情况下，有一定的性能损失
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, left, mid, right);
        }
    }

    /**
     * 对左右两个空间数组进行合并
     *
     * @param arr   原始数组
     * @param left  当前排序的数组区间左边界
     * @param mid   当前排序的数组区间左右边界中值
     * @param right 当前排序的数组区间右边界
     */
    private static void merge(Comparable[] arr, int left, int mid, int right) {
        // 定义并初始化辅助数组
        for (int k = left; k <= right; k++) {
            aux[k] = arr[k]; // 将arr[]当前左右区间原始复制到aux[]
        }

        // 初始化：i指向左区间的起始位置l，j指向右区间的起始位置mid+1
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) { // 左侧元素用尽，直接取右侧
                arr[k] = aux[j++];
            } else if (j > right) { // 右侧元素用尽，取左侧
                arr[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) < 0) { // 左侧小于右侧，取左侧
                arr[k] = aux[i++];
            } else { // 左侧大于右侧，取右侧
                arr[k] = aux[j++];
            }
        }
    }

    /**
     * 对arr[left...right]区间使用插入排序
     *
     * @param arr 原始数组
     * @param left   要排序数组区间的左边界
     * @param right   要排序数组区间的右边界
     */
    public static void insertSort(Comparable[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > left && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = Sorts.generateRandomArray(N, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.MergeSort", arr);

        arr = Sorts.generateRandomArray(N, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.ShellSort", arr);

//        arr = Sorts.generateRandomArray(N, 0, 100000);
//        Sorts.isSorted("com.lzp.algorithm.sort.InsertionSort", arr);
    }
}
