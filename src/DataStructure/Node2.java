package DataStructure;

public class Node2<E> {
	E data;
	Node2<E> next;	// 다음 노드 객체를 가리키는 래퍼런스 변수
	Node2<E> prev;	// 이전 노드 객체를 가리키는 래퍼런스 변수
	
	Node2(E data){
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}
