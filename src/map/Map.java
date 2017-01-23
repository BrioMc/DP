package map;

import java.io.BufferedWriter;
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
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */
public class Map {
	/**
	 * current turn
	 */
	private int turn;
	/**
	 * Map's square
	 */
	public Square[][] map;
	/**
	 * dim X of map
	 */
	private int dimX;
	/**
	 * dim Y of map
	 */
	private int dimY;
	/**
	 * Throne door
	 */
	private ThroneDoor door;
	/**
	 * ThroneDoor Square number
	 */
	private int doorRoom;
	/**
	 * ThroneDoor Square
	 */

	private Square addit;
	/**
	 * Graph of map
	 * 
	 */
	private Graph graph;
	/**
	 * Map instance
	 * 
	 */
	private static Map instance;
	/**
	 * 
	 */
	private String mapPicture;

	/**
	 * Parametrized constructor of the class map.
	 *
	 * @param doorRoom
	 *            : The room where the Throne Door is located {@code int}
	 * @param dimX
	 *            : The number of rows of the map {@code int}
	 * @param dimY
	 *            : The number of columns of the map {@code int}
	 * @param door
	 *            : The door of the throne {@code ThroneDoor}
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
		this.graph = new Graph(dimX * dimY);
		Kruskal();
		this.mapPicture = toStringMap();
		doShortcut();
		keyDistribution();
	}

	/**
	 * Private method for initializing the map Complexity O(n^2)
	 * 
	 * 
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
	 * 
	 * Complexity O(n)
	 * 
	 * 
	 */
	private ArrayList<Walls> iniWalls() {
		ArrayList<Walls> walls = new ArrayList<>();
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
		return walls;
	}

	/**
	 * Public method used for generating the map with a Singleton.
	 * 
	 * Complexity O(1)
	 *
	 * @param doorRoom
	 *            : The room where the Throne Door is located {@code int}
	 * @param dimX
	 *            : The number of rows of the map {@code int}
	 * @param dimY
	 *            : The number of columns of the map {@code int}
	 * @param door
	 *            : The door of the throne {@code ThroneDoor}
	 * 
	 * 
	 */
	public static void generateInstance(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		instance = new Map(doorRoom, dimX, dimY, door);
	}

	/**
	 * Public method for obtaining an instance of the map (Singleton Design
	 * Pattern)
	 * 
	 * Complexity O(1)
	 *
	 * @return instace : Instance of the class map {@code Map}
	 */
	public static Map getInstance() {
		return instance;
	}

	/**
	 * Public method for obtaining the graph of the map
	 * 
	 * Complexity O(1)
	 *
	 * @return graph : Graph with the paths between squares {@code Graph}
	 */
	public Graph getGraph() {
		return this.graph;
	}

	/**
	 * Public method that returns the Throne Door
	 * 
	 * Complexity O(1)
	 *
	 * @return door : The door of the throne {@code ThroneDoor}
	 */
	public ThroneDoor getDoor() {
		return door;
	}

	/**
	 * Public method, returns the additional room (1111)
	 * 
	 * Complexity O(1)
	 *
	 * @return addit : The additional room with the ID 1111 {@code Square}
	 */
	public Square getThrone() {

		return addit;
	}

	/**
	 * Public method, returns the door room
	 * 
	 * Complexity O(1)
	 *
	 * @return doorRoom : The id of the room where the door is {@code int}.
	 */
	public int getDRoom() {
		return this.doorRoom;
	}

	/**
	 * Public method that obtains the matrix of rooms
	 * 
	 * Complexity O(1)
	 *
	 * @return map : Returns the "mapping" of all the rooms {@code Square}
	 */
	public Square[][] getMap() {

		return map;
	}

	/**
	 * Private method that returns the number of rows
	 * 
	 * Complexity O(1)
	 * 
	 * @return dimX : Returns the number of rows {@code int}
	 */
	public int getDimX() {
		return this.dimX;
	}

	/**
	 * Public method that returns method that returns the number of rows
	 * 
	 * Complexity O(1)
	 * 
	 * @return dimY : Returns the number of columns {@code int}
	 */
	public int getDimY() {
		return this.dimY;
	}

	/**
	 * Public method that returns the map size
	 * 
	 * Complexity O(1)
	 * 
	 * @return DimX*DimY - 1 (Squares froms 0 to DimX*DimY-1) {@code int}
	 */
	public int getTMap() {
		return (this.map.length * this.getDimY()) - 1;
	}

	/**
	 * Public method that returns the map size
	 * 
	 * Complexity O(1)
	 * 
	 * @return current turn {@code int}
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Private method that returns a room with a given ID
	 * 
	 * Complexity O(1)
	 * 
	 * @param i
	 *            : ID of the room that we want to obtain {@code Integer}
	 * @return x : Room wit the ID of the parameter i {@code Square}
	 */
	private Square getSquare(Integer i) {
		Square x;
		x = map[i / getDimY()][i % getDimY()];
		return x;
	}

	/**
	 * Public method that returns the SouthEast corner of the map
	 * 
	 * Complexity O(1)
	 * 
	 * @return ID of the SouthEast corner {@code int}
	 */
	public int surE() {
		return map[dimX - 1][dimY - 1].getId();
	}

	/**
	 * Public method that returns the SouthWest corner of the map
	 * 
	 * Complexity O(1)
	 * 
	 * @return ID of the SouthWest corner {@code int}
	 */
	public int surW() {
		return map[dimX - 1][0].getId();
	}

	/**
	 * Public method that returns the NorthWest corner of the map
	 * 
	 * Complexity O(1)
	 * 
	 * @return ID of the NorthWest corner {@code int}
	 */
	public int norW() {
		return map[0][0].getId();
	}

	/**
	 * Public method that returns the NorthEast corner of the map
	 * 
	 * Complexity O(1)
	 * 
	 * @return ID of the NorthEast corner {@code int}
	 */
	public int norE() {
		return map[0][dimY - 1].getId();
	}

	/**
	 * Private method that returns the distance between two rooms
	 * 
	 * Complexity O(n)
	 * 
	 * @param o
	 *            : Origin room {@code int}
	 * @param d
	 *            : Destination room {@code int}
	 * 
	 * @return i : Distance between the two rooms {@code int}
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
	 * Private method that propagates the mark that is needed in order to
	 * correctly torn down the walls
	 * 
	 * Complexity O(n)
	 * 
	 * @param node
	 *            : The node we are going to put the mark into {@code Square}
	 * @param mark
	 *            : The mark we are going to give to the node {@code int}
	 * 
	 * 
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
	 * Public method that applies the Kruskal algorithm to the map in order to
	 * create a maze.
	 * 
	 * Complexity O(n^)
	 * 
	 * 
	 */
	private void Kruskal() {
		ArrayList<Walls> walls = iniWalls();
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

			}
		}
		graph.floyd();
		graph.warshall();

	}

	/**
	 * Private method that torn down some walls after the maze is generated,
	 * creating some shortcuts .
	 * 
	 * Complexity O(n)
	 * 
	 * 
	 */
	private void doShortcut() {
		int count = 0;
		int max = (int) (((getDimX() * getDimY()) * 0.05));
		// Other positions
		while (count < max) {
			// take random square

			int rnd = GenAleatorios.generarNumero(getDimX() * getDimY());
			int s = rnd + getDimY();
			int w = rnd - 1;
			int e = rnd + 1;
			int n = rnd - getDimY();
			// N
			if (!graph.adyacente(rnd, n) && rnd / getDimY() != 0 && distance(rnd, n) > 3) {
				graph.newArc(rnd, n, 1);
				graph.newArc(n, rnd, 1);
				count++;
			}
			// S
			else if (!graph.adyacente(rnd, s) && rnd / getDimY() != getDimY() - 1 && distance(rnd, s) > 3) {
				graph.newArc(rnd, s, 1);
				graph.newArc(s, rnd, 1);
				count++;
			} // W
			else if (!graph.adyacente(rnd, w) && rnd % getDimY() != 0 && distance(rnd, w) > 3) {
				graph.newArc(rnd, w, 1);
				graph.newArc(w, rnd, 1);

				count++;
			} // E
			else if (!graph.adyacente(rnd, e) && rnd % getDimY() != getDimY() - 1 && distance(rnd, e) > 3) {
				graph.newArc(rnd, e, 1);
				graph.newArc(e, rnd, 1);
				count++;
			}
		}
		graph.floyd();
		graph.warshall();

	}

	/**
	 * Private method that sorts the given array based on the frequency of each
	 * room is visited
	 * 
	 * Complexity O(n^2)
	 * 
	 * @param V
	 *            : Array with the ID of the map's rooms {@code int}
	 * 
	 * 
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
	 * Private method that calculates the frequency which each room is visited
	 * from an origin to the throne room Backtracking Scheme
	 * 
	 * Complexity O(n^2)
	 * 
	 * @param x
	 *            : Empty ArrayList which will be used for calculations of the
	 *            best paths. {@code ArrayList<Integer>}
	 * @param origin
	 *            : Room where the algorithm starts {@code Integer}
	 * 
	 * 
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
	 * Private method for key distribution across 9 given rooms
	 * 
	 * Complexity O(n^2)
	 * 
	 * @param rooms
	 *            : Array with the 9 rooms where the keys will be distributed
	 *            {@code int}
	 * 
	 * 
	 */
	private void distKeys(int[] rooms) {
		int count = 0;
		int x = 0;
		int y = 0;
		boolean rest = false;
		boolean tst = false;

		for (int room : rooms) {
			x = room / getDimY();
			y = room % getDimY();
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
	 * Private method that organizes the key distribution
	 * 
	 * Complexity O(n)
	 * 
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

	}

	/**
	 * Public method that inserts a character in the map
	 * 
	 * @param pj
	 *            : Character we want to insert {@code pj}
	 * 
	 * 
	 */
	public void insertPj(Pj pj) {
		int c = pj.getRoom();
		int i = c / getDimY();

		int j = c % getDimY();
		map[i][j].insertPj(pj);

	}

	/**
	 * Public method that paints the labyrinth on the screen
	 * 
	 * Complexity O(n^2)
	 * 
	 * @return ma : The whole map drawing. {@code String}
	 */
	public String toStringMap() {
		String ma = "";
		int origin = 0, destination = 0;
		for (int x = 0; x < getDimY(); x++) {
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
					if (j != getDimY() - 1) {
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
					if (j != getDimY() - 1) {
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
	 * Public method that updates the state of the map each turn. Once it's
	 * done, the turn ends
	 * 
	 * Complexity O(n^2)
	 * 
	 * 
	 * 
	 */
	public void process() {
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				map[i][j].proccessT(this.turn);

			}
		}
		this.turn++;
	}

	/**
	 * Public method that exports the Pj's path to the log
	 * 
	 * Complexity O(n)
	 * 
	 * @param bufOut
	 *            BufferedWriter
	 */
	public void writeInit(BufferedWriter bufOut) {
		write(this.mapPicture, bufOut);
		for (int i = 0; i < dimY * dimX; i++) {
			if (map[i / dimY][i % dimY].nPj() > 0) {
				write(map[i / dimY][i % dimY].showPathPj(), bufOut);
			}
		}
	}

	/**
	 * Private method which prints on the screen the information of every turn
	 * 
	 * Complexity O(n^2)
	 * 
	 * @param bufOut
	 *            Buffered writer
	 */
	public void writeTurn(BufferedWriter bufOut) {

		try {
			// Write Turn data

			bufOut.write("(turn:" + (this.turn - 1) + ")");
			bufOut.newLine();
			// Write Map data
			bufOut.write("(map:" + getTMap() + ")");
			bufOut.newLine();
			// Write Door data
			bufOut.write(door.showDoor());
			// Write Map look
			bufOut.write(toStringMap());

			// Write squares with keys
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j].nkeys() != 0) {
						bufOut.write(map[i][j].showKeys());
					}
				}
			}
			// Write Pjs
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j].nPj() != 0) {
						bufOut.write("(" + map[i][j].showPj());

					}
				}
			}

			if (addit.nPj() > 0) {
				bufOut.write("(thronemembers)\n");
				bufOut.write("(newking:" + addit.showPj());

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Private method which opens the buffered writer
	 * 
	 * Complexity O(n^2)
	 * 
	 * @param x
	 *            String that we want to print
	 * @param bufOut
	 *            BufferedWriter
	 */
	public void write(String x, BufferedWriter bufOut) {
		try {
			bufOut.write(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Public Method that paints the information of the map
	 * 
	 * Complexity O(n^2)
	 * 
	 * 
	 */
	public void paintMap() {
		// paint turn data
		System.out.println("(turn:" + (this.turn - 1) + ")");
		// Paint Map data
		System.out.println("(map:" + getTMap() + ")");
		// Paint door data
		System.out.print(door.showDoor());
		// Paint Map look
		System.out.print(toStringMap());
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
				if (map[i][j].nPj() != 0) {
					System.out.print("(" + map[i][j].showPj());
				}
			}
		}

		if (addit.nPj() > 0) {
			System.out.println(("(thronemembers)"));
			System.out.println(("(newking:" + addit.showPj()));

		}

	}

	/**
	 * Private method which prints on the screen the information above the map
	 * 
	 * Complexity O(n^2)
	 * 
	 * 
	 * @return String :Returns the string containing the map data
	 */
	public String printInitMap() {
		String x = "";
		x = toStringMap();
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