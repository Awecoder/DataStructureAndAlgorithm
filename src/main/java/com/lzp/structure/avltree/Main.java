package com.lzp.structure.avltree;

import com.lzp.structure.map.BSTMap;
import com.lzp.structure.util.FileUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * AVL平衡二叉树和二分搜索树性能对比
 *
 * @author lzp
 * @version v1.0 at 2019/1/20
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileUtils.readFile("./src/main/resources/book.txt", words)) {
            System.out.println("Total words: " + words.size());

            // 测试最坏退化成链表的情况
            Collections.sort(words);

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            // Test BSTMap
            long startTime = System.nanoTime();

            BSTMap<String, Integer> bst = new BSTMap<>();
            for (String word : words) {
                if (bst.contains(word)) {
                    bst.set(word, bst.get(word) + 1);
                } else {
                    bst.add(word, 1);
                }
            }
            for (String word : words)
                bst.contains(word);

            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");

            // Test AVLTree
            startTime = System.nanoTime();

            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if (avlTree.contains(word)) {
                    avlTree.set(word, avlTree.get(word) + 1);
                } else {
                    avlTree.add(word, 1);
                }
            }
            for (String word : words)
                avlTree.contains(word);

            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
        }

        System.out.println();
    }
}
