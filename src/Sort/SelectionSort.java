package Sort;

public class SelectionSort {
	/*
	 * 선택정렬
	 * 현재 위치에 들어갈 데이터를 찾아 선택하는 알고리즘
	 * 최솟값을 찾아 맨 앞자리와 교환
	 */
	
	public static void selctionSort(int[] arr) {
		selectionSort(arr, arr.length);
	}
	
	private static void selectionSort(int[] arr, int size) {
		for(int i=0; i<size-1; i++) {
			int min_index = i;
			
			// 최소값을 갖고 있는 인덱스 찾기
			for(int j=i+1; j<size; j++) {
				if(arr[j] < arr[min_index]) {
					min_index = j;
				}
			}
			
			// i번째 값과 찾은 최소값을 서로 교환
			swap(arr, min_index, i);
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
