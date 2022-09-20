package Sort;

public class BinaryInsertionSort {
	/*
	 * 이진 삽입 정렬
	 * 
	 * - 오름차순을 기준으로-
	 * 1. 현재 타겟이 되는 숫자에 대해 이전 위치에 있는 원소들에 들어 갈 위치를 이분 탐색을 통해 얻어냄(첫번째 타겟은 두번째 원소부터 시작)
	 * 2. 들어가야 할 위치를 비우기 위해 후방 원소들을 한칸 씩 밀고 비운 공간에 타겟을 삽입
	 * 3. 그 다음 타겟을 찾아 위와 같은 방법으로 반복
	 */
	
//	public static void binaryInsertionSort(int[] arr) {
//		binaryInsertionSort(arr, arr.length);
//	}
//	
//	private static void binaryInsertionSort(int[] arr, int size) {
//		for(int i=1; i<size; i++) {
//			int target = arr[i];
//			
//			// 이분탐색을 통해 target이 들어가야 할 위치를 얻음
//			int location = binarySearch(arr, target, 0, i);
//			
//			int j = i -1;
//			
//			// 시프팅 작업
//			while(j >= location) {
//				arr[j+1] = arr[j];	// 이전 원소를 한칸씩 뒤로 미룸
//				j--;
//			}
//			
//			arr[location] = target;
//		}
//	}
//	
//	private static int binarySearch(int[] arr, int key, int low, int high) {
//		int mid;
//		while(low < high) {
//			mid = low + ((high - low) / 2);
//			
//			if(key < arr[mid]) {
//				// 탐색 상한선을 중간위치로 변경
//				high = mid;
//			}else {
//				// 하한선을 중간 다음 위치로 변경
//				low = mid + 1;
//			}
//		}
//		
//		return high;
//	}
	
	public static void binaryInsertionSort(int[] arr) {
		if(arr.length < 2 ) return;
		
		int incLength = getAscending(arr, 0, arr.length);
		binaryInsertionSort(arr, 0, arr.length, incLength);
	}
	
	// arr	정렬 할 배열
	// low	정렬 할 배열 구간의 하한선(arr[low] 미포함)
	// high	정렬 할 배열 구간의 상한선(arr[high] 미포함)
	// start 정렬 할 배열의 원소 탐색 시작점
	private static void binaryInsertionSort(int[] arr, int low, int high, int start) {
		// start와 low가 같다면 이분 탐색 시작점은 low + 1 부터이므로 start를 1 증가시킴
		if(low == start) {
			start++;
		}
		
		// start 이전 원소들은 이미 오름차순으로 정렬 된 상태이므로 start부터 시작하여 이분 탐색 및 시프팅을 통해 삽입정렬을 해줌
		for(; start<high; start++) {
			// 타겟 넘버
			int target = arr[start];
			
			int loc = binarySearch(arr, target, low, start);
			
			int j= start -1;
			
			// 타겟이 들어갈 위치보다 큰 원소들을 뒤로 미룸
			while(j >= loc) {
				arr[j+1] = arr[j];	// 이전 원소를 한 칸씩 뒤로 미룸
				j--;
			}
			
			// 위 반복문에서 탈출 하는 경우 앞의 원소가 타겟보다 작다는 의미이므로 타겟 원소는 j번째 원소 뒤에 와야함
			// 그러므로 타겟은 j+1에 위치
			arr[loc] = target;
		}
	}
	
	// 이분 탐색
	private static int binarySearch(int[] arr, int key, int low, int high) {
		int mid;
		while(low < high) {
			mid = low + ((high - low) / 2);
			// 안정 정렬을 위해 key가 arr[mid]보다 작을 때만 상한선을 옮김
			if(key < arr[mid]) {
				high = mid;
			}else {
				low = mid + 1;
			}
		}
		
		return low;
	}
	
	// low부터 몇 개의 원소가 오름차순 혹은 내림차순인지를 반환하는 메소드
	private static int getAscending(int[] arr, int low, int high) {
		int limit = low + 1;
		if(limit == high) {
			// 정렬 할 원소가 arr[low] 1개 뿐이라는 의미
			return 1;
		}
		
		// 오름차순일 경우(안정 정렬을 위해 같은 경우까지 포함)
		if(arr[low] <= arr[limit]) {
			// 오름차순일때까지 반복(limit이 high 범위를 벗어나면 안됨)
			while(limit < high && arr[limit - 1] <= arr[limit]) {
				limit++;
			}
		}else {
			// 내림차순일 경우
			while(limit < high && arr[limit - 1] > arr[limit]) {
				limit++;
			}
			reversing(arr, low, limit);
		}
		
		return limit - low;
	}
	
	// 원소를 뒤집어줌
	private static void reversing(int[] arr, int low, int high) {
		// arr[low] <= arr[i] < arr[high] 범위이므로 마지막 인덱스는 high-1부터 시작
		high--;
		while(low < high) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
			low++;
			high--;
		}
	}
}
