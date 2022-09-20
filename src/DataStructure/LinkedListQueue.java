package DataStructure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E>, Cloneable {
	/*
	 * LinkedList 연결리스트를 기반으로 구현
	 * 연결리스트로 구현한 큐가 가장 대중적
	 * 배열로 구현한 큐의 경우 내부에서 Object[] 배열을 담고있고, 요소가 배열에 들어있는 양에 따라 용적을 줄이거나 늘려주어야하고,
	 * 큐를 선형적인 큐로 구현했을 경우 요소들이 뒤로 쏠리기 때문에 이러한 문제를 효율적으로 국복하고자 원형 형태로 구현하는데 이 구현이 고려해야할 점도 많고 조금 복잡함
	 * 하지만 연결리스트로 구현하게 될 경우 위와같은 단점들이 해결됨
	 * 각 데이터들을 노드 객체에 담고 노드 간 서로 연결해주기 때문에 배열처럼 요소 개수에 따라 늘려주거나 줄여줄 필요도 없고 삽입, 삭제 때는 연결 된 링크만 끊어주거나 이어주면 되기 때문에 관리면에서도편함
	 * 
	 * 구현 방식은 단일 연결리스트(단방향)와 거의 다를게 없음
	 */
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public LinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/*
	 * offer()
	 * 후방에 데이터를 추가
	 */
	@Override
	public boolean offer(E value) {
		Node<E> newNode = new Node<E>(value);
		
		if(size == 0) {
			// 비어있을 경우
			head = newNode;
		}else {
			// 그 외의 경우 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록
			tail.next = newNode;
		}
		
		// tail이 가리키는 노드를 새 노드로 바꿔줌
		tail = newNode;
		size++;
		
		return true;
	}
	
	/*
	 * poll()
	 * 가장 앞 위체이 있는 요소인 head를 삭제
	 * 중요한 점은 삭제할 요소가 없는 경우
	 * poll은 삭제할 요소가 없다면 null을 반환
	 */
	@Override
	public E poll() {
		// 삭제할 요소가 없는 경우 null을 반환
		if(size == 0) {
			return null;
		}
		
		// 삭제될 요소의 데이터를 반환하기 위한 임시 변수
		E element = head.data;
		
		// head 노드의 다음노드
		Node<E> nextNode = head.next;
		
		// head의 모든 데이터들을 삭제
		head.data = null;
		head.next = null;
		
		// head가 가리키는 노드를 삭제된 head노드의 다음 노드를 가리키도록 변경
		head = nextNode;
		size--;
		
		return element;
	}
	
	public E remove() {
		E element = poll();
		
		if(element == null) {
			throw new NoSuchElementException();
		}
		
		return element;
	}
	
	/*
	 * peek()
	 * 가장 앞에 있는 데이터를 삭제하지 않고 확인만 하고싶을 때
	 */
	@Override
	public E peek() {
		// 요소가 없을 경우 null을 반환
		if(size == 0) return null;
		
		return head.data;
	}
	
	public E element() {
		E element = peek();
		
		if(element == null) {
			throw new NoSuchElementException();
		}
		
		return element;
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
		 * head 데이터부터 x가 null이 될때까지 value랑 x의 데이터랑 같은지 비교
		 * 같을 경우 true 반환
		 */
		for(Node<E> x=head; x!=null; x=x.next) {
			if(value.equals(x.data)) return true;
		}
		
		return false;
	}
	
	public void clear() {
		for(Node<E> x=head; x!=null;) {
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
		}
		size = 0;
		head = tail = null;
	}
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 있는 큐의 요소들을 객체배열(Object[])로 반환
	 * - 큐를 이미 생성 된 다른 배열에 목사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
	 * 
	 * - get LinkedListQueue to array (using toArray())
	 * Object[] q1 = listQueue.toArray();
	 * 
	 * - get LinkedListQueue to array (using toArray(T[] a))
	 * Integer[] q2 = new Integer[10];
	 * q2 = listqueue.toArray(q2);
	 * 
	 * 1번의 장점이라면 큐에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환
	 * 2번의 장점은 객체 클래스로 상속관계에 있는 타입이거나 래퍼와 같이 데이터 타입을 유연하게 캐스팅할 여지가 있음
	 * 또한 큐에 원소가 5개 있고 q2 배열에 10개의 원소가 있다면 q2에 0-4 인덱스에 원소가 복사되고, 그 외의 원소에는 기존 q2배열에 초기화 되어있던 원소가 그대로 남음
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		int idx = 0;
		for(Node<E> x=head; x!=null; x=x.next) {
			array[idx++] = (E) x.data;
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if(a.length <size) {
			// Array.newInstance(컴포넌트 타입, 생성할 크기)
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		
		int i = 0;
		
		// 얕은 복사를 위한 s 배열
		Object[] result = a;
		for(Node<E> x=head; x!=null; x=x.next) {
			result[i++] = x.data;
		}
		
		return a;
	}
	
	/*
	 * clone()
	 * 깊은 복사를 하고싶을 때 사용
	 */
	@Override
	public Object clone() {
		// super.clone() 은 CloneNotSupportedException 예외를 처리해줘야함
		try {
			@SuppressWarnings("unchecked")
			LinkedListQueue<E> clone = (LinkedListQueue<E>) super.clone();	// 새로운 큐 객체 생성
			clone.head = null;
			clone.tail = null;
			clone.size = 0;
			
			// 내부까지 복사되는 것이 아니기 때문에 내부 데이터들을 모두 복사
			for(Node<E> x=head; x!=null; x=x.next) {
				clone.offer(x.data);
			}
			
			return clone;
		}catch(CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	
	/*
	 * sort()
	 * LinkedListQueue 경우 노드 객체들이 연결된 형태이기 때문에 정렬을 이용하고자 한다면 Object[] 배열로 변환해서 정렬하는 것이 가장 빠름
	 * - Comparable이 구현되어 있어 따로 파라미터로 Comparator를 넘겨주지 않는 경우
	 * - Comparator을 넘겨주어 정의 된 정렬 방식을 사용하는 경우
	 */
	public void sort() {
		/*
		 * Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된 정렬방식을 사용
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
		// 정렬 된 a 배열을 큐로 복사
		for(Node<E> x=head; x!=null; x=x.next, i++) {
			x.data = (E) a[i];
		}
	}
}
