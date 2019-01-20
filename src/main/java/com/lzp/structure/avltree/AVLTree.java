package com.lzp.structure.avltree;

import com.lzp.structure.util.FileUtils;

import java.util.ArrayList;

/**
 * AVL平衡二叉树实现--基于二分搜索树基础实现
 * <p>
 * 平衡二叉树出现的原因：就是防止二分搜索树退化成链表
 * <p>
 * 代码可优化的地方---> 在执行新增操作后，计算节点的高度，
 * 如果节点的高度与未插入元素之前的高度一致的话，就不再需要进行自平衡操作。
 *
 * @author lzp
 * @version v1.0 at 2018/12/2
 */
public class AVLTree<K extends Comparable<K>, V> {
    /**
     * 内部节点类
     */
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        // 节点高度值
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            // 对于新的叶子节点高度值为1
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取节点的高度
     * <p>
     * 主要是对node为空做处理，而不需要在其它代码逻辑中反复判断
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获取节点的平衡因子
     * <p>
     * 平衡二叉树特性，同一节点的左右子树高度差不大于1
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断该二叉树是否是一棵二分搜索树
     * <p>
     * 二分搜索树特性：采用中序遍历，得到的序列是有序的。
     *
     * @return 布尔
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     * <p>
     * 根据高度差不大于1的特性
     *
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        // 递归
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }


    /**
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     * <pre>
     *         y                              x
     *        / \                           /   \
     *       x   T4     向右旋转 (y)        z     y
     *      / \       - - - - - - - ->    / \   / \
     *     z   T3                       T1  T2 T3 T4
     *    / \
     *  T1   T2
     * </pre>
     *
     * @param y 高度差大于1的节点
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程<核心代码>
        x.right = y;
        y.left = T3;

        // 更新节点的高度值，只需要更新x和y节点，其它没有改变
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     * <pre>
     *     y                             x
     *   /  \                          /   \
     *  T1   x      向左旋转 (y)       y     z
     *      / \   - - - - - - - ->   / \   / \
     *    T2  z                     T1 T2 T3 T4
     *       / \
     *      T3 T4
     *  </pre>
     *
     * @param y 高度差大于1的节点
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新节点的高度值，只需要更新x和y节点，其它没有改变
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * 平衡树插入元素操作
     * <p>
     * 由于二叉搜索树插入元素时，都是插入到叶子节点上的。
     * 非叶子节点，进行值替换。
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        // 递归终止条件
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        // 递归逻辑过程
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { // key相等时，更新key值
            node.value = value;
        }

        // 平衡二叉树准备操作
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced : " + balanceFactor);
//        }

        // 自平衡操作
        // LL情况：左孩子的左子树插入节点，进行右旋转
        // 条件1：树:不平衡
        // 条件2：左孩子的左子树高度大于等于右子树高度
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR情况：右孩子的右子树插入节点，进行做旋转
        // 条件1：树不平衡
        // 条件2：右孩子的右子树高度大于等于左子树高度
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR情况：左孩子的右子树插入节点，先对左孩子进行左旋转，再进行右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL情况：右孩子的左子树插入节点，先对右孩子进行右旋转，再进行左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
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

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    private Node mininum(Node node) {
        if (node.left == null) {
            return node;
        }
        return mininum(node.left);
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        // 用维护要返回的node
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else { // key.compareTo(node.key) == 0

            if (node.left == null) { // 待删除节点左子树为空的情况
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) { // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else { // 左右孩子均不为空的情况(核心代码)
                // 找到右子树最小元素
                Node successor = mininum(node.right);
                // 右子树删除最小元素
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }
        // 在删除元素之后，node的为空直接返回null
        if (retNode == null) {
            return null;
        }
        // 更新节点高度
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int blanceFactor = getBalanceFactor(retNode);

        // 自平衡操作
        // LL
        if (blanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // LR
        if (blanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RR
        if (blanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // RL
        if (blanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileUtils.readFile("./src/main/resources/book.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST: " + map.isBST());
            System.out.println("is Balanced: " + map.isBalanced());

            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced()) {
                    throw new RuntimeException("Error");
                }
            }
        }

        System.out.println();
    }
}
