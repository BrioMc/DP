package pj;

import java.util.ArrayList;

import actions.DoorAction;
import actions.KeyAction;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

import api.Compare;
import door.Key;
import map.Dir;
import map.Map;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */

public abstract class Pj implements Compare<Pj> {
	/** Pj Identifier */
	private Integer id;
	/** Pj Name */
	private String name;
	/** Room in which the player is */
	private int room;
	/** Movements list */
	protected ArrayList<Dir> rutes;
	/** Tag Identifier */
	private char tag;
	/** List of keys that pj has */
	protected ArrayList<Key> keys;
	/** Current turn */
	protected int currTurn;
	/** Initial turn */
	protected int initialTurn;
	/** Type of key action */
	protected KeyAction keyAction;
	/** Type of Door action */
	protected DoorAction doorAction;

	/**
	 * Parameterized constructor of the class Pj
	 * 
	 * Complexity O(1)
	 * 
	 * @param name
	 *            : name of the character {@code String}
	 * @param M
	 *            : Tag that will be displayed on the screen {@code char}
	 * @param turn
	 *            : Turn when the character spawns {@code int}
	 * @param room:
	 *            Room where the character is initially located {@code int}
	 */
	protected Pj(String name, char M, int turn, int room) {
		this.tag = M;
		this.name = name;
		this.room = room;
		this.initialTurn = turn;
		this.currTurn = turn;
		this.rutes = new ArrayList<Dir>();
		this.keys = new ArrayList<Key>();
	}

	/**
	 * Public method that obtains the pj's identifier
	 * 
	 * Complexity O(1)
	 * 
	 * @return id : ID of the character {@code int}
	 */
	private int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * Public method that obtains the pj's name
	 * 
	 * Complexity O(1)
	 * 
	 * @return name : name of the character {@code String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Public method that obtains the pj's current turn
	 * 
	 * Complexity O(1)
	 * 
	 * @return turn : current turn of the character {@code int}
	 */
	public int getTurn() {
		return currTurn;
	}

	/**
	 * 
	 * @param x
	 *            : key to add
	 */
	public void setKey(Key x) {
		keys.add(0, x);
	}

	/**
	 * 
	 * @return : first key in list
	 */
	public Key getKey() {
		return keys.remove(0);
	}

	/**
	 * 
	 * @return : list keys size
	 */
	public int numKeys() {
		return keys.size();
	}

	/**
	 * Public method which sets the room where the character is located
	 * 
	 * Complexity O(1)
	 * 
	 * @param i
	 *            : Room where we will put the character into {@code int}
	 * 
	 * 
	 */
	public void setRoom(int i) {
		this.room = i;
	}

	/**
	 * Public method which returs where is located the character
	 * 
	 * Complexity O(1)
	 * 
	 * @return room : Room where the character is {@code int}
	 */
	public int getRoom() {
		return room;
	}

	/**
	 * Public method that obtains the pj's tag
	 * 
	 * Complexity O(1)
	 * 
	 * @return tag : Tag that represents the character {@code int}
	 */
	public char getTag() {
		return tag;
	}

	/**
	 * Increases the character's turn
	 * 
	 * Complexity O(1)
	 * 
	 * 
	 */
	public void sumTurn() {
		this.currTurn++;
	}

	/**
	 * Protected method that establishes the character's paths
	 * 
	 * Complexity O(n)
	 * 
	 * @param x
	 *            : ArrayList with the predefined rooms where the character will
	 *            move into
	 * 
	 * 
	 */
	protected void asigRute(ArrayList<Integer> x) {
		Map m = Map.getInstance();
		while (!x.isEmpty()) {
			int room = x.get(0);
			x.remove(0);
			if (!x.isEmpty()) {
				// W
				if (room == (x.get(0) + 1)) {
					rutes.add(Dir.O);
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
	 * Movement Algorithm which consists on finding the shortest path between
	 * two rooms
	 * 
	 * Complexity O(1)
	 * 
	 * @param origin
	 *            : ID of the origin room
	 * @param destination
	 *            : ID of the destination room
	 * @return ways : ArrayList with the rooms in the path
	 */
	protected ArrayList<Integer> shortestPath(int origin, int destination) {
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
	 * Private method for movement action, if the character can do a movement,
	 * then deletes it from the actual room and inserts it in another
	 * 
	 * Complexity O(1)
	 * 
	 * @param i
	 *            : direction the character must follow
	 * 
	 */
	private void move(Dir i) {
		Map map = Map.getInstance();
		int x = this.room / map.getDimY();
		int y = this.room % map.getDimY();
		switch (i) {
		case S:
			if (x < map.getDimX() - 1) {

				map.getMap()[x + 1][y].insertPj(this);
				map.getMap()[x][y].removePj(this);
				setRoom(map.getMap()[x + 1][y].getId());
			}
			break;
		case O:
			if (y > 0) {
				map.getMap()[x][y - 1].insertPj(this);
				map.getMap()[x][y].removePj(this);
				setRoom(map.getMap()[x][y - 1].getId());

			}
			break;
		case N:
			if (x > 0) {
				map.getMap()[x - 1][y].insertPj(this);
				map.getMap()[x][y].removePj(this);
				setRoom(map.getMap()[x - 1][y].getId());

			}
			break;
		case E:
			if (y < map.getDimY() - 1) {
				map.getMap()[x][y + 1].insertPj(this);
				map.getMap()[x][y].removePj(this);
				setRoom(map.getMap()[x][y + 1].getId());

			}
			break;
		}
	}

	/**
	 * Protected method for the door action, implemented by interfaces
	 * 
	 * 
	 * 
	 */
	private boolean actionDoor() {
		return doorAction.doorAction(this);
	}

	/**
	 * Protected method for the key action, implemented by interfaces
	 * 
	 * @param x
	 *            : The map where the Character is
	 * 
	 * 
	 */
	private void actionKey(Map x) {
		keyAction.keyAction(x.getMap(), this);
	}

	/**
	 * Protected method for the actions of the character
	 * 
	 * Complexity O(1)
	 * 
	 * 
	 */
	public void actionPj() {
		boolean door = false;
		Map x = Map.getInstance();
		if (x.getTurn() == 7) {
			System.out.println();
		}
		this.sumTurn();
		if (x.getDRoom() == getRoom())
			door = actionDoor();
		if (!this.rutes.isEmpty()) {

			move(this.rutes.get(0));
			this.rutes.remove(0);
		}
		if (!door)
			actionKey(x);
	}

	/**
	 * public abstract method for to String
	 * 
	 * Complexity O(1)
	 * 
	 */
	public abstract String toString();

	/**
	 * Returns a string with the path that the character will follow
	 * 
	 * Complexity O(1)
	 * 
	 * @return x : String with the path that the character will follow
	 */
	public String showPath() {
		String x = "(path:" + this.getTag() + ":";
		for (int i = 0; i < this.rutes.size(); i++) {
			x += " " + rutes.get(i).toString();
		}
		x += ")\n";
		return x;
	}

	/**
	 * Method implemented from the Compare interface
	 */
	public int compareTo(Pj t) {
		return (this.id.compareTo(t.getId()));
	}

	/**
	 * Method implemented from the Compare interface
	 */
	public boolean isEqual(Pj t) {
		return (this.id.equals(t.getId()));
	}

}
