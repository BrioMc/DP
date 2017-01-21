package pj;

import java.util.ArrayList;

import actions.KeyAction;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

import api.Compare;
import door.Key;
import map.Dir;
import map.Map;
import map.Square;

public abstract class Pj implements Compare<Pj> {
	/** Pj Identifier */
	private Integer id;
	/** Pj Name */
	private String name;
	/** Room in which the player is */
	int room;
	/** Movements list */
	private ArrayList<Dir> rutes;
	/** Tag Identifier */
	private char tag;
	/** List of keys that pj has */
	public ArrayList<Key> keys;
	/** Movement Flag */
	private boolean move;
	/** Current turn */
	int currTurn;
	/** Type of key action */
	KeyAction keyAction;

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param room
	 */
	protected Pj(String name, char M, int turn, int room) {
		this.move = false;
		this.tag = M;
		this.name = name;
		this.room = room;
		this.currTurn = turn;
		this.rutes = new ArrayList<Dir>();
		this.keys = new ArrayList<Key>();
	}

	/**
	 * Public method for take pj's identifier
	 * 
	 * @return id
	 */
	private int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * Public method for take pj's name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Public method for take pj's initial turn
	 * 
	 * @return turn
	 */
	public int getTurn() {
		return currTurn;
	}

	/**
	 * Public method for set pj's room
	 * 
	 * @param i
	 */
	private void setRoom(int i) {
		this.room = i;
	}

	/**
	 * Public method for take pj's room
	 * 
	 * @return room
	 */
	public int getRoom() {
		return room;
	}

	/**
	 * Public method for take pj's tag
	 * 
	 * @return tag
	 */
	public char getTag() {
		return tag;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getMove() {
		// TODO Auto-generated method stub
		return move;
	}

	/**
	 * 
	 */
	public void moveOn() {
		this.move = true;
	}

	/**
	 * 
	 */
	public void sumTurn() {
		this.currTurn++;
	}

	/**
	 * 
	 */
	public void resetT() {
		this.move = false;

	}

	void asigRute(ArrayList<Integer> x) {
		Map m = Map.getInstance();
		while (!x.isEmpty()) {
			int room = x.get(0);
			x.remove(0);
			if (!x.isEmpty()) {
				// W
				if (room == (x.get(0) + 1)) {
					rutes.add(Dir.W);
				}
				// E
				else if (room == (x.get(0) - 1)) {
					rutes.add(Dir.E);

				}
				// S
				else if (room == (x.get(0) + m.getDimY())) {
					rutes.add(Dir.N);

				}
				// N
				else if (room == (x.get(0) - m.getDimY())) {
					rutes.add(Dir.S);

				}

			}
		}
	}

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	ArrayList<Integer> shortestPath(int origin, int destination) {
		Map m = Map.getInstance();
		ArrayList<Integer> ways = new ArrayList<Integer>();
		ways.add(origin);
		while (origin != destination) {
			origin = m.getGraph().next(origin, destination);
			ways.add(origin);
		}

		return ways;
	}

	/**
	 * Method for movement action, if can do movement, then delete from actual
	 * room and insert in
	 * 
	 * @param i
	 *            direction
	 * @param map
	 *            Map on which to move
	 */
	private void move(Dir i, Square[][] map) {

		int x = this.room / map[0].length;
		int y = this.room % map[0].length;
		switch (i) {
		case S:
			if (x < map.length - 1) {

				map[x + 1][y].insertPj(this);
				map[x][y].removePj(this);
				setRoom(map[x + 1][y].getId());
			}
			break;
		case W:
			if (y > 0) {
				map[x][y - 1].insertPj(this);
				map[x][y].removePj(this);
				setRoom(map[x][y - 1].getId());

			}
			break;
		case N:
			if (x > 0) {
				map[x - 1][y].insertPj(this);
				map[x][y].removePj(this);
				setRoom(map[x - 1][y].getId());

			}
			break;
		case E:
			if (y < map[0].length - 1) {
				map[x][y + 1].insertPj(this);
				map[x][y].removePj(this);
				setRoom(map[x][y + 1].getId());

			}
			break;
		}
	}

	/**
	 * Method for action door, can be open (O) or close (C)
	 * 
	 * @param x
	 *            Map
	 * @param c
	 *            identifier character of HouseTag
	 * @return True if door is open
	 */

	protected abstract boolean actionDoor(Map x);

	private void actionKey(Map x) {
		keyAction.keyAction(x.getMap(), this);
	}

	/**
	 * Method for action pj
	 * 
	 * @param x
	 * @param i
	 * @param c
	 */
	protected void actionPj() {
		Map x = Map.getInstance();
		if (x.getDRoom() == getRoom())
			actionDoor(x);
		if (!this.rutes.isEmpty()) {
			this.sumTurn();
			this.moveOn();
			move(this.rutes.get(0), x.getMap());
			if (this instanceof Lannister || this instanceof WhiteWalkers) {
				this.rutes.add(this.rutes.get(0));
			}
			this.rutes.remove(0);
		}
		actionKey(x);
	}

	/**
	 * public method for watch pj's information
	 */

	/**
	 * 
	 * @param house
	 * @return
	 */
	public String showRute() {
		String x = "(rute:" + this.getTag() + ":";
		for (int i = 0; i < this.rutes.size(); i++) {
			x += " " + rutes.get(i).toString();
		}
		x += ")\n";
		return x;
	}

	/**
	 * CompareTo
	 */
	public int compareTo(Pj t) {
		return (this.id.compareTo(t.getId()));
	}

	/**
	 * Equals
	 */
	public boolean isEqual(Pj t) {
		return (this.id.equals(t.getId()));
	}

}
