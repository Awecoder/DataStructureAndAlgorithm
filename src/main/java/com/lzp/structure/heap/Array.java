package com.lzp.structure.heap;

/**
 * 自定义数组类
 *
 * @author lzp
 * @version v1.0 at 2018/10/27
 */
public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 返回数组元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回数组容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 返回数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 动态调整数组
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 向指定位置index添加元素 O(n)
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 容量若满，扩容一倍
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 向数组末尾添加一个元素 0(1)
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向数组头部添加一个元素 0(n)
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 删除并返回index位置元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        E res = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        // 注意置空
        data[size] = null;
        // 元素个数为1/4时，缩容一半
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return res;
    }

    /**
     * 删除尾部元素 0(1)
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除头部元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 获取指定位置元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        return data[index];
    }

    /**
     * 获取头部元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取尾部元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改index索引位置的元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        data[index] = e;
    }

    /**
     * 查找数组是否包含某元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找某元素在数组中的第一个索引，如不存在，返回-1
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除数组中匹配元素e的第一个元素
     *
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("index 不合法");
        }
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1, 100);
        System.out.println(arr);
        arr.addFirst(-1);
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);
        System.out.println(arr.find(8));
        arr.removeElement(8);
        System.out.println(arr);
        System.out.println(arr.get(7));
        arr.set(7, 100);
        System.out.println(arr);
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr);
        System.out.println(arr.contains(100));
    }
}
