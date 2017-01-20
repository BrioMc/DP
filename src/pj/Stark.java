package pj;



import actions.PickKey;
import map.Map;
import map.Square;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */

public class Stark extends Pj {
	
	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Stark(String name, char M, int turn, int Room) {

		super(name, M, turn, Room);
		super.keyAction = new PickKey();
		

		// TODO Auto-generated constructor stub
	}

	 public void actionPj() {
		 super.actionPj();
	}
	/**
	 * 
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
		String pj = "";
		pj = ("(Stark:" + getTag() + ":" + getRoom() + ":" + currTurn + ":");
		
			for (int x = 0; x < this.keys.size(); x++) {
				pj += (keys.get(x).toString() + " ");
			
			pj += (")");

		}
		return pj;
	}

}
