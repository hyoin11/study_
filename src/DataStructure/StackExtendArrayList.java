package DataStructure;

import java.util.EmptyStackException;

public class StackExtendArrayList<E> extends ArrayList<E> implements StackInterface<E> {
	/*
	 * ArrayList를 상속한 Stack
	 */
	
	// 초기 용적 할당 X
	public StackExtendArrayList() {
		super();
	}
	
	// 초기 용적 할당 O
	public StackExtendArrayList(int capacity) {
		super(capacity);
	}
	
	@Override
	public E push(E item) {
		addLast(item);	// ArrayList의 addLast()
		return item;
	}
	
	@Override
	public E pop() {
		int length = size();
		E obj = remove(length - 1);	// ArrayList의 remove()
		
		return obj;
	}
	
	@Override
	public E peek() {
		int length = size();
		if(length == 0) throw new EmptyStackException();
		
		E obj = get(length - 1); 	// ArrayList의 get()
		
		return obj;
	}
	
	@Override
	public int search(Object value) {
		int idx = lastIndexOf((E) value);	// ArrayList의 lastIndexOf()
		
		if(idx >= 0) return size() - idx;
		
		return -1;
	}
	
	@Override
	public boolean empty() {
		return size() == 0;
	}
}
