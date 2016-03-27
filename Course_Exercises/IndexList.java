package algorithms.Course_Exercises;

public interface IndexList<E> {
	
	boolean isEmpty();
	
	/**
	 * 
	 * @return
	 */
	
	int size();
	
	/**
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	
	E set( int index, E element);
	
	/**
	 * Return the element at index
	 * @param index
	 * @return element at index
	 */
	
	E get( int index );
	
	E remove( int index );

}
