package com.lzp.structure.heap.leetcode;

import java.util.*;

/**
 * 代码进化--->比较器的使用1
 */
public class Solution3 {
    private class Word {
        public int e;
        public int freq;

        public Word(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }
    }

    private class MyComparator implements Comparator<Word>{

        @Override
        public int compare(Word o1, Word o2) {
            return o1.freq - o2.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // 1. 采用Map做词频统计
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 2. 优先队列处理--PriorityQueue 默认最小堆
        Queue<Word> pq = new PriorityQueue<>(new MyComparator());
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Word(key, map.get(key)));
            } else if (map.get(key) > pq.peek().freq) {
                pq.remove();
                pq.add(new Word(key, map.get(key)));
            }
        }

        // 3. 输出
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove().e);
        }
        return res;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> list = new Solution3().topKFrequent(nums, k);
        printList(list);
    }
}
