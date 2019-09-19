package dataStructures.collection.list;

import dataStructures.collection.AbstractCollection;

public abstract class AbstractList<T> extends AbstractCollection<T> implements List<T>{

    protected AbstractList() {
    }
    
	public abstract T get(int index);

	public T set(int index, T newVal) {
		throw new UnsupportedOperationException();
	}

	public abstract void add(int index, T x);

	public abstract void remove(int index);

	public abstract ListIterator<T> listIterator();
	
}
