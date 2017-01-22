package actions;

import map.Square;
import pj.Pj;
/**
 * Group: 		We are your waifu 
 * Members:		Ignacio Caro Cumplido 
 *				Javier Ballesteros Moron 
 */
public class PickKey implements KeyAction {

	
	/**Method that implements the action of picking a key
	 *  Complexity O(1)
	 *  
	 *  @param map : Matrix with all the rooms
	 *  @param pj : Character
	 *  
	 */
	@Override
	public void keyAction(Square[][] map, Pj pj) {
		int x = pj.getRoom() / map[0].length;
		int y = pj.getRoom() % map[0].length;

		if (map[x][y].nkeys() > 0) {
			pj.setKey(map[x][y].removeKey());
		}

	}

}
