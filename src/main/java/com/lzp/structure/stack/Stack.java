package com.lzp.structure.stack;

/**
 * 自定义栈接口
 *
 * @author lzp
 * @version v1.0 at 2018/10/27
 */
public interface Stack<E> {
    /**
     * 元素入栈
     *
     * @param e
     */
    void push(E e);

    /**
     * 元素出栈
     *
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素
     *
     * @return
     */
    E peek();

    /**
     * 获取栈中元素个数
     *
     * @return
     */
    int getSize();

    /**
     * 是否栈空
     *
     * @return
     */
    boolean isEmpty();
}
