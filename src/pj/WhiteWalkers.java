package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */
import api.Queue;
import map.Map;

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
				if (x.getMap()[j][y].checkPj().getHTag() != 'W') {
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
		this.houseTag = 'W';
	}

	/**
	 * 
	 */
	public void actionPj() {
		super.actionPj(this.rutes, this.houseTag);
		takepj();
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
