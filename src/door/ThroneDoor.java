package door;

import java.io.BufferedWriter;
import java.io.IOException;

import api.Tree;

/**
 * We are your waifu 
 * Ignacio Caro Cumplido 
 * Javier Ballesteros Moron
 * EC1
 * 2�
 */

/**
 * 
 * Class ThroneDoor, with all it's essential attributes: *A flag as a state of
 * the door(open, close) *An array which is not modified and stores the reset
 * combination of the lock of the door in a Tree. *The tested keys in a Tree *An
 * Integer (depth) as a needed requerioment in order to open the door.
 * 
 */
public class ThroneDoor {
	private boolean isOpen;
	private Key[] comb;
	private Tree<Key> lock = new Tree<Key>();
	private Tree<Key> tested = new Tree<Key>();
	private int depth;

	/**
	 * Default constructor
	 */
	public ThroneDoor(int dp) {
		isOpen = false;
		depth = dp;
		lock = new Tree<Key>();
		lock = new Tree<Key>();
		comb = new Key[200];
	}

	/**
	 * Parametrized constructor with the depth and the Key's array as parameters
	 * 
	 * @param keys
	 *            {@code Key[]}
	 * @param depthn
	 *            * parametros.
	 */
	public ThroneDoor(Key[] keys, int depthn) {
		isOpen = false;
		depth = depthn;
		lock = new Tree<Key>();
		tested = new Tree<Key>();
		comb = keys;
		cfglock(0, keys.length - 1);
	}

	/**
	 * Method for setting the door up with the array that has been received
	 * previously.
	 * 
	 * @param ini
	 *            integer with the first value to take into account
	 * @param fin
	 *            integer with the last value to take into account
	 */

	private void cfglock(int start, int end) {
		int half = (start + end) / 2;
		lock.insert(comb[half]);
		// System.out.print(comb[mitad].toString() + ",");
		if (start < end) {
			cfglock(start, half - 1);
			cfglock(half + 1, end);
		}

	}

	/**
	 * Method for close the door with initial combination
	 * 
	 */
	public void closeDoor() {
		isOpen = false;
		lock = new Tree<Key>();
		cfglock(0, comb.length - 1);
	}

	/**
	 * Shows the state of the lock inOrden.
	 */
	public String showLock() {

		return lock.inOrden();

	}

	/**
	 * Shows the state of the tested keys inOrden.
	 */
	public String showTested() {
		return tested.inOrden();
	}

	/**
	 * Method which returns wether the door is open or closed open=true
	 * closed=false
	 * 
	 * @return state of the door
	 */
	public boolean isOp() {
		return isOpen;
	}

	// /**
	// * Method which checks the state of the door and, in case of being open,
	// * returns the corresponding message.
	// *
	// * @return state of the door
	// */
	// public boolean estaAbierta() {
	// return isOpen;
	//
	// }
	/**
	 * 
	 */
	public String showDoor() {
		String m = "";
		System.out.print("(Door");
		if (isOp()) {
			m += (":open:");
		} else {
			m += (":close:");
		}
		m += (this.depth + ":");
		m += showLock();
		m += (":");
		m += showTested();
		m += ("\n");
		return m;
	}

	/**
	 * 
	 * @param key
	 *            Key which is introduced with a value !=Null
	 * @return
	 */
	public boolean open(Key key) {
		if (!tested.belongs(key)) {
			if (lock.belongs(key)) {
				lock.remove(key);
				tested.insert(key);
				if (lock.nHojas() <= (lock.nNodos() - lock.nHojas()) && lock.depth() < this.depth) {
					isOpen = true;
				}
			} else {
				System.out.println("The key does not match with the door lock");
			}
		} else {
			System.out.println("This key has already been tested, it can't be repeated.");
		}
		return isOpen;
	}

}
