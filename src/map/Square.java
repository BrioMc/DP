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
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */
public class Square implements Compare<Square> {
	/** Square identif */
	private Integer id;
	/** Arraylist with keys */
	private ArrayList<Key> keys;
	/** Queue with pjs in this square */
	private Queue<Pj> pjs;

	private Integer mark;

	/**
	 * <<<<<<< HEAD Constructor for the class Square
	 * 
	 * @param i
	 *            is the ID we want to assign to the square =======
	 * 
	 * @param i
	 *            >>>>>>> refs/remotes/origin/master
	 */
	public Square(int i) {
		id = i;
		keys = new ArrayList<Key>();
		pjs = new Queue<Pj>();
		mark = id;
	}

	/**
	 * <<<<<<< HEAD Method to get the square's ID
	 * 
	 * @return {@code int} the ID of this square =======
	 * 
	 * @return >>>>>>> refs/remotes/origin/master
	 */
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * 
	 * @param mark
	 */
	public void setMark(Integer mark) {
		this.mark = mark;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getMark() {
		return mark;
	}

	/**
	 * Method to insert a key into the square's arrayList
	 * 
	 * @param kye
	 *            is the key to be inserted =======
	 * @param kye
	 *            >>>>>>> refs/remotes/origin/master
	 */
	public void insertKey(Key kye) {
		this.keys.add(kye);
	}

	/**
	 * 
	 * @return
	 */
	public Key removeKey() {
		return this.keys.remove(this.keys.size() - 1);
	}

	/**
	 * Method which shows all the keys stored in the square arrayList
	 */

	public String showKeys() {
		String m = "";
		m += ("(square:" + getId() + ": ");
		for (int x = 0; x < this.keys.size(); x++) {
			m += (keys.get(x).toString() + " ");
		}
		m += (")\n");
		return m;
	}

	/**
	 * Method to insert a pj into the square's queue
	 * 
	 * @param pj
	 *            is the pj to be inserted ======= /**
	 * 
	 * @param pj
	 *            >>>>>>> refs/remotes/origin/master
	 */
	public void insertPj(Pj pj) {
		pjs.add(pj);
	}

	/**
	 * Method to remove a character from the queue
	 * 
	 * @param pj
	 *            is the pj to be removed from the queue
	 */

	public void removePj(Pj pj) {
		pjs.remove(pj);
	}

	/**
	 * 
	 * 
	 */
	public String showPj() {
		String m = "";
		for (int i = 0; i < pjs.size(); i++) {
			Pj temp = pjs.get(i);
			switch (temp.getHTag()) {
			case 'S':
				Stark stark = (Stark) pjs.get(i);
				m = stark.showPj();
				m += "\n";
				break;
			case 'W':
				WhiteWalkers whiteWalkers = (WhiteWalkers) pjs.get(i);
				m = whiteWalkers.showPj();
				m += "\n";
				break;
			case 'T':
				Targaryen targaryen = (Targaryen) pjs.get(i);
				m = targaryen.showPj();
				m += "\n";
				break;
			case 'L':
				Lannister lannister = (Lannister) pjs.get(i);
				m = lannister.showPj();
				m += "\n";
				break;

			}

		}
		return m;
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
	 * 
	 * @return
	 */

	public int nPj() {
		return this.pjs.size();
	}

	/**
	 * 
	 * @return
	 */
	public Pj checkPj() {
		return pjs.peek();
	}

	/**
	 * @return
	 */
	public Pj takePj() {
		return pjs.pollFirst();
	}

	/**
	 * 
	 */
	public void resetTurn() {
		for (int y = 0; y < pjs.size(); y++) {
			Pj temp = pjs.get(y);
			temp.resetT();
		}
	}

	/**
	 * 
	 * @param i
	 * @param m
	 */
	public void proccessT(int i) {
		for (int y = 0; y < pjs.size(); y++) {
			Pj temp = pjs.get(y);
			if (!temp.getMove() && temp.getTurn() <= i) {
				temp.sumTurn();
				temp.moveOn();
				switch (temp.getHTag()) {
				case 'S':
					Stark stark = (Stark) temp;
					stark.actionPj();
					break;
				case 'W':
					WhiteWalkers whiteWalkers = (WhiteWalkers) temp;
					whiteWalkers.actionPj();
					break;
				case 'T':
					Targaryen targaryen = (Targaryen) temp;
					targaryen.actionPj();
					break;
				case 'L':
					Lannister lannister = (Lannister) temp;
					lannister.actionPj();
					break;

				}

			}

		}
	}

	/**
	 * <<<<<<< HEAD Compares two squares in a numerically way
	 * 
	 * @param t
	 *            is the square we want to compare to
	 * @return {@code 0} if this {@code Square} equals the argument
	 *         {@code Square} t ID, {@code 1} if this {@code Square} ID is
	 *         greater than the argument {@code Square} t ID and {@code -1} if
	 *         this {@code Square} ID is lower than the argument {@code Square}
	 *         t ID.
	 */

	public int compareTo(Square t) {
		// TODO Auto-generated method stub
		return this.id.compareTo(t.getId());
	}

	/**
	 * <<<<<<< HEAD Compares two squares in a logic way
	 * 
	 * @param t
	 *            is the square we want to compare to
	 * @return {@code true} if this {@code sqaure} equals the argument
	 *         {@code square} t ID, Otherwise returns {@code false}.
	 */

	public boolean isEqual(Square t) {
		// TODO Auto-generated method stub
		return this.id.equals(t.getId());
	}

}