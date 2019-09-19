package dataStructures.collection.stack;

import dataStructures.collection.list.List.IterMod;
import dataStructures.collection.list.ListIterator;
import dataStructures.collection.list.MyLinkedList;

public class LinkedListStack<E> implements Stack<E> {
	
	private MyLinkedList<E> linkedList;
	{
		linkedList=new MyLinkedList<>();
	}

	@Override
	public synchronized E push(E item) {
		linkedList.add(item);
		return item;
	}

	@Override
	public synchronized E pop() {
		E lastE=null;
		ListIterator<E> iter=linkedList.listIterator(IterMod.BEGIN_LAST);
		if (iter.hasPrevious()) {
			lastE=iter.previous();
			iter.remove();
		}
		iter=null;
		return lastE;
	}

	@Override
	public E peek() {
		E lastE=null;
		ListIterator<E> iter=linkedList.listIterator(IterMod.BEGIN_LAST);
		if (iter.hasPrevious()) {
			lastE=iter.previous();
		}
		iter=null;
		return lastE;
	}

	@Override
	public int size() {
		return linkedList.size();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public String toString() {
		return linkedList.toString();
	}
}
