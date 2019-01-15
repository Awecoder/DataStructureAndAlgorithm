package top.dtyy.structure.heap;

import java.util.Arrays;

/**
 * 最小堆--直接数组实现
 * <pre>
 * 直接数组实现时，从索引位置1开始放置元素
 * 父节点 = i / 2
 * 左孩子节点 = 2 * i
 * 右孩子节点 = 2 * i + 1
 * </pre>
 *
 * @author lzp
 * @version v1.0 at 2018/12/6
 */
public class MinHeap<E extends Comparable<? super E>> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;
    private int size;

    public MinHeap(int capacity) {
        // 由于索引0位置置空，容量 + 1
        data = (E[]) new Comparable[capacity + 1];
        size = 0;
    }

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(E[] arr) {
        size = arr.length;
        System.out.println("heap size = " + size);
        data = (E[]) new Comparable[arr.length * 2 + 1];
        for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }
        System.out.println("data initialized " + Arrays.toString(data));
        // 数组元素堆调整，倒序对父节点做下沉操作
        for (int i = parent(size); i > 0; i--) {
            siftDown(i);
        }
        System.out.println("heap.data[] = " + Arrays.toString(data));
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int index) {
        return index * 2;
    }

    private int rightChild(int index) {
        return index * 2 + 1;
    }

    public void add(E e) {
        if (size == data.length - 1) {
            resize(data.length * 2);
        }
        data[++size] = e;
        siftUp(size);
    }

    private void siftUp(int index) {
        // index = 1为堆顶，没必要上浮
        // 小顶堆，父节点元素大于子节点时，子节点上浮
        while (index > 1 && data[parent(index)].compareTo(data[index]) > 0) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    private void swap(int left, int right) {
        E temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Comparable[newCapacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public E findMin() {
        return data[1];
    }

    public E extractMin() {
        E res = findMin();

        swap(1, size);
        data[size--] = null;
        siftDown(1);

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return res;
    }

    private void siftDown(int index) {
        while (leftChild(index) <= size) {
            int child = leftChild(index);
            // 当右节点也存在，并且右节点小于左节点值
            if (child + 1 <= size && data[child].compareTo(data[child + 1]) > 0) {
                child++;
            }
            if (data[index].compareTo(data[child]) > 0) {
                swap(index, child);
            }
            index = child;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 2, 3, 4};
        MinHeap<Integer> heap = new MinHeap<>(arr);

        System.out.println("heap.findMin() = " + heap.findMin());
        System.out.println();
        heap.add(1);
        System.out.println("heap.getSize() = " + heap.getSize());
        System.out.println("heap.data[] = " + Arrays.toString(heap.data));
        System.out.println("heap.findMin() = " + heap.findMin());
        System.out.println();
        heap.extractMin();
        System.out.println("heap.getSize() = " + heap.getSize());
        System.out.println("heap.data[] = " + Arrays.toString(heap.data));
        System.out.println("heap.findMin() = " + heap.findMin());
    }
}
