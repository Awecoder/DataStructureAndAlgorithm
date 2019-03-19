package com.lzp.structure.trie;

import java.util.TreeMap;

/**
 * Trie树（字典树、前缀树）的实现
 *
 * @author lzp
 * @version v1.0 at 2019/3/17
 */
public class Trie {
    /**
     * 节点实现
     */
    class Node {
        // 当前字母节点是否为某单词结尾
        public boolean isWord;
        // TreeMap用于承载当前节点孩子节点
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    // 根节点
    private Node root;
    // 元素个数
    private int size;

    public Trie() {
        root = new Node();
        this.size = 0;
    }

    /**
     * 向树中添加新的单词
     *
     * @param word 新单词
     */
    public void add(String word) {
        // 当前节点
        Node cur = root;
        // 根据单词字母向下匹配
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 若没有匹配节点，新创建一个节点类型
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 判断最后一个字母的状态,如果为新单词修改isWord和size
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查看单词word是否在Trie树中
     *
     * @param word 给定单词
     * @return 布尔
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询Trie树中是否有单词匹配前缀prefix
     *
     * @param prefix 前缀
     * @return 布尔
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    /**
     * 查询Trie树中是否有单词匹配前缀prefix，字符【.】匹配任意字符
     * 此即为LeetCode 211
     *
     * @param word 给定字符串
     * @return 布尔
     */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        // 若字符c不是[.]，判断是否能匹配到下一个节点
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else { // 通配符[.]
            // 如果下一个字符是.,则遍历当前node所有的子节点集合
            for (char nextChar : node.next.keySet()) {
                //
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 获取单词个数
     *
     * @return int
     */
    public int getSize() {
        return size;
    }


}
