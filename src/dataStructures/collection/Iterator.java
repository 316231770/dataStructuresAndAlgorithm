package dataStructures.collection;

/**
 * 
     * @ClassName: Iterator
     * @Description: 迭代器,迭代集合的每个元素
     * @author tangjia
     * @date 2019年9月13日
 */
public interface Iterator<T> {
	boolean hasNext();
	T next();
	void remove();
}
