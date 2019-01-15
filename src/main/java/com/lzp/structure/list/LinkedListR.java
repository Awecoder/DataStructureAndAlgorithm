package com.lzp.structure.list;

import javafx.util.Pair;

/**
 * 链表--递归实现
 *
 * @author lzp
 * @version v1.0 at 2018/11/24
 */
public class LinkedListR<E> {
    /**
     * 节点内部类
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }
    }

    /**
     * 链表的递归实现中，不需要使用虚拟头节点，就可以实现无差异位置0问题
     */
    private Node head;
    private int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    /**
     * 获取链表中元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表index(0-based)位置添加新的元素e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        head = add(head, index, e);
        size++;
    }

    /**
     * 以node为头节点的链表的index位置插入元素e
     *
     * @param node
     * @param index
     * @param e
     * @return
     */
    private Node add(Node node, int index, E e) {
        // 演草分析
        if (index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }

    /**
     * 链表头部添加新元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 链表尾部添加新元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取链表第index(0-based)位置元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        return get(head, index);
    }

    /**
     * 获取以node为头节点，第index位置的元素
     *
     * @param node
     * @param index
     * @return
     */
    private E get(Node node, int index) {
        if (index == 0) {
            return node.e;
        }
        return get(node.next, index - 1);
    }

    /**
     * 获取链表头部元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表尾部元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表index(0-based)位置的元素
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        set(head, index, e);
    }

    /**
     * 以node的为头节点，修改第index位置的元素
     *
     * @param node
     * @param index
     * @param e
     */
    private void set(Node node, int index, E e) {
        if (index == 0) {
            node.e = e;
            // 此处一定要有返回
            return;
        }
        set(node.next, index - 1, e);
    }

    /**
     * 查找链表中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(head, e);
    }

    /**
     * 以node为节点，查找是否包含元素e
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.equals(e)) {
            return true;
        }
        return contains(node.next, e);
    }

    /**
     * 删除链表中第index(0-based)位置元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        Pair<Node, E> res = remove(head, index);
        size--;
        head = res.getKey();
        return res.getValue();
    }

    /**
     * 以node为头节点，删除index位置的元素
     * 返回值包含两个元素，删除后链表头节点和删除的值
     *
     * @param node
     * @param index
     * @return
     */
    private Pair<Node, E> remove(Node node, int index) {
        if (index == 0) {
            return new Pair<>(node.next, node.e);
        }
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    /**
     * 删除头部元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除尾部元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 链表中删除元素e
     * @param e
     */
    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }
        node.next = removeElement(node.next, e);
        if (node.e.equals(e)) {
            size--;
            return node.next;
        } else {
            return node;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while(cur != null){
            res.append(cur.e + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListR<Integer> linkedList = new LinkedListR<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);
        System.out.println(linkedList.getSize());
        linkedList.addLast(999);
        System.out.println(linkedList);
        System.out.println(linkedList.contains(999));
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println(linkedList.get(2));
        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.addLast(3);
        System.out.println(linkedList);
        linkedList.removeElement(3);
        System.out.println(linkedList);
        System.out.println(linkedList.getSize());
    }
}
