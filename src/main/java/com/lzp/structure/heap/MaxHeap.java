package com.lzp.structure.heap;

/**
 * 使用数组实现最大堆
 *
 * @author lzp
 * @version v1.0 at 2018/12/3
 */
public class MaxHeap<E extends Comparable<E>> {
    /**
     * 底层采用自定义数组来实现
     * <pre>
     *     由于采用已有实现，数组元素从0索引位置开始
     *     父节点位置 = (i-1) / 2
     *     左节点位置 = 2 * i + 1
     *     右节点位置 = 2 * i + 2
     * </pre>
     */
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * heapify操作：将任意数字整理成堆的形状
     * 从第一个非叶子节点开始，从后往前做下沉操作。
     * 找到第一个非叶子节点，即找到最后一个节点的父亲节点。
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // heapify操作
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 返回堆中元素个数
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("0没有父节点");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮操作：元素从数组最后上浮到正确位置
     *
     * @param index
     */
    private void siftUp(int index) {
        // 父节点小于子节点时，交换位置
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            // 更新当前索引
            index = parent(index);
        }
    }
    // 下沉操作
    // 当从最大堆中取出元素后，会留下空位置
    // 取数组最后的元素，放置于堆首位置
    // 下沉：与最大的孩子交换位置

    /**
     * 返回堆中最大元素
     *
     * @return
     */
    public E findMax() {
        return data.get(0);
    }

    /**
     * 从堆中取出最大元素
     *
     * @return
     */
    public E extractMax() {
        E res = findMax();

        // 交换数组0位置和最后元素
        data.swap(0, data.getSize() - 1);
        // 删除尾部元素
        data.removeLast();
        // 头部元素下沉操作
        siftDown(0);

        return res;
    }

    /**
     * 父节点位置元素下沉
     *
     * @param index
     */
    private void siftDown(int index) {
        // 下沉操作至少需要左孩子节点存在
        while (leftChild(index) < data.getSize()) {
            int child = leftChild(index);
            if (child + 1 < data.getSize()
                    && data.get(child + 1).compareTo(data.get(child)) > 0) {
                // 若右孩子节点存在，并大于左孩子节点
                child++;
            }
            // 如果当前节点大于孩子节点中的最大值，break
            if (data.get(index).compareTo(data.get(child)) >= 0) {
                break;
            }
            data.swap(index, child);
            // 更新元素索引
            index = child;
        }
    }

    /**
     * 取出堆中最大元素，并替换成元素e
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E res = findMax();
        // 将堆顶元素替换成元素e
        data.set(0, e);
        // 堆顶元素下沉
        siftDown(0);

        return res;
    }
}
