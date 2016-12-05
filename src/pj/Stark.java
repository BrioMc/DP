package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */

public class Stark extends Pj {
	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Stark(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		this.houseTag = 'S';

		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public void actionPj() {
		super.actionPj(this.rutes, this.houseTag);
	}

	/**
	 * 
	 * @return
	 */
	public String showPj() {
		// TODO Auto-generated method stub
		return super.showPj("Stark");
	}

}
