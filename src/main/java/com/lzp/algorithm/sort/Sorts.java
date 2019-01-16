package com.lzp.algorithm.sort;

import java.lang.reflect.Method;

/**
 * 排序工具类
 * <p>
 * 产生大量随机指定范围内的随机数据
 * 测试排序算法性能
 *
 * @author lzp
 * @version v1.0 at 2019/1/16
 */
public class Sorts {
    // 私有构造
    private Sorts() {
    }

    /**
     * 生成随机数组
     *
     * @param n      数组元素个数
     * @param rangeL 范围左边界
     * @param rangeR 范围右边界
     * @return 返回整型数组
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        // 使用断言，满足条件继续执行，失败报AssertionError
        // 程序中尽量采用异常处理，因为断言会导致程序直接退出
        assert rangeL <= rangeR;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }

    /**
     * 打印数组内容
     *
     * @param arr 数组
     */
    public static void print(Object arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
        return;
    }

    /**
     * 判断数组元素是否有序，按元素比较
     *
     * @param arr 输入数组
     * @return
     */
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断数组在某排序下是否有序
     *
     * @param className 排序类名
     * @param arr       输入数组
     */
    public static void isSorted(String className, Comparable[] arr) {
        try {
            // 通过类名获取Class对象
            Class sortClass = Class.forName(className);
            // 通过Class对象获取到排序方法
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});

            // 排序参数构造
            Object[] params = new Object[]{arr};

            // 排序前时间
            long startTime = System.currentTimeMillis();

            // 调用排序方法
            sortMethod.invoke(null, params);

            // 排序后时间
            long endTime = System.currentTimeMillis();

            // 判断数组是否排序
            assert isSorted(arr);

            System.out.println(sortClass.getSimpleName() + ": " + (endTime - startTime) + "ms");
            Sorts.print(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
