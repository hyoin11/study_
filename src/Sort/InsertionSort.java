package Sort;

public class InsertionSort {
	/*
	 * 삽입정렬
	 * 현재 비교하고자 하는 타겟과 그 이전의 원소들과 비교하며 자리를 교환
	 * 두번째 원소부터 시작
	 * '안정정렬'
	 */
	
	public static void insertionSort(int[] arr) {
		insertionSort(arr, arr.length);
	}
	
	private static void insertionSort(int[] arr, int size) {
		for(int i=1; i<size; i++) {
			int target = arr[i];
			
			int j= i-1;
			
			// 타겟이 이전 원소보다 크기 전 까지 반복
			while(j >= 0 && target < arr[j]) {
				arr[j+1] = arr[j];
				j--;
			}
			
			/*
			 * 위 반복문에서 탈출 하는 경우 앞의 원소가 타겟보다 작다는 의미
			 * 타겟 원소는 j번째 원소 뒤에 와야함
			 * 그러므로 타겟은 j+1에 위치
			 */
			arr[j+1] = target;
		}
	}
}
