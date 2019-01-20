package com.lzp.structure.set;

import com.lzp.structure.avltree.AVLTree;
import com.lzp.structure.set.Set;

/**
 * 基于AVLTree实现Set
 *
 * @author lzp
 * @version v1.0 at 2019/1/20
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {
    // 底层容器 -- AVL
    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    /**
     * 获取集合元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return avl.getSize();
    }

    /**
     * 返回集合是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    /**
     * 向集合添加新元素
     *
     * @param e
     */
    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    /**
     * 删除集合中元素e
     *
     * @param e
     */
    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    /**
     * 集合中是否包含某元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }
}
