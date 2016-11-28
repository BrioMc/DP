package map;

<<<<<<< HEAD
public class Walls {

	private int origin;
	private int destination;

	Walls(int x, int y) {
		origin = x;
		destination = y;
	}

	public int getOrigin() {
		return origin;
	}

	public int getDestination() {
		return destination;
	}

	public void showWalls() {
		System.out.println(origin + "->" + destination);
	}

	public boolean equals(Walls x) {
		boolean equals = false;
		if ((this.origin == x.origin && this.destination == x.destination)
				|| (this.origin == x.destination && this.destination == this.origin)) {
			equals = true;
		}
		return equals;
=======


public class Walls {
	
	private int origin;
	private int destination;
	
	protected Walls(int x, int y){
		origin = x;
		destination = y;
	}
	
	public int getOrigin(){
		return origin;
	}
	public int getDestination(){
		return destination;
	}
	
	public boolean equals(Walls x){
		boolean equals=false;
		if((this.origin==x.origin&&this.destination==x.destination)
				||(this.origin==x.destination&&this.destination==this.origin)){
			equals=true;
		}
	return equals;
>>>>>>> refs/remotes/origin/master
	}
}
