package com.lzp.structure.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 键值映射--返回所有以该前缀开头的键的值的总和
 * LeetCode 677
 *
 * @author lzp
 * @version v1.0 at 2019/3/19
 */
public class MapSum {
    class Node {
        public int val;
        public Map<Character, Node> next;

        public Node(int val) {
            this.val = val;
            next = new HashMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node node) {
        int res = node.val;
        for (char c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }
}
