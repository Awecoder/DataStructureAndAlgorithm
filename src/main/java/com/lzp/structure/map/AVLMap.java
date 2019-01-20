package com.lzp.structure.map;

import com.lzp.structure.avltree.AVLTree;

/**
 * 基于AVL平衡二叉树实现map
 *
 * @author lzp
 * @version v1.0 at 2019/1/20
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
    // 底层容器--AVL树
    private AVLTree<K, V> avl;

    public AVLMap() {
        this.avl = new AVLTree<>();
    }

    /**
     * 添加键值对
     *
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        avl.add(key, value);
    }

    /**
     * 根据key删除value
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        return avl.remove(key);
    }

    /**
     * 是否包含key
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key) {
        return avl.contains(key);
    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        return avl.get(key);
    }

    /**
     * 为key设置新值newValue
     *
     * @param key
     * @param newValue
     */
    @Override
    public void set(K key, V newValue) {
        avl.set(key, newValue);
    }

    /**
     * 映射中元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return avl.getSize();
    }

    /**
     * 映射是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
