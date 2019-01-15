package com.lzp.structure.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树的实现
 *
 * @author lzp
 * @version v1.0 at 2018/10/28
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树添加新的元素
     *
     * @param e
     */
    public void add(E e) {
        // 必须对root重新赋值
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树种插入元素e
     * 【递归算法】
     * 返回插入新节点后二分搜索树的根
     *
     * @param node 要插入新元素的节点
     * @param e    元素
     * @return
     */
    private Node add(Node node, E e) {
        // 将null视为一颗二叉树
        // 初始调用时，不需要单独讨论root==null
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 二分搜索树是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 以node为根的二分搜索树中是否包含元素e
     * 【递归算法】
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else { // 找到元素e
            return true;
        }
    }

    /**
     * 二分搜索树的前序遍历--根左右
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 以node为根节点的前序遍历--根左右
     * 【递归算法】
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            // 栈后入先出，故先压右节点
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树的中序遍历--左根右
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 以node为根节点的中序遍历--左根右
     * 【递归算法】
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 二分搜索树的后序遍历--左右根
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 以node为根节点的后序遍历--左右根
     * 【递归算法】
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二分搜索树的层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 返回二分搜索树的最小元素
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST为空");
        }
        return minimumNR(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值节点
     * 【递归算法】
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        // 终止条件
        if (node.left == null) {
            return node;
        }
        // 更小的问题
        return minimum(node.left);
    }

    /**
     * 以node为根的二分树最小节点
     * 【非递归】
     *
     * @param node
     * @return
     */
    private Node minimumNR(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 返回二分搜索树的最小元素
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST为空");
        }
        return maximumNR(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大节点
     * 【递归算法】
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        // 终止条件
        if (node.right == null) {
            return node;
        }
        // 更小的问题
        return maximum(node.right);
    }

    /**
     * 以node为根的二分树最大节点
     * 【非递归】
     *
     * @param node
     * @return
     */
    private Node maximumNR(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 从二分搜索树中删除最小值所在节点，返回最小值
     *
     * @return
     */
    public E removeMin() {
        // 查找最小值仅用于返回值
        E res = minimum();
        root = removeMin(root);
        return res;
    }

    /**
     * 删除以node为根的二分搜索树的最小节点
     *
     * @param node
     */
    private Node removeMin(Node node) {
        // 终止条件
        if (node.left == null) {
            // 当前节点可能存在右子树
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        // 删除node.left中最小节点，结果重新赋给node.left
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最大节点
     *
     * @return
     */
    public E removeMax() {
        E res = maximum();
        root = removeMax(root);
        return res;
    }

    /**
     * 删除以node为根的二分搜索树最大节点
     *
     * @param
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }
    // 叶子节点的删除操作与只有左或右孩子节点的删除操作一致

    /**
     * 从二分搜索树中删除元素为e的节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 【递归算法】
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    public Node remove(Node node, E e) {
        // 未找到该元素
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // 找到该节点
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，替换待删除节点
            Node successor = minimum(node.right);
            // removeMin中已对size做了处理
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点【算法描述中的实现】
     *
     * @param node
     * @param e
     * @return
     */
    public Node remove1(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove1(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove1(node.right, e);
        } else if (node.left != null && node.right != null) {
            node.e = minimum(node.right).e;
            node.right = remove1(node.right, node.e);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     * 【采用前序遍历方式】
     *
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateBSTDepth(depth) + "null\n");
            return;
        }
        res.append(generateBSTDepth(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateBSTDepth(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
