package api;

/**
 * Group: 		We are your waifu 
 * Members:		Ignacio Caro Cumplido 
 *				Javier Ballesteros Moron 
 */
import java.util.LinkedList;

public class Queue<E> extends LinkedList<E> {

	private static final long serialVersionUID = 1L;

	/**
	 * Add element to Queue
	 */
	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		return super.add(arg0);
	}

	/**
	 * Remove if exist, the element take for param
	 */
	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return super.remove(arg0);
	}

	/**
	 * return queue size
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}

	/**
	 * Take first in
	 */
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return super.peek();
	}

	/**
	* 
	*/
	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return super.pollFirst();
	}

	/**
	 * return true if Queue is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}

}
