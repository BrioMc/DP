package pj;

import java.util.ArrayList;

import actions.OpenDoor;
import actions.PickKey;
import map.Dir;
import map.Map;

public class Targaryen extends Pj {

	/**
	 * Parametrized constructor of the class Targaryen
	 * 
	 * Complexity O(1)
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
	public Targaryen(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		super.keyAction = new PickKey();
		super.doorAction = new OpenDoor();
		targaryenWays();
	}

	/**
	 * Method that calculates the Targaryen's paths Complexity O(n)
	 */
	public void targaryenWays() {
		Map m = Map.getInstance();
		ArrayList<Integer> x = new ArrayList<>();
		x.add(this.getRoom());
		Dir d = Dir.S;
		int actRoom = this.getRoom();
		while (actRoom != m.getDRoom()) {
	
			actRoom = nextRommWallFollow(actRoom, d);
			d = whereLook(x.get(x.size() - 1), actRoom);
			x.add(actRoom);

		}
		asigRute(x);
	}

	/**
	 * Method which determines what direction is the Targaryen facing Complexity
	 * O(1)
	 * 
	 * @param past
	 *            : Last room where the Targaryen was
	 * @param post
	 *            :The next room in his path
	 * @return d : Direction that the Targaryen is facing
	 */
	private Dir whereLook(Integer past, Integer post) {

		Dir d = Dir.E;

		if (past == (post + 1)) {
			d = Dir.O;
		} else if (past == (post - 1)) {
			d = Dir.E;
		} else if (past == (post - Map.getInstance().getDimY())) {
			d = Dir.S;
		} else if (past == (post + Map.getInstance().getDimY())) {
			d = Dir.N;
		}

		return d;
	}

	/**
	 * Method which determines the access order to the adyacent rooms based on
	 * what direction is facing
	 * 
	 * Complexity O(1)
	 * 
	 * @param act
	 *            : Actual room
	 * @param d
	 *            : Direction that the Targaryen is facing
	 * @return x : room priority
	 */
	private int[] look(int act, Dir d) {
		int[] x = new int[4];
		// right,front,left, back
		if (d == Dir.S) {
			x[0] = act - 1;
			x[1] = act + Map.getInstance().getDimY();
			x[2] = act + 1;
			x[3] = act - Map.getInstance().getDimY();

		} else if (d == Dir.N) {
			x[0] = act + 1;
			x[1] = act - Map.getInstance().getDimY();
			x[2] = act - 1;
			x[3] = act + Map.getInstance().getDimY();

		} else if (d == Dir.O) {
			x[0] = act - Map.getInstance().getDimY();
			x[1] = act - 1;
			x[2] = act + Map.getInstance().getDimY();
			x[3] = act + 1;

		} else if (d == Dir.E) {
			x[0] = act + Map.getInstance().getDimY();
			x[1] = act + 1;
			x[2] = act - Map.getInstance().getDimY();
			x[3] = act - 1;

		}

		return x;
	}

	/**
	 * Method which determines which room to move into based on the Right Hand
	 * Rule.
	 * 
	 * Complexity O(1)
	 * 
	 * @param act
	 *            : Actual room
	 * @param d
	 *            : Direction that the Targaryen is facing
	 * @return x : Destination room
	 */
	private int nextRommWallFollow(int actRoom, Dir d) {
		Map m = Map.getInstance();
		int nextR = 0;
		int[] v = look(actRoom, d);
		// Check right from pj position
		if (m.getGraph().adyacente(actRoom, v[0])) {
			nextR = v[0];

		}
		// Check down from pj position
		else if (m.getGraph().adyacente(actRoom, v[1])) {
			nextR = v[1];

		}
		// Check left from pj position
		else if (m.getGraph().adyacente(actRoom, v[2])) {
			nextR = v[2];
		}
		// Check up from pj position
		else if (m.getGraph().adyacente(actRoom, v[3])) {
			nextR = v[3];
		}

		return nextR;
	}

	/**
	 * Public method that displays information of the character
	 * 
	 * @return pj : String with the information's message
	 */
	public String toString() {
		String pj = "";
		pj = ("targaryen:" + getTag() + ":" + getRoom() + ":");
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

	/**
	 * 
	 * @return
	 */

}

/**
* 
*/
