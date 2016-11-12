package pj;

import java.util.ArrayList;

import map.Dir;
import map.Map;
import map.Square;
import api.Compare;
import door.Key;
import door.ThroneDoor;

public abstract class Pj implements Compare<Pj> {
	/** Pj Identifier */
	protected Integer id;
	/** Pj Name */
	protected String name;
	/** Turn when pj begin. */
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
	protected int turnAct;

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param room
	 */
	protected Pj(String name, char M, int turn, int room) {
		this.turnAct = 0;
		this.move = false;
		this.tag = M;
		this.name = name;
		this.room = room;
		this.turnInitial = turn;
		this.rutes = new Dir[200];
	}

	/**
	 * 
	 * @return
	 */
	public char getTag() {
		return tag;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public int getTurn() {
		return turnInitial;
	}

	/**
	 * 
	 * @return
	 */
	public int getRoom() {
		return room;
	}

	public void setRoom(int i) {
		this.room = i;
	}

	/**
	 * 
	 * @param rutes
	 */
	public void setRutes(Dir[] rutes) {
		this.rutes = rutes;
	}

	/**
 * 
 */
	protected void showPj(String house) {
		System.out.print("(" + house + ":" + getTag() + ":" + getRoom() + ":"
				+ turnAct + ":");
	}

	/**
	 * 
	 * @param i
	 * @param map
	 */
	protected void move(Dir i, Square[][] map) {
		int x = this.room / map.length;
		int y = this.room % map[0].length;
		switch (i) {
		case S:
			if (x < map.length) {
				map[x][y].removePj(this);
				map[x + 1][y].insertPj(this);
				setRoom(map[x + 1][y].getId());
			}
			break;
		case W:
			if (y > 0) {
				map[x][y].removePj(this);
				map[x][y - 1].insertPj(this);
				setRoom(map[x][y - 1].getId());

			}
			break;
		case N:
			if (x > 0) {
				map[x][y].removePj(this);
				map[x - 1][y].insertPj(this);
				setRoom(map[x - 1][y].getId());

			}
			break;
		case E:
			if (y < map.length) {
				map[x][y].removePj(this);
				map[x][y + 1].insertPj(this);
				setRoom(map[x][y + 1].getId());

			}
			break;
		}
	}

	/**
 * 
 */
	protected void actionPj(Map x) {
		if (x.getDRoom() == getRoom()) {
			actionDoor(x, 'c');
		}
	}

	/**
 * 
 */
	protected boolean actionDoor(Map x, char c) {
		boolean doorRoom = false;
		doorRoom = true;
		switch (c) {
		case 'C':
			x.getDoor().closeDoor();
			;
			break;
		case 'O':
			if (x.getDoor().open(keys.remove(keys.size()))) {
				x.getMap()[0][0].removePj(this);
				x.getThrone().insertPj(this);
			}
			break;

		}
		return doorRoom;

	}

	/**
 * 
 */
	public int compareTo(Pj t) {
		return (this.id.compareTo(t.getId()));
	}

	/**
 * 
 */
	public boolean isEqual(Pj t) {
		return (this.id.equals(t.getId()));
	}
}
