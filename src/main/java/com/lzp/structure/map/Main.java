package com.lzp.structure.map;


import com.lzp.structure.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类--傲慢与偏见词频统计
 *
 * @author lzp
 * @version v1.0 at 2018/12/2
 */
public class Main {

    public static double testMap(Map<String, Integer> map) {
        long startTime = System.nanoTime();

        List<String> words = new ArrayList<>();
        if (FileUtils.readFile("./src/main/resources/book.txt", words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("Total diff words: " + map.getSize());
            System.out.println("Frequency of pride: " + map.get("pride"));
            System.out.println("Frequency of prejudice: " + map.get("prejudice"));
        }


        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        Map<String, Integer> map1 = new LinkedListMap<>();
        double time1 = testMap(map1);
        System.out.println("LinkedListMap time costs : " + time1 + "s");
        Map<String, Integer> map2 = new BSTMap<>();
        double time2 = testMap(map2);
        System.out.println("BSTMap time costs : " + time2 + "s");
    }
}
