package com.lzp.structure.unionfind;

/**
 * 并查集接口
 *
 * @author lzp
 * @version v1.0 at 2019/3/24
 */
public interface UF {
    /**
     * 元素个数
     *
     * @return int
     */
    int getSize();

    /**
     * 判断两元素是否连接
     *
     * @param p 元素索引
     * @param q 元素索引
     * @return 布尔
     */
    boolean isConnected(int p, int q);

    /**
     * 合并元素，将属于一个集合的元素归为一个集合
     *
     * @param p 元素索引
     * @param q 元素索引
     */
    void unionElements(int p, int q);
}
