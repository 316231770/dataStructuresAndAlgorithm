package dataStructures.collection.list;

import dataStructures.collection.Collection;

/**
 * 
     * @ClassName: List
     * @Description: 表集合类,能通过索引位置改变表的指定项
     * @author tangjia
     * @date 2019年9月13日
 */
public interface List<T> extends Collection<T>{
	T get(int index);
	T set(int index,T newVal);
	/**
	 * 
	     * @Title: add
	     * @Description: index位置植入x元素,其后的元素向后推移
	     * @param @param index 要增加的位置索引
	     * @param @param x 参数 要增加的位置元素
	     * @return void 返回类型
	     * @throws
	 */
	void add(int index,T x);
	void remove(int index);
	
	ListIterator<T> listIterator();
	ListIterator<T> listIterator(IterMod iterMod);
	/**
	 * 
	     * @ClassName: IterMod
	     * @Description: 从头或者从尾开始迭代
	     * @author tangjia
	     * @date 2019年9月14日
	 */
	enum IterMod{BEGIN_FIRST,BEGIN_LAST};
}
