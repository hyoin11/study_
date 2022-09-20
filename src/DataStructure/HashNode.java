package DataStructure;

public class HashNode<E> {
	// hash와 key값은 변하지 않으므로 상수로 선언
	final int hash;
	final E key;
	HashNode<E> next;
	
	public HashNode(int hash, E key, HashNode<E> next) {
		this.hash = hash;
		this.key = key;
		this.next = next;
	}
}
