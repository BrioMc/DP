package map;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import door.Key;
import door.ThroneDoor;
import pj.Lannister;
import pj.Pj;
import pj.Stark;
import pj.Targaryen;
import pj.WhiteWalkers;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

public class Map {
	private int turn;
	public Square[][] map;
	private int dimX;
	private int dimY;
	protected ThroneDoor door;
	protected int doorRoom;
	protected Square addit;
	protected ArrayList<Walls> walls;
	protected Graph graph;
	public static Map instance;

	/**
	 * Private method for initializing the map
	 */
	private void iniMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Square(i * map[i].length + j);
			}
		}
	}

	/**
	 * Private method for initializing the walls in this order: N E S W
	 */
	private void iniWalls() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				if (i != 0)
					walls.add(new Walls(i * getDimY() + j, (i - 1) * getDimY() + j));
				if (j != map[i].length - 1)
					walls.add(new Walls(map[i][j].getId(), map[i][j + 1].getId()));
				if (i != map.length - 1)
					walls.add(new Walls(map[i][j].getId(), map[i + 1][j].getId()));
				if (j != 0)
					walls.add(new Walls(map[i][j].getId(), map[i][j - 1].getId()));

			}
		}
	}

	/**
	 * 
	 */
	protected boolean[] retWalls(int i, int j) {
		boolean[] wa = new boolean[4];
		int n = 0, m = 0;
		// N
		wa[0] = false;
		// S
		wa[1] = false;
		// W
		wa[2] = false;
		// E
		wa[3] = false;

		// charge wallBool
		for (int z = 0; z < walls.size(); z++) {
			// n = walls.get(z).getOrigin() / map[i].length;
			// m = walls.get(z).getOrigin() % map[i].length;
			n = (i * getDimY() + j);
			m = walls.get(z).getOrigin();
			// System.out.println(n+"-"+m);
			if (n == m) {
				// System.out.println("entra");
				if (walls.get(z).getOrigin() == walls.get(z).getDestination() + getDimY()
						&& graph.getArc(walls.get(z).getOrigin(), walls.get(z).getDestination()) != 1) {
					// N
					wa[0] = true;
				}
				if (walls.get(z).getOrigin() == walls.get(z).getDestination() - getDimY()
						&& graph.getArc(walls.get(z).getOrigin(), walls.get(z).getDestination()) != 1) {
					// S
					wa[1] = true;
				}
				if (walls.get(z).getOrigin() == (walls.get(z).getDestination() + 1)
						&& graph.getArc(walls.get(z).getOrigin(), walls.get(z).getDestination()) != 1) {
					// W
					wa[2] = true;
				}
				if (walls.get(z).getOrigin() == (walls.get(z).getDestination() - 1)
						&& graph.getArc(walls.get(z).getOrigin(), walls.get(z).getDestination()) != 1) {
					// E
					wa[3] = true;
				}

			}

		}

		return wa;
	}

	/**
	 * 
	 * @param doorRoom
	 * @param dimX
	 * @param dimY
	 * @param door
	 */
	public Map(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		this.map = new Square[dimX][dimY];
		iniMap();
		this.turn = 0;
		this.dimX = dimX;
		this.dimY = dimY;
		this.door = door;
		this.doorRoom = doorRoom;
		this.addit = new Square(1111);
		this.walls = new ArrayList<Walls>();
		iniWalls();
		this.graph = new Graph(dimX * dimY);
	}

	/**
	 * 
	 * @param doorRoom
	 * @param dimX
	 * @param dimY
	 * @param door
	 */
	public static void generateInstance(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		instance = new Map(doorRoom, dimX, dimY, door);
	}

	/**
	 * 
	 * @param doorRoom
	 * @param dimX
	 * @param dimY
	 * @param door
	 */

	public static Map getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param x
	 */
	public void setWalls(ArrayList<Walls> x) {
		walls = x;
	}

	/**
	 * 
	 */
	public void showW() {
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).showWalls();
		}
	}

	/**
	 * Public method that return door
	 */
	public ThroneDoor getDoor() {
		return door;
	}

	/**
	 * Public method, return Throne room
	 */
	public Square getThrone() {

		return addit;
	}

	/**
	 * 
	 * @return The id of the room where the door is.
	 */

	public int getDRoom() {
		return this.doorRoom;
	}

	/**
	 * 
	 * @return
	 */
	public Square[][] getMap() {

		return map;
	}

	/**
	 * 
	 * @return
	 */
	public int getDimX() {
		return this.dimX;
	}

	/**
	 * 
	 * @return
	 */
	public int getDimY() {
		return this.dimY;
	}

	/**
	 * /** Return the map size
	 * 
	 * @return map size
	 */
	public int getTMap() {
		return (this.map.length * this.map[0].length) - 1;
	}

	public ArrayList<Walls> getWalls() {
		return walls;
	}

	/**
	 * 
	 * @return
	 */
	public int surE() {
		return map[map.length - 1][map[0].length - 1].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int surW() {
		return map[map.length - 1][0].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int norW() {
		return map[0][0].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int norE() {
		return map[0][map[0].length - 1].getId();
	}

	/**
	 * 
	 */
	/**
	 * 
	 * @param i
	 * @return
	 */
	private Square getSquare(Integer i) {
		Map map = Map.getInstance();
		Square x;
		x = map.map[i / map.getDimY()][i % map.getDimY()];
		return x;
	}

	/**
	 * 
	 * @param node
	 * @param mark
	 */
	private void markPropagation(Square node, int mark) {
		if (mark != node.getMark()) {
			Set<Integer> ady = new HashSet<Integer>();
			node.setMark(mark);
			graph.adyacentes(node.getId(), ady);
			if (!ady.isEmpty()) {
				for (Integer i : ady)
					markPropagation(getSquare(i), mark);
			}
		}
	}

	/**
	 * 
	 */
	public void doShortcut() {
		int count = 0;
		int max = (int) (((getDimX() * getDimY()) * 0.05));
		// Other positions
		while (count < max) {
			// take random square

			int rnd = GenAleatorios.generarNumero(getDimX() * getDimY());
			int i = rnd / getDimY();
			int j = rnd % getDimY();
			int s = rnd + getDimY();
			int w = rnd - 1;
			int e = rnd + 1;
			int n = rnd - getDimY();
			boolean[] x = retWalls(i, j);
			// N
			if (x[0] && distance(rnd, n) > 3) {
				graph.newArc(rnd, n, 1);
				graph.newArc(n, rnd, 1);
				count++;
			}
			// S
			else if (x[1] && distance(rnd, s) > 3) {
				graph.newArc(rnd, s, 1);
				graph.newArc(s, rnd, 1);
				count++;
			} // W
			else if (x[2] && distance(rnd, w) > 3) {
				graph.newArc(rnd, w, 1);
				graph.newArc(w, rnd, 1);

				count++;
			} // E
			else if (x[3] && distance(rnd, e) > 3) {
				graph.newArc(rnd, e, 1);
				graph.newArc(e, rnd, 1);
				count++;
			}
		}
		graph.floyd();
		graph.warshall();

	}

	/**
	 * 
	 */
	public void Kruskal() {
		ArrayList<Walls> selected = new ArrayList<>();
		int rnd; // random number
		int mark;
		Walls aux;
		Square origin;
		Square destination;
		// fillArcs();

		while (!walls.isEmpty()) {
			rnd = GenAleatorios.generarNumero(walls.size());
			aux = walls.remove(rnd);
			origin = getSquare(aux.getOrigin());
			destination = getSquare(aux.getDestination());

			if (origin.getMark() != destination.getMark()) {

				this.graph.newArc(origin.getId(), destination.getId(), 1);
				this.graph.newArc(destination.getId(), origin.getId(), 1);
				// mark = map.map[origin.getId() /
				// map.getLength()][origin.getId() % map.getWidth()].getMark();

				mark = getSquare(aux.getDestination()).getMark();
				markPropagation(origin, mark);

			} else {
				if ((graph.getArc(origin.getId(), destination.getId()) != 1)
						|| (graph.getArc(destination.getId(), origin.getId()) != 1)) {
					selected.add(aux);

				}
			}

		}
		walls.addAll(selected);
		graph.floyd();
		graph.warshall();
		// doShortcut();

	}

	/**
	 * 
	 */
	public int distance(int o, int d) {
		int i = 0;
		int destination = o;
		while (destination != d && destination != -1) {
			destination = graph.next(destination, d);
			i++;
		}
		return i;

	}

	/**
	 * 
	 * @param ady
	 * @param i
	 */
	public void mostFreq(Set<Integer> ady, Integer i) {
		if (i == getDRoom()) {

		} else {

		}
	}

	/**
	 * Public method for insert pj in map
	 * 
	 * @param pj
	 */
	public void insertPj(Pj pj) {
		int c = pj.getRoom();
		int i = c / map[0].length;

		int j = c % map[0].length;
		map[i][j].insertPj(pj);

	}

	/**
	 * Public method for key distribution
	 * 
	 * @param rooms
	 */
	public void distKeys(int[] rooms) {
		int count = 0;
		int x = 0;
		int y = 0;
		boolean rest = false;
		boolean tst = false;

		for (int i = 0; i < rooms.length; i++) {
			x = rooms[i] / map.length;
			y = rooms[i] % map[1].length;
			tst = false;
			do {
				Key key = new Key(count);
				//
				map[x][y].insertKey(key);
				if (count % 2 != 0 && map[x][y].nkeys() == 5) {
					tst = true;
				}
				if (count % 2 != 0 && map[x][y].nkeys() < 5 && !rest) {
					map[x][y].insertKey(key);
				}
				rest = false;
				// System.out.println(count);
				count++;

			} while (map[x][y].nkeys() < 5);

			if ((count - 1) % 2 != 0 && (count - 2) % 2 == 0 && tst) {
				rest = true;
				count--;
			} else
				rest = false;
		}
	}

	/**
	 * 
	 * @param turn
	 */
	public void process(int turn) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].proccessT(turn);
				map[i][j].resetTurn();
			}
		}
		this.turn++;
	}

	/**
	 * 
	 * @return
	 */
	private String writeMapG() {
		String ma = "";
		int origin = 0, destination = 0;
		for (int x = 0; x < map[0].length; x++) {
			ma += (" _");
		}

		ma += "\n";
		for (int i = 0; i < map.length; i++) {
			ma += ("|");
			for (int j = 0; j < map[i].length; j++) {
				origin = i * getDimY() + j;
				destination = origin + 1;
				// Show if have 1 pj only in square
				if (map[i][j].nPj() == 1) {
					if (j != map[i].length - 1) {
						ma += (map[i][j].checkPj().getTag());
						if (graph.getArc(origin, destination) != 1) {
							ma += ("|");
						} else {
							ma += (" ");
						}
					} else {
						ma += (map[i][j].checkPj().getTag());
					}
				}
				// Show if have more that 1 pj in square
				else if (map[i][j].nPj() > 1) {
					if (j != map[0].length - 1) {
						ma += (String.valueOf(map[i][j].nPj()));
						if (graph.getArc(origin, destination) != 1) {
							ma += ("|");
						} else {
							ma += (" ");
						}
					} else {
						ma += (String.valueOf(map[i][j].nPj()));
					}
				}
				// Print if square is empty
				else {
					destination = origin + getDimY();
					if (j != map[0].length - 1) {
						// Paint botton wall if exist
						if (graph.getArc(origin, destination) != 1 || i == map.length - 1) {
							ma += ("_");
						} else {
							ma += (" ");
						}
						// Paint left wall if exist
						destination = origin + 1;

						if (graph.getArc(origin, destination) != 1) {
							ma += ("|");
						} else {
							ma += (" ");
						}
					} else {
						if (graph.getArc(origin, destination) != 1 || i == map.length - 1) {
							ma += ("_");
						} else {
							ma += (" ");
						}
					}
				}
			}
			ma += ("|");
			ma += "\n";
		}
		return ma;
	}

	public void ways(ArrayList<ArrayList<Integer>> m, ArrayList<Integer> x, int roomAct) {
	
		x.add(roomAct);
		if (roomAct == doorRoom) {
			m.add(x);
		} else {

			Set<Integer> ady = new LinkedHashSet<Integer>();

			graph.adyacentes(roomAct, ady);
			for (Integer i : ady) {

				if (!x.contains(i)) {
					ways(m, x, i);
					x.remove(x.size() - 1);

				}

			}

		}
	}

	/**
	 * 
	 * @param x
	 * @param origin
	 */
	public void mostFreq(ArrayList<Integer> x, Integer origin) {

		x.add(origin);
		if (origin == (dimY * dimX) - 1) {
			Square room;
			for (int i = 0; i < x.size(); i++) {

				room = getSquare(x.get(i));
				room.setFreq(room.getFreq() + 1);
			}
		} else {
			Set<Integer> ady = new LinkedHashSet<Integer>();
			graph.adyacentes(origin, ady);
			for (Integer i : ady) {
				if (!x.contains(i)) {
					mostFreq(x, i);
					x.remove(x.size() - 1);
				}
			}
		}

	}

	/**
	 * 
	 * @param bufferOut
	 * @throws IOException
	 */
	public void writelog(BufferedWriter bufferOut) throws IOException {
		// Write Turn data
		bufferOut.write("(turn:" + this.turn + ")");
		bufferOut.newLine();
		// Write Map data
		bufferOut.write("(map:" + getTMap() + ")");
		bufferOut.newLine();
		// Write Door data
		bufferOut.write(door.showDoor());
		bufferOut.newLine();
		// Write Map look
		bufferOut.write(writeMapG());
		bufferOut.newLine();
		// Write squares with keys
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].nkeys() != 0) {
					bufferOut.write(map[i][j].showKeys());
					bufferOut.newLine();
				}
			}
		}
		// Write Pjs
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				bufferOut.write(map[i][j].showPj());
				bufferOut.newLine();
			}
		}
	}

	/**
	 * Public Method for paint map summary
	 */
	public void paintMap() {
		// paint turn data
		System.out.println("(turn:" + this.turn + ")");
		// Paint Map data
		System.out.println("(map:" + getTMap() + ")");
		// Paint door data
		System.out.print(door.showDoor());
		// Paint Map look
		System.out.print(writeMapG());
		// Paint squares with keys
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].nkeys() != 0) {
					System.out.print(map[i][j].showKeys());
				}
			}
		}
		// Paint pjs in map
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].showPj());
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public void paintMarks() {
		for (int x = 0; x < map[0].length; x++) {
			System.out.print(" _");
		}

		System.out.println();
		for (int i = 0; i < map.length; i++) {
			System.out.print("|");
			for (int j = 0; j < map[i].length; j++) {
				boolean[] wa = retWalls(i, j);
				// Show if have 1 pj only in square
				System.out.print(map[i][j].getMark());
				if (j != map[0].length - 1) {
					// Paint botton wall if exist
					if (wa[1] || i == map.length - 1) {
						System.out.print("_");
					} else {
						System.out.print(" ");
					}
					// Paint left wall if exist
					if (wa[3]) {
						System.out.print("|");
					} else {
						System.out.print(" ");
					}
				} else {
					if (wa[1] || i == map.length - 1) {
						System.out.print("_");
					} else {
						System.out.print(" ");
					}
				}
			}
			System.out.print("|");
			System.out.println();
		}

		System.out.println();
	}

	public static void main(String args[]) {
		int dimX = 6;
		int dimY = 6;
		int doorRoom = (dimX * dimY) - 1;
		int altLock = 3;
		// int maxTurns = 50;
		int numKeys = 15;

		// Creating and configuring the door. It is not specified here since
		// it was specified in the previous stage
		// Add the door to the map (it will be stored into the Throne square)

		int[] listaIdKeys = new int[numKeys];
		int j = 1;
		for (int i = 0; i < numKeys; i++) {
			listaIdKeys[i] = j;
			j = j + 2;
		}
		Key[] combination = new Key[numKeys];
		for (int i = 0; i < combination.length; i++) {
			combination[i] = new Key(listaIdKeys[i]);
		}
		ThroneDoor door = new ThroneDoor(combination, altLock);

		Stark starkE = new Stark("Eddard", 'E', 1, 0);

		// Creating the map
		// Parameters: door square, columns number, rows number,
		// depth for the lock
		// The constructor must create the different squares for the map
		Map.generateInstance(doorRoom, dimX, dimY, door);
		Map map = Map.getInstance();

		// Generate the keys and distribute them. In this stage, we pass to the
		// map an array
		// with the identifiers of the squares where the keys are going to be
		// distributed

		// The generation of the keys will be performed by the map
		// The distribution is as follows:
		// (square:3: 0 1 1 2 3)
		// (square:4: 3 4 5 5 6)
		// (square:6: 7 7 8 9 9)
		// (square:8: 10 11 11 12 13)
		// (square:9: 13 14 15 15 16)
		// (square:10: 17 17 18 19 19)
		// (square:11: 20 21 21 22 23)
		// (square:12: 23 24 25 25 26)
		// (square:13: 27 27 28 29 29)
		int[] idSalasConLlaves = { 3, 4, 6, 8, 9, 10, 11, 12, 13 };
		map.distKeys(idSalasConLlaves);

		// Creating the characters
		// Creating a Stark
		// Parameters: name, mark, turn in which it will start the simulation
		// and initial square

		// Creating the route for the Stark:
		// (route:E: S S E E N E N E S E S S W S E E)
		Dir[] direccionesE = { Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N, Dir.E, Dir.S, Dir.E, Dir.S, Dir.S,
				Dir.W, Dir.S, Dir.E, Dir.S };
		starkE.setRoutes(direccionesE);

		// Adding the character into the map
		map.insertPj(starkE);

		// Creating a Targaryen
		// Parameters: name, mark, turn in which it will start the simulation
		// and initial square
		Targaryen targaryenD = new Targaryen("Daenerys", 'D', 1, 0);
		// (route:D: E S S S W S E E N E S S E E)
		Dir[] direccionesD = { Dir.E, Dir.S, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.S, Dir.S,
				Dir.E, Dir.E };

		targaryenD.setRoutes(direccionesD);
		// Adding the character into the map
		map.insertPj(targaryenD);

		// Creating a White Walker
		// Parameters: name, mark, turn in which it will start the simulation
		// and initial square
		WhiteWalkers walker = new WhiteWalkers("Caminante", 'C', 1, map.surW());
		// (route:C: N N N E S E N N E N E E S S S S S )
		Dir[] direccionesC = { Dir.N, Dir.N, Dir.N, Dir.E, Dir.S, Dir.E, Dir.N, Dir.N, Dir.E, Dir.N, Dir.E, Dir.E,
				Dir.S, Dir.S, Dir.S, Dir.S, Dir.S };
		walker.setRoutes(direccionesC);
		// Adding the character into the map
		map.insertPj(walker);

		// Creating a Lannister
		// Parameters: name, mark, turn in which it will start the simulation
		// and initial square
		Lannister lannisterT = new Lannister("Tyrion", 'T', 1, map.getDRoom());
		// (ruta:T: N N W N N W S W W N N W S S S S S E E E E E )
		Dir[] direccionesT = { Dir.N, Dir.N, Dir.W, Dir.N, Dir.N, Dir.W, Dir.S, Dir.W, Dir.W, Dir.N, Dir.N, Dir.W,
				Dir.S, Dir.S, Dir.S, Dir.S, Dir.S, Dir.E, Dir.E, Dir.E, Dir.E, Dir.E };
		lannisterT.setRoutes(direccionesT);
		// Adding the character into the map
		// map.paintMap();
		map.insertPj(lannisterT);

		// Executing the simulation
		// The process method must be executed turn after turn, traversing the
		// map from square 0
		// to the last square and the characters stored in each square must
		// execute their actions
		// in a chronologically order (the characters that arrived first are the
		// first in leaving the square)

		// map.graph.showArcs();
		map.Kruskal();
		map.doShortcut();
		for (int i = 0; i < 50; i++) {

			map.process(i);
			map.paintMap();
		}
		ArrayList<ArrayList<Integer>> x = new ArrayList<>();
		ArrayList<Integer> m = new ArrayList<>();
		map.ways(x, m, 0);

		for (j = 0; j < x.size(); j++) {
			for (int l = 0; l < x.get(j).size(); l++) {
				System.out.print(x.get(j).get(l) + " ");

			}
			System.out.println();
		}

	}

}