package map;

import pj.Pj;
import api.Compare;
import door.Key;

public class Room implements Compare<Room> {
	private int id;
	private Key[] keys;
	//Debe ser una cola (el primero que entra,el primero que sale)
	private Pj[] pjs;

	public int compareTo(Room t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEqual(Room t) {
		// TODO Auto-generated method stub
		return false;
	}

}
