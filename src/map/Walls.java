package map;

import api.Compare;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class Walls implements Compare<Walls> {

	/** Room where the wall is */
	private int origin;
	/** Room where the wall is oriented to */
	private int destination;

	/**
	 * Parametrized constructor of the class Walls
	 * 
	 * Complexity O(1)
	 * 
	 * @param x
	 *            : Origin
	 * @param y
	 *            : Destination
	 */
	Walls(int x, int y) {
		origin = x;
		destination = y;
	}

	/**
	 * Method that gets the origin
	 * 
	 * Complexity O(1)
	 * 
	 * @return origin : Origin square
	 */
	public int getOrigin() {
		return origin;
	}

	/**
	 * Method that gets the destination
	 * 
	 * Complexity O(1)
	 * 
	 * @return destination : Destination square
	 */
	public int getDestination() {
		return destination;
	}

	/**
	 * Show wall
	 */
	public void showWalls() {
		System.out.println(origin + "->" + destination);
	}

	/**
	 * Implemented from the Compare Interface
	 */
	@Override
	public int compareTo(Walls x) {
		int equals = 0;

		if ((this.origin == x.getOrigin() && this.destination == x.getDestination())
				|| (this.origin == x.getDestination() && this.destination == this.getOrigin())) {
			equals = 1;
		}
		return equals;
	}

	/**
	 * Implemented from the Compare Interface
	 */
	@Override
	public boolean isEqual(Walls x) {
		boolean equals = false;

		if ((this.origin == x.getOrigin() && this.destination == x.getDestination())
				|| (this.origin == x.getDestination() && this.destination == this.getOrigin())) {
			equals = true;
		}
		return equals;
	}
}