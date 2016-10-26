package door;

import api.Compare;

/**
 * We are your waifu 
 * Ignacio Caro Cumplido 
 * Javier Ballesteros Moron
 * EC1
 * 2º
 */
public class Key implements Compare<Key> {
	private Integer id;
/**
 * 
 */
	public Key() {
		this.id = null;
	}
/**
 * 
 * @param id
 */
	public Key(Integer id) {
		this.id = id;
	}
/**
 * 
 */
	public String toString() {
		return Integer.toString(this.id);
	}
/**
 * 
 */
	public boolean isEqual(Key key) {
		return this.id.equals(key.id);
	}
/**
 * 
 */
	public int compareTo(Key key) {
		return (this.id.compareTo(key.id));
	}
}
