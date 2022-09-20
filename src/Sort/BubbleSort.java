package Sort;

public class BubbleSort {
	/*
	 * 거품정렬
	 * 두 개의 인접한 원소를 비교하여 정렬 (매번 연속된 두 인덱스를 비교)
	 * 앞에서부터 현재 원소와 바로 다음 원소를 비교한 뒤 조건에 일치하면 교환
	 */
	
	public static void bubbleSort(int[] arr) {
		bubbleSort(arr, arr.length);
	}
	
	private static void bubbleSort(int[] arr, int size) {
		// 배열 크기 -1만큼 진행
		for(int i=1; i<size; i++) {
			// 각 라운드별 비교 횟수는 배열 크기의 현재 라운드를 뺸 만큼 비교함
			for(int j=0; j<size-i; j++) {
				// 현재 원소가 다음 원소보다 클 경우 서로 원소의 위치를 바꿔줌
				if(arr[j] > arr[j+1]) {
					swap(arr, j, j+1);
				}
			}
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
