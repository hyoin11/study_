package DataStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

public class CollectionFramework<T> {
	/*
	 * 자료구조
	 * 일련의 일정 타입들의 데이터 모임 또는 관계를 나타낸 구성체
	 * 
	 * - 선형 자료구조(Linear Data Structure)
	 * 		리스트(List), 큐(Queue), 덱(Deque)
	 * 
	 * - 비선형 자료구조(Nonlinear Data Structure)
	 * 		그래프(Graph), 트리(Tree)
	 * 
	 * - 집합(Set)
	 */
	/*
	 * Java Collections FrameWork
	 * 일정 타입의 데이터들이 모여 쉽게 가공할 수 있도록 지원하는 자료구조들의 뼈대(기본 구조)
	 * 자바에서 제공하는 컬렉션은 크게 3가지 인터페이스로 나뉘어짐
	 * List, Queue, Set
	 * 
	 * List Interface
	 * 	선형 자료구조
	 * 	순서가 있는 데이터를 목록으로 이용할 수 있도록
	 * 	동적 크기를 가지며 배열처럼 사용
	 * 
	 * 	List를 구현하는 클래스
	 * 	1. ArrayList
	 * 		Object[] 배열을 사용하면서 내부 구현을 통해 동적으로 관리
	 * 		요소 접근에서는 탁월한 성능을 보이나, 중간의 요소가 삽입, 삭제가 일어나는 경우 그 뒤의 요소들은 한 칸씩 밀어야 하거나 당겨야 하기 때문에 삽입, 삭제에서는 비효율적인 모습을 보임
	 * 	2. LinkedList
	 * 		데이터와 주소로 이루어진 클래스를 만들어 서로 연결하는 방식
	 * 		데이터와 주소로 이루어진 클래스를 Node라고 하는데, 각 노드는 이전의 노드와 다음 노드를 연결하는 상식(이중 연결 리스트라고도 함)
	 * 		즉, 객체끼리 연결한 방식
	 *		요소를 검색해야 할 경우 처음 노드부터 찾으려는 노드가 나올 때 까지 연결된 노드들을 모두 방문해야한다는 점에서 성능이 떨어짐
	 *		해당 노드를 삭제, 삽입해야할 경우 해당 노드의 링크를 끊거나 연결만 해주면 되기 때문에 삽입, 삭제에서는 매우 좋은 효율을 보임
	 *	3. Vector
	 *		기본적으로 ArrayList와 거의 같다고 보면 됨
	 *		항상 동기화를 지원(여러 쓰레드가 동시에 데이터에 접근하려하면 순차적으로 처리하도록 함)
	 *		멀티 쓰레드에서는 안전하지만, 단일 쓰레드에서도 동기화를 하기 때문에 ArrayList에 비해 성능이 약간 느림
	 *	4. Stack
	 *		Last In First Out
	 *		가장 대표적인 예시로는 '뒤로가기', 새로운 페이지로 넘어갈 때마다 넘어가기 전 페이지를 스텍에 쌓고, 뒤로가기를 누른다면 가장 위에 있는 페이지부터 꺼내오는 방식
	 *		참고로 Stack의 경우 Vector클래스를 상속받고 있고, Vector에 있는 메소드를 이용하여 구현되고 있어 크게 다를 것이 없음
	 */
	
	// 방법 1
	ArrayList<T> arrayList = new ArrayList<>();
	LinkedList<T> linkedList = new LinkedList<>();
	Vector<T> vector = new Vector<>();
	Stack<T> stack = new Stack<>();
	
	// 방법 2
	List<T> arrayList2 = new ArrayList<>();
	List<T> linkedList2 = new LinkedList<>();
	List<T> vector2 = new Vector<>();
	List<T> stack2 = new Stack<>();
	
	// Stack은 Vector를 상속하기 때문에 아래와 같이 생성할 수 있음
	Vector<T> stack3 = new Stack<>();
//	public static <T> void main(String[] args) {
//		ArrayList<T> arr = new ArrayList<>();
//	}
	
	/*
	 * Queue Interface
	 * 	First In First Out
	 * 	가장 앞쪽에 있는 위치를 head라고 부르고, 가장 후위에 있는 위치를 tail이라고 부름
	 * 	Queue를 상속하고 있는 Deque이라는 인터페이스도 있음
	 * 	큐는 한쪽 방향으로만(단방향) 삽입 삭제가 가능한 반면, 덱은 Double ended Queue라는 의미로 양쪽에서 삽입삭제가 가능한 자료구조(큐에서 확장된 형태)
	 * 
	 * 	Queue/Deque를 구현하는 클래스
	 * 	1. LinkedList
	 * 		List를 구현하기도 하지만, 덱도 구현함
	 * 		Deque Interface는 Queue Interface를 상속받음
	 * 		즉, LinkedList는 사실상 List, Deque, Queue 용도로쓸 수 있음
	 * 		덱/큐를 링크드리스트처럼 노드 객체로 연결해서 관리하길 원한다면 링크드리스를 쓰면 됨
	 * 	2. ArrayDeque
	 * 		ArrayList처럼 Object[] 배열로 구현되어 있는 것
	 * 	3. PriorityQueue
	 * 		우선순위 큐
	 * 		데이터 우선순위에 기반하여 우선순위가 높은 데이터가 먼저 나오는 원리
	 * 		따로 정렬방식을 지정하지 않는다면 낮은 숫자가 높은 우선순위를 갖음
	 * 		쉽게 생각하면 sort() 메소드와 같은 순서로 데이터 우선순위를 갖음
	 * 		최댓값, 최솟값을 꺼내올 때 매우 유용
	 * 		다만, 사용자가 정의한 객체를 타입으로 쓸 경우 반드시 Comparator 또는 Comparable을 통해 정렬 방식을 구현해주어야 함
	 */
	
	Queue<T> queue = new LinkedList<>();
	Deque<T> deque = new LinkedList<>();
	ArrayDeque<T> arrayDeque = new ArrayDeque<>();
	PriorityQueue<T> priorityQueue = new PriorityQueue<>();
	
	Deque<T> arrayDeque2 = new ArrayDeque<>();
	Deque<T> linkedListDeque = new LinkedList<>();
	
	Queue<T> arrayDeque3 = new ArrayDeque<>();
	Queue<T> linkedListDeque2 = new LinkedList<>();
	Queue<T> priorityQueue2 = new PriorityQueue<>();
	
	/*
	 * Set
	 * 	말 그대로 집합
	 * 	데이터를 중복해서 저장할 수 없음
	 * 	입력 순서대로의 저장 순서를 보장하지 않음
	 * 	(다만 LinkedHashSet은 Set임에도 불구하고 입력 순서대로의 저장순서를 보장함. 그러나 데이터를 중복해서 저장할 수 없음)
	 * 	데이터 중복을 허용하고 싶지 않은데 입력 순서를 보장받고 싶다면 LinkedHashSet을 사용
	 * 	Queue와 유사하게 Set을 상속받고 있는 SortedSet Interface도 있음
	 * 
	 * 	Set/SortedSet를 구현하는 클래스
	 * 	1. HashSet
	 * 		입력 순서를 보장하지 않고, 순서도 보장하지 않음
	 * 		Hash에 의해 데이터의 위치를 특정시켜 해당 데이터를 빠르게 색인(search)할 수 있게 만듬
	 * 		hash 기능과 set 컬렉션이 합쳐진 것
	 * 		삽입, 삭제, 색인이 매우 빠름
	 * 	2. LinkedHashSet
	 * 		Link + Hash + Set이 결합된 형태
	 * 		중복은 허용하지 않으면서 순서를 보장
	 * 	3. TreeSet
	 * 		데이터의 가중치에 따른 순서대로 정렬되어 보장
	 * 		중복되지 않으면서 특정 규칙에 의해 정렬된 형태의 집합을 쓰고 싶을 때 사용
	 * 		특정 구간의 집합요소들을 탐색할 때 매우 유용
	 * 		(트리라는 자료구조 자체가 데이터를 일정 순서에 의해 정렬하는 구조. 거기에 더해진 것이 Set의 중복값 방지 자료구조)
	 */
	HashSet<T> hashSet = new HashSet<>();
	LinkedHashSet<T> linkedHashSet = new LinkedHashSet<>();
	TreeSet<T> treeSet = new TreeSet<>();
	
	SortedSet<T> sortedSet = new TreeSet<>();
	
	Set<T> hashSet2 = new HashSet<>();
	Set<T> linkedHashSet2 = new LinkedHashSet<>();
	Set<T> treeSet2 = new TreeSet<>();
}
