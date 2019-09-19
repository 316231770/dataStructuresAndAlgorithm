package dataStructures.collection;

public interface Iterable<T> {
	/**
	 * 
	     * @Title: iterator
	     * @Description: 返回集合的迭代器
	     * @return Iterator<T> 返回类型 迭代器对象
	     * @throws
	 */
	Iterator<T> iterator();
}
