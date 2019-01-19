package com.lzp.structure.avltree;

import com.lzp.structure.util.FileUtils;

import java.util.ArrayList;

/**
 * AVL平衡二叉树实现--基于二分搜索树基础实现
 * <p>
 * 平衡二叉树出现的原因：就是防止二分搜索树退化成链表
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

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { // key相等时，更新key值
            node.value = value;
        }
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced : " + balanceFactor);
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

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
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
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 此时找到要删除的节点
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = mininum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
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
        }

        System.out.println();
    }
}
