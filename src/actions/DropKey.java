package actions;

import map.Square;
import pj.Pj;

public class DropKey implements KeyAction {

	/**Method that implements the action of dropping a key
	 * 
	 */
	@Override
	public void keyAction(Square[][] map, Pj pj){
		int x = pj.getRoom() / map[0].length;
		int y = pj.getRoom() % map[0].length;
		
		if (pj.keys.size() != 0) {
			if (pj.getRoom() % 2 == 1) {

				map[x][y].insertKey(pj.keys.get(pj.keys.size() - 1));
				pj.keys.remove(pj.keys.get(pj.keys.size() - 1));
			}
		}
	}

}
