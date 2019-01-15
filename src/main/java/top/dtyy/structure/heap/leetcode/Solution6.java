package top.dtyy.structure.heap.leetcode;

import java.util.*;

/**
 * 代码进化--->比较器的使用4 -- 采用lambda表达式进一步简化代码
 */
public class Solution6 {

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
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
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
        List<Integer> list = new Solution6().topKFrequent(nums, k);
        printList(list);
    }
}