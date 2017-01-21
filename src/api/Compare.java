package api;

/**
 * Group: 		We are your waifu 
 * Members:		Ignacio Caro Cumplido 
 *				Javier Ballesteros Moron 
 */
public interface Compare<T> {
		/**
	 * Method which allows to compare two objects of the same type
	 * @param t : Object to be compared
	 * @return 0 if this object equals the argument object,
	 *			less than 0 if it's lower and more than 0 if is greater; 
	 */
	int compareTo(T t);
	/**
	 * Compares two objects of the same type
	 * 
	 * @param t : Object to be compared
	 * @return True if both are equal, false if not.
	 */
	boolean isEqual(T t);

}
