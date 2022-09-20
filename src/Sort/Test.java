package Sort;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int[] arr = {10, 2, 5, 1, 9, 3, 4, 7, 6, 8};
		
//		SelectionSort selection = new SelectionSort();
//		selection.selctionSort(arr);
		
//		BubbleSort bubble = new BubbleSort();
//		bubble.bubbleSort(arr);
		
//		InsertionSort insertion = new InsertionSort();
//		insertion.insertionSort(arr);
		
//		MergeSort merge = new MergeSort();
//		merge.mergeSort(arr);
		
//		QuickSort quick = new QuickSort();
//		quick.quickSort(arr);
		
//		ShellSort shell = new ShellSort();
//		shell.shellSort(arr);
		
//		HeapSort heap = new HeapSort();
//		heap.heapSort(arr);
		
		BinaryInsertionSort binaryInsertion = new BinaryInsertionSort();
		binaryInsertion.binaryInsertionSort(arr);
		System.out.println(Arrays.toString(arr));
		
	}
}
