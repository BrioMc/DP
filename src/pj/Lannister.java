package pj;

import door.Key;
import map.Map;
import map.Square;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
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
		Key k;
		for (int i = 0; i < 30; i++) {
			k = new Key(i);
			keys.add(k);
			if (i % 2 != 0) {
				keys.add(k);
			}
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	protected boolean actionDoor(Map x){
		boolean doorRoom = false;

		if(this.room ==x.getDRoom()){
			x.getDoor().closeDoor();
			doorRoom = true;
		}
			
		return doorRoom;

	}
	


	protected void keyAction(Square[][] map){
		int x = this.room / map[0].length;
		int y = this.room % map[0].length;
		
		if (this.keys.size() != 0) {
			if (this.room % 2 == 1) {

				map[x][y].insertKey(this.keys.get(this.keys.size() - 1));
				this.keys.remove(this.keys.get(this.keys.size() - 1));
			}
		}
	}
	/**
	 * 
	 * @return
	 */
	public String showPj() {
		// TODO Auto-generated method stub
		return super.showPj("Lannister");
	}

}
