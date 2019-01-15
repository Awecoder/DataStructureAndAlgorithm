package com.lzp.structure.map;

/**
 * 映射接口
 *
 * @author lzp
 * @version v1.0 at 2018/12/2
 */
public interface Map<K, V> {
    /**
     * 添加键值对
     *
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 根据key删除value
     *
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 是否包含key
     *
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 为key设置新值newValue
     *
     * @param key
     * @param newValue
     */
    void set(K key, V newValue);

    /**
     * 映射中元素个数
     *
     * @return
     */
    int getSize();

    /**
     * 映射是否为空
     *
     * @return
     */
    boolean isEmpty();
}
