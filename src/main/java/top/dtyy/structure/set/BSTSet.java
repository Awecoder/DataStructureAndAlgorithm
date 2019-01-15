package top.dtyy.structure.set;

import top.dtyy.structure.bst.BST;

/**
 * 集合实现--使用自定义二分搜索树
 *
 * @author lzp
 * @version v1.0 at 2018/11/29
 */
public class BSTSet<E extends Comparable<E>> implements Set<E>{
    /**
     * 底层采用二分搜索树
     */
    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    /**
     * 获取集合元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return bst.size();
    }

    /**
     * 返回集合是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    /**
     * 向集合添加新元素
     *
     * @param e
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }

    /**
     * 删除集合中元素e
     *
     * @param e
     */
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    /**
     * 集合中是否包含某元素
     *
     * @return
     */
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }
}
