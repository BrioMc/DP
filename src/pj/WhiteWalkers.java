package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */
import api.Queue;
import map.Map;

import java.util.ArrayList;

import actions.CloseDoor;
import actions.DoNothingKey;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class WhiteWalkers extends Pj {

	/** Pjs Queue */
	private Queue<Pj> pjsT;

	/**
	 * Method that allows the WhiteWalkers to kidnap another character and store
	 * it into the queue of this class
	 */
	private void takepj() {
		Map x = Map.getInstance();
		int j = this.getRoom() / x.getMap()[0].length;
		int y = this.getRoom() % x.getMap()[0].length;
		boolean take = false;
		int i = 0;
		while (!take && i < x.getMap()[j][y].nPj()) {
			i++;
			if (x.getMap()[j][y].nPj() != 0) {
				if (!(x.getMap()[j][y].checkPj() instanceof WhiteWalkers)) {
					take = true;
					pjsT.add(x.getMap()[j][y].takePj());
				}
			}
		}
	}

	/**
	 * Parametrized constructor of the class WhiteWalkers
	 * 
	 * @param name
	 *            : Name of the character
	 * @param M
	 *            : Tag of the character
	 * @param turn
	 *            : Initial turn of the character
	 * @param Room
	 *            : Starting room of the character
	 */
	public WhiteWalkers(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		super.keyAction = new DoNothingKey();
		super.doorAction = new CloseDoor();
		this.currTurn = turn;
		pjsT = new Queue<Pj>();
		whiteWalkersWays();
	}

	/**
	 * Method that calculates the WhiteWalkers' path
	 */
	private void whiteWalkersWays() {
		Map m = Map.getInstance();
		ArrayList<Integer> x = new ArrayList<>();

		x = shortestPath(m.surW(), m.norW());
		asigRute(x);
		x = shortestPath(m.norW(), m.norE());
		asigRute(x);
		x = shortestPath(m.norE(), m.surE());
		asigRute(x);
		x = shortestPath(m.surE(), m.surW());
		asigRute(x);

	}

	/**
	 * Protected method for the actions of the character
	 * 
	 * Complexity O(1)
	 * 
	 * 
	 */
	public void actionPj() {
		if (this.rutes.size() == 0) {
			whiteWalkersWays();
		}
		super.actionPj();
		takepj();
	}

	/**
	 * Public method that displays information of the character
	 * 
	 * Complexity O(n)
	 * 
	 * @return pj : String with the information's message
	 */
	public String toString() {

		String pj = "";
		pj = ("walker:" + getTag() + ":" + getRoom() + ":");
		if (currTurn > initialTurn) {
			pj += (currTurn - 1 + ":");
		} else {
			pj += (initialTurn + ":");

		}

		for (int i = 0; i < pjsT.size(); i++) {
			pj += (" " + pjsT.get(i).getTag());
		}
		pj += (")");
		return pj;
	}

}
