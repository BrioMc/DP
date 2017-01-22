package actions;

import map.Square;
import pj.Pj;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class DropKey implements KeyAction {

	/**
	 * Method that implements the action of dropping a key Complexity O(1)
	 * 
	 * @param map
	 *            : Matrix with all the rooms
	 * @param pj
	 *            : Character
	 * 
	 */
	@Override
	public void keyAction(Square[][] map, Pj pj) {
		int x = pj.getRoom() / map[0].length;
		int y = pj.getRoom() % map[0].length;

		if (pj.numKeys() != 0) {
			if (pj.getRoom() % 2 == 0) {

				map[x][y].insertKey(pj.getKey());
			}
		}
	}

}
