package map;

import pj.Lannister;
import pj.Pj;
import pj.Stark;
import pj.Targaryen;
import pj.WhiteWalkers;
import door.Key;
import door.ThroneDoor;
import java.util.ArrayList;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */

public class Map {
	private int turn;
	public Square[][] map;
	protected ThroneDoor door;
	protected int doorRoom;
	protected Square addit;
	protected ArrayList<Walls> walls;
	public static Map instance = null;
	protected Grafo graph;

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

	public static void generateInstance(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		instance = new Map(doorRoom, dimX, dimY, door);
	}

	public Map(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		this.map = new Square[dimX][dimY];
		iniMap();
		this.turn = 0;
		this.door = door;
		this.doorRoom = doorRoom;
		this.addit = new Square(1111);
		this.walls = new ArrayList<Walls>();
		iniWalls();
		this.graph = new Grafo();
	}

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

					walls.add(new Walls(map[i][j].getId(), map[i - 1][j].getId()));
				if (j != map[i].length - 1)
					walls.add(new Walls(map[i][j].getId(), map[i][j + 1].getId()));
				if (i != map.length - 1)
					walls.add(new Walls(map[i][j].getId(), map[i + 1][j].getId()));

				if (j != 0)
					walls.add(new Walls(map[i][j].getId(), map[i][j - 1].getId()));

			}
		}
	}

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
		// TODO Auto-generated method stub
		return addit;
	}

	/**
	 * 
	 * @return
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
	 * Return the map size
	 * 
	 * @return map size
	 */
	public int getTMap() {
		return (this.map.length * this.map[0].length) - 1;
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
	 * Public Method for paint map summary
	 */
	public void paintMap() {
		System.out.println("(turno:" + this.turn + ")");
		System.out.println("(mapa:" + getTMap() + ")");
		door.showDoor();
		// walls, N E S W
		boolean[] wa = new boolean[2];
		int n = 0, m = 0;

		for (int x = 0; x < map[0].length; x++) {

			System.out.print(" _");
		}
		System.out.println();
		for (int i = 0; i < map.length; i++) {

			System.out.print("|");

			for (int j = 0; j < map[i].length; j++) {
				// E
				wa[0] = false;
				// S
				wa[1] = false;

				// charge wallBool
				for (int z = 0; z < walls.size(); z++) {
					n = walls.get(z).getOrigin() / map[i].length;
					m = walls.get(z).getOrigin() % map[i].length;
					if (n == i && m == j) {
						n = walls.get(z).getDestination() / map[i].length;
						m = walls.get(z).getDestination() % map[i].length;
						if (i < n) {
							// S
							wa[1] = true;
						}
						if (j < m) {
							// E
							wa[0] = true;
						}
					}
					n = walls.get(z).getDestination() / map[i].length;
					m = walls.get(z).getDestination() % map[i].length;

					if (n == i && m == j) {
						n = walls.get(z).getOrigin() / map[i].length;
						m = walls.get(z).getOrigin() % map[i].length;
						if (i > n) {
							// S
							wa[1] = true;
						}
						if (j > m) {
							// E
							wa[0] = true;
						}
					}

				}
				// Show if have 1 pj only in square
				if (map[i][j].nPj() == 1) {
					if (j != map[i].length - 1) {
						System.out.print(map[i][j].takePj().getTag());
						if (wa[0]) {
							System.out.print("|");

						} else {

							System.out.print(" ");
						}
					} else {
						System.out.print(map[i][j].takePj().getTag());

					}
					// Show if have more that 1 pj in square

				} else if (map[i][j].nPj() > 1) {
					if (j != map[0].length - 1) {
						System.out.print(map[i][j].nPj());
						if (wa[0]) {
							System.out.print("|");

						} else {

							System.out.print(" ");
						}

					} else {
						System.out.print(map[i][j].nPj());

					}

				} // Print if square is empty
				else {

					if (j != map[0].length - 1) {
						// Paint botton wall if exist
						if (wa[1]) {
							System.out.print("_");

						} else {

							System.out.print(" ");
						}
						// Paint left wall if exist

						if (wa[0]) {
							System.out.print("|");

						} else {
							System.out.print(" ");
						}

					} else {

						if (wa[1]) {
							System.out.print("_");

						} else {

							System.out.print(" ");
						}

					}

				}

			}

			System.out.print("|");

			System.out.println("");

		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].nkeys() != 0) {
					map[i][j].showKeys();
				}

			}
		}
	}

	public static void main(String args[]) {
		int dimX = 6;
		int dimY = 8;
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
		map.paintMap();

	}

}