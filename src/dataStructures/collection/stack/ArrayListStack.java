package dataStructures.collection.stack;

import dataStructures.collection.list.MyArrayList;

public class ArrayListStack<E> implements Stack<E> {
	
	private MyArrayList<E> arrayList;
	
	{
		arrayList=new MyArrayList<>();
	}

	@Override
	public synchronized E push(E item) {
		arrayList.add(item);
		return item;
	}

	@Override
	public synchronized E pop() {
		E e=arrayList.get(arrayList.size()-1);
		arrayList.remove(arrayList.size()-1);
		return e;
	}

	@Override
	public E peek() {
		return arrayList.get(arrayList.size()-1);
	}

	@Override
	public int size() {
		return arrayList.size();
	}

	@Override
	public boolean isEmpty() {
		return arrayList.isEmpty();
	}

	@Override
	public String toString() {
		return arrayList.toString();
	}
}
