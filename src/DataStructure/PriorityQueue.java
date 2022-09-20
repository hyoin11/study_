package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements Queue<E>, Cloneable {
	/*
	 * 우선순위 큐(Priority Queue)
	 * 대체적으로 힙 자료구조를 기반으로 구현됨
	 * 우선순위 큐는 각 요소들이 각각의 우선 순위를 갖고 있고, 요소들의 대기열에서 우선순위가 높은 요소가 우선순위가 낮은 요소 보다 먼저 제공되는 자료구조
	 * 힙과 유사한 구조를 갖고 있으나 개념 자체는 다름
	 * 힙은 기본적으로 중점이 되는 것이 최솟값 또는 최댓값을 빠르게 찾기인 반면에, 우선순위 큐는 우선순위가 높은 순서대로 요소를 제공받는다
	 * 우선순위 큐를 구현하는데 있어 가장 대표적인 구현 방식이 힙 자료구조를 활용하는 방식
	 * 힙 자료구조를 이용한 우선순위 큐의 구현
	 * 
	 * 힙을 사용한 우선순위 큐는 어떻게 구현되나
	 * 힙에서는 최대 혹은 최소값을 빠르게 찾기 위함을 우선순위가 높은 요소를 빠르게  찾기 위한다고 바꾸어 생각하면 좀 더 편함
	 * 모든 요소들을 고려하여 우선순위를 정할 필요 없이 부모 노드는 자식노드보다 항상 우선순위가 앞선다는 조건만 만족시키며 완전 이진트리 형태로 채워나가는 것
	 * 루트 노드는 항상 우선순위가 높은 노드
	 * 형제간의 우선순위는 고려되지 않음
	 * 이러한 정렬 상태를 흔히 반 정렬 상태 혹은 느슨한 정렬 상태, 약한 힙(weak haep)이라고도 불림
	 * 형제간의 대소비교가 필요없는 이유는 우선순위가 높은 순서대로 뽑는 것이 포인트임
	 * 즉, 원소를 넣을 때도 우선순위가 높은 순서대로 나올 수 있도록 유지되야하고 뽑을 때 또한 우선순위가 높은 순서 차례대로 나오기만 하면 됨
	 * 
	 * 트리 구조를 배열로 구현하게 될 경우 특징
	 * 	1. 구현의 용이함을 위해 시작 인덱스(root)는 1부터 시작
	 * 	2. 각 노드와 대응되는 배열의 인덱스는 불변함
	 * 성질
	 * 	1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 * 2
	 * 	2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 * 2 + 1
	 * 	3. 부모 노드 인덱스 = 자식 노드 인덱스 / 2
	 * 
	 * 최소 힙을 기준
	 */
	
	private final Comparator<? super E> comparator;	// 객체를 정렬하고자 할 때, 혹은 임의의 순서로 정렬하고 싶을 때 Comparator를 파라미터로 받아 설정할 수 있도록 한 변수. 즉, 우선순위를 결정해주는 변수라고 보면됨
	private static final int DEFAULT_CAPACITY = 10;	// 최소(기본) 용적 크기
	
	private int size;	// 요소 개수
	private Object[] array;	// 요소를 담을 배열
	
	// 생성자 Type 1 (초기 공간 할당 X)
	public PriorityQueue() {
		this(null);
	}
	
	public PriorityQueue(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
	
	// 생성자 Type 2 (초기 공간 할당 O)
	public PriorityQueue(int capacity) {
		this(capacity, null);
	}
	
	public PriorityQueue(int capacity, Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator = comparator;
	}
	
	// 받은 인덱스의 부모 노드 인덱스를 반환
	private int getParent(int index) {
		return index / 2;
	}
	
	// 받은 인덱스의 왼쪽 자식 노드 인덱스를 반환
	private int getLeftChild(int index) {
		return index * 2;
	}
	
	// 받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
	private int getRightChild(int index) {
		return index * 2 + 1;
	}
	
	/*
	 * resize()
	 * 배열의 크기를 재조정하기 위해 쓰는 메소드
	 * 
	 * @param newCapacity	새로운 용적 크기
	 */
	private void resize(int newCapacity) {
		// 새로 만들 배열
		Object[] newArray = new Object[newCapacity];
		
		// 새 배열에 기존에 있던 배열의 요소들을 모두 복사
		for(int i=1; i<=size; i++) {
			newArray[i] = array[i];
		}
		
		/*
		 * 현재 배열은 GC처리를 위해 null로 명확하게 처리한 뒤 새 배열을 array에 연결
		 */
		this.array = null;
		this.array = newArray;
	}
	
	/*
	 * offer()
	 * 데이터를 추가
	 * 큐의 삽입은 두가지로 나뉨
	 * - 사용자가 Comparator를 사용하여 정렬 방법을 PriorityQueue 생성단계에서 넘겨 받은 경우(comparator가 null이 아닌 경우)
	 * - 클래스 내에 정렬 방식을 Comparable로 구현했거나 기본 정렬 방식을 따르는 경우(comparator가 null인 경우)
	 * 
	 * 배열의 마지막 부분에 원소를 넣고 부모노드를 찾아가면서 부모노드가 삽입노드보다 작을 때까지 요소를 교환해가면서 올라감
	 * sift-up(상향 선별)
	 * 값을 추가할 때는 size + 1 위치에 새로운 값을 추가하고 상향 선별 과정을 거쳐 재배치를 해줌
	 */
	@Override
	public boolean offer(E value) {
		// 배열 용적이 꽉 차있을 경우 용적을 두배로 늘려줌
		if(size + 1 == array.length) {
			resize(array.length * 2);
		}
		
		siftUp(size + 1, value);	// 가장 마지막에 추가되는 위치와 넣을 값(타겟)을 넘겨줌
		size++;	// 정상적으로 재배치가 끝나면 사이즈(요소 개수)증가
		return true;
	}
	
	/*
	 * 상향 선별
	 * 
	 * @param idx	추가할 노드의 인덱스
	 * @param target	재배치 할 노드
	 */
	private void siftUp(int idx, E target) {
		// comparator가 존재한다면 comparator를 넘겨주고 아닐경우 Comparable로 비교하도록 분리
		if(comparator != null) {
			siftUpComparator(idx, target, comparator);
		}else {
			siftUpComparable(idx, target);
		}
	}
	
	// Comparator를 이용한 sift-up
	@SuppressWarnings("unchecked")
	private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
		// root보다 클 때 까지만 탐색
		while(idx > 1) {
			int parent = getParent(idx);	// 삽입 노드의 부모 노드 인덱스 구하기
			Object parentVal = array[parent];	// 부모 노드의 값
			
			// 타겟 노드 우선순위(값)이 부모노드보다 크면 반복문 종료
			if(comp.compare(target, (E) parentVal) >= 0) break;
			
			// 부모노드가 타겟노드보다 우선순위가 크므로 현재 삽입될 위치에 부모노드 값으로 교체해주고 타겟노드의 위치를 부모노드의 위치로 변경
			array[idx] = parentVal;
			idx = parent;
		}
		
		// 최종적으로 삽입 될 위치에 타겟 노드 요소를 저장
		array[idx] = target;
	}
	
	// 삽입 할 객체의 Comparable을 이용한 sift-up
	@SuppressWarnings("unchecked")
	private void siftUpComparable(int idx, E target) {
		// 타겟 노드가 비교될 수 있도록 한 변수를 만듬
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		// 노드 재배치 과정은 siftUpComparator와 같음
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
			
			if(comp.compareTo((E) parentVal) >= 0) break;
			
			array[idx] = parentVal;
			idx = parent;
		}
		
		array[idx] = comp;
	}
	
	/*
	 * poll()
	 * 루트에 있는 노드를 삭제하고, 마지막에 위치해있던 노드를 루트노드로 가져와 자식노드가 배재치하려는 노드보다 크거나 자식노드가 없을 때 까지 자신의 위치를 찾아가면 됨
	 * 중요한 점은 왼쪽 자식 노드와 오른쪽 자식 노드 중 작은 값을 가진 노드랑 재배치 할 노드와 비교해야함
	 * 아래로 내려가면서 재배치 하는 과정을 sift-down(하향 선별)이라고 함
	 */
	@Override
	public E poll() {
		// poll은 뽑으려는 요소(루트)가 null일 경우 null을 반환
		if(array[1] == null) return null;
		
		// 그 외의 경우 remove()에서 반환되는 요소를 반환
		return remove();
	}
	
	@SuppressWarnings("unchecked")
	public E remove() {
		if(array[1] == null) {
			// 뽑으려는 요소(루트)가 null일 경우 예외를 던지도록
			throw new NoSuchElementException();
		}
		
		E result = (E) array[1];	// 삭제된 요소를 반환하기 위한 임시 변수
		E target = (E) array[size];	// 타겟이 될 요소
		
		array[size] = null;	// 타겟 노드(index)를 비움
		size--;
		siftDown(1, target);
		
		return result;
	}
	
	/*
	 * 하향 선별
	 * 
	 * @param idx	삭제할 노드의 인덱스
	 * @param target	재배치 할 노드
	 */
	private void siftDown(int idx, E target) {
		if(comparator != null) {
			siftDownComparator(idx, target, comparator);
		}else {
			siftDownComparable(idx, target);
		}
	}
	
	// Comparator를 이용한 sift-down
	@SuppressWarnings("unchecked")
	private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
		array[idx] = null;	// 삭제 할 인덱스의 노드를 삭제
		
		int parent = idx;	// 삭제 노드부터 시작 할 부모 노드 인덱스를 가리키는 변수
		int child;	// 교환 될 자식 인덱스를 가리키는 변수
		
		// 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 때 까지 반복
		while((child = getLeftChild(parent)) <= size) {
			int right = getRightChild(parent);	// 오른쪽 자식 인덱스
			Object childVal = array[child];	// 왼쪽 자식의 값(교환될 요소)
			
			/*
			 * 오른쪽 자식 인덱스가 사이즈를 넘지 않으면서 왼쪽 자식이 오른쪽 자식보다 큰 경우
			 * 재배치 할 노드는 작은 자식과 비교해야 하므로 child와 childVal을 오른쪽 자식으로 바꿔줌
			 */
			if(right <= size && comp.compare((E) childVal, (E) array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
			
			// 재배치 할 노드가 자식 노드보다 작을 경우 반복문을 종료
			if(comp.compare(target, (E) childVal) <= 0) break;
			
			// 현재 부모 인덱스에 자식 노드 값을 대체해주고 부모 인덱스를 자식 인덱스로 교체
			array[parent] = child;
			parent = child;
		}
		
		// 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어줌
		array[parent] = target;
		
		/*
		 * 용적 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4 미만일 경우 용적을 반으로 줄임
		 * 단, 최소 용적보단 커야함
		 */
		if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void siftDownComparable(int idx, E target) {
		Comparable<? super E> comp = (Comparable<? super E>) target;
		
		array[idx] = null;
		
		int parent = idx;
		int child;
		
		while((child = (parent << 1)) <= size) {
			int right = child + 1;
			
			Object c = array[child];
			
			if(right <= size && ((Comparable<? super E>) c).compareTo((E) array[right]) > 0){
				child = right;
				c = array[child];
			}
			
			if(comp.compareTo((E) c) <= 0) break;
			
			array[parent] = c;
			parent = child;
		}
		array[parent] = comp;
		
		if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
	}
	
	// 자주 사용되는 메소드
	public int size() {
		return this.size;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public E peek() {
		if(array[1] == null) {
			// root가 null일 경우 예외 던짐
			throw new NoSuchElementException();
		}
		
		return (E) array[1];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(Object value) {
		for(int i=1; i<=size; i++) {
			if(array[i].equals(value)) return true;
		}
		
		return false;
	}
	
	public void clear() {
		for(int i=0; i<array.length; i++) {
			array[i] = null;
		}
		
		size = 0;
	}
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 있는 큐의 요소들을 객체배열(Object[])로 반환
	 * - 이미 생성 된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
	 * 
	 * - get priorityQueue to array (using toArray())
	 * Object[] q1 = priorityQueue.toArray();
	 * 
	 * - get priorityQueue to array (using toArray(T[] a))
	 * Integer[] q2 = new Integer[10];
	 * q2 = priorityQueue.toArray(q2);
	 * 
	 * 1번의 장점이라면 우선순위 큐에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환
	 * 2번의 장점이라면 객체 클래스로 상속관계에 있는 타입이거나 래퍼 같이 데이터 타입을 유연하게 캐스팅 할 여지가 있다는 것
	 * 또한 큐의 원소 5가 있고 q2 배열에 10의 원소가 있다면 q2에 0-4 인덱스에 원소가 복사되고, 그 외의 원소는 기본 q2배열에 초기화 되어있던 원소가 그대로 남음
	 */
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length <= size) {
			return (T[]) Arrays.copyOfRange(array, 1, size+1, a.getClass());
		}
		
		System.arraycopy(array, 1, a, 0, size);
		
		return a;
	}
	
	/*
	 * clone()
	 * 깊은 복사를 하고싶을 때 쓰는 메소드
	 */
	@Override
	public Object clone() {
		// super.clone() 은 CloneNotSupportedException 예외를 처리해줘야함
		try {
			PriorityQueue<?> cloneHeap = (PriorityQueue<?>) super.clone();
			
			cloneHeap.array = new Object[size + 1];
			
			// 단순히 clone()만 한다고 내부 데이터까지 깊은 복사가 되는 것이 아니므로 내부 데이터들도 모두 복사해줌
			System.arraycopy(array, 0, cloneHeap.array, 0, size +1);
			
			return cloneHeap;
		}catch(CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
}
