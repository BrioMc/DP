package pj;

import java.util.ArrayList;

import actions.CloseDoor;
import actions.DropKey;
import door.Key;
import map.Map;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class Lannister extends Pj {

	/**
	 * Parametrized constructor of the class Lannister
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
	public Lannister(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		super.keyAction = new DropKey();
		super.doorAction = new CloseDoor();
		Key k;
		for (int i = 30; i >= 0; i--) {
			k = new Key(i);
			if (i % 2 != 0) {
				keys.add(k);
			}
		}
		lannisterWays();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method that calculates the Lannister's path
	 */
	private void lannisterWays() {
		Map m = Map.getInstance();
		ArrayList<Integer> x = new ArrayList<>();

		x = shortestPath(m.surE(), m.norE());
		asigRute(x);
		x = shortestPath(m.norE(), m.norW());
		asigRute(x);
		x = shortestPath(m.norW(), m.surW());
		asigRute(x);
		x = shortestPath(m.surW(), m.surE());
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
			lannisterWays();
		}

		super.actionPj();

	}

	/**
	 * Public method that displays information of the character
	 * 
	 * @return pj : String with the information's message
	 */
	public String toString() {
		String pj = "";
		pj = ("lannister:" + getTag() + ":" + getRoom() + ":");
		if (currTurn > initialTurn) {
			pj += (currTurn - 1 + ":");
		} else {
			pj += (initialTurn + ":");

		}

		for (int x = 0; x < this.keys.size(); x++) {
			pj += (" " + keys.get(x).toString());

		}
		pj += (")");
		return pj;
	}
}
