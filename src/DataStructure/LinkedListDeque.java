package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LinkedListDeque<E> implements Queue<E>, Cloneable {
	/*
	 * Deque는 Double-ended queue의 줄임말
	 * 양방향 연결리스트와 유사한 메커니즘
	 * 두 개의 종료 지점이 있다는 것. 양쪽 방향으로 삽입 삭제가 이루어 질 수 있음
	 * 
	 * 배열로 구현한 Deque와의 차이점
	 * 노드 단위로 구성 된 객체를 서로 연결하여 구성되어있음
	 */
	
	private Node2<E> head;	// 가장 앞에 있는 노드를 가리키는 변수
	private Node2<E> tail;	// 가장 뒤에 있는 노드를 가리키는 변수
	private int size;	// 요소(노드)의 개수
	
	public LinkedListDeque() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/*
	 * offer()
	 * 데이터를 추가
	 */
	public boolean offerFirst(E value) {
		Node2<E> newNode = new Node2<E>(value);	// 새 노드 생성
		newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결
		
		/*
		 * head가 null이 아닐 경우에만 기존 head노드의 prev 변수가 새 노드를 가리키도록 함
		 * 기존 head노드가 없는 경우(null)는 데이터가 아무것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 됨
		 */
		if(head != null) {
			head.prev = newNode;
		}
		
		head = newNode;	// head가 가리키는 노드가 새 노드를 가리키도록 함
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우 = 이전의 head가 null인 경우)
		 * 데이터가 한개(새 노드) 밖에 없으므로 새 노드는 처음 시작노드이자 마지막 노드임. 즉 tail = haed
		 */
		if(head.next == null) {
			tail = head;
		}
		
		return true;
	}
	
	@Override
	public boolean offer(E value) {
		return offerLast(value);
	}
	
	public boolean offerLast(E value) {
		// 데이터가 없을 경우 offerFirst()로 인자를 넘겨줌
		if(size == 0) {
			return offerFirst(value);
		}
		
		Node2<E> newNode = new Node2<E>(value);
		
		tail.next = newNode;	// tail이 가리키는 노드의 다음 노드를 새 노드를 가리키도록 연결
		newNode.prev = tail;	// 새 노드가 가리키는 이전노드 또한 tail이 가리키는 노드로 연결
		tail = newNode;	// tail이 가리키는 노드를 새 노드로 가리키도록 변경
		size++;
		
		return true;
	}
	
	/*
	 * poll()
	 * 요소를 삭제하고자 할 때
	 */
	@Override
	public E poll() {
		return pollFirst();
	}
	
	public E pollFirst() {
		if(size == 0) return null;
		
		E element = head.data;	// 반환하기 위한 데이터
		
		Node2<E> nextNode = head.next;	// head의 다음노드
		
		// head가 가리키는 모든 데이터들 삭제
		head.data = null;
		head.next = null;
		
		// 삭제하기 전 데이터가 두 개 이상 있을 경우(head와 tail이 같지 않을 경우)
		if(nextNode != null) nextNode.prev = null;
		
		head = null;
		head = nextNode;	// head가 가리키는 노드를 삭제한 노드의 다음 노드로 갱신
		size--;
		
		/*
		 * 삭제된 요소가 덱의 유일한 요소였을 경우 그 요소는 head이자 tail
		 * 삭제되면서 tail도 가리킬 요소가 없기 때문에 size가 0일 경우 tail도 null로 변환
		 */
		if(size == 0) tail = null;
		
		return element;
	}
	
	public E revmoe() {
		return removeFirst();
	}
	
	public E removeFirst() {
		E element = poll();
		
		if(element == null) throw new NoSuchElementException();
		
		return element;
	}
	
	public E pollLast() {
		if(size == 0) return null;
		
		E element = tail.data;	// 변환하기 위한 데이터
		
		Node2<E> prevNode = tail.prev;
		
		// tail이 가리키는 노드의 데이터와 링크 삭제
		tail.data = null;
		tail.prev = null;
		
		// 삭제하기 전 데이커가 두 개 이상 있을 경우(head와 tail이 같지 않을 경우)
		if(prevNode != null) {
			prevNode.next = null;
		}
		
		tail = null;
		tail = prevNode;
		size--;
		
		/*
		 * 삭제된 요소가 덱의 유일한 요소였을 경우 그 요소는 head이자 tail
		 * 삭제되면서 head도 가리킬 요소가 없기 때문에 size가 0일 경우 head도 null
		 */
		if(size == 0) {
			head = null;
		}
		
		return element;
	}
	
	public E removeLast() {
		E element = pollLast();
		
		if(element == null) throw new NoSuchElementException();
		
		return element;
	}
	
	/*
	 * peek()
	 * 데이터를 삭제하지 않고 확인만 하고싶을 때 사용
	 */
	@Override
	public E peek() {
		return peekFirst();
	}
	
	public E peekFirst() {
		// 요소기 없을 경우 null 반환
		if(size == 0) return null;
		
		return head.data;
	}
	
	public E peekLast() {
		// 요소가 없을 경우 null 반환
		if(size == 0) return null;
		
		return tail.data;
	}
	
	public E element() {
		return getFirst();
	}
	
	public E getFirst() {
		E item = peek();
		
		// 앞이 원소가 null이라면(size = 0) 예외를 던짐
		if(item == null) throw new NoSuchElementException();
		
		return item;
	}
	
	public E getLast() {
		E item = peekLast();
		
		// 앞의 원소가 null이라면(size = 0) 예외를 던짐
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
		/*
		 * head 데이터부터 x가 null이 될 때까지 value랑 x의 데이터(x.data)랑 같은기 비교
		 */
		for(Node2<E> x=head; x!=null; x=x.next) {
			if(value.equals(x.data)) return true;
		}
		
		return false;
	}
	
	public void clear() {
		for(Node2<E> x=head; x!=null;) {
			Node2<E> next = x.next;
			
			x.data = null;
			x.next = null;
			x.prev = null;
			
			x = next;
		}
		size = 0;
		head = tail = null;
	}
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 있는 덱의 요소들을 객체배열(Object[])로 반환
	 * - 덱을 이미 생성 된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
	 * 
	 * - get LinkedListDeque to array (using toArray())
	 * Object[] de1 = linkedListDeque.toArray();
	 * 
	 * - get LinkedListDeque to array (using toArray(T[] a))
	 * Integer[] dq2 = new Integer[10];
	 * dq2 = linkedListDeque.toArray(dq2);
	 * 
	 * 1번의 장점이라면 덱에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환
	 * 2번의 장점이라면 객체 클래스로 상속관계에 있는 타입이거나 래퍼같이 데이터 타입을 유연하게 캐스팅할 여지가 있다는 것
	 * 또한 덱의 원소가 5개 있고, dq2 배열에 10개의 원소가 있다면 dq2에 0-4 인덱스에 원소가 복사되고, 그 외의 원소는 기존 dq2배열에 초기화 되어있던 원소가 그대로 남음
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		int idx = 0;
		for(Node2<E> x=head; x!=null; x=x.next) {
			array[idx++] = (E) x.data;
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length < size) {
			// Arrays.newInstance(컴포넌트 타입, 생성할 크기)
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
	 * clone()
	 * 깊은 복제를 하고싶을 때 사용
	 */
	@Override
	public Object clone() {
		// super.colne()은 CloneNotSupportedException 예외를 처리해주어야함
		try {
			@SuppressWarnings("unchecked")
			LinkedListDeque<E> clone = (LinkedListDeque<E>) super.clone();	// 새로운 덱 객체 생성
			clone.head = null;
			clone.tail = null;
			clone.size = 0;
			
			// 내부까지 복사되는 것이 아니기 때문에 내부 데이터들을 모두 복사
			for(Node2<E> x=head; x!=null; x=x.next) {
				clone.offerLast(x.data);
			}
			return clone;
		}catch(CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
	/*
	 * sort()
	 * - 객체에 Comparable이 구현되어 있어 따로 파라미터로 Comparator를 넘겨주지 않는 경우
	 * - Comparator를 넘겨주어 정의 된 정렬 방식을 사용하는 경우
	 */
	public void sort() {
		/*
		 * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬 방식을 사용
		 * 만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable 에러가 발생
		 * 만약 구현되어있을 경우 null로 파라미터를 넘기면 Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬
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
