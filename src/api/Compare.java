package api;

/**
 * We are your waifu 
 * Ignacio Caro Cumplido 
 * Javier Ballesteros Moron
 * EC1
 * 2º
 */
public interface Compare<T> {
	int compareTo(T t);

	boolean isEqual(T t);

}
