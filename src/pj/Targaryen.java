package pj;

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
	// TODO Auto-generated constructor stub
}

/**
* 
*/
