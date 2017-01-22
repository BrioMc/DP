package actions;

import map.Square;
import pj.Pj;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public interface KeyAction {
	/**
	 * Interface method for implementing key actions Complexity O(1)
	 * 
	 * @param map
	 *            : Matrix with all the rooms
	 * @param pj
	 *            : Character
	 * 
	 */
	void keyAction(Square[][] map, Pj pj);
}
