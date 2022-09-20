package Sort;

public class ShellSort {
	/*
	 * 셀 정렬
	 * 삽입 정렬을 기반으로 삽입정렬의 장점은 살리면서 단점은 줄인 방식
	 * 비교하면서 찾고, 공간을 필요로 하지 않기 때문에 제자리 정렬임
	 * 다만, 삽입정렬과 다르게 일정 간격을 주기로 하여 비교 및 교환이 일어나기 때문에 구조상 안정정렬은 아님
	 * 
	 * 1. 간격(gap)을 설정
	 * 2. 각 간격별로 분류 된 서브(부분) 리스트에 대해 삽입정렬을 함
	 * 3. 각 서브(부분) 리스트의 정렬이 끝나면 간격을 줄임
	 * 3. 간격이 1이 될 때 까지 2번 과정으로 되돌아가며 반복
	 * 
	 * 간격은 너무 적으면 건너 뛰는 간격이 적어 속도가 느려지고, 반대로 간격이 너무 많으면 오버헤드가 발생함
	 * 그래서 많은 사람들이 각 간격에 따라 평균적으로 좋은 간격들을 내놓았는데, 이를 갭 시퀸스(Gap Sequences)라고 함
	 * 그 중 가장 좋은 퍼포먽스를 보인 Ciura 시퀸스는 {1, 4, 10, 23, 57, 132, 301, 701, 1750}
	 */
	
	private final static int[] gap = {1, 4, 10, 23, 57, 132, 301, 701, 1750};
	
	public static void shellSort(int[] arr) {
		shellSort(arr, arr.length);
	}
	
	// 맨 처음 gap을 참조 할 인덱스를 구하는 메소드
	private static int getGap(int length) {
		int index = 0;
		// 최소한 부분 배열의 원소가 2개씩은 비교 되도록 나눠줌
		int len = (int)(length / 2.25);
		while(gap[index] < len) {
			index++;
		}
		return index;
	}
	
	private static void shellSort(int[] arr, int size) {
		int index = getGap(size);	// 첫 gap을 사용할 index
		
		// gap[index] 값부터 gap[0] 까지 반복
		for(int i=index; i>=0; i--) {
			for(int j=0; j<gap[i]; j++) {
				// 각 부분 리스트에 대해 삽입정렬을 함
				insertionSort(arr, j, size, gap[i]);
			}
		}
	}
	
	private static void insertionSort(int[] arr, int start, int size, int gap) {
		// 부분 배열의 두번째 원소부터 size까지 반복 (gap 값씩 건너뜀)
		for(int i=start+gap; i<size; i+=gap) {
			int target = arr[i];
			int j= i - gap;
			
			// 타겟 원소가 이전의 원소보다 작을 때 까지 반복
			while(j >= start && target < arr[j]) {
				arr[j+gap] = arr[j];	// 이전 원소를 한칸씩 뒤로 미룸
				j -= gap;
			}
			
			// 위 반복문에서 탈출 하는 경우 앞의 원소가 타겟보다 작다는 의미이므로 타겟 원소는 j번째 소 뒤에 와야함
			// 그러므로 타겟은 j+gap에 위치
			arr[j+gap] = target;
		}
	}
	
	private static void shellSort2(int[] arr, int size) {
		int gapIndex = getGap(size);
		
		// 갭이 1이 될때까지 반복
		while(gapIndex >= 0) {
			int step = gap[gapIndex--]; // 현재 갭(step)
			
			// 삽입 정렬 과정
			// 각 부분리스트의 두번째 원소의 인덱스부터 순회
			// 예로 step이 3일때 0,1,2는 이전 원소와 비교할 것이 없음
			// 그러므로 step부터 순회
			for(int i=step; i<size; i++) {
				// j는 target 원소가 되며 현재 원소(target) arr[j]가 이전 원소 arr[j-step]보다 작을때 까지 반복
				for(int j=i; j>=step && arr[j]<arr[j-step]; j-=step) {
					// 현재(target) 원소의 인덱스(j)와 이전의 원소(j-step)의 인덱스에 있는 원소의 값을 교환
					swap(arr, j, j-step);
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
