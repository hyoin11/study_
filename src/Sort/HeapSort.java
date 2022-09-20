package Sort;

public class HeapSort {
	/*
	 * 힙정렬
	 * 힙은 최소값 또는 최대값을 빠르게 찾아내기 위해 완전이진트리 형태로 만들어진 자료구조
	 * (완전이진트리 -> 마지막 레벨을 제외한 모든 노드가 채워져있고, 모든 노드(사실상 마지막 레벨 노드)가 왼쪽부터 채워져있는)
	 */
	
	// 부모 인덱스를 얻는 함수
	private static int getParent(int child) {
		return (child-1) / 2;
	}
	
	public static void heapSort(int[] arr) {
		int size = arr.length;
		
		// 부모노드와 heapify과정에서 음수가 발생하면 잘못된 참조가 발생하기 때문에 원소가 1개이거나 0개일 경우는 정렬할 필요가 없으므로 바로 함수를 종료
		if(size < 2) return;
		
		// 가장 마지막 노드의 부모 노드 인덱스
		int parentIdx = getParent(size - 1);
		
		// maz heap 만들기
		for(int i=parentIdx; i>=0; i--) {
			// 부모노드(i)를 1씩 줄이면서 heap 조건을 만족시키도록 재구성
			heapify(arr, i, size-1);
		}
		
		// 정렬 과정
		for(int i=size-1; i>0; i--) {
			// root인 0번째 인덱스와 i번째 인덱스의 값을 교환해준 뒤
			// 0~(i-1)까지의 부분트리에 대해 max heap을 만족하도록 재구성
			swap(arr, 0, i);
			heapify(arr, 0, i-1);
		}
	}
	
	// 힙을 만드는 함수
	// heapify는 부모노드부터 자식노드로 진행되기 때문에 siftDown 과정이라고도 함
	private static void heapify(int[] arr, int parentIdx, int lastIdx) {
//		// Top-Down 방식
//		// 현재 트리에서 부모 노드의 자식노드 인덱스를 각각 구해줌
//		// 현재 부모 인덱스를 가장 큰 값을 갖고 있다고 가정
//		int leftChildIdx = 2 * parentIdx + 1;
//		int rightChildIdx = 2 * parentIdx + 2;
//		int largestIdx = parentIdx;
//		
//		// left child node와 비교
//		// 자식노드 인덱스가 끝의 원소 인덱스를 넘어가지 않으면서 현재 가장 큰 인덱스보다 왼쪽 자식노드의 값이 더 클 경우
//		// 가장 큰 인덱스를 가리키는 largestIdx를 왼쪽 자식 노드인덱스로 바꿔줌
//		if(leftChildIdx < lastIdx && arr[largestIdx] < arr[leftChildIdx]) {
//			largestIdx = leftChildIdx;
//		}
//		
//		// left child node와 비교
//		// 자식노드 인덱스가 끝의 원소 인덱스를 넘어가지 않으면서 현재 가장 큰 인덱스보다 오르쪽 자식노드의 값이 더 클 경우
//		// 가장 큰 인덱스를 가리키는 largestIdx를 오른쪽 자식 노드인덱스로 바꿔줌
//		if(rightChildIdx < lastIdx && arr[largestIdx] < arr[rightChildIdx]) {
//			largestIdx = rightChildIdx;
//		}
//		
//		// largestIdx와 부모노드가 같지 않다는 것은 위 자식노드 비교 과정에서 현재 부모노드보다 큰 노드가 존재한다는 뜻
//		// 그럴경우 해당 자식 노드와 부모노드를 교환, 교환 된 자식노드를 부모노드로 삼은 서브트리 검사를하도록 재귀 호출
//		if(parentIdx != largestIdx) {
//			swap(arr, largestIdx, parentIdx);
//			heapify(arr, largestIdx, lastIdx);
//		}
		
		// Bottom-Up 방식
		int leftChildIdx;
		int rightChildIdx;
		int largestIdx;
		
		// 현재 부모 인덱스의 자식 노드 인덱스가 마지막 인덱스를 넘지 않을 때 까지 반복
		// 이때 왼쪽 자식 노드를 기준
		// 오른쪽 자식 노드를 기준으로 범위를 검사하게 되면 마지막 부모 인덱스가 왼쪽 자식만 갖고 있을 경우 왼쪽 자식 노드와는 비교 및 교환을 할 수 없기 때문
		while((parentIdx * 2) + 1 <= lastIdx) {
			leftChildIdx = (parentIdx * 2) + 1;
			rightChildIdx = (parentIdx * 2) + 2;
			largestIdx = parentIdx;
			
			// left child node와 비교
			// 범위는 while문에서 검사했으므로 별도 검사 필요 없음
			if(arr[leftChildIdx] > arr[largestIdx]) {
				largestIdx = leftChildIdx;
			}
			
			// right child node와 비교
			// right child node는 범위를 검사해주어야함
			if(rightChildIdx <= lastIdx && arr[rightChildIdx] > arr[largestIdx]) {
				largestIdx = rightChildIdx;
			}
			
			// 교환이 발생했을 경우 두 원소를 교체 한 후 교환이 된 자식노드를 부모 노드가 되도록 교체
			if(largestIdx != parentIdx) {
				swap(arr, parentIdx ,largestIdx);
				parentIdx = largestIdx;
			}else {
				return;
			}
		}
	}
	
	// 두 인덱스의 원소를 교환하는 함수
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
