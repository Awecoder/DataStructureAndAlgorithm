package com.lzp.structure.heap;

/**
 * 优先队列的实现
 *
 * @author lzp
 * @version v1.0 at 2018/12/5
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    /**
     * 底层实现采用最大堆（数组）
     */
    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
