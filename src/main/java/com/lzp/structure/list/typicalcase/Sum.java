package com.lzp.structure.list.typicalcase;

/**
 * 数组加和--递归的使用
 *
 * @author lzp
 * @version v1.0 at 2018/11/24
 */
public class Sum {
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int num) {
        // 递归终止条件-->求解基本问题
        if (num == arr.length) {
            return 0;
        }
        // 把原问题转化为更小的子问题
        return arr[num] + sum(arr, num + 1);
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 2, 1, 4};
        System.out.println(sum(nums));
    }
}
