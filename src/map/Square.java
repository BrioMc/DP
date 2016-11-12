package map;

import java.util.ArrayList;
import pj.Pj;
import api.*;
import door.Key;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */
public class Square implements Compare<Square> {
	/** Square identif */
	private Integer id;
	/** Arraylist with keys */
	private ArrayList<Key> keys;
	/** Queue with pjs in this square */
	private Queue<Pj> pjs;

	/**
	 * 
	 * @param i
	 */
	public Square(int i) {
		id = i;
		keys = new ArrayList<Key>();
		pjs = new Queue<Pj>();
	}

	/**
 * 
 */
	public void showKeys() {
		System.out.print("(sala:" + getId() + ": ");
		for (int x = 0; x < this.keys.size(); x++) {
			System.out.print(keys.get(x).toString() + " ");
		}
		System.out.println(")");
	}

	/**
	 * Publiv method for take array size
	 * 
	 * @return array size
	 */
	public int nkeys() {
		return this.keys.size();
	}

	/**
	 * 
	 * @return
	 */
	public int nPj() {
		return this.pjs.size();
	}

	public Pj takePj() {
		return pjs.peek();
	}

	/**
	 * 
	 * @param kye
	 */
	public void insertKey(Key kye) {
		this.keys.add(kye);
	}

	/**
	 * 
	 * @param pj
	 */
	public void insertPj(Pj pj) {
		pjs.add(pj);
	}

	public void removePj(Pj pj) {
		pjs.remove(pj);
	}

	/**
 * 
 */
	public int compareTo(Square t) {
		// TODO Auto-generated method stub
		return this.id.compareTo(t.getId());
	}

	/**
 * 
 */
	public boolean isEqual(Square t) {
		// TODO Auto-generated method stub
		return this.id.equals(t.getId());
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}