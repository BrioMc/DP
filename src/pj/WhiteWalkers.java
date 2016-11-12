package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */
import api.Queue;

public class WhiteWalkers extends Pj {
	/** Pjs Queue */
	private Queue<Pj> pjs;

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public WhiteWalkers(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		pjs = new Queue<Pj>();
	}

	/**
	 * public method for watch pj's information
	 */
	public void showPj() {
		// TODO Auto-generated method stub

		super.showPj("WhiteWalkers");
		while (pjs.iterator().hasNext()) {
			System.out.print(" " + pjs.iterator().next().getTag());
		}
		System.out.println(")");
	}

}
