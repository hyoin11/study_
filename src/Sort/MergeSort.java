package Sort;

public class MergeSort {
	/*
	 * 합병정렬 / 병합정렬
	 * 문제를 분할하고, 분할한 문제를 정복하여 합치는 과정
	 * 안정정렬(stable sort) 알고리즘
	 * 
	 * 1. 주어진 리스트를 절반으로 분할하여 부분리스트로 나눈다(divide:분할)
	 * 2. 해당 부분리스트의 길이가 1이 아니라면 1번 과정을 되풀이한다
	 * 3. 인접한 부분리스트끼리 정렬하여 합친다(conqure:정복)
	 */
	
	private static int[] sorted;	// 합치는 과정에서 정렬하여 원소를 담을 임시배열
	
	public static void mergeSort(int[] arr) {
		sorted = new int[arr.length];
		mergeSort(arr, 0, arr.length-1);
		sorted = null;
	}
	
	// Top-Down 방식
//	private static void mergeSort(int[] arr, int left, int right) {
//		// left == right 즉, 부분리스트가 1개의 원소만 갖고있는 경우 더 이상 쪼갤 수 없으므로 return
//		if(left == right) return;
//		
//		int mid = (left + right) / 2;
//		
//		mergeSort(arr, left, mid);	// 절반 중 왼쪽 부분리스트
//		mergeSort(arr, mid+1, right);	// 절반 중 오른쪽 부분리스트
//		
//		merge(arr, left, mid, right);	// 	병합작업
//	}
	
	// Bottom-Up 방식
	private static void mergeSort(int[] arr, int left, int right) {
		// 1 - 2 - 4 - 8 ... 식으로 1부터 나누는 기준을 두배씩 늘림
		for(int size=1; size<=right; size+=size) {
			// 순서대로 병합
			
			for(int l=0; l<=right-size; l+=2*size) {
				int low = l;
				int mid = l+size-1;
				int high = Math.min(l+(2*size)-1, right);
				System.out.println("low " + low + " mid " + mid + " high " + high);
				merge(arr, low, mid, high);
			}
		}
	}
	
	/*
	 * 합칠 부분리스트는 arr배열의 left~right까지
	 * 
	 * arr		정렬할 배열
	 * left		배열의 시작점
	 * mid		배열의 중간점
	 * right	배열의 끝	
	 */
	
	private static void merge(int[] arr, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;
//		System.out.println(Arrays.toString(sorted));
//		System.out.println("arr" + Arrays.toString(arr));
		
		while(l <= mid && r <= right) {
			// 왼쪽 l번째 원소가 오른쪽 r번째 원소보다 작거나 같을 경우
			// 왼쪽의 l번째 원소를 새 배열에 넣고 l과 idx를 1 증가
			if(arr[l] <= arr[r]) {
				sorted[idx] = arr[l];
				idx++;
				l++;
			}
			// 오른쪽 r번째 원소가 왼쪽 l번째 원소보다 작거나 같을 경우
			// 오른쪽 r번째 원소를 새 배열에 넣고 r과 idx를 1 증가
			else {
				sorted[idx] = arr[r];
				idx++;
				r++;
			}
		}
			
		// 왼쪽 먼저 모두 채워졌을 경우 = 오른쪽 원소가 아직 남아있을 경우
		// 오른쪽 나머지 원소들을 새 배열에 채워줌
		if(l > mid) {
			while(r <= right) {
				sorted[idx] = arr[r];
				idx++;
				r++;
			}
		}
		// 오른쪽 먼저 모두 채워졌을 경우 = 왼쪽 원소가 아직 남아있을 경우
		// 왼쪽 나머지 원소들을 새 배열에 채워줌
		else {
			while(l <= mid) {
				sorted[idx] = arr[l];
				idx++;
				l++;
			}
		}
		
		// 정렬된 새 배열을 기존의 배열에 복사해 옮겨줌
		for(int i=left; i<=right; i++) {
			arr[i] = sorted[i];
		}
	}
}
