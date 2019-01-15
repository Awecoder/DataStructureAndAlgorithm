package top.dtyy.structure.set;

import top.dtyy.structure.util.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合测试类
 *
 * @author lzp
 * @version v1.0 at 2018/11/29
 */
public class Main {
    public static double testSet(Set<String> set) {
        long startTime = System.nanoTime();

        List<String> words = new ArrayList<>();
        if (FileUtils.readFile("./src/main/resources/book.txt", words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total diff words: " + set.getSize());
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        Set<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet);
        System.out.println("BSTSet time costs : " + time1 + "s");
        Set<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet);
        System.out.println("LinkedListSet time costs : " + time2 + "s");
    }
}
