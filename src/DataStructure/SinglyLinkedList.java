package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Cloneable {
	/*
	 * LinkedList
	 * ArrayList와 가장 큰 차이점은 '노드'라는 객체를 이용하여 연결
	 * ArrayList의 경우 최상위 타입인 오브젝트 배열(Object[])을 사용하여 데이터를 담아두었다면
	 * LinkedList는 배열을 이용하는 것이 아닌 하나의 객체를 두고 그 안에 데이터와 다른 노드를 가리키는 래퍼런스 데이터로 구성하여 여러 노드를 하나의 체인처럼 연결하는 것
	 * 노드(node)는 데이터와 다른 노드를 가리킬 주소데이터를 담은 객체
	 * 노드들이 여러개가 연결 되어있는 것을 연결 리스트, 즉 LinkedList라고 함
	 * 연결된 노드들에서 삽입을 한다면 링크만 바꿔주면 되기 때문에 매우 편리
	 * 반대로 삭제를 한다면 삭제할 노드에 연결 된 이전노드에서 링크를 끊고 삭제할 노드의 다음 노드를 연결해주면 됨
	 */
	
	private Node<E> head;	// 노드의 첫 부분
	private Node<E> tail;	// 노드의 마지막 부분
	private int size;	// 요소 개수
	
	// 생성자
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/*
	 * search() 메소드 부터 구현
	 * 단일 연결리스트이다보니 특정 위치의 데이터를 삽입, 삭제, 검색하기 위해서는 처음 노드부터 next변수를 통해 특정 위치까지 찾아가야하기 때문
	 */
	// 특정 위치의 노드를 반환하는 메소드
	private Node<E> search(int index){
		// 범위 밖일 경우 예외 던지기
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> x = head; 	// head가 가리키는 노드부터 시작
		
		for(int i=0; i<index; i++) {
			x = x.next;	// x노드의 다음 노드를 x에 저장
		}
		return x;
	}
	
	/*
	 * add 메소드
	 * - 가장 앞부분에 추가 - addFirst(E value)
	 * 		데이터를 이동시키는 것이 아닌 새로운 노드를 생성하고 새 노드의 레퍼런스 변수(next)가 head 노드를 가리키도록
	 * - 가장 마지막 부분에 추가(기본값) - addLast(E value)
	 * 		LinkedList API를 보면 add 메소드를 호출하면 add()는 addLast()를 호출함
	 * 		addFirst()를 먼저 구현한 이유는 size가 0일 경우(= 아무런 노드가 없을 경우)는 결국 처음으로 데이터를 추가한다는 뜻이기 때문에 addFirst()를 호출하면 됨
	 * - 특정 위치에 추가 - add(int index, E value)
	 * 		넣으려는 위치 앞뒤로 링크를 새로 업데이트해줘야함
	 */
	public void addFirst(E value) {
		Node<E> newNode = new Node<E>(value);	// 새 노드 생성
		newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결
		head = newNode;	// head가 가리키는 노드를 새 노드로 변경
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(= 데이터가 새 노드밖에 없는 경우)
		 * 데이터가 한개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자 마지막 노드
		 * 즉, tail = head
		 */
		if(head.next == null) {
			tail = head;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value);	// 새 노드 생성
		
		if(size == 0) {	// 처음 넣는 노드일 경우 addFirst로  추가
			addFirst(value);
			return;
		}
		
		// 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고 tail이 가리키는 노드를 새 노드로 바꿔줌
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	@Override
	public void add(int index, E value) {
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
		if(index == 0) {
			addFirst(value);
			return;
		}
		
		// 추가하려는 index가 마지막 위치일 경우 addLast 호출
		if(index == size) {
			addLast(value);
			return;
		}
		
		// 추가하려는 위치의 이전 노드
		Node<E> prev_Node = search(index-1);
		
		// 추가하려는 위치의 노드
		Node<E> next_Node = prev_Node.next;
		
		// 추가하려는 노드
		Node<E> newNode = new Node<E>(value);
		
		// 이전 노드가 가리키는 노드를 끊은 뒤 새 노드로 변경
		// 또한 새노드가 가리키는 노드는 next_Node로 설정
		prev_Node.next = null;
		prev_Node.next = newNode;
		newNode.next = next_Node;
		size++;
	}
	
	/*
	 * remove()
	 * - 가장 앞의 요소(head)를 삭제 - remove()
	 * 		head가 가리키는 요소만 없애주면 됨
	 * 		head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤 head를 다음 노드로 업데이트
	 * 		그리고 삭제하려는 노드가 리스트에서 유일한 노드였을 경우 해당 노드를 삭제하면 tail이 가리키는 노드 또한 없어지게 됨. 이에 대해서도 정확한 처리를 해줘야함
	 * - 특정 index의 요소를 삭제 - remove(int index)
	 * 		add(int index, E value) 와 정반대로 구현해주면 됨
	 * 		삭제하려는 노드의 이전 노드의 next 변수를 삭제하려는 노드의 다음 노드를 가리키도록 해주면 됨
	 * - 특정 요소를 삭제 - remove(Object value)
	 * 		특정 인덱스의 요소를 샂게하는 메소드와 동일한 메커니즘으로 작동
	 * 		다만 고려해야할 점은 삭제하려는 요소가 존재하는지를 염두해두어야 함
	 */
	public E remove() {
		Node<E> headNode = head;
		
		if(headNode == null) {
			throw new NoSuchElementException();
		}
		
		// 삭제된 노드를 반환하기 위한 임시 변수
		E element = headNode.data;
		
		// head의 다음 노드
		Node<E> nextNode = head.next;
		
		// head 노드의 데이터들을 모두 삭제
		head.data = null;
		head.next = null;
		
		// head가 다음 노드를 가리키도록 업데이트
		head = nextNode;
		size--;
		
		// 삭제된 요소가 리스트의 유일한 요소였을 경우 head 이자 tail이었으므로 삭제되면서 tail도 가리킬 요소가 없기 때문에 size가 0일 경우 tail도 null
		if(size == 0) {
			tail = null;
		}
		return element;
	}

	@Override
	public E remove(int index) {
		// 삭제하려는 노드가 첫 번쨰 원소일 경우
		if(index == 0) {
			return remove();
		}
		
		// 잘못된 범위에 대한 예외
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> prevNode = search(index-1);	// 삭제할 노드의 이전 노드
		Node<E> removeNode = prevNode.next;	// 삭제할 노드
		Node<E> nextNode = removeNode.next; // 삭제할 노드의 다음 노드
		
		E element = removeNode.data;	// 삭제되는 노드의 데이터를 반환하기 위한 임시변수
		
		// 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음노드로 변경
		prevNode.next = nextNode;
		
		// 데이터 삭제
		removeNode.next = null;
		removeNode.data = null;
		size--;
		
		return element;
	}

	@Override
	public boolean remove(Object value) {
		Node<E> prevNode = head;
		boolean hasValue = false;
		Node<E> x = head;	// removedNode
		
		// value와 일치하는 노드를 찾음
		for(; x!=null; x=x.next) {
			if(value.equals(x.data)) {
				hasValue = true;
				break;
			}
			prevNode = x;
		}
		
		// 일치하는 요소가 없을 경우 false 반환
		if(x == null) {
			return false;
		}
		
		// 만약 삭제하려는 노드가 head라면 기존 remove() 사용
		if(x.equals(head)) {
			remove();
			return true;
		}else {
			// 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
			prevNode.next = x.next;
			
			x.data = null;
			x.next = null;
			size--;
			return true;
		}
	}
	
	/*
	 * get()
	 * index로 들어오는 값을 인덱스 삼아 해당 위치에 있는 요소를 반환
	 * search()를 이용하면 되는데 search()는 노드를 반환하고 get()은 노드의 데이터를 반환
	 */
	@Override
	public E get(int index) {
		return search(index).data;
	}

	/*
	 * set()
	 * index에 위치한 데이터를 새로운 데이터(value)로 교체
	 */
	@Override
	public void set(int index, E value) {
		Node<E> replaceNode = search(index);
		replaceNode.data = null;
		replaceNode.data = value;
	}
	
	/*
	 * indexOf()
	 * 사용자가 찾고자 하는 요소의 위치를 반환
	 */
	@Override
	public int indexOf(Object value) {
		int index = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			if(value.equals(x.data)) {
				return index;
			}
			index++;
		}
		
		// 찾고자 하는 요소를 찾지 못했을 경우 -1을 반환
		return -1;
	}

	/*
	 * contains()
	 * 찾고자 하는 요소의 존재여부를 반환
	 */
	@Override
	public boolean contains(Object item) {
		return indexOf(item) >= 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * clear()
	 * 모든 요소들을 비워버리는 작업
	 * 객체 자체를 null해주기 보다는 모든 노드를 하나하나 null 해주는 것이 가비지 컬렉터가 명시적으로 해당 메모리를 안쓴다고 인지하기 때문에 메모리 관리 효율 측면에서 조금이나마 더 좋음
	 */
	@Override
	public void clear() {
		for(Node<E> x = head; x != null;) {
			Node<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x = nextNode;
		}
		head = tail = null;
		size = 0;
	}
	
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * clone()
	 * 깊은 복사를 위한 메소드
	 */
	public Object clone() throws CloneNotSupportedException {
		@SuppressWarnings("unchecked")
		SinglyLinkedList<? super E> clone = (SinglyLinkedList<? super E>) super.clone();
		
		clone.head = null;
		clone.tail = null;
		clone.size = 0;
		
		for(Node<E> x = head; x != null; x = x.next) {
			clone.addLast(x.data);
		}
		
		return clone;
	}
	
	/*
	 * toArray()
	 * ArrayList와 마찬가지로 두가지
	 * - 아무런 인자 없이 현재 있는 리스트를 객체배열(Object[])로 반환해주는 Object[]
	 * - 리스트를 이미 생성 된 다른 배열에 복사해주고자 할 때 쓰이는 T[] toArray(T[] a)
	 * 
	 * 차이점
	 * SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
	 * 
	 * - get list to array (using toArray())
	 * Object[] array1 = list.toArray();
	 * 
	 * - get list to array (using toArray(T[] a))
	 * Integer[] array2 = new Integer[10];
	 * array2 = list.toArray(array2);
	 * 
	 * 1번의 장점은 링크드리스트에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환됨
	 * 2번의 장점은 객체 클래스로 상속관계에 있는 타입이거나 Wrapper(Integer -> Number)같이 데이터 타입을 유연하게 캐스팅할 여지가 있다는 것
	 * 또한 리스트에 원소 5개가 있고 array2 배열에 10개의 원소가 있다면 array2에 0-4 인덱스에 원소가 복사되고, 그 외 원소는 array2 배열에 초기화 되어있던 원소가 그대로 남음
	 * 그리고 특정 타입의 객체 배열로 받고 싶은 경우 arrayList의 배열 생성방식과 차이가 있음
	 * 노드 객체에 데이터를 담고있는 연결리스트이기 때문에 노드 자체가 래퍼클래스나 사용자가 만든 클래스 같은 데이터를 갖을 수 없음
	 * 노드의 data 변수가 객체 타입 변수인 것이지 노드 자체가 객체 타입을 갖지 못함 그래서 Arrays.copyOf(), System.arraycopy를 쓰기 어려움
	 * lang.reflect 패키지에 있는 array 클래스의 도움을 받을 수 있음
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		int idx = 0;
		for(Node<E> x = head; x != null; x = x.next) {
			array[idx++] = (E) x.data;
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			// Array.newInstance(컴포넌트 타입, 생성할 크기)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		int i = 0;
		Object[] result = a;
		for(Node<E> x = head; x != null; x = x.next) {
			result[i++] = x.data;
		}
		return a;
	}
	/*
	 * Object[] toArray() 경우 리스트에 있는 요소 개수만큼 복사하여 Object[]를 반환
	 * Object가 최상위 타입이기 때문에 배열에 데이터를 그대로 담고 반환
	 * 
	 * T[] toArray(T[] a) 제네릭 메소드로, 링크드리스트의 E타입과 다른 제네릭
	 * 상위타입으로 들어오는 객체에 대해서도 데이터를 담을 수 있도록 별도의 제네릭 메소드를 구성하는 것
	 * 하위타입, 즉 축소할 경우 Array 클래스에서 예외를 던지기 때문에 이에 대한 별다른 예외를 처리해줄 필요 없다
	 * 
	 * 그리고 파라미터로 주어지는 배열 a가 현재 리스트의 요소보다 작은 경우와 그 반대의 경우가 있음
	 * 작은 경우 size에 맞게 a의 공간을 재할당 하면서 리스트에 있던 모든 요소를 복사
	 * 또한 a에 대한 타입을 알기위해 먼저 getClass().getComponentType()을 통해 객체가 어떤 유형의 배열인지를 파라미터에 넣고 배열의 크기를 설정해줒ㅁ
	 * 그런다음 a를 얕은 복사를 통한 Object[] 배열을 하나 만들어 해당 배열을 통해 데이터를 넣어준 다음 a를 반환
	 * 얕은 복사이기 때문에 result배열에 담으면 자동으로 a배열에도 영향을 미치는 것을 활용한 방법
	 * 
	 * 반대로 파라미터로 들어오는 배열의 크기가 현재 ArrayList에 있는 array보다 크면 앞부분부터 array에 있던 요소만 복사해서 a배열에 넣고 반환
	 */
	
	/*
	 * sort()
	 * ArrayList에서는 따로 만들지 않음(ArrayList자체가 내부에서 Object[] 배열을 사용하고 있기 때문에 Arrays.sort()를 해주면 됨)
	 * 
	 * 객체 배열의 경우 Collections.sort()를 사용하게 되는데 이것도 내부에서 Arrays.sort()를 사용함
	 * 해당 리스트를 Object[] 배열로 변환시켜 Arrays.sort()를 통해 정렬한 뒤, 정렬된 데이터를 다시 리스트의 노드에서 셋팅을 해주는 것
	 * 
	 * 만약 래퍼클래스 타입(Integer, String, Double...)이라면 따로 Comparator을 구현해주지 않아도 되지만, 사용자 클래스 경우는 사용자가 따로 해당 객체에 Comparable을 구현해주거나 Comparator를 구현해주어 파라미터로 넘겨주어야 함
	 * 
	 * 즉, 정렬 메소드를 만들 때, 두가지 경우를 생각
	 * 1. 객체에 Comparable이 구현되어있어 따로 파라미터로 Comparator를 넘겨주지 않는 경우
	 * 2. Comparator를 넘겨주어 정의 된 정렬 방식을 사용
	 */
	public void sort() {
		/*
		 * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬 방식을 사용
		 * 만약 구현되어 있지 않으면 cannot be cast to class java.lang.Comparable 에러가 발생
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬
		 */
		sort(null);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void sort(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		
		int i = 0;
		for(Node<E> x = head; x != null; x = x.next, i++) {
			x.data = (E) a[i];
		}
	}
	
}
