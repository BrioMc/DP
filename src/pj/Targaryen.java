package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */
import map.Map;
import door.Key;

public class Targaryen extends Pj {

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Targaryen(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		this.houseTag = 'T';

		// TODO Auto-generated constructor stub
	}

	public void showPj() {
		// TODO Auto-generated method stub
		super.showPj("Targaryen");
	}

	/**
	 * 
	 * @param x
	 */
	public void actionPj() {
		super.actionPj(this.rutes, this.houseTag);

	}
}
