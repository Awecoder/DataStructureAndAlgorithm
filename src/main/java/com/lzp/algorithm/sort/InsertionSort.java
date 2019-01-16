package com.lzp.algorithm.sort;

/**
 * 插入排序代码实现
 * <p>
 * 插入排序时间复杂度0(n^2)
 * 其优势是可以提前终止循环，比选择排序快
 * 如果在部分有序的情况下，插入排序时间复杂度最低为0(n)
 *
 * @author lzp
 * @version v1.0 at 2019/1/16
 */
public class InsertionSort {
    /**
     * 私有构造
     */
    private InsertionSort() {

    }

    /**
     * 排序方法
     *
     * @param arr
     */
    public static void sort0(Comparable[] arr) {
        // 数组长度
        int n = arr.length;

        // 从0到n-1位置进行遍历
        for (int i = 0; i < n; i++) {
            // 方案1
            // 从i位置开始，向前比较，遇到比当前位置大的就交换值，直到0位置或者是前一元素值大于当前元素值。
            // 使用插入排序，可以保证当前位置之前的元素都是有序的。
            // 特别注意j > 0, 因为是与前一个元素做比较
            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 从小到大实现
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        // 数组长度
        int n = arr.length;

        // 从0到n-1位置进行遍历
        for (int i = 0; i < n; i++) {
            // 方案1的弊端，就是内层循环中不断的进行交换操作，一次交换操作就是三次赋值
            // 方案2 -- 改进，每次内部循环减少两次赋值
            // 首先保存当前元素值
            Comparable e = arr[i];
            // 操作需要，j变量必须放到外侧
            int j = i;
            // 从当前位置向前遍历，若前一个元素值大于当前元素值，赋前一个元素值给当前位置
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = Sorts.generateRandomArray(10000, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.InsertionSort", arr);
    }
}
