package actions;

import map.Square;
import pj.Pj;

public class PickKey implements KeyAction {

	
	/**Method that implements the action of picking a key
	 * 
	 */
	@Override
	public void keyAction(Square[][] map, Pj pj) {		
		int x = pj.getRoom() / map[0].length;
		int y = pj.getRoom() % map[0].length;
			
		if (map[x][y].nkeys() > 0) {
			pj.keys.add(map[x][y].removeKey());
		}
		

	}

}
