package com.lzp.algorithm.heap;

import java.util.Arrays;

/**
 * 最大索引堆
 *
 * @author lzp
 * @version v1.0 at 2019/3/2
 */
public class IndexMaxHeap<E extends Comparable> {
    /**
     * 最大索引堆中的数据
     **/
    private E[] data;
    /**
     * 最大索引堆中的索引，indexes[x]= i 表示索引i在x的位置
     * indexes数组的左右：保存原始数组data的索引
     */
    private int[] indexes;
    /**
     * 最大索引堆中的反向索引，reverse[i] = x 表示索引i在x的位置
     * reverse数组的作用：根据data索引快速找到indexes对应索引位置
     */
    private int[] reverse;
    /**
     * 堆元素个数
     */
    private int size;
    /**
     * 堆容器容量
     */
    private int capacity;

    /**
     * 构造方法，构造一个空堆，可容纳capacity个元素
     *
     * @param capacity 给定容量
     */
    public IndexMaxHeap(int capacity) {
        // 堆数据容器data数组从1索引位置开始，三个数组都是从1开始索引
        data = (E[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];

        for (int i = 0; i <= capacity; i++) {
            reverse[i] = 0;
        }
        size = 0;
        this.capacity = capacity;
    }

    /**
     * 返回索引堆的元素个数
     *
     * @return 堆元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 返回索引堆是否为空
     *
     * @return 布尔值
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 插入新的元素。传入的i对于用户来讲，是从0索引的。
     *
     * @param i 新元素的索引
     * @param e 新元素
     */
    public void insert(int i, E e) {
        // 底层容器是数组，防止越界
        assert size + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;

        // 判断索引i位置没有元素
        assert !contains(i);

        // 用户输入索引--->data数组实际使用索引
        i = i + 1;
        data[i] = e;
        // 插入到indexes数组末尾
        indexes[size + 1] = i;
        // 此时i对应size + 1，调整后会改变。
        reverse[i] = size + 1;

        // 堆元素个数加1，并且上浮
        siftUp(++size);
    }

    /**
     * 从堆中取出堆顶元素
     *
     * @return 堆顶元素
     */
    public E extractMax() {
        assert size > 0;

        // 查出堆顶元素
        E ret = data[indexes[1]];
        // 交换堆顶与末尾元素，在indexes即为交换data数组索引
        swapIndexes(1, size);
        // 调整原任务的优先级为0，但是并不改变data数组元素
        reverse[indexes[size]] = 0;
        // 堆元素个数减1
        size--;
        // 此时堆顶元素下沉
        siftDown(1);

        return ret;
    }

    /**
     * 获取最大索引堆中的堆顶元素
     * 例：获取优先级最高的进程的优先级
     *
     * @return 元素
     */
    public E getMax() {
        assert size > 0;
        return data[indexes[1]];
    }

    /**
     * 获取最大索引堆中的堆顶元素索引
     * 例：获取优先级最高进程的进程号
     *
     * @return 索引
     */
    public int getMaxIndex() {
        assert size > 0;
        return indexes[1] - 1;
    }

    /**
     * 判断传入i位置是否已存在元素
     * 例：判断某进程号是否已被使用
     *
     * @param i 用户传入索引i
     * @return 布尔值
     */
    public boolean contains(int i) {
        assert i + 1 >= 1 && i + 1 <= capacity;
        // reverse数组初始化为全0
        return reverse[i + 1] != 0;
    }

    /**
     * 获取最大索引堆中索引为i的元素
     * 例：获取进程i的优先级
     *
     * @param i 索引
     * @return 元素
     */
    public E getElement(int i) {
        assert contains(i);

        return data[i + 1];
    }

    /**
     * 获取最大索引堆中索引为i的元素修改为newElement
     * 例：修改进程i的优先级
     *
     * @param i          索引
     * @param newElement 新元素
     */
    public void change(int i, E newElement) {
        assert contains(i);

        // 索引转换，以及元素修改
        i = i + 1;
        data[i] = newElement;

        // 实时调整最大堆中的位置，有可能上浮或下沉
        siftUp(reverse[i]);
        siftDown(reverse[i]);

        // 如果没有reverse数组，根据data索引找到其在reverse索引的位置只能靠遍历
        // 时间复杂度为O(logn)
//        for (int idx = 1; idx <= size; i++) {
//            if (indexes[idx] == i) {
//                siftUp(idx);
//                siftDown(idx);
//                return;
//            }
//        }
    }

    /**
     * 交换indexes两索引位置的原data索引
     *
     * @param i 左索引
     * @param j 右索引
     */
    private void swapIndexes(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;

        // 同时reverse数组也要交换
        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;
    }


    /*****************************************************/
    /*                    堆核心辅助方法                  */
    /*****************************************************/

    /**
     * 元素上浮
     * 索引堆中，数据间比较根据data元素大小比较，但实际操作的是索引
     *
     * @param idx indexes数组索引
     */
    private void siftUp(int idx) {
        while (idx > 1 && data[indexes[idx / 2]].compareTo(data[indexes[idx]]) < 0) {
            // 交换indexes两索引位置的原data索引
            swapIndexes(idx / 2, idx);
            idx /= 2;
        }
    }

    /**
     * 元素下沉
     * 索引堆中，数据间比较根据data元素大小比较，但实际操作的是索引
     *
     * @param idx indexes数组索引
     */
    private void siftDown(int idx) {
        while (2 * idx <= size) {
            int childIdx = 2 * idx;
            if (childIdx + 1 <= size && data[indexes[childIdx + 1]].compareTo(data[indexes[childIdx]]) > 0) {
                childIdx++;
            }
            if (data[indexes[idx]].compareTo(data[indexes[childIdx]]) >= 0) {
                break;
            }
            swapIndexes(idx, childIdx);
            idx = childIdx;
        }
    }

    public static void main(String[] args) {

        int N = 1000000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<Integer>(N);
        for (int i = 0; i < N; i++) {
            indexMaxHeap.insert(i, (int) (Math.random() * N));
        }
        assert indexMaxHeap.testIndexes();
    }

    // 测试索引堆中的索引数组index和反向数组reverse
    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes() {

        int[] copyIndexes = new int[size + 1];
        int[] copyReverseIndexes = new int[size + 1];

        for (int i = 0; i <= size; i++) {
            copyIndexes[i] = indexes[i];
            copyReverseIndexes[i] = reverse[i];
        }

        copyIndexes[0] = 0;
        copyReverseIndexes[0] = 0;
        Arrays.sort(copyIndexes);
        Arrays.sort(copyReverseIndexes);

        // 在对索引堆中的索引和反向索引进行排序后,
        // 两个数组都应该正好是1...size这size个索引
        boolean res = true;
        for (int i = 1; i <= size; i++)
            if (copyIndexes[i - 1] + 1 != copyIndexes[i] ||
                    copyReverseIndexes[i - 1] + 1 != copyReverseIndexes[i]) {
                res = false;
                break;
            }

        if (!res) {
            System.out.println("Error!");
            return false;
        }

        return true;
    }


}
