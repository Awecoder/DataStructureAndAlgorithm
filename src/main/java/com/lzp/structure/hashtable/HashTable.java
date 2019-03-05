package com.lzp.structure.hashtable;

import java.util.TreeMap;

/**
 * 哈希表实现
 *
 * @author lzp
 * @version v1.0 at 2019/3/5
 */
public class HashTable<K, V> {

    // 取模值素数数组
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317,
            196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843,
            50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    // 最大最小可容忍平均数
    private static final int UPPER_TOLERANCE = 10;
    private static final int LOWER_TOLERANCE = 2;
    // 初始模数组索引
    private int capacityIndex = 0;

    // 哈希表底层数据结构--此次采用的是TreeMap
    // 此处的bug，hashtable是不要求元素可比较的，
    // 然底层采用了TreeMap有序容器要求元素可比较
    private TreeMap<K, V>[] hashtable;
    // 哈希表元素个数
    private int size;
    // 模
    private int M;

    public HashTable() {
        this.M = capacity[capacityIndex];
        this.size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    /**
     * 哈希函数--快速找到数组索引
     *
     * @param key key值
     * @return 索引位置
     */
    private int hash(K key) {
        // 对key哈希值按位与，取绝对值
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 获取元素个数
     *
     * @return 个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 向哈希表添加元素
     *
     * @param key   key
     * @param value value
     */
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= UPPER_TOLERANCE * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    /**
     * 哈希表删除元素
     *
     * @param key key
     * @return value
     */
    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.get(key);
            size--;

            if (size < LOWER_TOLERANCE * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    /**
     * 修改元素value
     *
     * @param key   key
     * @param value value
     */
    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "doesn't exist");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    /**
     * 采用新的模扩容
     *
     * @param newM 新的模值
     */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                // 陷阱：一定要保证此时hash()采用的是新的newM，this.M = newM.
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }
}
