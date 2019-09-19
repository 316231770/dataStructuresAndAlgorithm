 package dataStructures.collection.list;

import dataStructures.collection.Iterator;

/**
 * 
     * @ClassName: MyArrayList
     * @Description: 表集合类,数组方式实现(元素可以重复,可以为空，
     * 随即访问快,插入和删除元素速度慢,容量满时需要扩容,
     * 涉及到数组元素的复制,效率很低)
     * @author tangjia
     * @date 2019年9月13日
 */
public class MyArrayList<AnyType> extends AbstractList<AnyType>
{
    private static final int DEFAULT_CAPACITY = 10;
    private AnyType [ ] theItems;
    private int theSize;
    /**
     * Construct an empty ArrayList.
     */
    public MyArrayList( )
    {
        doClear( );
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * Returns true if this collection is empty.
     * @return true if this collection is empty.
     */ 
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        return theItems[ idx ];    
    }
        
    /**
     * 替换索引为idx的元素为newVal
     */
    public AnyType set( int idx, AnyType newVal )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        AnyType old = theItems[ idx ];    
        theItems[ idx ] = newVal;
        
        return old;    
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity( int newCapacity )
    {
        if( newCapacity < theSize )
            return;

        AnyType [ ] old = theItems;
        theItems = (AnyType []) new Object[ newCapacity ];
        for( int i = 0; i < size( ); i++ )
            theItems[ i ] = old[ i ];
    }
    
    /**
     * 数组链表末尾添加元素
     */
    public boolean add( AnyType x )
    {
    add( size( ), x );
        return true;            
    }
    
    /**
     * 指定索引位置idx增加元素x,其余元素向后移动
     */
    public void add( int idx, AnyType x )
    {
        if( theItems.length == size( ) )
            ensureCapacity( size( ) * 2 + 1 );

        for( int i = theSize; i > idx; i-- )
            theItems[ i ] = theItems[ i - 1 ];

        theItems[ idx ] = x;
        theSize++;  
    }
      
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public void remove( int idx )
    {
        for( int i = idx; i < size( ) - 1; i++ )
            theItems[ i ] = theItems[ i + 1 ];
        theSize--;    
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void clear( )
    {
        doClear( );
    }
    
    private void doClear( )
    {
        theSize = 0;
        ensureCapacity( DEFAULT_CAPACITY );
    }
    
    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public Iterator<AnyType> iterator( )
    {
        return new ArrayListIterator( );
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
            StringBuilder sb = new StringBuilder( "[ " );
            Iterator<AnyType> it=MyArrayList.this.iterator( );
            while (it.hasNext()) {
            	AnyType e = (AnyType) it.next();
            	sb.append( e + " " );
			}
            sb.append( "]" );
            return new String( sb );
    }
    
    /**
     * 
         * @ClassName: ArrayListIterator
         * @Description: 数组链表的迭代器实现(向后迭代,可删除)
         * @author tangjia
         * @date 2019年9月14日
     */
    private class ArrayListIterator implements Iterator<AnyType>
    {
    	//当前待扫描的索引位置
        private int current = 0;
        private boolean okToRemove = false;
        
        public int getCurrent() {
			return current;
		}
		public void setCurrent(int current) {
			this.current = current;
		}
		public boolean isOkToRemove() {
			return okToRemove;
		}
		public void setOkToRemove(boolean okToRemove) {
			this.okToRemove = okToRemove;
		}
		public boolean hasNext( )
        {
            return current < size( );
        }
        public AnyType next( )
        {
            if( !hasNext( ) ) 
                throw new java.util.NoSuchElementException( ); 
            okToRemove = true;    
            return theItems[ current++ ];
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
            MyArrayList.this.remove( --current );
            okToRemove = false;
        }
    }
    
    /**
     * 
         * @ClassName: ArrayListListIterator
         * @Description: 数组链表的表迭代器实现(向前后迭代,可删除,添加,替换)
         * @author tangjia
         * @date 2019年9月14日
     */
    private class ArrayListListIterator extends ArrayListIterator implements ListIterator<AnyType>
    {
    	//flag标志位0表示迭代器没移动，1表示像后移动，2表示像前移动
    	private int flag=0;
    	
		@Override
		public boolean hasPrevious() {
			return getCurrent() >= 0;
		}

		@Override
		public AnyType previous() {
			if( !hasPrevious( ) ) 
                throw new java.util.NoSuchElementException( );
			setOkToRemove(true);
			AnyType type=theItems[getCurrent()];
			int newCurrent=getCurrent()-1;
			setCurrent(newCurrent);
			flag=2;
            return type;
		}

		@Override
		public AnyType next() {
			AnyType type=super.next();
			flag=1;
			return type;
		}
		/**
		 * 在指针越过的索引位置,增加一个元素
		 */
		@Override
		public void add(AnyType x) {
			MyArrayList.this.add(getCurrent()-1, x);
		}
		/**
		 * 在指针越过的索引位置,替换一个元素
		 */
		@Override
		public void set(AnyType x) {
			MyArrayList.this.set(getCurrent()-1, x);
		}

		@Override
		public void remove() {
			if( !isOkToRemove() )
                throw new IllegalStateException( );
			if (flag == 1) {
				super.remove();
			}else if (flag == 2) {
				int newCurrent=getCurrent()+1;
				MyArrayList.this.remove( newCurrent );
	            setOkToRemove(false);
			}
		}
    }
    
	@Override
	public ListIterator<AnyType> listIterator() {
		return listIterator(IterMod.BEGIN_FIRST);
	}

	@Override
	public ListIterator<AnyType> listIterator(IterMod iterMod) {
		ArrayListListIterator listIterator=new ArrayListListIterator();
		if (IterMod.BEGIN_FIRST == iterMod) {
			listIterator.setCurrent(0);
		}else if (IterMod.BEGIN_LAST == iterMod) {
			listIterator.setCurrent(size()-1);
		}
		return listIterator;
	}
	
    public static void main( String [ ] args )
    {
        MyArrayList<Integer> lst = new MyArrayList<>( );

        for( int i = 0; i < 10; i++ ){
        	lst.add( i );
        	lst.add( i );
        	lst.add( null );
        }
                
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );
        System.out.println( lst.contains(22) );
    }

}
