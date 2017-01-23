package actions;

import map.Map;
import pj.Pj;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class OpenDoor implements DoorAction {

	/**
	 * Method that implements the action of open the door Complexity O(1)
	 * 
	 * @param pj
	 *            : Character
	 * 
	 */
	@Override
	public boolean doorAction(Pj pj) {
		Map x = Map.getInstance();
		boolean doorRoom = false;

		if (!x.getDoor().isOp()) {
			if (pj.numKeys() != 0) {
				if (x.getDoor().open(pj.getKey())) {
					x.getMap()[pj.getRoom() / x.getDimY()][pj.getRoom() % x.getDimY()].removePj(pj);
					pj.setRoom(1111);
					x.getThrone().insertPj(pj);
					doorRoom = true;

				}
			}
		} else {
			x.getMap()[pj.getRoom() / x.getDimY()][pj.getRoom() % x.getDimY()].removePj(pj);
			pj.setRoom(1111);
			x.getThrone().insertPj(pj);
			doorRoom = true;

		}

		return doorRoom;
	}

}
