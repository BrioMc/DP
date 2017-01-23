package map;

import java.util.ArrayList;

import api.Compare;
import api.Queue;
import door.Key;
import pj.Lannister;
import pj.Pj;
import pj.Stark;
import pj.Targaryen;
import pj.WhiteWalkers;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class Square implements Compare<Square> {
	/** Square identif */
	private Integer id;
	/** Arraylist with keys */
	private ArrayList<Key> keys;
	/** Queue with pjs in this square */
	private Queue<Pj> pjs;
	/** Integer with a mark for the square (used for Kruskal) */
	private Integer mark;
	/**
	 * Integer with the frequency of the square (Used on the key distribution)
	 */
	private Integer freq;

	/**
	 * Package-public constructor for the class Square
	 * 
	 * Complexity O(1)
	 * 
	 * @param i
	 *            : The ID we want to assign to the square
	 */
	public Square(int i) {
		this.id = i;
		this.freq = 0;
		this.keys = new ArrayList<Key>();
		this.pjs = new Queue<Pj>();
		this.mark = id;
	}

	/**
	 * Public method that gets the square's ID
	 * 
	 * Complexity O(1)
	 * 
	 * @return id : {@code int} the ID of this square
	 * 
	 */
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * Package-Protected method that sets a mark in the square
	 * 
	 * Complexity O(1)
	 * 
	 * @param mark
	 *            : Mark we want to put
	 * 
	 * 
	 */
	public void setMark(Integer mark) {
		this.mark = mark;
	}

	/**
	 * Package-Protected method that sets a mark in the square
	 * 
	 * Complexity O(1)
	 * 
	 * @return mark : The mark of the square
	 */
	Integer getMark() {
		return mark;
	}

	/**
	 * Public method that inserts a key into the square's arrayList in orden
	 * 
	 * Complexity O(n)
	 * 
	 * @param kye
	 *            : The key to be inserted
	 * 
	 */
	public void insertKey(Key kye) {
		int y = 0;
		boolean enct = false;
		if (!this.keys.isEmpty()) {
			while (y < this.keys.size() && !enct) {
				if (kye.getId() >= this.keys.get(y).getId()) {

					y++;
				} else {
					enct = true;
				}
			}
			this.keys.add(y, kye);
		} else {

			this.keys.add(kye);
		}

	}

	/**
	 * Public ethod that gets a key from the square and removes it from the
	 * square
	 * 
	 * Complexity O(1)
	 * 
	 * @return The key the character will pick
	 */
	public Key removeKey() {
		return this.keys.remove(0);
	}

	/**
	 * Package-Protected method that
	 * 
	 * Complexity O(1)
	 * 
	 * @return freq : Returns the frequency of the square
	 */
	public Integer getFreq() {
		return freq;
	}

	/**
	 * Package-Protected method that sets the frequency of the square
	 * 
	 * Complexity O(1)
	 * 
	 * @param freq
	 *            : Frequency we want to put
	 * 
	 * 
	 */
	public void setFreq(Integer freq) {
		this.freq = freq;
	}

	/**
	 * Package-Protected method that shows all the keys in the square
	 * 
	 * Complexity O(n)
	 * 
	 * @return all the keys in the square
	 */
	public String showKeys() {
		String m = "";
		m += ("(square:" + getId() + ":");
		for (int x = 0; x < this.keys.size(); x++) {
			m += (" " + keys.get(x).toString());
		}
		m += (")\n");
		return m;
	}

	/**
	 * Public method which inserts a character into the square's queue
	 * 
	 * Complexity O(1)
	 * 
	 * @param pj
	 *            : The character to be inserted
	 * 
	 */
	public void insertPj(Pj pj) {
		pjs.add(pj);
	}

	/**
	 * Public method which removes a character from the queue
	 * 
	 * Complexity O(1)
	 * 
	 * @param pj
	 *            : The character to be removed from the queue
	 * 
	 */
	public void removePj(Pj pj) {
		pjs.remove(pj);
	}

	/**
	 * Package-protected method that shows the characters in the map
	 * 
	 * Complexity O(n)
	 * 
	 * @return M : String with the info of the characters in the square
	 */
	public String showPj() {
		String m = "";
		for (int i = 0; i < pjs.size(); i++) {
			if (pjs.size() > 1 && i > 0) {
				m += "(";
			}
			if (pjs.get(i) instanceof Stark) {
				Stark stark = (Stark) pjs.get(i);
				m += stark.toString();
				m += "\n";
			} else {
				if (pjs.get(i) instanceof WhiteWalkers) {
					WhiteWalkers whiteWalkers = (WhiteWalkers) pjs.get(i);
					m += whiteWalkers.toString();
					m += "\n";
				} else {
					if (pjs.get(i) instanceof Targaryen) {
						Targaryen targaryen = (Targaryen) pjs.get(i);
						m += targaryen.toString();
						m += "\n";
					} else {
						if (pjs.get(i) instanceof Lannister) {
							Lannister lannister = (Lannister) pjs.get(i);
							m += lannister.toString();
							m += "\n";
						}
					}
				}
			}
		}
		return m;
	}

	/**
	 * Package-protected method that shows the characters path
	 * 
	 * Complexity O(n)
	 * 
	 * @return M : String with the info of the characters path in the square
	 */
	public String showPathPj() {
		String m = "";
		for (int i = 0; i < pjs.size(); i++) {

			if (pjs.get(i) instanceof Stark) {
				Stark stark = (Stark) pjs.get(i);
				m += stark.showPath();
			} else {
				if (pjs.get(i) instanceof WhiteWalkers) {
					WhiteWalkers whiteWalkers = (WhiteWalkers) pjs.get(i);
					m += whiteWalkers.showPath();
				} else {
					if (pjs.get(i) instanceof Targaryen) {
						Targaryen targaryen = (Targaryen) pjs.get(i);
						m += targaryen.showPath();
					} else {
						if (pjs.get(i) instanceof Lannister) {
							Lannister lannister = (Lannister) pjs.get(i);
							m += lannister.showPath();
						}
					}
				}
			}
		}
		return m;
	}

	/**
	 * Public method for getting array size
	 * 
	 * Complexity O(1)
	 * 
	 * @return key.size() : Number of keys in the array
	 */
	public int nkeys() {
		return this.keys.size();
	}

	/**
	 * Public method that returns the number of characters in the square
	 * Complexity O(1)
	 * 
	 * @return pjs.size() : Number of characters in the square
	 */
	public int nPj() {
		return this.pjs.size();
	}

	/**
	 * Public method that returns the first character in the queue
	 * 
	 * Complexity O(1)
	 * 
	 * @return psj.peek() : The first character in the queue
	 */
	public Pj checkPj() {
		return pjs.peek();
	}

	/**
	 * Public method that retrieves and removes the first character in the queue
	 * 
	 * Complexity O(1)
	 * 
	 * @return pjs.pollFirst() : Fist character in the queue
	 */
	public Pj takePj() {
		return pjs.pollFirst();
	}

	/**
	 * Method that processes the state of the square each turn
	 * 
	 * Complexity O(n)
	 * 
	 * @param i
	 *            : Current turn
	 * 
	 * 
	 */
	public void proccessT(int i) {
		int y = 0;
		while (y < pjs.size()) {

			if (pjs.get(y).getTurn() == i) {

				if (pjs.get(y) instanceof Stark) {
					Stark stark = (Stark) pjs.get(y);
					stark.actionPj();
				} else {
					if (pjs.get(y) instanceof WhiteWalkers) {

						WhiteWalkers whiteWalkers = (WhiteWalkers) pjs.get(y);
						whiteWalkers.actionPj();
					} else {
						if (pjs.get(y) instanceof Targaryen) {

							Targaryen targaryen = (Targaryen) pjs.get(y);
							targaryen.actionPj();
						} else {
							if (pjs.get(y) instanceof Lannister) {
								Lannister lannister = (Lannister) pjs.get(y);
								lannister.actionPj();
							}
						}
					}
				}
				y--;
			}
			y++;
		}
	}

	/**
	 * Implemented from the Compare Interface
	 */

	public int compareTo(Square t) {
		return this.id.compareTo(t.getId());
	}

	/**
	 * Implemented from the Compare Interface
	 */

	public boolean isEqual(Square t) {
		return this.id.equals(t.getId());
	}

}