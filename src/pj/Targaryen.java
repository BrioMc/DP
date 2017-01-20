package pj;

import java.util.ArrayList;

import actions.PickKey;
import map.Dir;
import map.Map;
import map.Square;

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
		super.keyAction = new PickKey();
		targaryenWays();
		// TODO Auto-generated constructor stub
	}

	private void targaryenWays() {
		Map m = Map.getInstance();
		ArrayList<Integer> x = new ArrayList<>();
		x.add(this.room);
		Dir d = Dir.S;
		int actRoom = this.room;
		while (actRoom != m.getDRoom()) {

			actRoom = nextRommWallFollow(actRoom, d);
			x.add(actRoom);
		}
		asigRute(x);
	}

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

		} else if (d == Dir.W) {
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
	 * 
	 * @param actRoom
	 * @param d
	 * @return
	 */
	private int nextRommWallFollow(int actRoom, Dir d) {
		Map m = Map.getInstance();
		int nextR = 0;
		int[] v = look(actRoom, d);
		// Check right from pj position
		if (m.getGraph().adyacente(actRoom, v[0])) {
			nextR = v[0];
			if (d == Dir.N) {
				d = Dir.E;
			} else if (d == Dir.S) {
				d = Dir.W;

			} else if (d == Dir.W) {
				d = Dir.N;

			} else if (d == Dir.E) {
				d = Dir.S;

			}
		}
		// Check down from pj position
		else if (m.getGraph().adyacente(actRoom, v[1])) {
			nextR = v[1];

		}
		// Check left from pj position
		else if (m.getGraph().adyacente(actRoom, v[2])) {
			nextR = v[2];
			if (d == Dir.N) {
				d = Dir.W;
			} else if (d == Dir.S) {
				d = Dir.E;

			} else if (d == Dir.W) {
				d = Dir.S;

			} else if (d == Dir.E) {
				d = Dir.N;

			}
		}
		// Check up from pj position
		else if (m.getGraph().adyacente(actRoom, v[3])) {
			nextR = v[3];
			if (d == Dir.N) {
				d = Dir.S;
			} else if (d == Dir.S) {
				d = Dir.N;

			} else if (d == Dir.W) {
				d = Dir.E;

			} else if (d == Dir.E) {
				d = Dir.W;

			}
		}

		return nextR;
	}

	/**
	 * 
	 */
	public void actionPj() {

		super.actionPj();

	}

	/**
	 * 
	 * @param x
	 */
	protected boolean actionDoor(Map x) {
		boolean doorRoom = false;

		if (!x.getDoor().isOp()) {
			if (!keys.isEmpty()) {
				if (x.getDoor().open(keys.remove(keys.size() - 1))) {
					x.getMap()[this.getRoom() / x.getTMap()][this.getRoom() % x.getTMap()].removePj(this);
					x.getThrone().insertPj(this);

				}
			}
		} else {
			x.getMap()[this.getRoom() / x.getTMap()][this.getRoom() % x.getTMap()].removePj(this);
			x.getThrone().insertPj(this);

		}
		doorRoom = true;

		return doorRoom;

	}

	protected void keyAction(Square[][] map) {
		int x = this.room / map[0].length;
		int y = this.room % map[0].length;

		if (map[x][y].nkeys() > 0) {
			this.keys.add(map[x][y].removeKey());
		}
	}

	/**
	 * 
	 * @return
	 */
	public String showPj() {
		String pj = "";
		pj = ("(Targaryen:" + getTag() + ":" + getRoom() + ":" + currTurn + ":");

		for (int x = 0; x < this.keys.size(); x++) {
			pj += (keys.get(x).toString() + " ");

			pj += (")");

		}
		return pj;
	}
}

