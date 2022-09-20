package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Cloneable {
	/*
	 * Doubly LinkedList
	 * 단일 연결리스트와의 차이라면 이전 노드를 가리키는 변수가 추가됨
	 * 
	 * 단일 연결리스트에 비해 검색(색인)능력이 좋아짐
	 */
	
	private Node2<E> head;	// 노드의 첫 부분
	private Node2<E> tail;	// 노드의 마지막 부분
	private int size;	// 요소의 개수
	
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/*
	 * search()
	 * 특정 위치의 데이터를 삽입, 삭제, 검색하기 위해서는 next변수를 통해 특정 위치까지 찾아가야 할 일이 많기 때문에 먼저 구현
	 * 양방향 연결리스트이니, index의 값이 tail에 가까운지(=size에 가까운지) head에 가까운지(=0에 가까운지) 구분하여 어디서부터 시작할지 정하여 탐색
	 */
	private Node2<E> search(int index){
		// 범위 밖일 경우 예외
		if(index < 0 || size <= index) {
			throw new IndexOutOfBoundsException();
		}
		
		if(size / 2 < index) {
			// 뒤에서부터 검색
			Node2<E> x = tail;
			for(int i=size-1; i>index; i--) {
				x = x.prev;
			}
			return x;
		}else {
			// 앞에서부터 검색
			Node2<E> x = head;
			for(int i=0; i<index; i++) {
				x = x.next;
			}
			return x;
		}
	}
	
	/*
	 * add()
	 * - 가장 앞부분에 추가 - addFirst(E value)
	 * 		새로운 노드를 생성하고 새 노드의 레퍼런스 변수인 next가 head 노드를 가리키도록해주고
	 * 		기존의 head노드의 prev는 새 노드를 가리킨 뒤, head를 새 노드를 가리키도록 업데이트해주면 됨
	 * - 가장 마지막 부분에 추가(기본값) - addLast(E value)
	 * 		size가 0일 경우(=아무런 노드가 없는 경우)는 결국 처음으로 데이터를 추가한다는 뜻이므로 addFirst()호출
	 * - 특정 위치에 추가 - add(int index, E value)
	 * 		먼저 넣으려는 위치의 노드와 이전의 노드를 찾아야함
	 * 		이전 노드의 next 변수는 새 노드를 가리키도록하고, 새 노드는 prev와 next를 앞뒤 노드를 가리키도록 한 뒤
	 * 		마지막으로 다음 노드의 prev 변수는 새 노드를 가리키도록 함
	 */
	public void addFirst(E value) {
		Node2<E> newNode = new Node2<E>(value); 	// 새 노드 생성
		newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결
		
		/*
		 * head가 null이 아닐 경우에만 기존 head노드의 prev 변수가 새 노드를 가리키도록 함
		 * 이유는 기존 head 노드가 없는 경우(null)는 데이터가 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 됨(head.prev하면 NullPointException 발생)
		 */
		if(head != null) {
			head.prev = newNode;
		}
		
		head = newNode;
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
		 * 데이터가 한개(새 노드)밖에 없으므로 새 노드는 처음 시작하는 노드이자 마지막 노드
		 * 즉 tail = head
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
		Node2<E> newNode = new Node2<E>(value);	// 새 노드 생성
		
		if(size == 0) {	// 처음 넣는 노드일 경우 addFirst로 추가
			addFirst(value);
			return;
		}
		
		/*
		 * 마지막 노드(tail)의 다음 노드가 새 노드를 가리키도록하고 tail이 가리키는 노드를 새 노드로 바꿔줌
		 */
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		size++;
	}

	@Override
	public void add(int index, E value) {
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if(size < index || index < 0) {
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
		Node2<E> prev_Node = search(index-1);
		
		// 추가하려는 위치의 노드
		Node2<E> next_Node = prev_Node.next;
		
		// 추가하려는 노드
		Node2<E> newNode = new Node2<E>(value);
		
		// 링크 끊기
		prev_Node.next = null;
		next_Node.prev = null;
		
		// 링크 연결하기
		prev_Node.next = newNode;
		
		newNode.prev = prev_Node;
		newNode.next = next_Node;
		
		next_Node.prev = newNode;
		size++;
	}
	
	/*
	 * remove()
	 * - 가장 앞의 요소(head)를 삭제 - remove()
	 * 		head가 가리키는 요소만 없애주면 됨
	 * 		head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤 head를 다음 노드로 업데이트를 해줌
	 * 		삭제하려는 노드가 리스트에서의 유일한 노드였을 경우 해당 노드를 삭제하면 tail이 가리키는 노드또한 없어지게 됨
	 * 		이에 대해서도 정확하게 처리해야함
	 * - 특정 index의 요소를 삭제 - remove(int index)
	 * 		add(int index, E value)와 정반대로 구현
	 * 		삭제하려는 노드의 이전 노드의 next변수를 삭제하려는 노드의 다음노드를 가리키도록
	 * - 특정 요소를 삭제 - remove(Object value)
	 * 		특정 index의 요소를 삭제하는 메소드와 동일
	 * 		다만 고려해야할 점은 삭제하려는 요소가 존재하는지 염두
	 * 		삭제하려는 요소를 찾지 못하면 false를 반환, 찾았을 경우 remove(int index)와 동일
	 */
	public E remove() {
		Node2<E> headNode = head;
		
		if(headNode == null) {
			throw new NoSuchElementException();
		}
		
		// 삭제된 노드를 반환하기 위한 임시 변수
		E element = headNode.data;
		
		// head의 다음 노드
		Node2<E> nextNode = head.next;
		
		// head 노드의 데이터들을 모두 삭제
		head.data = null;
		head.next = null;
		
		/*
		 * head의 다음 노드가 null이 아닐 경우에만 prev 변수를 null로 없데이트 해주어야함
		 * 이유는 nextNode가 없는 경우(null)는 데이터가 아무것도 없던 상태였으므로 nextNode.prev를 하면 잘못된 참조가 됨
		 */
		if(nextNode != null) {
			nextNode.prev = null;
		}
		
		head = nextNode;
		size--;
		
		/*
		 * 삭제된 요소가 리스트의 유일한 요소였을 경우 그 요소는 head 이자 tail
		 * 삭제되면서 tail도 가리킬 요소가 없기 때문에 size가 0일경우 tail도 null로 변환
		 */
		if(size == 0) {
			tail = null;
		}
		
		return element;
	}

	@Override
	public E remove(int index) {
		if(size <= index || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// 삭제하려는 노드가 첫 번째 노드일 경우
		if(index == 0) {
			E element = head.data;
			remove();
			return element;
		}
		
		Node2<E> prevNode = search(index - 1);	// 삭제할 노드의 이전 노드
		Node2<E> removedNode = prevNode.next;	// 삭제할 노드
		Node2<E> nextNode = removedNode.next;	// 삭제할 노드의 다음 노드
		
		E element = removedNode.data;	// 삭제되는 노드의 데이터를 반환하기 위한 임시변수
		
		/*
		 * index == 0 일 때의 조건에서 이미 헤드노드의 삭제에 대한 분기가 있기 때문에 prevNode는 항상존재
		 * 
		 * 그러나 nextNode의 경우 null일 수 있기 때문에 (= 마지막 노드를 삭제하려는 경우)
		 * 이전처럼 반드시 검사를 해준 뒤, nextNode.prev에 접근해야함
		 */
		prevNode.next = null;
		removedNode.next = null;
		removedNode.prev = null;
		removedNode.data = null;
		
		if(nextNode != null) {
			nextNode.prev = null;
			nextNode.prev = prevNode;
			prevNode.next = nextNode;
		}else {
			/*
			 * nextNode가 null이라는 것은 마지막 노드를 삭제했다는 의미
			 * prevNode가 tail이 됨(연결 해줄 것이 없음)
			 */
			tail = prevNode;
		}
		
		size--;
		
		return element;
	}

	@Override
	public boolean remove(Object value) {
		Node2<E> prevNode = head;
		Node2<E> x = head;	// removedNode;
		
		// value와 일치하는 노드를 찾음
		for(; x!=null; x=x.next) {
			if(value.equals(x.data)) {
				break;
			}
			prevNode = x;
		}
		
		// 일치하는 요소가 없을 경우
		if(x == null) {
			return false;
		}
		
		// 삭제하려는 노드가 head일 경우
		if(x.equals(head)) {
			remove();
			return true;
		}else {
			// remove(int index)와 같은 메커니즘으로 삭제
			Node2<E> nextNode = x.next;
			
			prevNode.next = null;
			x.data = null;
			x.next = null;
			x.prev = null;
			
			if(nextNode != null) {
				nextNode.prev = null;
				nextNode.prev = prevNode;
				prevNode.next = nextNode;
			}else {
				tail = prevNode;
			}
			
			size--;
			
			return true;
		}
	}
	
	/*
	 * get()
	 * 해당 위치에 있는 요소를 반환
	 * search() 이용
	 */
	@Override
	public E get(int index) {
		return search(index).data;
	}
	
	/*
	 * set()
	 * 기존 인덱스에 위치한 데이터를 새로운 데이터로 교체
	 */
	@Override
	public void set(int index, E value) {
		Node2<E> replaceNode = search(index);
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
		
		for(Node2<E> x = head; x!=null; x=x.next) {
			if(value.equals(x.data)) {
				return index;
			}
			index++;
		}
		
		return -1;
	}
	
	public int lastIndexOf(Object o) {	// 역방향 탐색
		int index = size;
		
		for(Node2<E> x = tail; x!=null; x=x.prev) {
			index--;
			if(o.equals(x.data)) {
				return index;
			}
		}
		
		return -1;
	}
	
	/*
	 * contains()
	 * 요소가 존재하는지 여부
	 */
	@Override
	public boolean contains(Object value) {
		return indexOf(value) >= 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		for(Node2<E> x = head; x!=null;) {
			Node2<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x.prev = null;
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
		DoublyLinkedList<? super E> clone = (DoublyLinkedList<? super E>) super.clone();
		
		clone.head = null;
		clone.tail = null;
		clone.size = 0;
		
		for(Node2<E> x=head; x!=null; x=x.next) {
			clone.addLast(x.data);
		}
		
		return clone;
	}
	
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 있는 리스트를 객체배열(Object[])로 반환해주는 Object[]
	 * - 리스트를 이미 생성 된 다른 배열에 복사해주고자 할 때 쓰는 T[]
	 * 
	 * 차이점
	 * DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
	 * 
	 * - get list to array (using toArray())
	 * Object[] array1 = list.toArray();
	 * 
	 * - get list to array (using toArray(T[] a))
	 * Integer[] array2 = new Integer[10];
	 * array2 = list.toArray(array2);
	 * 
	 * 1번의 장점은 더블링크드리스트에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환
	 * 2번의 장점은 객체 클래스로 상속관계에 있는 타입이거나 데이터 타입을 유연하게 캐스팅 할 여지가 있음
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;
		for(Node2<E> x=head; x!=null; x=x.next) {
			array[index++] = (E) x.data;
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
		for(Node2<E> x=head; x!=null; x=x.next) {
			result[i++] = x.data;
		}
		return a;
	}
	
	/*
	 * sort()
	 * SinglyLinkedList와 동일
	 */
	public void sort() {
		/*
		 * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬 방식을 사용
		 * 만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable 에러발생
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면 Arras.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬
		 */
		sort(null);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void sort(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		
		int i = 0;
		for(Node2<E> x=head; x!=null; x=x.next, i++) {
			x.data = (E) a[i];
		}
	}
}
