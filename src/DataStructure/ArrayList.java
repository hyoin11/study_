package DataStructure;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {
	/*
	 * 다른 자료구조와 달리 Object[] 배열(객체 배열)을 두고 사용한다는 점
	 * 동적 할당을 전제로 함
	 * 데이터 사이에 빈 공간을 허락하지 않음
	 * 리스트 계열 자료구조는 데이터들이 연속되어 있어야 함
	 */
	
	/*
	 * DEFAULT_CAPACITY : 배열이 생성 될 때의 최초 할당 크기(용적)이자 최소 할당 용적 변수로 기본값은 10
	 * EMPTY_ARRAY : 아무것도 없는 빈 배열(용적이 0 인 배열)
	 * size : 배열에 담긴 요소의 개수 (용적 크기가 아님!!!)
	 * arrya : 요소들을 담을 배열
	 */
	private static final int DEFAULT_CAPACITY = 10;	// 최소(기본) 용적 크기
	private static final Object[] EMPTY_ARRAY = {};	// 빈 배열
	
	private int size;	// 요소 개수
	
	Object[] array;	// 요소를 담을 배열
	
	// 생성자1 (초기 공간 할당 X)
	public ArrayList() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자2 (초기 공간 할당 O)
	public ArrayList(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	
	// 동적할당을 위한 resize 메소드 구현
	private void resize() {
		int array_capacity = array.length;
		
		// if array's capacity 0
		if(Arrays.equals(array, EMPTY_ARRAY)) {	// 주소가 아닌 값을 비교해야 하기 때문에 Arrays.equals() 메소드를 사용
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 용량이 꽉 찰 경우
		if(size == array_capacity) {
			int new_capacity = array_capacity * 2;
			
			// copy
			array = Arrays.copyOf(array, new_capacity);
			return;
		}
		
		// 용적의 절반 미만으로 요소가 차지하고 있을 경우
		if(size < (array_capacity / 2)) {
			int new_capacity = array_capacity / 2;
			
			// copy
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
			return;
		}
	}
	
	/*
	 * add 메소드 구현
	 * add 메소드에는 크게 3가지로 분류가 됨
	 * - 가장 마지막 부분에 추가(기본값) - addLast(E value)
	 * - 특정 위치에 추가 - add(int index, E value)
	 * 		특정 인덱스를 포함한 뒤에 있는 모든 요소들을 모두 한칸씩 뒤로 옮긴 뒤 그 위치에 새로운 데이터를 삽입
	 * - 가장 앞부분에 추가 - addFirst(E value)
	 * 		특정 위치를 0으로 보내면 됨
	 */
	@Override
	public boolean add(E value) {
		// 가장 마지막 부분에 추가
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		// 꽉차있는 상태라면 용적 재할당
		if(size == array.length) {
			resize();
		}
		array[size] = value;	// 마지막 위치에 요소 추가
		size++;	// 사이즈 1 증가
	}

	@Override
	public void add(int index, E value) {
		// 중간에 추가
		if(index > size || index < 0) {	// 영역을 벗어날 경우 예외 발생
			throw new IndexOutOfBoundsException();
		}
		
		if(index == size) {	// index가 마지막 위치라면 addLast 메소드로 요소 추가
			addLast(value);
		}else {
			if(size == array.length) {	// 꽉차있다면 용적 재할당
				resize();
			}
			
			// index 기준 후자에 있는 모든 요소들을 한 칸씩 뒤로 밀기
			for(int i=size; i>index; i--) {
				array[i] = array[i-1];
			}
			
			array[index] = value;	// index 위치에 요소 할당
			size++;
		}
	}
	
	public void addFirst(E value) {
		add(0, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if(index >= size || index < 0) {	// 범위 벗어나면 예외 발생
			throw new IndexOutOfBoundsException();
		}
		
		// Object 타입에서 E타입으로 캐스팅 후 반환;
		return (E) array[index];
	}
	/*
	 * @SuppressWarnings("uncheckd")
	 * 붙이지 않으면 return값에 type safe에 대해 경고를 보냄
	 * 반환되는 것을 보면 E타입으로 캐스팅을 하고 있고 그 대상이 되는 것은 object[] 배열의 objec 데이터임
	 * 즉, Object -> E 타입으로 변환을 하는 것인데 이 과정에서 변환할 수 없는 타입을 가능성이 있다는 경고로 메소드 옆에 경고 표시가 뜨는데, add 하여 받아들이는 데이터 타입은 유일하게 E타입만 존재함
	 * 그렇기때문에 형 안정성이 보장됨
	 * 한마디로 ClassCastException이 뜨지 않으니 이 경고들을 무시하겠다는것임
	 */
	
	/*
	 * set은 기존 index에 위치한 데이터를 새로운 데이터로 교체하는 것
	 */
	@Override
	public void set(int index, E value) {
		if(index >= size || index < 0) {	// 범위를 벗어날 경우 예외 발생
			throw new IndexOutOfBoundsException();
		}else {
			// 해당 위치의 요소를 교체
			array[index] = value;
		}
	}
	
	/*
	 * 찾고자 하는 요소의 위치를 반환
	 * 중복된 요소가 존재하면 가장 처음에 찾은 요소를 반환
	 * 찾고자 하는 요소가 없다면 -1을 반환
	 * 동등연산자(==)가 아니라 .equals()로 비교해야함
	 */
	@Override
	public int indexOf(Object value) {
		// index를 0 부터 탐색
		
		int i=0;
		
		// value와 같은 객체(요소 값)일 경우 i(위치) 반환
		for(i=0; i<size; i++) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		
		// 일치하는 것이 없을 경우 -1을 반환
		return -1;
	}
	
	public int lastIndexOf(E value) {
		// index를 거꾸로 탐색
		
		for(int i=size-1; i>=0; i--) {
			if(array[i].equals(value)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/*
	 * 요소가 존재하는지 안하는지를 반환
	 */
	@Override
	public boolean contains(Object value) {
		// 0이상이면 요소가 존재
		if(indexOf(value) >= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * remove 메소드는 크게 2가지로 나눌 수 있음
	 * - 특정 index의 요소를 삭제 - remove(int index)
	 * 		index에 위치한 데이터를 삭제하고, 해당 위치 이후의 데이터들을 한칸씩 당겨오는 것
	 * - 특정 요소를 삭제 - remove(Object value)
	 * 		특정 요소를 찾아 삭제. 여러개가 있을 경우 가장 앞에있는 요소만 삭제
	 * 		특정 요소의 index를 찾아 index로 요소를 삭제해주면 됨
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		if(index >= size ||index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		E element = (E) array[index]; 	// 삭제될 요소를 반환하기 위해 임시로 담아둠
		array[index] = null;
		
		// 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당겨옴
		for(int i=index; i<size; i++) {
			array[i] = array[i+1];
			array[i+1] = null;
		}
		size--;
		resize();
		return element;
	}

	@Override
	public boolean remove(Object value) {
		// 삭제하고자 하는 요소의 인덱스 찾기
		int index = indexOf(value);
		
		// -1이라면 array에 요소가 없다는 의미이므로 false 반환
		if(index == -1) {
			return false;
		}
		
		// index 위치에 있는 요소를 삭제
		remove(index);
		return true;
	}
	
	/*
	 * arrayList가 동적으로 할당되면서 요소들을 삽입, 삭제가 많아지면서 리스트에 담긴 요소의 개수를 기억하기 힘듬
	 * 또, size변수는 private 접근제한자를 갖고 있기 때문에 외부에서 직접 참조할 수 없음(size를 접근할 수 있게 될 경우 size에 사용자가 고의적으로 데이터를 조작할 수 있기 때문)
	 */
	@Override
	public int size() {
		return size;	// 요소 개수 반환
	}
	
	/*
	 * 현재 arraylist에 요소가 단 하나도 존재하지 않고 비어있는지를 알려줌
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;	// 요소가 0개일 경우 비어있다는 의미이므로 true를 반환
	}

	/*
	 * 모든 요소들을 비워버리는 작업
	 * 리스트에 요소를 담아두었다가 초기화가 필요할 때 쓸 수 있음
	 * 또한 모든 요소를 비워버린다는 것은 요소가 0개라는 말로 size 또한 0으로 초기화해주고, 배열의 용적 또한 현재 용적의 절반으로 줄일 수 있도록 해줌
	 * (초기값이 아니라 절반인 이유는 현재 용적량의 기대치에 근접할 가능성이 높기 때문에 일단은 용적을 절반으로 줄임. 또한 그만큼 데이터를 쓰지 않더라도 삭제 과정에서 용적량을 줄이기 때문에 크게 문제되지 않음)
	 */
	@Override
	public void clear() {
		// 모든 공간을 null 처리
		for(int i=0; i<size; i++) {
			array[i] = null;
		}
		size = 0;
		resize();
	}
	
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * clone()
	 * arrayList를 새로 하나 복제하고 싶을 때 쓰는 메소드
	 * = 연산자로 객체를 복사하게되면 주소를 복사하는 것이기 때문에 복사한 객체에서 데이터를 조작할 경우 원본 객체까지 영향을 미침(얕은 복사. shallow copy)
	 * Object에 있는 메소드이지만 접근제어자가 protected로 되어있어 만들어 사용하는 사용자 클래스의 경우 Cloneable 인터페이스를 implement해야함
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// 새로운 객체 생성
		ArrayList<?> cloneList = (ArrayList<?>) super.clone();	// super.clone() 자체가 생성자 비슷한 역할
		
		// 새로운 객체의 배열도 생성해주어야 함(객체는 얕은복사가 되기 때문)
		cloneList.array = new Object[size];
		
		// 배열의 값을 복사함
		System.arraycopy(array, 0, cloneList.array, 0, size);
		
		return cloneList;
	}
	
	/*
	 * toArray()
	 * 크게 2가지가 있음
	 * - 아무런 이자 없이 현재 있는 arrayList의 리스트를 객체배열로 반환해주는 Object[] toArray()
	 * - arrayList를 이미 생성 된 다른 배열에 복사해주고자할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * ArrayList<Integer> list = new ArrayList<>();
	 * 
	 * - get list to array(using toArray())
	 * Object[] array1 = list.toArray();
	 * 
	 * - get list to array(using toArray(T[] a)
	 * Integer[] array2 = new Integer[10];
	 * array2 = list.toArray(array2);
	 * 
	 * 1번의 장점은 arrayList에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환됨
	 * 2번의 장점은 객체 클래스로 상속관계에 있는 타입이거나 Wrapper(Integer -> int)같이 데이터 타입을 유연하게 캐스팅할 여지가 있음
	 * 	또한 리스트에 원소 5개가 있고, array2 배열에 10개의 원소가 있다면 array2에 0-4 인덱스에 리스트에 있던 원소가 복사되고, 그 외의 원소는 기존 array2배열에 초기화 되어있던 원소가 그대로 남음
	 */
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			// copyOf(원본배열, 복사할 길이, Class<? extends T[]> 타입)
			return (T[]) Arrays.copyOf(array, size, a.getClass());
		}
		
		// 원본배열, 원본배열 시작위치, 복사할 배열, 복사할 배열 시작위치, 복사할 요소 수
		System.arraycopy(array, 0, a, 0, size);
		return a;
	}
	/*
	 * 제네릭 메소드로, arrayList의 E타입하고는 다른 제네릭
	 * 예를들어 E type이 Integer이고 T type은 Object라고하면
	 * 오브젝트는 인티저보다 상위 타입으로, 오브젝트 안에 인티저 타입의 데이터를 담을 수도 있음
	 * 이 외에도 사용자가 만든 부모, 자식 클래스 같이 상속관계에 있는 클래스들에서 하위 타입이 상위 타입으로 데이터를 받고 싶을 때 쓸 수 있도록 하기 위함
	 * 즉, 상위타입으로 들어오는 객체에 대해서도 데이터를 담을 수 있도록 별도의 제네릭메소드를 구성
	 * 
	 * 그리고 들어오는 배열(a)가 현재 array의 요소 개수(size)보다 작으면 size에 맞게 a의 공간을 재할당 하면서 array에 있던 모든 요소를 복사
	 * 쉽게 이해해보면 ArrayList의 array의 요소가 4개 {1,2,3,4}있다고 가정
	 * 그리고 Object[] copy = new Object[1]라는 배열을 하나 만들었는데 공간이 한개밖에 없음
	 * 그러면 array의 요소 1개만 복사하는 것이 아니라 copy배열의 사이즈가 1에서 4로 증가하여 copy 배열에 {1,2,3,4}가 모두 담기게됨
	 * 
	 * 또한 앞서 상위 타입에 대해서도 담을 수 있도록 하기 위해 copyOf메소드에서 class라는 파라미터를 마지막에 넣어줌(a.getClass())
	 * 그런 다음 Object[] 배열로 리턴된 것을 T[]타입으로 캐스팅하여 반환
	 * 
	 * 반대로 파라미터로 들어오는 배열의 크기가 현재 array보다 크다면 앞부분부터 array에 있던 요소만 복사해서 a 배열에 넣고 반환
	 */
}
