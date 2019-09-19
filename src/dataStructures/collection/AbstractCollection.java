package dataStructures.collection;

public abstract class AbstractCollection<T> implements Collection<T>{
	
	protected AbstractCollection() {
    }

	public abstract Iterator<T> iterator();

	public abstract int size();

	public boolean isEmpty() {
		return size() == 0;
	}

	public void clear() {
		Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
	}

	@Override
	public boolean contains(T t) {
		Iterator<T> it = iterator();
        if (t==null) {
            while (it.hasNext())
                if (it.next()==null)
                    return true;
        } else {
            while (it.hasNext())
                if (t.equals(it.next()))
                    return true;
        }
        return false;
	}

	@Override
	public boolean add(T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(T t) {
		Iterator<T> it = iterator();
        if (t==null) {
            while (it.hasNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (t.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
	}

	
}
