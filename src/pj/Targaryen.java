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

	/**
	 * 
	 * @param x
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
		return super.showPj("Targaryen");
	}
}

/**
* 
*/
