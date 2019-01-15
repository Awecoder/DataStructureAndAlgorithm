package top.dtyy.structure.segmenttree;

/**
 * 线段树
 *
 * @author lzp
 * @version v1.0 at 2019/1/3
 */
public class SegmentTree<E> {
    /**
     * 底层线段树容器--数组（会有用不到的空间）
     */
    private E[] tree;

    /**
     * 元素容器
     */
    private E[] data;

    /**
     * 数据融合接口
     */
    private Merger<E> merger;

    /**
     * 构造
     *
     * @param arr    传入数组
     * @param merger 数据融合器
     */
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        // 初始化线段树数组
        tree = (E[]) new Object[4 * arr.length];
        // 创建线段树
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 创建线段树
     *
     * @param treeIndex tree数组索引位置
     * @param l         data数组区间左索引
     * @param r         data数组区间右索引
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归终止条件
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // 计算左右子树索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // data数组元素索引
        // 防止l + r值很大时溢出
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 节点的值取决于业务，例如左右子树和、最大值等
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return data.length;
    }

    /**
     * 获取指定索引位置元素
     *
     * @param index 索引
     * @return 元素
     */
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("不合法的index");
        }
        return data[index];
    }

    /**
     * 返回左孩子节点索引(元素从数组0位置开始放的规律)
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回右孩子节点索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 返回区间[queryL, queryR]的值
     *
     * @param queryL 区间左边界
     * @param queryR 区间右边界
     * @return 范围的值
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("索引不合法");
        }
        // 返回在以0为根的线段树中[0, data.length-1]范围内，搜索[queryL, queryR]的值
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 回在以treeId为根的线段树中[l, r]范围内，搜索[queryL, queryR]的值
     * <pre>
     * 例如在[0, 7]区间内查询[2, 5]区间值 【logn】
     * </pre>
     *
     * @param treeIndex 线段树节点索引
     * @param l         data中区间左边界
     * @param r         data中区间右边界
     * @param queryL    data中搜索区间左边界
     * @param queryR    data中搜索区间右边界
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 递归终止条件
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        // 未终止，需要到孩子节点中查找
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 搜索区间在节点单个子树的情况
        if (queryL >= mid + 1) {
            // 搜索区间左边界在右侧时，整个搜索区间都会在右侧区间中
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) { // 条件不要写错幺!!
            // 同理，右边界小于中间值，说明整个搜索区间在左侧
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        // 不满足单子树情况，说明搜索区间分布在两个子树上
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 将index位置的值，更新为e【logn】
     *
     * @param index 索引位置
     * @param e     要替换的值
     */
    public void set(int index, E e) {
        // 越界判断
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index非法!");
        }
        // 更新数据容器index位置值
        data[index] = e;
        // 更新线段树中各索引位置的值
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 递归更新线段树
     *
     * @param treeIndex 线段树中当前索引位置
     * @param l         data数组左边界
     * @param r         data数组右边界
     * @param index     data数组要求更新的索引位置
     * @param e         要替换的值
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 递归终止条件：找到index位置
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 若index在右边界[mid+1, r]
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }

        // 重点来了！
        // 某索引位置值更新，其父索引位置值也要相应更新
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(",");
            }
        }
        return res.toString();
    }
}
