package pj;

import door.Key;
import map.Map;

import java.util.ArrayList;

import actions.DropKey;
import actions.LannisterAndWW;
/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

public class Lannister extends Pj {

	/**
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Lannister(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		super.keyAction = new DropKey();
		super.movementType = new LannisterAndWW();
		Key k;
		for (int i = 0; i < 30; i++) {
			k = new Key(i);
			keys.add(k);
			if (i % 2 != 0) {
				keys.add(k);
			}
		}
		lannisterWays();
		// TODO Auto-generated constructor stub
	}

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
	 * 
	 */
	protected boolean actionDoor(Map x) {
		boolean doorRoom = false;

		if (this.room == x.getDRoom()) {
			x.getDoor().closeDoor();
			doorRoom = true;
		}

		return doorRoom;

	}

	public void actionPj() {

		super.actionPj();

	}

	/**
	 * 
	 * @return
	 */

	public String showPj() {
		String pj = "";
		pj = ("(Lannister:" + getTag() + ":" + getRoom() + ":" + currTurn + ":");

		for (int x = 0; x < this.keys.size(); x++) {
			pj += (keys.get(x).toString() + " ");

		}
		pj += (")");
		return pj;
	}
}
