package com.lzp.structure.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先遍历的非递归实现
 * <pre>
 *  时间复杂度0(n),空间复杂度0(h)--h是树的高度
 *  </pre>
 *
 * @author lzp
 * @version v1.0 at 2018/11/28
 */
public class BSTSearchNR {
    /**
     * 节点内部类
     */
    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 前序遍历非递归实现
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(Node root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            res.add(curNode.val);

            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }
        return res;
    }

    public List<Integer> inOrderTraversal(Node root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                res.add(curNode.val);
                curNode = curNode.right;
            }
        }
        return res;
    }

    public List<Integer> postOrderTraversal(Node root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Stack<Node> output = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            output.push(curNode);

            if (curNode.left != null) {
                stack.push(curNode.left);
            }
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
        }
        while (!output.isEmpty()) {
            res.add(output.pop().val);
        }
        return res;
    }
}
