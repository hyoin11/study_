package DataStructure;

public class LinkedHashNode<E> {
	final int hash;
	final E key;
	
	LinkedHashNode<E> next;	// for Separate Chaining
	
	LinkedHashNode<E> nextLink;	// for linked List(Set)
	LinkedHashNode<E> prevLink;	// for linked List(Set)
	
	public LinkedHashNode(int hash, E key, LinkedHashNode<E> next) {
		this.hash = hash;
		this.key = key;
		this.next = next;
		
		this.nextLink = null;
		this.prevLink = null;
	}
}
