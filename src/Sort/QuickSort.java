package Sort;

public class QuickSort {
	/*
	 * 퀵 정렬
	 * 피벗(pivot)을 기준으로 나누어 하나는 피벗보다 작은 값들의 부분 리스트, 다른 하나는 피벗보다 큰 값들의 부분리스트로 정렬한 다음
	 * 각 부분리스트에 대해 다시 위 처럼 재귀적으로 수행하여 정렬
	 * 제자리 정렬(in-place-sort), 불안정정렬(unstable sort)
	 * 
	 * 1. 피벗을 하나 선택
	 * 2. 피벗을 기준으로 양쪽으로 피벗보다 큰 값, 혹은 작은 값을 찾음(왼쪽에서는 피벗보다 큰 값을 찾고, 오른쪽에서는 피벗보다 작은 값을 찾음)
	 * 3. 양 방향에서 찾은 두 원소를 교환
	 * 4. 왼쪽에서 탐색하는 위치와 오른쪽에서 탐색하는 위치가 엇갈리지 않을 때 까지 2번으로 돌아가 위 과정을 반복
	 * 5. 엇갈린 기점을 기준으로 두 개의 부분리스트로 나누어 1번으로 돌아가 해당 부분리스트의 길이가 1이 아닐때 까지 1번과정을 반복(divide:분할)
	 * 6. 인접한 부분리스트끼리 합침(conqure:정복)
	 */
	
	public static void quickSort(int[] arr) {
//		leftPivotSort(arr, 0, arr.length-1);
//		rightPivotSort(arr, 0, arr.length-1);
		middlePivotSort(arr, 0, arr.length-1);
	}
	
	// 왼쪽 피벗 선택 방식
	private static void leftPivotSort(int[] arr, int low, int high) {
		// low가 high보다 크거나 같다면 정렬 할 원소가 1개 이하이므로 정렬하지 않고 return
		if(low >= high) return;
		
		// 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로 만들어 준 뒤, 최종적으로 pivot의 위치를 얻음
		// 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나눠 분할 정복을 해줌
		
		int pivot_index = leftPivotPartition(arr, low, high);
		
		leftPivotSort(arr, low, pivot_index-1);
		leftPivotSort(arr, pivot_index+1, high);
	}
	
	// pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
	private static int leftPivotPartition(int[] arr, int left, int right) {
		int low = left;
		int high = right;
		int pivot = arr[left];
		
		while(low < high) {
			// high가 low보다 크면서, high의 요소가 pivot보다 작거나 같은 원소를 찾을 때 까지 high를 감소
			while(pivot < arr[high] && low < high) {
				high--;
			}
			
			// high가 low보다 크면서, low의 요소가 pivot보다 큰 원소를 찾을 때 가지 low를 증가
			while(arr[low] <= pivot && low < high) {
				low++;
			}
			
			// 교환 할 두 요소를 찾으면 두 요소를 바꿈
			swap(arr, low ,high);
		}
		
		// 마지막으로 맨 처음 pivot으로 설정했던 위치의 원소와 low가 가리키는 원소를 바꿈
		swap(arr, left, low);
		
		// 두 요소가 교환되었다면 피벗이였던 요소는 low에 위치하므로 low를 반환
		return low;
	}
	
	// 오른쪽 피벗 선택 방식
	private static void rightPivotSort(int[] arr, int low, int high) {
		// low가 high보다 크거나 같다면 정렬 할 원소가 1개 이하이므로 정렬하지 않고 return
		if(low >= high) return;
		
		// 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로 만들어준 뒤, 최종적으로 pivot의 위치를 얻음
		// 그리고 나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어 분할 정복을 해줌
		int pivot_index = rightPivotPartition(arr, low, high);
		
		rightPivotSort(arr, low, pivot_index-1);
		rightPivotSort(arr, pivot_index+1, high);
	}
	
	// pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
	private static int rightPivotPartition(int[] arr, int left, int right) {
		int low = left;
		int high = right;
		int pivot = arr[right];
		
		// low가 high보다 작을 때까지만 반복
		while(low < high) {
			// high가 low보다 크면서, low의 요소가 pivot보다 큰 원소를 찾을 때 까지
			while(arr[low] < pivot && low < high) {
				low++;
			}
			
			// high가 low보다 크면서, high의 요소가 pivot보다 작거나 같은 원소를 찾을 때 까지
			while(pivot <= arr[high] && low < high) {
				high--;
			}
			
			// 교환 될 두 요소를 찾으면 두 요소를 바꿈
			swap(arr, low, high);
		}
		
		// 마지막으롤 맨 처음 pivot으로 설정했던 위치의 원소와 high가 가르키는 원소를 바꿈
		swap(arr, right, high);
		
		return high;
	}
	
	// 중간 피벗 선택 방식
	private static void middlePivotSort(int[] arr, int low, int high) {
		// low가 high보다 크거나 같다면 정렬 할 원소가 1개 이하이므로 정렬하지 않고 return
		if(low >= high) return;
		
		// 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로 만들어 준 뒤, 최종적으로 피봇의 위치를 얻음
		int pivot_index = middlePivotPartition(arr, low, high);
		
		middlePivotSort(arr, low, pivot_index);
		middlePivotSort(arr, pivot_index+1, high);
	}
	
	// pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
	private static int middlePivotPartition(int[] arr, int left, int right) {
		// low와 high는 각각 배열의 끝에서 1 벗어난 위치부터 시작
		int low = left - 1;
		int high = right + 1;
		int pivot = arr[(left + right) / 2];
		
		while(true) {
			// 1 증가시키고 난 뒤의 low 위치의 요소가 pivot보다 큰 요소를 찾을 때 까지
			do {
				low++;
			}while(arr[low] < pivot);
			
			// 1 감소시키고 난 뒤의 high 위치가 low 보다 크거나 같은 위치이면서 high 위치의 요소가 pivot보다 작은 요소를 찾을 때 까지
			do {
				high--;
			}while(pivot < arr[high] && low <= high);
			
			// 만약 high가 low보다 크지 않으면 swap하지 않고 high를 리턴
			if(low >= high) return high;
			
			// 교환 될 두 요소를 찾았으면 두 요소를 바꿈
			swap(arr, low, high);
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
