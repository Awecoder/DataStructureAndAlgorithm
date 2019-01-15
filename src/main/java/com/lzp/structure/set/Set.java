package com.lzp.structure.set;

/**
 * 集合接口
 *
 * @author lzp
 * @version v1.0 at 2018/11/29
 */
public interface Set<E> {
    /**
     * 获取集合元素个数
     *
     * @return
     */
    int getSize();

    /**
     * 返回集合是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 向集合添加新元素
     *
     * @param e
     */
    void add(E e);

    /**
     * 删除集合中元素e
     *
     * @param e
     */
    void remove(E e);

    /**
     * 集合中是否包含某元素
     *
     * @param e
     * @return
     */
    boolean contains(E e);
}
