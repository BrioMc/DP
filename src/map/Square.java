package map;

import java.util.ArrayList;
import pj.Pj;
import api.*;
import door.Key;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */
public class Square implements Compare<Square> {
	/** Square identif */
	private Integer id;
	/** Arraylist with keys */
	private ArrayList<Key> keys;
	/** Queue with pjs in this square */
	private Queue<Pj> pjs;

	/**
	 * Constructor for the class Square
     * @param i is the ID we want to assign to the square
	 */
	public Square(int i) {
		id = i;
		keys = new ArrayList<Key>();
		pjs = new Queue<Pj>();
	}


	/**
     * Method which shows all the keys stored in the square arrayList
     */
	public void showKeys() {
		System.out.print("(square:" + getId() + ": ");
		for (int x = 0; x < this.keys.size(); x++) {
			System.out.print(keys.get(x).toString() + " ");
		}
		System.out.println(")");
	}

	/**
	 * Public method for getting array size
	 * 
	 * @return array size
	 */
	public int nkeys() {
		return this.keys.size();
	}

	/**
	 * Method to get the number of pjs inside the
     *
	 * @return the number of pjs inside the square
	 */
	public int nPj() {
		return this.pjs.size();
	}

	public Pj takePj() {
		return pjs.peek();
	}

	/**
	 * Method to insert a key into the square's arrayList
     *
	 * @param kye is the key to be inserted
	 */
	public void insertKey(Key kye) {
		this.keys.add(kye);
	}

	public Key removeKey(){
		return this.keys.remove(this.keys.size());
	}
	/**
	 * Method to insert a pj into the square's queue
     *
	 * @param pj is the pj to be inserted
	 */
	public void insertPj(Pj pj) {
		pjs.add(pj);
	}

	/**
     * Method to remove a character from the queue
     *
     * @param pj is the pj to be removed from the queue
     */
	public void removePj(Pj pj) {
		pjs.remove(pj);
	}

	/**
     * Compares two squares in a numerically way
     *
     *@param t is the square we want to compare to
     *@return {@code 0} if this {@code Square} equals the argument {@code Square} t ID,
     *        {@code 1} if this {@code Square} ID is greater than the argument {@code Square} t ID
     *        and {@code -1} if  this {@code Square} ID is lower than the argument {@code Square} t ID.
     */
	public int compareTo(Square t) {
		// TODO Auto-generated method stub
		return this.id.compareTo(t.getId());
	}

    /**
     *Compares two squares in a logic way
     *
     *@param t is the square we want to compare to
     *@return {@code true} if this {@code sqaure} equals the argument {@code square} t ID,
     *        Otherwise returns {@code false}.
     */
    public boolean isEqual(Square t) {
		// TODO Auto-generated method stub
		return this.id.equals(t.getId());
	}

	/**
	 * Method to get the square's ID
     *
	 * @return {@code int} the ID of this square
	 */
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}