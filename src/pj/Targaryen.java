package pj;

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
	

		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param x
	 */
	protected boolean actionDoor(Map x){
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
	
	

	protected void keyAction(Square[][] map){
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
		// TODO Auto-generated method stub
		return super.showPj("Targaryen");
	}
}

/**
* 
*/
