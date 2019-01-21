package com.lzp.algorithm.sort;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		int N = 100;
		Integer[] arr = Sorts.generateRandomArray(N, 0, 10000);
		Sorts.isSorted("com.lzp.algorithm.sort.Sort", arr);
		System.out.println(Arrays.toString(arr));

//		arr = Sorts.generateRandomArray(N, 0, 100000);
//		Sorts.isSorted("com.lzp.algorithm.sort.ShellSort", arr);
//
//		arr = Sorts.generateRandomArray(N, 0, 100000);
//		Sorts.isSorted("com.lzp.algorithm.sort.InsertionSort", arr);
	}

	public static Comparable[] aux; // 归并需要的辅助数组

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];    // 一定要指定aux的大小范围，不然aux为null
		sort(a, 0, a.length - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = (lo + hi) / 2;
		sort(a, lo, mid); // 左半边排序
		sort(a, mid + 1, hi); // 右半边排序
		merge(a, lo, mid, hi); // 原地归并
	}


	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k]; // 先将a[]复制到aux[]

		for (int k = lo; k <= hi; k++) { // 将aux[] 归并到a[]
			if (i > mid) // 左侧元素用尽，取右侧
				a[k] = aux[j++];
			else if (j > hi) // 右侧元素用尽，取左侧
				a[k] = aux[i++];
			else if (aux[j].compareTo(aux[i]) < 0) // 右侧当前元素小于左侧，取右侧
				a[k] = aux[j++];
			else // 左侧小于右侧，取左侧
				a[k] = aux[i++];
		}
	}
}