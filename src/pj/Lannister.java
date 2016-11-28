package pj;

import map.Map;
import door.Key;
import map.Dir;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */

public class Lannister extends Pj {
	/**
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Lannister(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		this.houseTag = 'L';
		Key k;
		for (int i = 0; i < 30; i++) {
			k = new Key(i);
			keys.add(k);
			if (i % 2 != 0) {
				keys.add(k);
			}
		}

		// TODO Auto-generated constructor stub
	}

	public void showPj() {
		// TODO Auto-generated method stub
		super.showPj("Lannister");
	}

	public void actionPj() {
		super.actionPj(this.rutes, this.houseTag);

	}

}
