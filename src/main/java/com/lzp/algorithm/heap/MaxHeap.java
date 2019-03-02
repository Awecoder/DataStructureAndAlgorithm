package com.lzp.algorithm.heap;

import java.util.Arrays;

/**
 * 使用数组实现最大堆
 *
 * @author lzp
 * @version v1.0 at 2019/2/25
 */
public class MaxHeap<E extends Comparable> {
    // 底层数据结构
    private E[] data;
    private int size;

    // 构造一个空堆，可容纳capacity个元素
    public MaxHeap(int capacity) {
        // 堆元素从索引1开始
        data = (E[]) new Comparable[capacity + 1];
        size = 0;
    }

    // 根据已有数组构建最大堆
    public MaxHeap(E[] arr) {
        size = arr.length;
        data = (E[]) new Comparable[arr.length * 2 + 1];
        for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }

        // 倒序下沉
        for (int i = parent(size); i > 0; i--) {
            siftDown(i);
        }
    }

    // 返回堆元素个数
    public int getSize() {
        return size;
    }

    // 判断是否堆空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取父节点索引
    public int parent(int index) {
        return index / 2;
    }

    // 获取左孩子节点索引
    public int leftChild(int index) {
        return 2 * index;
    }

    // 获取右孩子节点索引
    public int rightChild(int index) {
        return 2 * index + 1;
    }

    // 添加元素
    public void add(E e) {
        if (size == data.length - 1) {
            resize(data.length * 2); // 扩容
        }
        data[++size] = e;
        siftUp(size);
    }

    // 上浮操作
    private void siftUp(int index) {
        while (index > 1 && data[parent(index)].compareTo(data[index]) < 0) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // 交换索引位置元素
    private void swap(int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    // 扩容操作
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Comparable[newCapacity];
        // 堆元素从1开始的话要去到size
        for (int i = 0; i < size + 1; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 获取最大堆最大值
    public E findMax() {
        return data[1];
    }

    // 获取并移除最大值
    public E extractMax() {
        E res = findMax();

        swap(1, size);
        data[size--] = null;
        siftDown(1);

        // 缩容
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return res;
    }

    // 下沉操作
    private void siftDown(int index) {
        while (leftChild(index) <= size) {
            int child = leftChild(index);
            // 如果右节点存在，找出最大的节点。
            if (child + 1 <= size && data[child].compareTo(data[child + 1]) < 0) {
                child++;
            }
            // 终止条件
            if (data[index].compareTo(data[child]) >= 0) {
                break;
            }
            // 如果当前节点值小于最大子节点值，交换
            swap(index, child);
            index = child;
        }
    }

    // 测试
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(10);

        int num = 10;
        for (int i = 0; i < num; i++) {
            maxHeap.add(new Integer((int) (Math.random() * num)));
        }
        System.out.println(maxHeap.getSize() + "个");

        Integer[] arr = new Integer[num];
        for (int i = 0; i < num; i++) {
            arr[i] = maxHeap.extractMax();
        }

        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < num; i++) {
            assert arr[i - 1] >= arr[i];
        }
    }
}
