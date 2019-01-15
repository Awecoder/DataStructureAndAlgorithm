package top.dtyy.structure.segmenttree;

import java.util.Arrays;

/**
 * @author lzp
 * @version v1.0 at 2019/1/3
 */
public class Test {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, -2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2, 4));
        segmentTree.set(2, 6);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2, 4));
    }
}
