package pj;

import map.Map;
import map.Dir;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

public class Lannister extends Pj {
	char houseTag= 'L';

	/**
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Lannister(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void actionPj(Map x, Dir i) {
		super.actionPj(x, Dir i,this.houseTag);

	}

}
