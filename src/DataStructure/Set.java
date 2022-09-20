package DataStructure;

public interface Set<E> {
	/*
	 * set
	 * 쉽게 말해서 집합이라고 보면 됨
	 * set혹은 set계열을 구현하는 클래스들을 다음과 같은 공통점이 있음
	 * 	1. 중복되는 요소(원소)를 허용하지 않음
	 * 	2. 저장 순서를 유지하지 않음(LinkedHashSet만 제외)
	 * 
	 * set은 크게 HashSet, LinkedHashSet, TreeSet으로 나뉨
	 * HastSet
	 * 	가장 기본적인 Set컬렉션의 클래스
	 * 	입력 순서를 보장하지 않고 순서도 마찬가지로 보장되지 않음
	 * 	가장 쉽게 이해할 수 있는 예로 중복확인
	 * 	hash에 의해 데이터의 위치를 특정시켜 해당 데이터를 빠르게 색인할 수 있게 만든 것
	 * LinkedHashSet
	 * 	중복은 허용하지 않으면서 순서를 보장받고 싶은 경우에 사용
	 * 	그나마 예로 들자면 페이지를 열때 만약 해당 페이지가 중복되는 경우 캐쉬는 다시 적재할 필요는 없지만
	 * 	새로운 페이지를 할당해야 할 경우 최근에 사용되지 않은 캐쉬를 비우고자 할 때 가장 오래된 캐쉬를 비우는 것이 현명할 것이다
	 * 	이를 LRU 알고리즘(Least Recently Used Algorithm)이라고 하는데, 이럴 때 입력된(저장된) 순서를 알아야 오래된 캐시를 비울 수 있음
	 * TreeSet
	 * 	저장 순서를 보장하지 않으며 중복 데이터 또한 넣지 못함
	 * 	다만 특별한 점이 있다면 SortedSet Interface의 이름을 보면 알 수 있듯 이를 구현한 TreeSet은 데이터의 가중치에 따른 순서대로 정렬되어 보장한다는 것
	 * 	우선순위큐도 입력한 순서대로가 아닌 값에 따라 정렬되어 큐에 담아짐
	 * 	마찬가지로 트리셋은 중복되지 않으면서 특정 규칙에 의해 정렬된 형태의 집합을 쓰고 싶을 때 사용
	 * 	정렬된 형태로 있다보니 특정 구간의 집합요소들을 탐색할 때 매우 유용
	 */
	
	/*
	 * @param <E>	 the type of elements in this set
	 */
	
	/*
	 * 지정된 요소가 set에 없는 경우 요소를 추가
	 * 
	 * @param e	집합에 추가될 요소
	 * @return {@code true} 만약 셋에 지정 요소가 포함되지 않아 정상적으로 추가 되었을 경우
	 * 			else, {@code false}
	 */
	boolean add(E e);
	
	/*
	 * 지정된 요소가 set에 있는 경우 해당 요소를 삭제
	 * 
	 * @param o	집합에서 삭제할 특정 요소
	 * @return {@code true} 만약 셋에 지정 요소가 포함되어 정상적으로 삭제되었을 경우,
	 * 			else, {@code false}
	 */
	boolean remove(Object o);
	
	/*
	 * 현재 집합에서 특정 요소가 포함되어있는지 여부를 반환
	 * 
	 * @param o 집합에서 찾을 특정 요소
	 * @return {@code true} set에 지정 요소가 포함되어 있을 경우,
	 * 			else {@code false}
	 */
	boolean contains(Object o);
	
	/*
	 * 지정된 객체가 현재 집합과 같은지 여부를 반환
	 * 
	 * @param o 집합과 비교할 객체
	 * @return {@code true} 비교할 집합과 동일한 경우,
	 * 			else, {@code false}
	 */
	boolean equals(Object o);
	
	/*
	 * 현재 집합이 빈 상태(요소가 없는 상태)인지 여부를 반환
	 * 
	 * @return {@code true} 집합이 비어있는 경우,
	 * 			else, {@code false}
	 */
	boolean isEmpty();
	
	/*
	 * 현재 집합의 요소의 개수를 반환
	 * 만약 들어있는 요소가 {@code Integer.MAX_VALUE}를 넘을 경우 {@code Integer.MAX_VALUE}를 반환
	 * 
	 * @return 집합에 들어있는 요소의 개수를 반
	 */
	int size();
	
	/*
	 * 집합의 모든 요소를 제거
	 * 이 작업을 수행하면 비어있는 집합이 됨
	 */
	void clear();
}
