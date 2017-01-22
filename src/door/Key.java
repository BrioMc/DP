package door;

import api.Compare;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class Key implements Compare<Key> {
	/** Identifier of the key */
	private Integer id;

	/**
	 * Default constructor of the class Key
	 */
	public Key() {
		this.id = null;
	}

	/**
	 * Parametrized constructor of the class Key
	 * 
	 * @param id
	 *            : ID of the key
	 */
	public Key(Integer id) {
		this.id = id;
	}

	/**
	 * method that returns the key's ID
	 * 
	 * @return id : Key's ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method that convert's the key's ID into a string for display reasons.
	 * 
	 * @return Integer.toString(this.id) : String ready to be displayed.
	 */
	public String toString() {
		return Integer.toString(this.id);
	}

	/**
	 * Method implemented from the Compare interface
	 */
	public boolean isEqual(Key key) {
		return this.id.equals(key.getId());
	}

	/**
	 * Method implemented from the Compare interface
	 */
	public int compareTo(Key key) {
		return (this.id.compareTo(key.getId()));
	}
}
