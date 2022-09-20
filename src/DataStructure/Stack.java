package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;

public class Stack<E> implements StackInterface<E>, Cloneable {
	/*
	 * Stack
	 * Stack 라이브러리는 Vector 클래스를 상속받아 구현하고 있음
	 * 즉, 스택 클래스에서도 벡터 클래스의 메소드들을 사용할 수 있게 되어 있는데 벡터 클래스는 기본적으로 어레이리스트와 구조가 거의 같음
	 * 
	 * - Bottom : 가장 밑에 있는 데이터 또는 그 데이터의 인덱스를 의미
	 * - Top : 가장 위에 있는 데이터 또는 그 데이터의 인덱스를 의미
	 * - Capacity : 데이터를 담기 위한 용적
	 * - size : 데이터의 개수
	 * 
	 * 데이터를 추가하는 작업을 push (리스트에서의 add와 같은 역할)
	 * 데이터를 삭제하는 작업을 pop (리스트에서의 remove와 같은 역할)
	 * push는 데이터를 기존의 리스트와 같은 메커니즘으로 인덱스가 증가하면서 추가
	 * 삭제는 리스트와 달리 가장 마지막 데이터를 삭제 (리스트에서의 remove()는 대개 가장 앞의 원소를 삭제)
	 * 
	 * 모든 자료구조는 동적할당을 전제로 함
	 */
	
	private static final int DEFAULT_CAPACITY = 10;	// 최소(기본) 용적 크기
	private static final Object[] EMPTY_ARRAY = {};	// 빈 배열
	
	private Object[] array; 	// 요소를 담을 배열
	private int size;	// 요소 개수
	
	// 생성자1 (초기 공간 할당 X)
	public Stack() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자2 (초기 공간 할당 O)
	public Stack(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	/*
	 * resize()
	 * 데이터의 개수에 따라 최적화 된 용적을 갖을 필요가 있음
	 * 그렇기 때문에 요소의 개수가 용적에 얼마만큼 차있는지를 확인하고, 적절한 크기에 맞게 배열의 용적을 변경해야함
	 * 또한 용적은 외부에서 마음대로 접급하면 데이터의 손상을 야기할 수 있기 때문에 private로 접근을 제한
	 */
	public void resize() {
		// 빈 배열일 경우 (capacity is 0)
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		int arrayCapacity = array.length;	// 현재 용적 크기
		
		// 용적이 가득 찰 경우
		if(size == arrayCapacity) {
			int newSize = arrayCapacity * 2;
			
			// 배열 복사
			array = Arrays.copyOf(array, newSize);
			return;
		}
		
		// 용적의 절반 미만으로 요소가 차지하고 있을 경우
		if(size < (arrayCapacity / 2)) {
			int newCapacity = (arrayCapacity / 2);
			
			// 배열 복사
			array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
			return;
		}
	}
	
	/*
	 * push()
	 * 스택에서는 항상 최상단(맨 위)에 데이터를 추가해야하므로 한 종류밖에 없음
	 */
	@Override
	public E push(E item) {
		// 용적이 꽉 차있다면 용적을 재할당
		if(size == array.length) {
			resize();
		}
		array[size] = item;	// 마지막 위치에 요소 추가
		size++;
		
		return item;
	}
	
	/*
	 * pop()
	 * push메소드 메커니즘을 반대로 구현하면 됨
	 * 다만 중요한 점은 삭제할 요소가 없는 경우(스택이 비어있는 경우) 이에 대한 예외로 EmptyStackException을 던짐
	 */
	@Override
	public E pop() {
		// 만약 삭제할 요소가 없다면 Stack이 비어있다는 의미로 예외 발생
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		@SuppressWarnings("unchecked")
		E obj = (E) array[size - 1];	// 삭제될 요소를 반환하기 위한 임시 변수
		
		array[size - 1] = null;	// 요소 삭제
		
		size--;
		resize();
		
		return obj;
	}

	/*
	 * peek()
	 * 가장 상단에 있는 데이터를 삭제하지 않고 확인만 하고싶을 때
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		// 만약 삭제할 요소가 없다면 Stack이 비어있다는 의미이므로 예외 발생
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		return (E) array[size - 1];
	}

	/*
	 * search()
	 * Top으로부터 떨어져있는 거리를 의미 (단, 1부터 시작)
	 * 수식으로 표현하면 size - index
	 */
	@Override
	public int search(Object value) {
		for(int idx = size-1; idx >= 0; idx--) {
			// 같은 객체를 찾았을 경우 size - idx 값을 반환
			if(array[idx].equals(value)) {
				return size - idx;
			}
		}
		
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		// 저장되어있던 모든 요소를 null 처리
		for(int i=0; i<size; i++) {
			array[i] = null;
		}
		size = 0;
		resize();
	}

	@Override
	public boolean empty() {
		return size == 0;
	}
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * clone()
	 * 깊은 복사로 아예 다른 하나의 클론을 만드는 것
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// 새로운 스택 객체 생성
		Stack<?> cloneStack = (Stack<?>) super.clone();
		
		// 새로운 스택의 배열도 생성해주어야 함(내부 객체는 깊은 복사가 되지 않기 때문)
		cloneStack.array = new Object[size];
		
		// 현재 배열을 새로운 스택의 배열에 값을 복사
		System.arraycopy(array, 0, cloneStack.array, 0, size);
		return cloneStack;
	}
	
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 stack의 리스트 객체배열(Object[])로 반환해주는 Object[]
	 * - stack을 이미 생성된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * Stack<Integer> stack = new Stack<>();
	 * 
	 * - get Stack to array (using toArray())
	 * Object[] stack1 = stack.toArray();
	 * 
	 * - get Stack to array (using toArray(T[] a))
	 * Integer[] stack2 = new Integer[10];
	 * stack2 = stack.toArray(stack2);
	 * 
	 * 1번의 장점은 스택에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환됨
	 * 2번의 장점은 객체클래스로 상속관계에 있는 타입이거나 테이터 타입을 유연하게 캐스팅할 여지가 있다는 것
	 */
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		
		System.arraycopy(array, 0, a, 0, size);
		
		return a;
	}
	
	/*
	 * sort()
	 * 자바의 데이터 정렬을 위한 비교기는 Comparable, Comparator가 있음
	 * Comparable을 쓰는 경우는 해당 객체의 기본 정렬 방법을 설정할 때
	 * Comparator의 경우는 특정한 경우에 임시적으로 쓸 수 있게 정렬을 정의할 때 쓰임
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
		Arrays.sort((E[])array, 0, size, c);
	}
}
