package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */
import java.util.ArrayList;

import api.Compare;
import door.Key;
import map.Dir;
import map.Map;
import map.Square;

public abstract class Pj implements Compare<Pj> {
	/** Pj Identifier */
	protected Integer id;
	/** Pj Name */
	protected String name;
	/** Turn when pj spawns. */
	protected int turnInitial;
	/** Room in which the player is */
	protected int room;
	/** Movements list */
	protected Dir[] rutes;
	/** Tag Identifier */
	protected char tag;
	/** List of keys that pj has */
	protected ArrayList<Key> keys;
	/** Movement Flag */
	protected boolean move;
	/** Current turn */
	protected int currTurn;
	/** House tag */
	protected char houseTag;

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param room
	 */
	protected Pj(String name, char M, int turn, int room) {
		this.currTurn = 0;
		this.move = false;
		this.tag = M;
		this.name = name;
		this.room = room;
		this.turnInitial = turn;
		this.rutes = new Dir[200];
		this.keys = new ArrayList<Key>();
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
	public char getHTag() {
		return houseTag;
	}

	/**
	 * Public method for take pj's identifier
	 * 
	 * @return id
	 */
	public int getId() {
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
		return turnInitial;
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
	 * Public method for set pj's room
	 * 
	 * @param i
	 */
	public void setRoom(int i) {
		this.room = i;
	}

	/**
	 * 
	 */
	public void moveOn() {
		this.move = true;
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
	public void sumTurn() {
		this.currTurn++;
	}

	/**
	 * Public method for set array rutes
	 * 
	 * @param rutes
	 */
	public void setRoutes(Dir[] rutes) {
		this.rutes = rutes;
	}

	/**
	 * public method for watch pj's information
	 */
	protected void showPj(String house) {
		System.out.print("(" + house + ":" + getTag() + ":" + getRoom() + ":" + currTurn + ":");
		if (houseTag != 'W') {
			for (int x = 0; x < this.keys.size(); x++) {
				System.out.print(keys.get(x).toString() + " ");
			}
			System.out.println(")");
		}
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
	protected void move(Dir i, Square[][] map) {

		int x = this.room / map.length;
		int y = this.room % map[0].length;
		switch (i) {
		case S:
			if (x < map.length) {
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
			if (y < map[0].length) {
				map[x][y + 1].insertPj(this);
				map[x][y].removePj(this);
				setRoom(map[x][y + 1].getId());

			}
			break;
		}
	}

	/**
	 * 
	 * @param map
	 * @param c
	 */
	protected void keyAction(Square[][] map, char c) {
		int x = this.room / map.length;
		int y = this.room % map[0].length;

		switch (c) {
		case 'L': // Lannister loses keys
			if (this.keys.size() != 0) {
				if (this.room % 2 == 1) {

					map[x][y].insertKey(this.keys.get(this.keys.size() - 1));
					this.keys.remove(this.keys.get(this.keys.size() - 1));
				}
			}

			break;
		case 'T': // Targaryen and Stark picks up keys
		case 'S':
			if (map[x][y].nkeys() > 0) {
				this.keys.add(map[x][y].removeKey());
			}
			break;
		default:
			break;
		}

	}

	/**
	 * Method for action pj
	 * 
	 * @param x
	 * @param i
	 * @param c
	 */
	protected void actionPj(Dir[] i, char c) {
		Map x = Map.getInstance();
		if (x.getDRoom() == getRoom()) {
			if (!actionDoor(x, c)) {

			}
			if (c == 'W' || c == 'L') {
				move(i[this.currTurn - 1], x.getMap());
			}

		} else {
			move(i[this.currTurn - 1], x.getMap());
		}

		if (c != 'W') {
			keyAction(x.getMap(), c);
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
	private boolean actionDoor(Map x, char c) {
		boolean doorRoom = false;
		switch (c) {

		case 'W':
		case 'L':
			x.getDoor().closeDoor();

			break;
		case 'T':
		case 'S':
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
			break;
		default:
			break;
		}
		return doorRoom;

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

	public void resetT() {
		this.move = false;

	}

}
