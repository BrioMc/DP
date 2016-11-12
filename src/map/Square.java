package map;

import java.util.ArrayList;
import pj.Pj;
import api.*;
import door.Key;

public class Square implements Compare<Square> {
	private Integer id;
	private ArrayList<Key> keys;
	private Queue<Pj> pjs;

	// Debe ser una cola (el primero que entra,el primero que sale)
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
	 * 
	 * @return
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