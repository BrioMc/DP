package map;

import api.Compare;

public class Walls implements Compare<Walls> {

	private int origin;
	private int destination;

	Walls(int x, int y) {
		origin = x;
		destination = y;
	}

	/**
	 * 
	 * @return
	 */
	public int getOrigin() {
		return origin;
	}

	/**
	 * 
	 * @return
	 */
	public int getDestination() {
		return destination;
	}

	/**
	 * 
	 */
	public void showWalls() {
		System.out.println(origin + "->" + destination);
	}

	@Override
	public int compareTo(Walls x) {
		int equals = 0;

		if ((this.origin == x.getOrigin() && this.destination == x.getDestination())
				|| (this.origin == x.getDestination() && this.destination == this.getOrigin())) {
			equals = 1;
		}
		return equals;
	}

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