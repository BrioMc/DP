package map;

public class Walls {

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

	/**
	 * 
	 * @param x
	 * @return
	 */
	public boolean equals(Walls x) {
		boolean equals = false;
		if ((this.origin == x.origin && this.destination == x.destination)
				|| (this.origin == x.destination && this.destination == this.origin)) {
			equals = true;
		}
		return equals;
	}
}