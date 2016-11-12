package pj;

import api.Queue;

public class WhiteWalkers extends Pj {
	private Queue<Pj> pjs;

	/**
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

	public void showPj() {
		// TODO Auto-generated method stub

		super.showPj("WhiteWalkers");
		while (pjs.iterator().hasNext()) {
			System.out.print(" " + pjs.iterator().next().getTag());
		}
		System.out.println(")");
	}

}
