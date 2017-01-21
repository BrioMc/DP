package map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import door.Key;
import door.ThroneDoor;
import pj.Pj;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

public class Map {
	private int turn;
	public Square[][] map;
	private int dimX;
	private int dimY;
	private ThroneDoor door;
	private int doorRoom;
	private Square addit;
	private ArrayList<Walls> walls;
	private Graph graph;
	private static Map instance;
	private BufferedWriter bufOut;

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

	public int getTurn() {
		return turn;
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
		for (Walls wall : walls) {
			n = (i * getDimY() + j);
			m = wall.getOrigin();
			// System.out.println(n+"-"+m);
			if (n == m) {
				// System.out.println("entra");
				if (wall.getOrigin() == wall.getDestination() + getDimY()
						&& graph.getArc(wall.getOrigin(), wall.getDestination()) != 1) {
					// N
					wa[0] = true;
				}
				if (wall.getOrigin() == wall.getDestination() - getDimY()
						&& graph.getArc(wall.getOrigin(), wall.getDestination()) != 1) {
					// S
					wa[1] = true;
				}
				if (wall.getOrigin() == (wall.getDestination() + 1)
						&& graph.getArc(wall.getOrigin(), wall.getDestination()) != 1) {
					// W
					wa[2] = true;
				}
				if (wall.getOrigin() == (wall.getDestination() - 1)
						&& graph.getArc(wall.getOrigin(), wall.getDestination()) != 1) {
					// E
					wa[3] = true;
				}

			}

		}

		return wa;
	}

	/**
	 * Parametrized constructor of the class map.
	 *
	 * @param doorRoom
	 *            : The room where the Throne Door is located (int)
	 * @param dimX
	 *            : The number of rows of the map (int)
	 * @param dimY
	 *            : The number of columns of the map (int)
	 * @param door
	 *            : The door of the throne (ThroneDoor)
	 */
	private Map(int doorRoom, int dimX, int dimY, ThroneDoor door) {
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
		Kruskal();
		write(writeMapG());
		doShortcut();
		keyDistribution();
	}

	/**
	 * Method used for generating the map with a Singleton.
	 *
	 * @param doorRoom
	 *            : The room where the Throne Door is located (int)
	 * @param dimX
	 *            : The number of rows of the map (int)
	 * @param dimY
	 *            : The number of columns of the map (int)
	 * @param door
	 *            : The door of the throne (ThroneDoor)
	 */
	public static void generateInstance(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		instance = new Map(doorRoom, dimX, dimY, door);
	}

	/**
	 * Method for obtaining the graph of the map
	 *
	 * @return graph : Graph with the paths between squares (Graph)
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Method for obtaining an instance of the map (Singleton Design Pattern)
	 *
	 * @return instace : Instance of the class map (Map)
	 */
	public static Map getInstance() {
		return instance;
	}

	/**
	 * Method that assigns an ArrayList of walls to the map.
	 *
	 * @param x
	 *            : ArrayList that contains all the walls that we want to put
	 *            into the map. (ArrayList<Walls>)
	 */
	public void setWalls(ArrayList<Walls> x) {
		walls = x;
	}

	/**
	 * Method that shows all the walls of the map
	 *
	 */
	public void showW() {
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).showWalls();
		}
	}

	/**
	 * Public method that returns the Throne Door
	 *
	 * @return door : The door of the throne (ThroneDoor)
	 */
	public ThroneDoor getDoor() {
		return door;
	}

	/**
	 * Public method, returns the additional room (1111)
	 *
	 * @return addit : The additional room with the ID 1111 (Square)
	 */
	public Square getThrone() {

		return addit;
	}

	/**
	 * Public method, returns the door room
	 *
	 * @return doorRoom : The id of the room where the door is (int).
	 */
	public int getDRoom() {
		return this.doorRoom;
	}

	/**
	 *
	 *
	 * @return map : Returns the "mapping" of all the rooms (Square[][])
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
	 * Return the map size
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
		return map[dimX - 1][dimY - 1].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int surW() {
		return map[dimX - 1][0].getId();
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
		return map[0][dimY - 1].getId();
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
		Square x;
		x = map[i / getDimY()][i % getDimY()];
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
	private void doShortcut() {
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
	private void Kruskal() {
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

			if (!Objects.equals(origin.getMark(), destination.getMark())) {

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

	}

	/**
	 * 
	 */
	private int distance(int o, int d) {
		int i = 0;
		int destination = o;
		while (destination != d && destination != -1) {
			destination = graph.next(destination, d);
			i++;
		}
		return i;

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
	private void distKeys(int[] rooms) {
		int count = 0;
		int x = 0;
		int y = 0;
		boolean rest = false;
		boolean tst = false;

		for (int room : rooms) {
			x = room / map.length;
			y = room % map[1].length;
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
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
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

	/**
	 * 
	 * @param x
	 * @param origin
	 */
	private void mostFreq(ArrayList<Integer> x, Integer origin) {

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
	 * @param V
	 */
	private void bubbleSorting(int[] V) {
		int ID;
		for (int i = 1; i < V.length; i++) {
			for (int j = 0; j < V.length - i; j++) {
				if (getSquare(V[j]).getFreq() < getSquare(V[j + 1]).getFreq()) {
					ID = getSquare(V[j]).getId();
					V[j] = V[j + 1];
					V[j + 1] = ID;

				}
			}
		}
	}

	/**
	 * 
	 */
	private void keyDistribution() {
		ArrayList<Integer> x = new ArrayList<>();
		mostFreq(x, 0);
		int[] rooms = new int[dimY * dimX];
		int[] keyRooms = new int[9];

		for (int i = 0; i < dimY * dimX; i++) {
			rooms[i] = getSquare(i).getId();
		}

		bubbleSorting(rooms);
		System.arraycopy(rooms, 0, keyRooms, 0, 9);

		distKeys(keyRooms);
		for (int keyRoom : keyRooms) {
			System.out.print(keyRoom + " ");
		}
	}

	public void addLog(String str) {
		try {
			bufOut = new BufferedWriter(new FileWriter("record.txt", true));
			bufOut.write(str);
			bufOut.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param bufferOut
	 * @throws IOException
	 */
	private void writeTurn() {
		try {
			// Write Turn data
			bufOut.write("(turn:" + this.turn + ")");
			bufOut.newLine();
			// Write Map data
			bufOut.write("(map:" + getTMap() + ")");
			bufOut.newLine();
			// Write Door data
			bufOut.write(door.showDoor());
			bufOut.newLine();
			// Write Map look
			bufOut.write(writeMapG());

			// Write squares with keys
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j].nkeys() != 0) {
						bufOut.write(map[i][j].showKeys());
						bufOut.newLine();
					}
				}
			}
			// Write Pjs
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j].nPj() != 0) {
						bufOut.write(map[i][j].showPj());

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(String x) {
		try {
			bufOut = new BufferedWriter(new FileWriter("record.txt", true));
			bufOut.write(x);
			bufOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void writeMap() {

		try {
			bufOut = new BufferedWriter(new FileWriter("record.txt", true));
			writeTurn();
			bufOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Public Method for paint map summary
	 */
	public void paintMap() {
		// paint turn data
		System.out.println("(turn:" + (this.turn-1) + ")");
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

	public String printInitMap() {
		String x = "";
		x = writeMapG();
		x += ("(turn:" + this.turn + ")");
		x += ("(map:" + getTMap() + ")");
		return x;
	}

	/**
	 * 
	 * @param args
	 */

	// public static void main(String args[]) {
	// int dimX = 6;
	// int dimY = 6;
	// int doorRoom = (dimX * dimY) - 1;
	// int altLock = 3;
	// // int maxTurns = 50;
	// int numKeys = 15;
	//
	// // Creating and configuring the door. It is not specified here since
	// // it was specified in the previous stage
	// // Add the door to the map (it will be stored into the Throne square)
	//
	// int[] listaIdKeys = new int[numKeys];
	// int j = 1;
	// for (int i = 0; i < numKeys; i++) {
	// listaIdKeys[i] = j;
	// j = j + 2;
	// }
	// Key[] combination = new Key[numKeys];
	// for (int i = 0; i < combination.length; i++) {
	// combination[i] = new Key(listaIdKeys[i]);
	// }
	// ThroneDoor door = new ThroneDoor(combination, altLock);
	//
	// // Creating the map
	// // Parameters: door square, columns number, rows number,
	// // depth for the lock
	// // The constructor must create the different squares for the map
	// Map.generateInstance(doorRoom, dimX, dimY, door);
	// Map map = Map.getInstance();
	// map.Kruskal();
	// map.doShortcut();
	// // Generate the keys and distribute them. In this stage, we pass to the
	// // map an array
	// // with the identifiers of the squares where the keys are going to be
	// // distributed
	//
	// // The generation of the keys will be performed by the map
	// // The distribution is as follows:
	// // (square:3: 0 1 1 2 3)
	// // (square:4: 3 4 5 5 6)
	// // (square:6: 7 7 8 9 9)
	// // (square:8: 10 11 11 12 13)
	// // (square:9: 13 14 15 15 16)
	// // (square:10: 17 17 18 19 19)
	// // (square:11: 20 21 21 22 23)
	// // (square:12: 23 24 25 25 26)
	// // (square:13: 27 27 28 29 29)
	//
	// map.keyDistribution();
	// map.paintMap();
	//
	// // Creating the characters
	// // Creating a Stark
	// // Parameters: name, mark, turn in which it will start the simulation
	// // and initial square
	//
	// // Creating the route for the Stark:
	// // (route:E: S S E E N E N E S E S S W S E E)
	// Stark starkE = new Stark("Eddard", 'E', 1, 0);
	// // Adding the character into the map
	// map.insertPj(starkE);
	// System.out.println(starkE.showRute());
	// // Creating a Targaryen
	// // Parameters: name, mark, turn in which it will start the simulation
	// // and initial square
	// // (route:D: E S S S W S E E N E S S E E)
	// Targaryen targaryenD = new Targaryen("Daenerys", 'D', 1, 0);
	// map.insertPj(targaryenD);
	// System.out.println(targaryenD.showRute());
	//
	// WhiteWalkers walker = new WhiteWalkers("Caminante", 'C', 1, map.surW());
	// map.insertPj(walker);
	// System.out.println(walker.showRute());
	// // Creating a Lannister
	// // Parameters: name, mark, turn in which it will start the simulation
	// // and initial square
	// Lannister lannisterT = new Lannister("Tyrion", 'T', 1, map.getDRoom());
	// // (ruta:T: N N W N N W S W W N N W S S S S S E E E E E )
	// // Adding the character into the map
	// // map.paintMap();
	// map.insertPj(lannisterT);
	// System.out.println(lannisterT.showRute());
	// // Executing the simulation
	// // The process method must be executed turn after turn, traversing the
	// // map from square 0
	// // to the last square and the characters stored in each square must
	// // execute their actions
	// // in a chronologically order (the characters that arrived first are the
	// // first in leaving the square)
	//
	// // map.graph.showArcs();
	//
	// }
}