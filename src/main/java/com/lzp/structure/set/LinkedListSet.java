package com.lzp.structure.set;

import com.lzp.structure.list.LinkedList;

/**
 * 集合的链表实现
 * <pre>
 *     链表并不需要元素具有可比较性，
 *     故不需要实现Comparable接口
 * </pre>
 *
 * @author lzp
 * @version v1.0 at 2018/11/29
 */
public class LinkedListSet<E> implements Set<E> {
    /**
     * 底层实现使用自定义链表
     */
    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    /**
     * 获取集合元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 返回集合是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 向集合添加新元素
     *
     * @param e
     */
    @Override
    public void add(E e) {
        if(!list.contains(e)){
            list.addFirst(e);
        }
    }

    /**
     * 删除集合中元素e
     *
     * @param e
     */
    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    /**
     * 集合中是否包含某元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }
}
