package dataStructures.collection;

/**
 * 
     * @ClassName: Collection
     * @Description: 存储一组类型相同的对象的集合
     * @author tangjia
     * @date 2019年9月13日
 */
public interface Collection<T> extends Iterable<T>{

	int size();
	boolean isEmpty();
	void clear();
	boolean contains(T t);
	boolean add(T t);
	boolean remove(T t);
}
