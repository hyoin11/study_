package Sort;

public class TimSort {
	/*
	 * 팀 정렬
	 * Merge Sort(합병 정렬), Insertion Sort(삽입 정렬)이 혼용 된 하이브리드 정렬
	 * 합병 정렬을 기반으로 구현하되, 일정 크기 이하의 부분 리스트에 대해서는 이진 삽입 정렬을 수행
	 */
	
	/*
	 * arrayLength / runSize 가 2의 제곱근에 가까울수록 좋음(병합정렬 특성)
	 * 즉, 나오는 수는(THRESHOLD / 2) <= runSize <= THRESHOLD 사이값이 됨
	 * 
	 * 이를 구하기 위해 runSize를 THRESHOLD(32) 보다 작을 때 까지 2씩 매 번 나눠가며 runSize로 나눴을 때 근접하도록 함
	 * 
	 * arrayLength / runSize 가 2의 제곱근에 가까울수록 좋기 때문에 runSize로 나눴을 때 2의 거듭제곱 값을 초과하지 않는게 좋음
	 */
	
	private static final int THRESHOLD = 32;
	
	// 최소 길이의 run 크기, 즉 minRun을 구하기 위한 메소드
	public static int minRunLength(int runSize) {
		/*
		 * 이 때 만약 연산 과정 중 홀수로 떨어지는 경우에는 runSize를 1증가시켜야함
		 * 즉, (runSize + 1)의 사이즈를 갖고 있어야 arrayLength / runSize 가 2^n을 초과하지 않음
		 */
//		int r = 0;	// 홀수가 발생할 때 2^x가 초과되지 않도록 하는 여분의 수
//		while(runSize >= THRESOLD) {
//			if(runSize % 2 == 1) {
//				r = 1;
//			}
//			runSize /= 2;
//		}
//		return runSize + r;
		
		// 비트 연산자 활용
		int r = 0;	// 홀수가 발생할 때 2^x가 초과되지 않도록 하는 여분의 수
		/*
		 * runSize & 1 을 하면, runSize의 가장 오른쪽 비트가 1일 경우에는 홀수이므로 1이 반환
		 * r = r | (runSize & 1) 에서 r은 1로 됨
		 * 한 번 r이 1로 되면 OR 연산자의 특성상 이 값은 바뀌지 않음
		 */
		while(runSize >= THRESHOLD) {
			r |= (runSize & 1);
			runSize >>= 1;
		}
		return runSize + r;
	}
	
	// run을 스택에 담아 둘 내부 정적 클래스
	private static class IntMergeStack{
		private int[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;	// run 스택의 원소 개수를 가리킬 변수
		
		public IntMergeStack(int[] arr) {
			this.array = arr;
			int lne = arr.length;
			
			runBase = new int[40];
			runLength = new int[40];
		}
		
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
		
		public void mergeForce() {
			// 나머지 모든 run을 병합
			while(stackSize > 1) {
				// 모든 run을 병합 할 것이기 때문에 2번 조건인 runLen[i-2] > runLen[i-1] 만 체크해주면서 병합
				if(stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				}else {
					merge(stackSize - 2);
				}
			}
		}
		
		public void merge() {
			/*
			 * 기본적인 메커니즘
			 * 1. runLen[i-3] > runLen[i-2] + runLen[i-1]
			 * 2. runLen[i-2] > runLen[i-1]
			 * 
			 * 스택에 요소가 5개 있을 때 기본 pivot은 상위 3개 요소 중 가운데를 지정
			 * ex) A, B, C, D, E (A = Bottom, E = Top)
			 * pivot = D (== stackSize -2)
			 * 
			 * runLen[pivot-1] = C, runLen[pivot] = D, runLen[pivot+1] = E 를 스택의 상위 세 요소로 함
			 * 메커니즘상 루프는 다음과 같은 경우에 기초함
			 * 
			 * 1. C <= D + E 및 C < E 일 경우, C 와 D 병합. 즉, pivot-1 과 pivot 병합
			 * 2. C <= D + E 및 C >= E 일 경우, D 와 E 병합. 즉, pivot 과 pivot+1 병합
			 * 3. C > D + E 및 D <= E 일 경우, D 와 E 병합. 즉, pivot 과 pivot+1 병합 
			 * 4. C > D + E 및 D > E 일 경우 루프 종료
			 * 
			 * while(stackSize > 1){
			 * 	// 1번 조건에 위배 될 경우
			 * 	if(stackSize > 2 && runLength[stackSize-3] <= runLength[stackSize-2] + runLength[stackSize-1]){
			 * 		// C < A 라면 B, C를 병합
			 * 		if(runLneght[stackSize-3] < runLength[stackSize-1]){
			 * 			merge(stackSize-3);
			 * 		}else{
			 * 			merge(stackSize-2);
			 * 		}
			 * 	}
			 *  // 2번 조건에 위배 될 경우
			 *  else if(runLength[stackSize-2] <= runLength[stackSize-1]){
			 *  	merge(stackSize-2); // A, B 병합
			 *  }
			 *  // 그 외 경우는 위 두 조건을 만족한다는 의미이므로 종료
			 *  else{
			 *  	break;
			 *  }
			 * }
			 * 
			 * 위 방식을 그대로 조건식으로 구현할 경우 stack 규칙이 깨짐
			 * 예를들어 120, 80, 25, 20이 스택이 있고 30이 스택에  추가되었다고 가정
			 * 즉, stack[] = {120, 80, 25, 20, 30}
			 * 
			 * 첫번째 반복문에서 첫번째 merge()시
			 * 첫번째 조건문의 runLen[pivot-1] <= runLen[pivot] + runLen[pivot+1] 을 만족시키며
			 * 하위 조건문 runLen[pivot-1] < runLen[pivot+1] 도 만족시키기 때문에 25와 20이 병합
			 * 
			 * 그러면 결과는 {120, 80, 45, 30}
			 * 
			 * 다음 반복문으로 넘어가게 되면 다음과 같음.
			 * 80 > 45 + 30 이기 때문에 첫 번째 조건문을 만족하지 못하며,
			 * 그 다음 조건문인 runLen[pivot] <= runLen[pivot+1] 또한 만족하지 못하므로
			 * stack의 유지의 두 조건을 모두 만족한다는 의미로 반복문이 종료됨
			 * 
			 * 하지만, 전체 남아있는 스택을 볼 때, 120 <= 80 + 45 를 만족하는게 있음에도 merge되지 않기에 stack의 규칙이 깨지게 됨
			 * 
			 * 즉, 상위 3개만 아니라 그 다음 아래의 3개의 요소에 대해서도 검사를 해야함 
			 */
			while(stackSize > 1) {
				// 1번 조건 C > B + A 에 위배 될 경우
				// 또는, D > C + B 에 위배 될 경우
				if(stackSize > 2 && runLength[stackSize-3] <= runLength[stackSize-2] + runLength[stackSize-1]
						|| stackSize > 3 && runLength[stackSize-4] <= runLength[stackSize-3] + runLength[stackSize-2]) {
					if(runLength[stackSize-3] < runLength[stackSize-1]) {
						merge(stackSize-3);
					}else {
						merge(stackSize-2);
					}
				}
				// 2번 조건 B > A 에 위배 될 경우
				else if(runLength[stackSize-2] <= runLength[stackSize-1]) {
					merge(stackSize-2);
				}
				// 그 외의 경우 위 두 조건을 만족한다는 의미이므로 종료
				else {
					break;
				}
			}
		}
			
		// idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
		private void merge(int idx) {
			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx+1];
			int length2 = runLength[idx+1];
			
			// idx와 idx+1 번째 run을 병합
			runLength[idx] = length1 + length2;
			
			/*
			 * 상위 3개 A, B, C에서 A, B가 병합 할 경우 C를 당겨옴
			 * 
			 * ex)
			 * stack[[A], [B], [C]]
			 * 
			 * runLen[idx] = length1 + length2
			 * stack[[A+B], [B], [C]]
			 * 
			 * C를 B위치로 당겨옴
			 * stack[[A+B], [C], [C]]
			 * 
			 * 이 때 마지막 [C] stack(i-1)은 더이상 참조될 일 없
			 */
			if(idx == (stackSize-3)) {
				runBase[idx+1] = runBase[idx+2];
				runLength[idx+1] = runLength[idx+2];
			}
			stackSize--;
			
			/*
			 
			  gallopRight ->								<- gallopLeft
			  				RUN A					   RUN B
			  ______________________________ ______________________________				
			  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
			  ------------------------------ ------------------------------
			  |___________| |______________| |___________________| |______|
			  less than MIN       RUN A'             RUN B'        greater than MAX
	  		  
                          |____________________________________|
						            merge RUN A' and RUN B'
	    	 */
			
			// start point (run B의 시작점보다 작으면서 run A 에서 merge를 시작할 위치)
			int low = gallopRight(array[start2], array, start1, length1);
			
			// 만약 run A의 길이와 merge를 시작할 지점이 같을 경우 이미 정렬되어있는 상태로 정렬할 필요없음
			if(length1 == low) return;
			
			start1 += low;
			length1 -= low;
			
			// end point (run A의 끝점보다 크면서 run B에서 merge가 끝나는 위치)
			int high = gallopLeft(array[start1+length1-1], array, start2, length2);
			
			if(high == 0) return;
			
			length2 = high;
			if(length1 <= length2) {
				mergeLow(start1, length1, start2, length2);
			}else {
				mergeHigh(start1, length1, start2, length2);
			}
		}
		
		// gallopRight 함수를 수행하여 run B의 첫번째 원소보다 큰 원소들이 첫번째 출현하는 위치를 run A에서 찾음
		// @param key		run B의 key
		// @param array		배
		// @param base		run A의 시작지점 
		// @param lenOfA	run B의 길
		// @param return	제외되어야 할 부분의 위치 다음 인덱스를 반환
		private int gallopRight(int key, int[] array, int base, int lenOfA) {
			int low = 0;	// 이전 탐색(gallop) 지점
			int high = 1;	// 현재 탐색(gallop) 지점
			
			// run B의 시작지점 값(key)이 run A의 시작지점 값보다 작을 경우 제외 될 원소는 없으므로 0리턴
			if(key < array[base]) {
				return 0;
			}else {
				/*
	    		
				  gallopRight ->								
				  				RUN A		      key	   RUN B
				  ______________________________ ______________________________				
				  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
				  ------------------------------ ------------------------------
				  |___________| |______________| |___________________| |______|
				  less than MIN       RUN A'             RUN B'        greater than MAX
		  		  
		  		                |____________________________________|
	 									merge RUN A' and RUN B'
		    	 */
				
				int maxLen = lenOfA;	// galloping을 하여 가질 수 있는 최대 한계값
				while(high < maxLen && array[base+high] <= key) {
					low = high;
					high = (high << 1) + 1;
					
					if(high <= 0) {
						// overflow 발생 시 run A의 끝점으로 초기화
						high = maxLen;
					}
				}
				
				if(high > maxLen) {
					high = maxLen;
				}
			}
			low++;
			
			// binary search (Upper bound)
			while(low < high) {
				int mid = low + ((high-low) >>> 1);
				
				if(key < array[base+mid]) {
					high = mid;
				}else {
					low = mid + 1;
				}
			}
			
			return high;
		}
		
		/*
		 * gallopLeft 함수를 수행하여 run A의 첫번째 원소(오른쪽 끝) 보다 큰 원소들이 첫번째로 출현하는 위치를 run B 에서 찾음
		 * 
		 * @param key		run A의 key
		 * @param array		array 배열
		 * @param base		run B의 시작 지점
		 * @param lenOfB	run B의 길이
		 * @return 			제외되어야 할 부분의 위치 다음 인덱스를 반
		 */
		private int gallopLeft(int key, int[] array, int base, int lenOfB) {
			int low = 0;
			int high = 1;
			
			// key가 B의 탐색의 첫 위치(오른쪽 끝)보다 크면 제외되어야 할 부분은 없으므로 run b의 길이를 반환
			if(key > array[base+lenOfB-1]) {
				return lenOfB;
			}else {
				/**
								
							                					<- gallopLeft
							RUN A					   RUN B
				______________________________ ______________________________				
				[   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
				￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
				|___________| |______________| |___________________| |______|
				less than MIN       RUN A'             RUN B'        greater than MAX
				
				|____________________________________|
				merge RUN A' and RUN B'
				*/
				
				// run B의 오른쪽부터 시작해야하므로 뒤쪽 시작점을 가리키는 변수
				int startPointOfRun = base + lenOfB - 1;
				
				int maxLen = lenOfB; // galloping을 하여 가질 수 있는 최대 한계값
				
				while(high < maxLen && key <= array[startPointOfRun - high]) {
					low = high;
					high = (high << 1) + 1;
					
					// overflow가 발생 시 run B의 끝점(왼쪽 끝)으로 초기화
					if(high <= 0) {
						high = maxLen;
					}
				}
				
				if(high > maxLen) {
					high = maxLen;
				}
				
				// 뒤에서부터 탐색했기 때문에 실제 가리키는 인덱스는 low > high 이므로 이분 탐색을 하기 위해 run B 에 대해 가리키는 지점을 서로 바꿔줌
				int temp = low;
				low = lenOfB - 1 - high;
				high = lenOfB - 1 - temp;
			}
			
			low++;
			
			// binary search (lower bound)
			while(low < high) {
				int mid = low + ((high - low) >>> 1);
				
				if(key <= array[base + mid]) {
					high = mid;
				}else {
					low = mid + 1;
				}
			}
			
			return high;
		}
		
		/*
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여 실제 배열 원소를 병합하는 메소드
		 * 
		 * @param start1	run A에서의 병합 시작 지점
		 * @param length1	run A에서의 병합해야 할 길이(개수)
		 * @param start2	run B에서의 병합 시작 지점
		 * @param length2	run B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLow(int start1, int length1, int start2, int length2) {
			// run A를 담을 임시 복사 배열
			int[] temp = new int[length1];
			System.arraycopy(array, start1, temp, 0, length1);	// run a를 temp 배열에 복사
			
			int insertIdx = start1;	// 재배치 되는 위치
			int runBIdx = start2;	// run B의 탐색 위치
			int tempIdx = 0;		// 복사한 run A의 탐색 위치
			
			int leftRemain = length1;	// 배치해야 할 run A의 원소 개수
			int rightRemain = length2;	// 	배치해야 할 run B의 원소 개수
			
			// 두 원소 중 먼저 소진 될 때 까지 반복
			while(leftRemain != 0 && rightRemain != 0) {
				// run B < run A 라면 run B 원소 삽입
				if(array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				}else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}
			
			// 왼쪽 부분 리스트가 남아있을 경우
			if(leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			}else {
				// 오른쪽 부분 리스트가 남아있을 경우
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}
		}
		
		/*
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여 실제 배열 원소를 병합하는 메소드
		 * 
		 * @param start1	run A에서의 병합 시작 지점
		 * @param length1	run A에서의 병합해야 할 길이(개수)
		 * @param start2	run B에서의 병합 시작 지점
		 * @param length2	run B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHigh(int start1, int length1, int start2, int length2) {
			// run B를 담을 임시 복사 배열
			int[] temp = new int[length2];
			System.arraycopy(array, start2, temp, 0, length2);
			
			int insertIdx = start2 + length2 - 1;	// 재배치되는 위치
			int runAIdx = start1 + length1 - 1;		// run A의 탐색 위치
			int tempIdx = length2 - 1;				// 복사한 run B의 탐색 위치
			
			int leftRemain = length1;	// 배치해야 할 run A의 원소 개수
			int rightRemain = length2;	// 배치해야 할 run B의 원소 개수
			
			while(leftRemain != 0 && rightRemain != 0) {
				// run A > run B 라면 run A 원소 삽입(내림차순이기 때문)
				if(array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				}else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}
			
			// 오른쪽 부분 리스트가 남아있을 경우
			if(rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			}else {
				// 왼쪽 부분 리스트가 남아있을 경우
				System.arraycopy(array, start1, array, start1, leftRemain);
			}
		}
	} // IntMergeStack class
	
	public static void sort(int[] arr) {
		sort(arr, 0, arr.length);
	}
	
	public static void sort(int[] arr, int low, int high) {
		int remain = high - low;
		
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요 없음
		if(remain < 2) return;
		
		// 일정 크기 이하의 배열이라면 binaryInsertionSort로 정렬 뒤 바로 반환
		// @see BinaryInsertionSort.BinaryInsertionSort
		if(remain < THRESHOLD) {
			int increseRange = getAscending(arr, low, high);
			BinarySort(arr, low, high, low + increseRange);
			return;
		}
		
		IntMergeStack ims = new IntMergeStack(arr);
		int minRun = minRunLength(remain); 	// run의 최소 길이
		do {
			// 정렬 된 구간의 길이를 구함
			int runLen = getAscending(arr, low, high);
			
			// 만약 정렬 된 부분의 길이가 minRun 보다 작다면 정렬 된 부분 길이를 포함한 부분 배열에 대해 이진 삽입 정렬을 시행
			if(runLen < minRun) {
				/*
				 * [low : low + minRun] 구간에 대해 정렬
				 * 
				 * counts : run에 있는 요소의 총 개수
				 * 이때 최소 run 크기가 남은 원소 개수보다 클 수 있으므로 이를 처리해줌
				 */
				int counts = remain < minRun ? remain : minRun;
				
				/*
				 * binarySort(array, low, high, start);
				 * index[low] ~ index[low + counts] 구간을 삽입 정렬을 하되,
				 * index[low + runLen] 부터 삽입정렬을 시작함
				 * (앞서 구한 오름차순 길이인 runLen에 의해 low + runLen의 이전 인덱스는 이미 오름차순 상태임)
				 */
				BinarySort(arr, low, low + counts, low + runLen);
				
				// 이진 삽입 정렬이 수행되었기에 증가하는 길이는 endPoint가 됨
				runLen = counts;
			}
			
			// stack에 run의 시작점과 해당 run의 길이를 스택에 push함
			ims.pushRun(low, runLen);
			ims.merge();
			
			low += runLen;
			remain -= runLen;
		}while(remain != 0);	// 정렬 해야 할 원소가 0개가 될 때까지 반복
		ims.mergeForce();	// 마지막으로 스택에 있던 run을 모두 병
	}
	
	private static void BinarySort(int[] arr, int low, int high, int start) {
		if(low == start) {
			start++;
		}
		
		for(; start<high; start++) {
			int target = arr[start];
			
			int loc = binarySearch(arr, target, low, start);
			
			int j = start - 1;
			
			while(j >= loc) {
				arr[j + 1] = arr[j];
				j--;
			}
			
			arr[loc] = target;
		}
	}
	
	private static int binarySearch(int[] arr, int key, int low, int high) {
		int mid;
		while(low < high) {
			mid = (low + high) >>> 1;
			if(key < arr[mid]) {
				high = mid;
			}else {
				low = mid + 1;
			}
		}
		return low;
	}
	
	private static int getAscending(int[] arr, int low, int high) {
		int limit = low + 1;
		if(limit == high) {
			return 1;
		}
		
		if(arr[low] <= arr[limit]) {
			while(limit < high && arr[limit-1] <= arr[limit]) {
				limit++;
			}
		}else {
			while(limit < high && arr[limit-1] > arr[limit]) {
				limit++;
			}
			reversing(arr, low, limit);
		}
		
		return limit - low;
	}
	
	private static void reversing(int[] arr, int low, int high) {
		high--;
		while(low < high) {
			int temp = arr[low];
			arr[low++] = arr[high];
			arr[high--] = temp;
		}
	}
}
