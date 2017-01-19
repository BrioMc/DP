package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */
import api.Queue;
import map.Map;
import map.Square;

public class WhiteWalkers extends Pj {

	/** Pjs Queue */
	private Queue<Pj> pjsT;

	/**
	 * 
	 */
	private void takepj() {
		Map x = Map.getInstance();
		int j = this.room / x.getMap()[0].length;
		int y = this.room % x.getMap()[0].length;
		boolean take = false;
		int i = 0;
		while (!take && i < x.getMap()[j][y].nPj()) {
			i++;
			if (x.getMap()[j][y].nPj() != 0) {
				if (!(x.getMap()[j][y].checkPj() instanceof WhiteWalkers)) {
					take = true;
					pjsT.add(x.getMap()[j][y].takePj());
				}
			}
		}
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public WhiteWalkers(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);
		this.currTurn = 0;
		pjsT = new Queue<Pj>();
	}

	/**
	 * 
	 */
	public void actionPj() {
		super.actionPj();
		takepj();
	}
	protected boolean actionDoor(Map x){
		boolean doorRoom = false;

		if(this.room ==x.getDRoom()){
			x.getDoor().closeDoor();
			doorRoom = true;
		}
			
		return doorRoom;

	}
	


	protected void keyAction(Square[][] map){
		
	}
	/**
	 * public method for watch pj's information
	 * 
	 * @return
	 */
	public String showPj() {
		// TODO Auto-generated method stub
		String pj = "";
		pj += super.showPj("WhiteWalkers");
		for (int i = 0; i < pjsT.size(); i++) {
			pj += (" " + pjsT.get(i).getTag());
		}
		pj += (")");
		return pj;
	}

}
