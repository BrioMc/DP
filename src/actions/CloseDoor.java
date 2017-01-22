package actions;

import map.Map;
import pj.Pj;
/**
 * Group: 		We are your waifu 
 * Members:		Ignacio Caro Cumplido 
 *				Javier Ballesteros Moron 
 */
public class CloseDoor implements DoorAction {

	/**Method that implements the action of open the door
	 *  Complexity O(1)
	 *  
	 *  @param pj : Character
	 *  
	 */
	@Override
	public boolean doorAction(Pj pj) {
		boolean doorRoom = false;

		if (pj.getRoom() == Map.getInstance().getDRoom()) {
			Map.getInstance().getDoor().closeDoor();
			doorRoom = true;
		}

		return doorRoom;

	}

}
