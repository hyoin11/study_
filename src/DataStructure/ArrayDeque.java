package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Queue<E>, Cloneable {
	/*
	 * Deque(덱)
	 * Double-ended queue의 줄임말
	 * 양방향 연결리스트와 유사한 메커니즘
	 * 양쪽 방향으로 삽입 삭제가 이루어 질 수 있도록 구현
	 * 덱의 장점은 스택처럼 사용할 수도 있고 큐 처럼 사용할 수도 있음
	 */
	
	private static final int DEFAULT_CAPACITY = 64;	// 최소(기본) 용적 크기
	
	private Object[] array;	// 요소를 담을 배열
	private int size;	// 요소 개수
	
	private int front;	// 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
	private int rear;	// 마지막 요소의 인덱스를 가리키는 변수
	
	// 생성자1 (초기 용적 할당 X)
	public ArrayDeque() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// 생성자2 (초기 용적 할당 O)
	public ArrayDeque(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	/*
	 * resize()
	 * size가 용적에 얼마만큼 차있는지를 확인하고, 적절한 크기에 맞게 배열의 용적을 변경해야함
	 */
	private void resize(int newCapacity) {
		int arrayCapacity = array.length;	// 현재 용적 크기
		
		Object[] newArray = new Object[newCapacity];	// 용적을 변경한 배열
		
		/*
		 * i = new array index
		 * j = original array
		 * index 요소 개수(size)만큼 새 배열에 값 복사
		 */
		for(int i=1, j=front+1; i<=size; i++, j++) {
			newArray[i] = array[i % arrayCapacity];
		}
		
		this.array = null;
		this.array = newArray;	// 새 배열을 기존 array의 배열로 덮어씌움
		
		front = 0;
		rear = size;
	}
	
	/*
	 * offer 계열 메소드
	 * - 가장 마지막 부분에 추가 : offer(E item), offerLast(E item)
	 * - 가장 앞의 부분에 추가 : offerFirst(E item)
	 * 		front변수를 이용하여 offerLast 반대로 구현
	 * 		주의해야할 점은 front값은 감소시켜야함
	 * 		front = (front - 1 + arrray.length) % array.length
	 */
	@Override
	public boolean offer(E item) {
		return offerLast(item);
	}
	
	public boolean offerLast(E item) {
		// 용적이 가득 찼을 경우
		if((rear + 1) % array.length == front) {
			resize(array.length * 2);	// 용적을 두배 늘려줌
		}
		
		rear = (rear + 1) % array.length;	// rear을 rear의 다음 위치로 갱신
		
		array[rear] = item;
		size++;
		
		return true;
	}
	
	public boolean offerFirst(E item) {
		// 용적이 가득 찼을 경우
		if((front - 1 + array.length) % array.length == rear) {
			resize(array.length * 2);	// 용적을 두 배 늘려줌
		}
		
		array[front] = item;	// front위치는 빈 공간이기 때문에 추가 해준 뒤 front 값을 갱신
		front = (front - 1 + array.length) % array.length;
		size++;
		
		return true;
	}
	
	/*
	 * poll계열 메소드
	 * - poll 및 pollFirst 메소드는 맨 앞의 요소를 삭제. front + 1 위치에 있는 요소를 삭제
	 * - pollLast 메소드는 맨 뒤의 요소를 삭제. rear위치에 있는 요소를 삭제
	 * 두가지 모두 중요한 점은 삭제할 요소가 없는 경우
	 */
	@Override
	public E poll() {
		return pollFirst();
	}
	
	public E pollFirst() {
		if(size == 0) {
			// 삭제할 요소가 없는 경우 null 반환
			return null;
		}
		
		front = (front + 1) % array.length; // front 를 한 칸 옮김
		
		@SuppressWarnings("unchecked")
		E item = (E) array[front];	// 반환할 데이터 임시 저장
		
		array[front] = null;	// 해당 front의 데이터 삭제
		size--;
		
		// 용적이 최소 크기(64)보다 크고 요소 개수가 1/4 미만일 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
			// 아무리 작아도 최소용적 미만으로 줄이지 않도록
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
		
		return item;
	}
	
	public E remove() {
		return removeFirst();	// 예외는 removeFirst()에서 던져줌
	}
	
	public E removeFirst() {
		E item = pollFirst();	// 	pollFirst()를 소환
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	public E pollLast() {
		if(size == 0) {	// 삭제할 요소가 없을 경우 null 반환
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E) array[rear];	// 반환할 데이터 임시 저장
		
		array[rear] = null;	// 해당 rear의 데이터 삭제
		
		rear = (rear - 1 + array.length) % array.length;	// rear를 한칸 옮김
		size--;
		
		// 용적이 최소 크기보다 크고 요소 개수가 1/4 미만일 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
			// 아무리 작아도 최소용적 미만으로 줄이지 않음
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
		
		return item;
	}
	
	public E removeLast() {
		E item = pollLast();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	/*
	 * peek()
	 * 가장 앞 또는 뒤에 있는 데이터를 삭제하지 않고 확인만 하고싶을 때 사용
	 */
	@Override
	public E peek() {
		return peekFirst();
	}
	
	public E peekFirst() {
		// 요소가 없을 경우 null 반환
		if(size == 0) return null;
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(front + 1) % array.length];
		
		return item;
	}
	
	public E peekLast() {
		// 요소가 없을 경우 null을 반환
		if(size == 0) return null;
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(rear)];
		
		return item;
	}
	
	public E element() {
		return getFirst();
	}
	
	public E getFirst() {
		E item = peek();
		
		// 앞의 원소가 null이라면(size == 0) 예외를 던짐
		if(item == null) throw new NoSuchElementException();
		
		return item;
	}
	
	public E getLast() {
		E item = peekLast();
		
		// 앞의 원소가 null이라면(size == 0) 예외를 던짐
		if(item == null) throw new NoSuchElementException();
		
		return item;
	}
	
	// 자주 사용되는 메소드
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(Object value) {
		int start = (front + 1) % array.length;
		
		/*
		 * i : 요소 개수만큼 반복
		 * idx : 원소 위치로, 매 회 (idx + 1) % array.length; 의 위치로 갱신
		 */
		for(int i=0, idx=start; i<size; i++, idx=(idx+1)%array.length) {
			if(array[idx].equals(value)) return true;
		}
		
		return false;
	}
	
	public void clear() {
		for(int i=0; i<array.length; i++) {
			array[i] = null;
		}
		
		front = rear = size = 0;
	}
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 있는 큐의 요소들을 객체배열(Object[])로 반환
	 * - 덱을 이미 생성 된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
	 * 
	 * - get ArrayDeque to array (using toArray())
	 * Object[] dq1 = arrayDeque.toArray();
	 * 
	 * - get ArrayDeque to array (using toArray(T[] a))
	 * Integer[] dq2 = new Integer[10];
	 * dq2 = arrayDeque.toArray(dq2);
	 * 
	 * 1번의 장점이라면 덱에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환
	 * 2번의 장점이라면 객체 클래스로 상속관계에 있는 타입이거나 래퍼같이 데이터 타입을 유연하게 캐스팅할 여지가 있다는 것
	 * 또한 덱의 원소 5개가 있고 dq2 배열에 10개의 원소가 있다면 dq2에 0-4 인덱스에 원소가 복사되고, 그 외의 원소는 기존 q2배열에 초기화 되어있던 원소가 그대로 남음
	 * 다만 ArrayDeque에서는 앞이 뒤보다 항상 앞에 있는 것이 아니라 중간이 비어버리는 경우가 발생할 수 있기 때문에
	 * 이러한 것을 방지하기 위해 정확한 시작 위치와 끝나는 위치 범위를 잘 설정해야함
	 * 복사되는 원소의 범위는 (front + 1) % array.length 부터 rear 까지
	 */
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		final T[] res;
		// 들어오는 배열의 길이가 덱의 요소 개수보다 작은 경우
		if(a.length < size) {
			/*
			 * front가 rear 보다 뒤에 있을 경우(또는 요소가 없을 경우 f==r)
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	f        r
			 */
			if(front <= rear) {
				return (T[]) Arrays.copyOfRange(array, front+1, rear+1, a.getClass());
			}
			
			/*
			 * front가 rear보다 앞에 있을 경우
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	r        f
			 */
			
			res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
			int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front + 1, res, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, res, rearlength, rear+1);
			
			return res;
		}
		
		/*
		 * front가 rear보다 뒤에 있을 경우(또는 요소가 없을 경우 (f==r)
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	f        r
		 */
		if(front <= rear) {
			System.arraycopy(array, front + 1, a, 0, size);
		}
		
		/*
		 * front가 rear보다 앞에 있을 경우
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	r        f
		 */
		else {
			int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front + 1, a, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, a, rearlength, rear + 1);
		}
		
		return a;
	}
	
	/*
	 * clone()
	 * 깊은 복사를 하고싶을 때
	 */
	@Override
	public Object clone() {
		// super.colne() 은 CloneNotSupportedException 예외를 처리해주어야 함
		try {
			@SuppressWarnings("unchecked")
			ArrayDeque<E> clone = (ArrayDeque<E>) super.clone();	// 새로운 덱 객체 생성
			
			// 새로운 덱의 배열도 생성해주어야 함(내부 객체는 깊은 복사가 되지 않기 때문)
			clone.array = Arrays.copyOf(array, array.length);
			return clone;
		}catch(CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
	/*
	 * sort()
	 * Comparable을 쓰는 경우는 해당 객체의 기본 정렬 방법을 설정할 때
	 * Comparator를 쓰는 경우는 특정한 경우에 임시적으로 쓸 수 있게 정렬을 정의할 때
	 */
	public void sort() {
		/*
		 * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬 방식을 사용
		 * 만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable 에러 발생
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬
		 */
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		// null 접근 방지를 위해 toArray로 요소만 있는 배열을 얻어 이를 정렬한 뒤 덮어 씌움
		Object[] res = toArray();
		
		Arrays.sort((E[]) res, 0, size, c);
		
		clear();
		
		/*
		 * 정렬된 원소를 다시 array에 0부터 차례대로 채움
		 * 이때 front = 0 인덱스는 비워야 하므로 사실상 1번째 인덱스부터 채워야함
		 */
		System.arraycopy(res, 0, array, 1, res.length);	// res 배열을 array에 복사
		
		this.rear = this.size = res.length;
	}
}
