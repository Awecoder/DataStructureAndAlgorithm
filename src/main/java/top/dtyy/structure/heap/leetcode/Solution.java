package top.dtyy.structure.heap.leetcode;


import top.dtyy.structure.heap.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * LeetCode 347
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 底层实现采用自定义优先队列
 * 通常该类问题会采用小顶堆来实现。
 * 此次实现采用大顶堆，并改变词频优先级
 */
public class Solution {
    /**
     * 2.2 单词内部类，用于承载单词和词频数据
     * 优先队列需要元素具有可比较性
     */
    private class Word implements Comparable<Word> {
        private int e, freq;

        public Word(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Word other) {
            if (this.freq < other.freq) {
                // 一般情况下，当前元素值比传入元素值大时，返回1
                // 此处相反，频次较小优先级越高，因此小于时返回1
                return 1;
            } else if (this.freq > other.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // 1. 首先采用TreeMap统计出单词词频
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 2.1 采用优先队列（最大堆）提取前k个元素
        PriorityQueue<Word> pq = new PriorityQueue<>();
        // 遍历map的key集合
        for (int key : map.keySet()) {
            if (pq.getSize() < k) {
                // 此时优先队列不满，元素数小于k
                // 元素入优先队列，元素大的下沉
                pq.enqueue(new Word(key, map.get(key)));
            } else if (map.get(key) > pq.getFront().freq) {
                // 当前单词词频大于优先队列中最小元素词频，
                // 在当前最大堆下，替换顶部元素，并判断是否下沉
                pq.dequeue();
                pq.enqueue(new Word(key, map.get(key)));
            }
        }
        // 3. 结果输出
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.dequeue().e);
        }
        return res;
    }
}