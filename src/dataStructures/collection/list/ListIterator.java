package dataStructures.collection.list;

import dataStructures.collection.Iterator;

/**
 * 
     * @ClassName: ListIterator
     * @Description: 表集合迭代器,可以向前迭代和增加,置换某项
     * @author tangjia
     * @date 2019年9月13日
 */
public interface ListIterator<T> extends Iterator<T>{
	
	boolean hasPrevious();
	T previous();
	
	void add(T x);
	void set(T x);
}
