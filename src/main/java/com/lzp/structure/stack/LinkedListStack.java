package com.lzp.structure.stack;

import com.lzp.structure.list.LinkedList;

/**
 * 栈的链表实现
 *
 * @author lzp
 * @version v1.0 at 2018/11/24
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    /**
     * 元素入栈
     *
     * @param e
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * 元素出栈
     *
     * @return
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    /**
     * 查看栈顶元素
     *
     * @return
     */
    @Override
    public E peek() {
        return list.getFirst();
    }

    /**
     * 获取栈中元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 是否栈空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
