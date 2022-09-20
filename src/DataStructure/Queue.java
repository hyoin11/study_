package DataStructure;

public interface Queue<E> {
	/*
	 * Queue
	 * 선입선출(FIFO = First In First Out)
	 * 시간 순으로 어떤 작업 또는 데이터를 처리할 필요가 있는 것들은 큐의 성질을 갖고 있다고 보면 됨
	 * 대표적으로 알고리즘에서는 BFS(너비 우선 탐색)에 사용됨
	 * 
	 * 큐는 ArrayQueue, LinkedQueue, Deque, PriorityQueue에 의해 구현됨
	 */
	
	/*
	 * offer()
	 * 큐의 가장 마지막에 요소를 추가
	 * 
	 * @param e 큐에 추가할 요소
	 * @return 큐에 요소가 정상적으로 추가되었을 경우 true를 반
	 */
	boolean offer(E e);
	
	/*
	 * poll()
	 * 큐의 첫번째 요소를 삭제하고 삭제 된 요소를 반환
	 * 
	 * @return 큐의 삭제된 요소 반환
	 */
	E poll();
	
	/*
	 * peek()
	 * 큐의 첫번째 요소를 반환
	 */
	E peek();
}
