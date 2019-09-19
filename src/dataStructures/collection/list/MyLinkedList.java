package dataStructures.collection.list;

import dataStructures.collection.Iterator;
/**
 * 
     * @ClassName: MyLinkedList
     * @Description: 表集合类,链表方式实现(元素可以重复,可以为空
     * 随机访问基于迭代方式速度慢,增加或者删除速度快,无需扩容)
     * @author tangjia
     * @date 2019年9月14日
 */
public class MyLinkedList<AnyType> extends AbstractList<AnyType>
{
    private int theSize;
    //链表本身增加或删除节点的操作次数
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
    
    /**
     * 
         * @ClassName: Node
         * @Description: 双向链表的节点类
         * @author tangjia
         * @date 2019年9月14日
     */
    private static class Node<AnyType>
    {
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
        
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
    }
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    public void clear( )
    {
        doClear( );
    }
    
    /**
     * 
         * @Title: doClear
         * @Description: 构造开始和结束两个链表节点
         * @param  参数
         * @return void 返回类型
         * @throws
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * 链表尾部添加元素为x的节点
     */
    public boolean add( AnyType x )
    {
        /*add( size( ), x );*/ 
    	addBefore(this.endMarker, x);
        return true;         
    }
    
    /**
     * idx位置植入x元素,其后的元素向后推移
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     *     
         * @Title: addBefore
         * @Description: 某个节点p之前增加节点且元素为x
         * @param @param p
         * @param @param x 参数
         * @return void 返回类型
         * @throws
     */
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * 查找索引idx的节点元素
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * 更新索引为idx节点元素为newVal
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * 查找索引idx的节点
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     *     
         * @Title: getNode
         * @Description: 指定节点索引的低位lower和高位upper，查找索引idx的节点(索引从0开始)
         * @param @param idx
         * @param @param lower
         * @param @param upper
         * @param @return 参数
         * @return Node<AnyType> 返回类型
         * @throws
     */
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * 删除索引idx的节点
     */
    public void remove(int idx)
    {
    	remove( getNode( idx ) );
    }
    
    /**
     * 
         * @Title: remove
         * @Description: 链表中删除节点p，并返回节点元素
         * @param @param p
         * @param @return 参数
         * @return AnyType 返回类型
         * @throws
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );
        Iterator<AnyType> iter=MyLinkedList.this.iterator();
        while (iter.hasNext()) {
			AnyType e = (AnyType) iter.next();
			sb.append( e + " " );
		}
        sb.append( "]" );
        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }
    
/**
 * 
     * @ClassName: LinkedListIterator
     * @Description: 双向链表的迭代器(向后迭代且可删除)
     * @author tangjia
     * @date 2019年9月14日
 */
    private class LinkedListIterator implements Iterator<AnyType>
    {
    	//当前待扫描的节点位置
        protected Node<AnyType> current = beginMarker.next;
        //迭代器增加或删除节点的操作次数(防止出现多个迭代器同时迭代)
        protected int expectedModCount = modCount;
        protected boolean okToRemove = false;
        
        public Node<AnyType> getCurrent() {
			return current;
		}

		public void setCurrent(Node<AnyType> current) {
			this.current = current;
		}

		public int getExpectedModCount() {
			return expectedModCount;
		}

		public void setExpectedModCount(int expectedModCount) {
			this.expectedModCount = expectedModCount;
		}

		public boolean isOkToRemove() {
			return okToRemove;
		}

		public void setOkToRemove(boolean okToRemove) {
			this.okToRemove = okToRemove;
		}

		public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * 
         * @ClassName: LinkedListListIterator
         * @Description: 双向链表的表迭代器(向前向后迭代且可增加删除替换)
         * @author tangjia
         * @date 2019年9月14日
     */
    private class LinkedListListIterator extends LinkedListIterator implements ListIterator<AnyType>{

    	private IterMod mod;

		public LinkedListListIterator(IterMod mod) {
			this.mod = mod;
			if (IterMod.BEGIN_FIRST == mod) {
				setCurrent(beginMarker.next);
			}else if (IterMod.BEGIN_LAST == mod) {
				setCurrent(endMarker.prev);
			}
		}

		public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
            
            if (IterMod.BEGIN_FIRST == mod) {
            	MyLinkedList.this.remove( current.prev );
			}else if (IterMod.BEGIN_LAST == mod) {
				MyLinkedList.this.remove( current.next );
			}
            expectedModCount++;
            okToRemove = false;       
        }
    	
		@Override
		public boolean hasPrevious() {
			return getCurrent() != beginMarker;
		}

		@Override
		public AnyType previous() {
			if( modCount != getExpectedModCount() )
                throw new java.util.ConcurrentModificationException( );
            if( !hasPrevious( ) )
                throw new java.util.NoSuchElementException( ); 
            
            AnyType previousItem = current.data;
            current = current.prev;
            okToRemove = true;
            return previousItem;
		}

		/**
		 * 在指针越过的节点位置,增加一个元素为x的节点
		 */
		@Override
		public void add(AnyType x) {
			MyLinkedList.this.addBefore(getCurrent().next, x);
			setExpectedModCount(getExpectedModCount()+1);
		}

		/**
		 * 在指针越过的节点位置,替换节点的元素
		 */
		@Override
		public void set(AnyType x) {
			getCurrent().next.data=x;
		}
    }

	@Override
	public ListIterator<AnyType> listIterator() {
		return listIterator(IterMod.BEGIN_FIRST);
	}
	
	@Override
	public ListIterator<AnyType> listIterator(IterMod iterMod) {
		LinkedListListIterator listIterator=new LinkedListListIterator(iterMod);
		return listIterator;
	}
	
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

        Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
        }
    }

}
