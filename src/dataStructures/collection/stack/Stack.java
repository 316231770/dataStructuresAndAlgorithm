package dataStructures.collection.stack;

public interface Stack<E> {
	E push(E item);
	E pop();
	E peek();
	
	int size();
	boolean isEmpty();
}
