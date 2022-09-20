package DataStructure;

import java.util.Arrays;

public class LinkedHashSet<E> implements Set<E> {
	/*
	 * 연결 해시 셋
	 * 입력 순서를 지킴
	 * HashSet을 구현했을 때, 기본적으로 데이터를 해싱한 값을 Node[] table 배열의 인덱스로 활용하여 해당 인덱스에 데이터를 저장함
	 * 이는 데이터에 대해 빠르게 탐색할 수 있는 장점이 있지만, 데이터가 해싱된 값에 따라 저장되는 위치가 달라지기 때문에 결정적으로 순서가 보장되지 않음
	 * 이러한 단점을 보완하기 위해 LinkedList를 구현할때, prev와 next 변수를 두고, head와 tail 변수를 두어 노드들을 상호 연결을 해주듯 같은 원리로 해시셋 구현과 동일하게 테이블에 저장하되, 순서를 보장할 수 있도록 링크드리스트의 구현기능을 추가
	 * 간단하게 말하자면, 이전 해시셋 구현과 같되, 링크드리스트처럼 연결해주는데 필요한 변수만 추가됨
	 * 
	 * Node 구성
	 * 	key : HashSet에서는 data(value)자체가 key값이 됨
	 * 	hash : 해싱 된 key의 고유값
	 * 	next : 체이닝의 다음 노드를 가리키는 변수
	 * 	prevLink : 순서유지를 위한 link에서 현재 노드의 이전 노드를 가리키는 변수
	 * 	nextLink : 순서유지를 위한 link에서 현재 노드의 다음 노드를 가리키는 변수
	 */
	
	/*
	 * HashSet 구현과 메커니즘이 같음
	 * 노드 배열에 데이터를 저장하되 각 노드의 링크 변수로 순서에 맞게 노드들을 참조하는 방식으로 구현
	 */
	
	// 최소 기본 용적이며 2^n꼴 형대가 좋음
	private final static int DEFAULT_CAPACITY = 1 << 4;
	
	// 3/4이상 채워질 경우 resize하기 위한 임계값
	private static final float LOAD_FACTOR = 0.75f;
	
	LinkedHashNode<E>[] table;	// 요소의 정보를 담고 있는 Node를 저장할 Node타입 배열
	private int size;	// 요소의 개수
	
	// 들어온 순서를 유지할 linkedList
	private LinkedHashNode<E> head;	// 노드의 첫 부분
	private LinkedHashNode<E> tail;	// 노드의 마지막 부분
	
	@SuppressWarnings("unchecked")
	public LinkedHashSet() {
		table = (LinkedHashNode<E>[]) new LinkedHashNode[DEFAULT_CAPACITY];
		size = 0;
		head = null;
		tail = null;
 	}
	
	// 보조 해시 함수(상속 방지를 위해 private static final 선언)
	private static  final int hash(Object key) {
		int hash;
		if(key == null) return 0;
		else {
			// hashCode()의 경우 음수가 나올 수 있으므로 절댓갑을 통해 양수로 변환
			return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
		}
	}
	/*
	 * 보조 해시 함수
	 * 반드시 필요한것은 아님
	 * 요즘에는 hash 함수도 고른 분포를 갖을 수 있게 설계되어있기 때문에 큰 역할을 하진느 못하지만
	 * 원래는 최대한 해시충돌을 피하기 위해 보조해시함수를 써서 고른 분포를 할 수 있도록 하기 위한 함수
	 * java11의 구현유사하게 key의 hashCode() 절댓값과 그 값을 16비트 왼쪽으로 밀어낸 값하고의 xor값을 반환하도록 만듦
	 * (만약 나머지 연산(%)을 할 경우에는 hashCode()값이 음수가 나올때를 고려하여 절댓값을 써야하나, 비트 &(and)연산을 통해 할 경우 절댓값이 필요없음)
	 */
	
	/*
	 * add()
	 * HashSet의 add와 과정이 동일하고, 마지막 노드에 대해 링크하는 것이 추가되는 것 뿐
	 */
	private void linkLastNode(LinkedHashNode<E> o) {
		LinkedHashNode<E> last = tail;
		tail = o;
		
		if(last == null) head = o;
		else {
			o.prevLink = last;
			last.nextLink = o;
		}
	}
	
	@Override
	public boolean add(E e) {
		// key(e)에 대해 만들어두었던 보조해시함수의 값과 key(데이터 e)를 보냄
		return add(hash(e), e) == null;
	}
	
	private E add(int hash, E key) {
		int idx = hash % table.length;
		
		LinkedHashNode<E> newNode = new LinkedHashNode<E>(hash, key, null);	// 새로운 노드
		
		// table[idx]가 비어있을 경우 새 노드 생성
		if(table[idx] == null) {
			table[idx] = new LinkedHashNode<E>(hash, key, null);
		}
		/*
		 * table[idx]에 요소가 이미 존재할 경우(==해시충돌)
		 * 두가지 존재
		 * 1. 객체가 같은 경우
		 * 2. 객체는 같지 않으나 얻어진 index가 같은 경우
		 */
		else {
			LinkedHashNode<E> temp = table[idx];	// 현재 위치 노드
			LinkedHashNode<E> prev = null;	// temp 이전 노드
			
			// 첫 노드(table[idx])부터 탐색
			while(temp != null) {
				/*
				 * 만약 현재 노드가 객체가 같은 경우(hash값이 같으면서 key가 같을 경우)는 해시셋은 중복을 허용하지 않으므로 key를 반납(반환)
				 * (key가 같은 경우는 주소가 같거나 객체가 같은 경우가 존재)
				 */
				if((temp.hash == hash) && (temp.key == key || temp.key.equals(key)))  return key;
				
				prev = temp;
				temp = temp.next;	// 다음 노드로 이동
			}
			
			// 마지막 노드에 새 노드를 연결
			prev.next = new LinkedHashNode<E>(hash, key, null);
		}
		size++;
		
		linkLastNode(newNode);	// table에 저장이 끝났으면 해당 노드를 연결
		
		// 데이터의 개수가 현재 table 용적의 75%를 넘어가는 경우 용적을 늘려줌
		if(size >= LOAD_FACTOR * table.length) {
			resize();
		}
		
		return null;	// 정상적으로 추가되었을 경우 null 반환
	}
	
	/*
	 * resize()
	 * 용적을 조정할 대상이 table[] 노드 배열밖에 안되기 때문에 hashSet과 완전히 동일
	 * 
	 * 일정 이상 데이터가 차면 배열을 늘려주어 재배치를 해주는 것이 성능에서 우월함
	 * 용적 대비 데이터 개수의 비율을 부하율이라하고 이 기준점을 0.75 마지노선으로 잡음
	 * 
	 * 두가지 방식으로 구현
	 * - 가장 보편적인 방식 (현재 용적의 두배로 늘려줌)
	 * - 용적이 만약 2^n꼴로 표현될 경우 수학,논리연산을 응용
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		int newCapacity = table.length * 2;
		
		// 기존 테이블의 두배 용적으로 생성
		final LinkedHashNode<E>[] newTable = (LinkedHashNode<E>[]) new LinkedHashNode[newCapacity];
		
		// 0번째 인덱스부터 차례대로 순회
		for(int i=0; i<table.length; i++) {
			// 각 인덱스의 첫 번째 노드(head)
			LinkedHashNode<E> value = table[i];
			
			// 해당 값이 없을 경우 다음으로 넘어감
			if(value == null) continue;
			
			table[i] = null;	// gc
			
			LinkedHashNode<E> nextNode;	// 현재 노드의 다음 노드를 가리키는 변수
			
			// 현재 인덱스에 연결 된 노드들을 순회
			while(value != null) {
				int idx = value.hash % newCapacity;	// 새로운 인덱스를 구함
				
				// 새로 담을 인덱스에 요소(노드)가 존재할 경우 == 새로 담을 newTable에 인덱스값이 겹칠 경우(해시충돌)
				if(newTable[idx] != null) {
					LinkedHashNode<E> tail = newTable[idx];
					
					// 가장 마지막 노드로 감
					while(tail.next != null) {
						tail = tail.next;
					}
					
					/*
					 * 반드시 value가 참조하고 있는 다음 노드와의 연결을 끊어주여야 함
					 * 안하면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어버려 잘못된 참조가 발생할 수 있음
					 */
					nextNode = value.next;
					value.next = null;
					tail.next = value;
				}
				// 충돌되지 않는다면(=빈공간이라면 해당 노드를 추가)
				else {
					/*
					 * 반드시 value가 참조하고 있는 다음 노드와의 연결을 끊어주어야 함
					 * 안하면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어버려 잘못된 참조가 발생할 수 있음
					 */
					nextNode = value.next;
					value.next = null;
					newTable[idx] = value;
				}
				
				value = nextNode;	// 다음 노드로 이동
			}
		}
		
		table = null;
		table = newTable;	// 새로 생성한 테이블을 테이블 변수에 연결
	}
	
	/*
	 * remove()
	 * 삽입할 때 기존의 요소와 중복되는지를 검색했던 방식 그대로하면 됨
	 * table에 해당 노드를 삭제해주고 삭제된 노드와 연결된 링크를 끊고 새로 이어줄 뿐
	 * 
	 * 과정
	 * 1. key로 받은 데이터를 보조해시 함수에 돌려 hash값을 얻어냄
	 * 2. hash값을 토대로 index를 구한 뒤 원소를 추가
	 * 3. 해당 위치에 원소가 존재할 경우 해당 node부터 연결된 노드의 끝까지 탐색하면서 key가 같은지 비교
	 * 4. 해당 노드의 체인을 끊어줌(앞 노드와 node.next를 연결)
	 * 5. 연결한 노드를 순서를 유지하는 node link에도 삭제 노드의 link끊고 해당 노드의 이전 노드와 다음 노드를 연결해줌
	 */
	private void unLinkNode(LinkedHashNode<E> o) {
		LinkedHashNode<E> prevNode = o.prevLink;	// 삭제하는 노드의 이전 노드
		LinkedHashNode<E> nextNode = o.nextLink;	// 삭제하는 노드의 이후 노드
		
		// prevNode가 null이라는 것은 삭제한 노드가 head노드였다는 의미이므로 그 다음노드인 nextNode가 head가 됨
		if(prevNode == null) head = nextNode;
		else {
			prevNode.nextLink = nextNode;
			o.prevLink = null;
		}
		
		// nextNode가 null이라는 것은 삭제한 노드가 tail이라는 의미로 이전 노드가 tail이 됨
		if(nextNode == null) tail = prevNode;
		else {
			nextNode.prevLink = prevNode;
			o.nextLink = null;
		}
	}
	
	@Override
	public boolean remove(Object o) {
		// null이 아니라는 것은 노드가 삭제되었다는 의미
		return remove(hash(o), o) != null;
	}
	
	private Object remove(int hash, Object key) {
		int idx = hash % table.length;
		
		LinkedHashNode<E> node = table[idx];
		LinkedHashNode<E> removedNode = null;
		LinkedHashNode<E> prev = null;
		
		if(node == null) return null;
		
		while(node != null) {
			// 같은 노드를 찾았다면
			if(node.hash == hash && (node.key == key || node.key.equals(key))) {
				removedNode = node;	// 삭제되는 노드를 반환하기 위해 담아둠
				
				// 해당 노드의 이전 노드가 존재하지 않을 경우(= table에 첫번째 체인 노드인 경우)
				if(prev == null) {
					table[idx] = node.next;
				}
				// 그 외엔 이전 노드의 next를 삭제할 노드의 다음노드와 연결
				else {
					prev.next = node.next;
				}
				
				// table의 체인을 끊었으니 다음으로 순서를 유지하는 link를 끊음
				unLinkNode(node);
				node = null;
				
				size--;
				break;	// table에서 삭제되었으니 탐색 종료
			}
			
			prev = node;
			node = node.next;
		}
		
		return removedNode;
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
	public boolean contains(Object o) {
		int idx = hash(o) % table.length;
		LinkedHashNode<E> temp = table[idx];
		
		/*
		 * 같은 객체 내용이어도 hash값은 다를 수 있음
		 * 하지만, 내용이 같은지를 알아보고 싶을 때 쓰는 메소드이기에 hash값은 따로 비교 안해주어도 큰 지장 없음
		 * 단, o가 null인지는 확인
		 */
		while(temp != null) {
			if(o == temp.key || (o != null && temp.key.equals(o))) return true;
			
			temp = temp.next;
		}
		
		return false;
	}
	
	@Override
	public void clear() {
		if(table != null && size > 0) {
			for(int i=0; i<table.length; i++) {
				table[i] = null;
			}
			
			size = 0;
		}
		head = tail = null;	// 마지막에 반드시 head와 tail을 끊어주저야 gc 처리가 됨
	}
	
	@SuppressWarnings("unchecked")
	@Override
	
	public boolean equals(Object o) {
		// 만약 파라미터 객체가 현재 객체와 동일한 객체라면 true
		if(o == this) return true;
		
		// 만약 o 객체가 LinkedHashSet 계열이 아닌 경우 false
		if(!(o instanceof LinkedHashSet)) return false;
		
		LinkedHashSet<E> oSet;
		
		/*
		 * Object로부터 LinkedhashSet<E>로 캐스팅 되어야 비교가 가능하기 때문에 만약 캐스팅이 불가능할 경우 ClassCastException 이 발생
		 * 이 경우 false를 return 하도록 try-catch문을 이용
		 */
		try {
			// LinkedHashSet 타입으로 캐스팅
			oSet = (LinkedHashSet<E>) o;
			
			// 사이즈가 다르다는 것은 명백히 다른 객체
			if(oSet.size != size) return false;
			
			for(int i=0; i<oSet.table.length; i++) {
				LinkedHashNode<E> oTable = oSet.table[i];
				
				while(oTable != null) {
					// 서로 capacity가 다를 수 있기 때문에 인덱스에 연결 된 원소들을 비교하는 것이 아닌 contains로 존재 여부를 확인해야함
					if(!contains(oTable)) return false;
					
					oTable = oTable.next;
				}
			}
		}catch(ClassCastException e) {
			return false;
		}
		
		// 위 검사가 모두 완료되면 같은 객체임이 증명됨
		return true;
	}
	
	// 조금 더 많은 기능을 원할 경우 추가해주면 좋은 메소드
	/*
	 * toArray()
	 * - 아무런 인자 없이 현재 있는 LinkedHashSet요소들을 객체 배열(Object[])로 반환해주는 Object[]
	 * - LinkedHashSet을 이미 생성된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)
	 * 
	 * 차이
	 * LinkedHashSet<Integer> hashSet = new LinkedHasSet<>();
	 * 
	 * - get LinkedHashSet to array (using toArray())
	 * Object[] s1 = hashSet.toArray();
	 * 
	 * - get LinkedHashSet to array (using toArray(T[] a))
	 * Integer[] s2 = new Integer[10];
	 * s2 = hashSet.toArray(s2);
	 * 
	 * 1번의 장점이라면 링크드해시셋에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환됨
	 * 2번의 장점이라면 객체 클래스로 상속관계에 있는 타입이거나 래퍼 같이 데이터 타입을 유연하게 캐스팅할 여지가 있다는 것
	 * 싱글링크드리스트나 더블링크드리스트의 toArray() 처럼 요소 순회를 head를 통해 순서대로 데이터를 저장하여 반환하면 됨
	 */
	public Object[] toArray() {
		if(table == null || head == null) return null;
		
		Object[] ret = new Object[size];
		int index = 0;
		
		// 들어온 순서대로 head부터 tail까지 순회
		for(LinkedHashNode<E> x=head; x!=null; x=x.nextLink) {
			ret[index] = x.key;
			index++;
		}
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		Object[] copy = toArray();
		if(a.length < size) {
			return (T[]) Arrays.copyOf(copy, size, a.getClass());
		}
		
		System.arraycopy(copy, 0, a, 0, size);
		
		return a;
	}
}
