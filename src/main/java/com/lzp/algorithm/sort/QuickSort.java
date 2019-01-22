package com.lzp.algorithm.sort;

/**
 * 普通单路排序
 * <pre>
 * 归并排序和快速排序，都是采用了分治算法，分而治之，将原问题分割成同等结构的子问题，将子问题逐一解决后，原问题也就得到了解决。
 * 归并排序，在分没有过多的处理，重点放到了合并上。
 * 快速排序，重点放在了如何分上，合并顺理成章。
 *
 * 测试：
 * 优化后的快速排序优于归并排序，远优于希尔排序
 * </pre>
 *
 * @author lzp
 * @version v1.0 at 2019/1/22
 */
public class QuickSort {
    private QuickSort() {
    }

    /**
     * 排序方法
     *
     * @param arr 数组
     */
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }


    /**
     * 递归排序方法
     *
     * @param arr   元素数组
     * @param left  当前递归待排序区间左边界
     * @param right 当前递归待排序区间右边界
     */
    private static void sort(Comparable[] arr, int left, int right) {
        // 对于小规模数组，使用插入排序优化
        if (right - left <= 15) {
            insertSort(arr, left, right);
            return;
        }
        // 标定值，切分数组空间，左侧小于pivot元素，右侧大于该元素
        int pivot = partition3(arr, left, right);
        sort(arr, left, pivot - 1);
        sort(arr, pivot + 1, right);
    }

    /**
     * 快排的核心方法--partition
     *
     * @param arr   原始数组
     * @param left  要切分数组区间左边界
     * @param right 要切分数组区间右边界
     * @return 标定值的索引
     */
    private static int partition(Comparable[] arr, int left, int right) {
        // 在arr[left..right]范围内，选择一个数组作为标定点
        swap(arr, left, (int) (Math.random() * (right - left + 1) + left));

        Comparable v = arr[left];

        int j = left; // arr[left+1...j] < v ; arr[j+1..i] > v
        // i为当前节点，j表示为小于v区间的右边界
        for (int i = j + 1; i <= right; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++; // j加1之后变为大于v区间的最左侧元素的索引
                swap(arr, j, i);
            }
        }

        // 交换头位置和j位置
        swap(arr, left, j);

        return j; // j左侧小于v，右侧大于v
    }

    /**
     * 双路快速排序的partition
     *
     * @param arr   原始数组
     * @param left  左边界
     * @param right 右边界
     * @return 标定值索引
     */
    private static int partition2(Comparable[] arr, int left, int right) {
        // 随机标定点
        swap(arr, left, (int) (Math.random() * (right - left + 1) + left));
        // 最左元素
        Comparable v = arr[left];

        //  arr[l+1...i) <= v; arr(j...r] >= v
        int i = left + 1;
        int j = right;
        while (true) {
            // i位置元素小于v,直到找到大于等于v的元素，循环终止
            // arr[i].compareTo(v) < 0不能是<=，加了后会造成两侧不平衡
            while (i <= right && arr[i].compareTo(v) < 0) {
                i++;
            }
            // j位置元素大于v，知道找到小于等于v的元素，循环终止
            // 同理，不能为>=
            while (j >= left + 1 && arr[j].compareTo(v) > 0) {
                j--;
            }
            // 终止条件
            if (i > j) {
                break;
            }
            // 交换i，j位置元素
            swap(arr, i, j);
            i++;
            j--;
        }
        // [left, left + 1...j, i...right]
        swap(arr, left, j);

        return j;
    }

    /**
     * 三路快速排序的partition
     *
     * @param arr   原始数组
     * @param left  左边界
     * @param right 右边界
     * @return 标定值索引
     */
    private static int partition3(Comparable[] arr, int left, int right) {
        // 随机选择标定点
        swap(arr, left, (int) (Math.random() * (right - left + 1) + left));

        Comparable v = arr[left];

        // 维护lt/gt/i指针
        int lt = left; // arr[left+1...lt] < v
        int gt = right + 1; // arr[gt...right] > v
        int i = left + 1; // arr[lt+1...i] == v
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, lt + 1, i);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, gt - 1, i);
                i++;
                gt--;
            } else { // arr[i] == v
                i++;
            }
        }
        // 将left处的标定值放到合适位置
        swap(arr, left, lt);
        return lt;
    }

    /**
     * 小范围内采用插入排序
     *
     * @param arr   原始数组
     * @param left  要排序区间左边界
     * @param right 要排序区间右边界
     */
    private static void insertSort(Comparable[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > left && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = Sorts.generateRandomArray(N, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.MergeSort", arr);

        arr = Sorts.generateRandomArray(N, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.ShellSort", arr);

        arr = Sorts.generateRandomArray(N, 0, 100000);
        Sorts.isSorted("com.lzp.algorithm.sort.QuickSort", arr);
    }

}
