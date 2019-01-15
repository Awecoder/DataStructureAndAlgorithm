package com.lzp.structure.list;

/**
 * 自定义链表实现
 *
 * @author lzp
 * @version v1.0 at 2018/11/19
 */
public class LinkedList<E> {
    /**
     * 链表节点内部类
     */
    private class Node {
        /**
         * public 使外部类可以访问到节点属性
         */
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

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 设置虚拟头节点
     */
    private Node dummyNode;
    /**
     * 链表元素个数
     */
    private int size;

    public LinkedList() {
        dummyNode = new Node(null, null);
        size = 0;
    }

    /**
     * 获取链表元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表的index（0-based）位置添加新的元素
     * <pre>
     *    关键是找到要添加节点位置的前一个节点
     *    从dummyHead开始遍历
     * </pre>
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        Node prev = dummyNode;
        for (int i = 0; i < index; i++) {
            // null 0 1 2 3
            prev = prev.next;
        }
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next= node;
        /**
         * 优雅的写法，借助Node节点类构造
         */
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头部添加新元素e
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表尾部添加新元素e
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取链表中index(0-based)位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        Node cur = dummyNode.next;
        for (int i = 0; i < index; i++) {
            // null 0 1 2 3
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取链表头部元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表尾部元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表index位置元素值
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        // 此处与get相同
        Node cur = dummyNode.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyNode.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除链表中index(0-index)位置的元素，并返回
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("不合法的参数");
        }
        // 与新增元素处理方法一致
        Node prev = dummyNode;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node cur = prev.next;
        prev.next = cur.next;
        cur.next = null;
        size--;
        return cur.e;
    }

    /**
     * 删除链表头部元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除链表尾部元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素
     *
     * @param e
     */
    public void removeElement(E e) {
        Node prev = dummyNode;
        while (prev.next != null){
            if(prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyNode.next; cur != null; cur = cur.next) {
            res.append(cur + " --> ");
        }
        res.append("Null");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
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
    }
}
