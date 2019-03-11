package com.lzp.structure.rbtree;

/**
 * 自定义实现左倾红黑树
 *
 * @author lzp
 * @version v1.0 at 2019/3/11
 */
public class RBTree<K extends Comparable<K>, V> {
    /**
     * 定义红黑常量
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        // 节点颜色标记
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            // 节点初始化为红色，因为2-3树中新节点总是要与其它节点做融合
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断节点node的颜色
     *
     * @param node 节点
     * @return 布尔
     */
    private boolean isRed(Node node) {
        // 红黑树的叶子节点是空节点，为黑色
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        // 若插入2-节点，原node是黑节点
        // 若插入3-节点，原node是红色节点
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 颜色翻转操作--根节点可能需要向上融合，是临时节点
     *
     * @param node 节点
     */
    private void flipsColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 向红黑树中添加新元素
     *
     * @param key   键
     * @param value 值
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 根节点必然为黑色
    }

    /**
     * 向node节点插入新元素
     *
     * @param node  当前节点
     * @param key   键
     * @param value 值
     * @return 添加后的节点
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value); // 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 解决思路总是先插入元素，再调整树结构
        // 第一步：左旋转
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 第二步：右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 第三步：颜色翻转
        if (isRed(node.left) && isRed(node.right)) {
            flipsColors(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key doesn't exist");
        }
        node.value = value;
    }
}
