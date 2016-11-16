package pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */
import map.Dir;
import map.Map;

public class Targaryen extends Pj {
	char houseTag= 'T';
	/**
	 * Parameterized constructor
	 * @param name
	 * @param M
	 * @param turn
	 * @param Room
	 */
	public Targaryen(String name, char M, int turn, int Room) {
		super(name, M, turn, Room);

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void actionPj(Map x, Dir i) {
		super.actionPj(x, Dir i,this.houseTag);
		int keys[]={0,1,1,2,3,3,4,5,5,6,7,7,8,9,9,10,11,11,12,13,13,14,15,
				15,16,17,17,18,19,19,20,21,21,22,23,23,24,25,25,26,27,27,28,29,29};
	}
}
