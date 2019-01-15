package top.dtyy.structure.heap.leetcode;

import java.util.*;

/**
 * 代码进化--->比较器的使用3 -- 针对特定数据比较，删除对象类
 */
public class Solution5 {

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
        // 此处删除了Word对象类，优先队列存放只存放单词，不再存放词频
        // map中的词频已经通过比较规则的定义，在优先队列中存放正确的单词
        Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        // 3. 输出
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }

    private static void printList(List<Integer> nums) {
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> list = new Solution5().topKFrequent(nums, k);
        printList(list);
    }
}