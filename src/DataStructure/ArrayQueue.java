package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E>, Cloneable {
	/*
	 * 큐를 선형적으로 접근하게 되면 쏠림현상이 발생하는데,
	 * 그렇다고 매 번 삭제 연산 때마다 삭제된 원소 뒤의 모든 원소들을 한 자리씩 앞으로 땡겨오는 것은 매우 비효율적, 배열 크기를 늘리는 것도 빈공간을 낭비
	 * 이를 해결하기 위한 아주 간단한 방법은 배열을 원형이라고 생각
	 * 이때 front(앞) rear(뒤) 변수가 필요함
	 * rear을 따라 원소가 추가되고, front를 따라 원소가 삭제됨
	 * 만약 더이상 빈 자리가 없을 경우에만 배열의 크기를 늘려주면 됨
	 * 여기서 front 변수 다음부터 원소가 추가됨
	 * front 위치의 공간을 비우지 않으면 rear와 front가 가리키는 위치는 같지만 비어있는지 가득차있는지 두 변수만으로 알수없음
	 * 그래서 front자리를 비우면 front==rear일때 큐가 비어있는 상태라는 것을 확인
	 * 이러한 구조를 보통 Circular Queue (원형큐, 환형큐)라고함 
	 */
	
	private static final int DEFAULT_CAPACITY = 64;	// 최소(기본) 용적 크기
	
	private Object[] array;	// 요소를 담을 배열
	private int size;	// 요소 개수
	
	private int front;	// 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
	private int rear;	// 마지막 요소의 인덱스를 가리키는 변수
	
	// 생성자1 (초기 용적 할당을 안할 경우)
	public ArrayQueue() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// 생성자2 (초기 용적 할당을 할 경우)
	public ArrayQueue(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	/*
	 * resize()
	 * 동적할당을 위한 메소드
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
			newArray[i] = array[j % arrayCapacity];
		}
		
		this.array = null;
		this.array = newArray;	// 새 배열을 기존 array의 배열로 덮어씌움
		
		front = 0;
		rear = size;
	}
	/*
	 * 용적을 증가하는 경우 : (rear + 1) % arrayCapacity == front)
	 * 용적을 줄여야 하는 경우 : (size < (arrayCapacity / 4))
	 * 두 경우 조건문은 달지 않고 파라미터로 받은 새 용적을 이용하여 용적의 사이즈를 변경할 것
	 */
	
	/*
	 * offer()
	 * 큐는 항상 맨 뒤에 데이터를 추가해야함
	 * 고려해야할 부분이라면 배열의 마지막 인덱스에 도달했을 경우 또는 배열이 꽉 차있을 경우
	 */
	@Override
	public boolean offer(E item) {
		// 용적이 가득 찼을 경우
		if((rear + 1) % array.length == front) {
			resize(array.length * 2);	// 용적을 두 배 늘려줌
		}
		
		rear = (rear + 1) % array.length;	// rear를 rear의 다음 위치로 갱신
		
		array[rear] = item;
		size++;
		
		return true;
	}
	
	/*
	 * poll()
	 * remove와 같은 역할로 front+1 위치에 있는 요소를 삭제
	 * 중요한 점은 삭제할 요소가 없는 경우
	 * remove() 경우 삭제할 요소가 없으면 NoSuchElementException() 예외를 던짐
	 * poll() 경우 삭제할 요소가 없다면 null을 반환
	 */
	@Override
	public E poll() {
		if(size == 0) {	// 삭제할 요소가 없을 경우 null을 반환
			return null;
		}
		
		front = (front + 1) % array.length;	// front를 한칸 옮김
		
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
		E item = poll();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	/*
	 * peek()
	 * 가장 앞에 있는 데이터를 삭제하지 않고 확인만 하고싶을 때
	 * 큐에 데이터가 없는 경우 null을 던짐
	 * 반대로 유사한 element()메소드는 큐에 요소가 없을 경우 remove()와 마찬가지로 NoSuchElementException 예외를 던짐
	 */
	@Override
	public E peek() {
		// 요소가 없을 경우 null을 반환
		if(size == 0) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		E item = (E) array[(front + 1) % array.length];
		return item;
	}
	
	public E element() {
		E item = peek();
		
		if(item == null) {
			throw new NoSuchElementException();
		}
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
		 * idx  : 원소 위치로, 매회 (idx + 1) % array.length 의 위치로 갱신
		 */
		for(int i=0, idx=start; i<size; i++, idx=(idx+1)%array.length) {
			if(array[idx].equals(value)) {
				return true;
			}
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
	 * - 아무런 인자 없이 현재 있는 큐의 요소들을 객체배열(Object[])로 반환해주는 Object[]
	 * - 큐를 이미 생성 된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
	 * 
	 * - get ArrayQueue to array (using toArray())
	 * Object[] q1 = arrayQueue.toArray();
	 * 
	 * - get ArrayQueue to array (using toArray(T[] a))
	 * Integer[] q2 = new Integer[10];
	 * q2 = arrayQueue.toArray(q2);
	 * 
	 * 1번의 장점이라면 큐에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환됨
	 * 2번의 장점이라면 객체 클래스로 상속관계에 있는 타입이거나 래퍼같이 데이터 타입을 유연하게 캐스팅할 여지가 있다는 것
	 * 또한 큐의 원소 5개가 있고 q2 배열에 10개의 원소가 있다면 q2에 0-4에 원소가 복사되고 그 외의 원소는 기존 q2배열에 초기화 되어있던 원소가 그대로 남음
	 * 다만 ArrayQueue에서는 앞(front)이 뒤(rear)보다 항상 앞에 있는 것이 아니라 중간이 비어버리는 경우가 발생할 수 있기 때문에 이러한 것을 방지하기 위해 정확한 시작 위치와 끝나는 위치 범위를 잘 설정해야함
	 * 복사되는 원소의 범위는 (front+1) % array.length 부터 rear까지
	 */
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		final T[] res;
		
		// 들어오는 배열의 길이가 큐의 요소 개수보다 작은 경우
		if(a.length < size) {
			/*
			 * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f==r)
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	f        r
			 */
			if(front <= rear) {
				return (T[]) Arrays.copyOfRange(array, front+1, rear+1, a.getClass());
			}
			
			/*
			 * front가 rear보다 뒤에 있을 경우
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	r        f
			 */
			res = (T[]) Arrays.copyOfRange(array, 0, size, a.getClass());
			
			int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front+1, res, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, res, rearlength, rear+1);
			
			return res;
		}
		
		/*
		 * front가 rear보다 앞에 있을 경우 (또는 요소가 없을 경우 f==r)
		 *  ______________________
		 *  |  |  |  |  |  |  |  |
		 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
		 *    	f        r
		 */
		if(front <= rear) {
			System.arraycopy(array, front+1, a, 0, size);
		}else {
			/*
			 * front가 rear보다 뒤에 있을 경우
			 *  ______________________
			 *  |  |  |  |  |  |  |  |
			 *  ˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉˉ
			 *    	r        f
			 */
			int rearlength = array.length - 1 - front;	// 뒷 부분의 요소 개수
			
			// 뒷 부분 복사
			if(rearlength > 0) {
				System.arraycopy(array, front+1, a, 0, rearlength);
			}
			
			// 앞 부분 복사
			System.arraycopy(array, 0, a, rearlength, rear+1);
		}
		
		return a;
	}
	
	/*
	 * clone()
	 * 깊은 복사를 하고싶을 때
	 */
	@Override
	public Object clone() {
		// super.clone() 은 CloneNotSupportedException 예외 처리를 해주어야 함
		try {
			@SuppressWarnings("unchecked")
			ArrayQueue<E> clone = (ArrayQueue<E>) super.clone();	// 새로운 큐 객체 생성
			
			// 새로운 큐의 배열도 생성해주어야 함 (내부 객체는 깊은 복사가 되지 않기 때문)
			clone.array = Arrays.copyOf(array, array.length);
			return clone;
		}catch(CloneNotSupportedException e) {
			throw new Error(e);	// 예외처리는 자유롭게
		}
	}
	
	/*
	 * sort()
	 * - 객체에 Comparable이 구현되어 있어 따로 파라미터로 Comparator를 넘겨주지 않는 경우
	 * - Comparator을 넘겨주어 정의 돈 정렬 방식을 사용하는 경우
	 */
	public void sort() {
		/*
		 * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬 방식을 사용
		 * 만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable 에러가 발생
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬
		 */
		sort(null);
	}
	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super E> c) {
		// null 접근 방지를 위해 toArray로 요소만 있는 배열을 얻어 이를 정렬한 뒤 덮어씌움
		Object[] res = toArray();
		
		Arrays.sort((E[]) res, 0, size, c);
		
		clear();
		
		/*
		 * 정렬된 원소를 다시 array에 0부터 차례대로 채움
		 * 이때 front = 0 인덱스는 비워야 하므로 사실상 1번째 인덱스부터 채워야 함
		 */
		System.arraycopy(res, 0, array, 1, res.length);	// res 배열을 array에 복사
		
		this.rear = this.size = res.length;
	}
}
