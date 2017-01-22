package pj;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import actions.OpenDoor;
import actions.PickKey;
import map.Map;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

public class Stark extends Pj {

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
	public Stark(String name, char M, int turn, int Room) {

		super(name, M, turn, Room);
		super.keyAction = new PickKey();
		super.doorAction = new OpenDoor();
		ArrayList<Integer> x = new ArrayList<>();
		starkWay(x, getRoom());
		asigRute(x);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method that calculates the Stark's path
	 * 
	 * @param x
	 *            : ArrayList of rooms
	 * @param actRoom
	 *            : Current room
	 * @return True if the method has found a possible solution, otherwise false
	 */
	private boolean starkWay(ArrayList<Integer> x, int actRoom) {

		x.add(actRoom);
		Map m = Map.getInstance();
		if (actRoom == m.getDRoom()) {
			return true;
		} else {
			Set<Integer> ady = new LinkedHashSet<Integer>();
			Map.getInstance().getGraph().adyacentes(actRoom, ady);

			for (Integer i : ady) {
				if (!x.contains(i)) {
					if (starkWay(x, i)) {
						return true;
					}
					x.remove(x.size() - 1);
				}
			}
		}

		return false;
	}

	/**
	 * Public method that displays information of the character
	 * 
	 * @return pj : String with the information's message
	 */
	public String toString() {
		String pj = "";
		pj = ("stark:" + getTag() + ":" + getRoom() + ":");
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
