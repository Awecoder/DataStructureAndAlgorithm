package com.lzp.structure.unionfind;

/**
 * 并查集实现1
 *
 * @author lzp
 * @version v1.0 at 2019/3/24
 */
public class UnionFind1 implements UF {
    /**
     * 底层数组，id为其所属集合
     */
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];

        // 各元素所属id初始化指向自己
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }


    /**
     * 元素个数
     *
     * @return int
     */
    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 判断两元素是否连接
     *
     * @param p 元素索引
     * @param q 元素索引
     * @return 布尔
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查询元素index所对应的集合编号
     * O(1)复杂度
     *
     * @param index
     * @return
     */
    private int find(int index) {
        if (index < 0 || index >= id.length) {
            throw new IllegalArgumentException("index不合法");
        }
        return id[index];
    }

    /**
     * 合并元素，将属于一个集合的元素归为一个集合
     * O(n)复杂度
     *
     * @param p 元素索引
     * @param q 元素索引
     */
    @Override
    public void unionElements(int p, int q) {
        // 分别找出两元素所属集合编号
        int pId = find(p);
        int qId = find(q);

        // 若两元素本来属于同一个集合
        if (pId == qId) {
            return;
        }

        // 遍历整个集合，需要把集合编号id为pId的元素改为qId
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }
}
