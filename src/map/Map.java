package map;

import pj.Lannister;
import pj.Pj;
import pj.Stark;
import pj.Targaryen;
import pj.WhiteWalkers;
import door.Key;
import door.ThroneDoor;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2�
 */

public class Map {
	private int turn;
	private Square[][] map;
	private ThroneDoor door;
	private int doorRoom;
	private Square addit;

	/**
	 * Constructor for the class Map
	 *
	 * @param doorRoom The room where the throne door is located
	 * @param dimX     Dimension of the X axis (Columns of the map)
	 * @param dimY	   Dimension of the Y axis (Rows of the map)
	 * @param door	   A previously created door of the throne.
	 */
	public Map(int doorRoom, int dimX, int dimY, ThroneDoor door) {
		this.map = new Square[dimX][dimY];
		iniMap();
		this.turn = 0;
		this.door = door;
		this.doorRoom = doorRoom;
		this.addit = new Square(1111);

	}

	/**
	 * Public method for initializing the map
	 */
	private void iniMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = new Square(i * map.length + j);
			}

		}
	}

	/**
	 * Public method that returns the door of the throne
	 *
	 * @return {@code ThroneDoor} door
	 */
	public ThroneDoor getDoor() {
		return door;
	}

	/**
	 * Public method, return Throne room
	 *
	 * @return
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
		return (this.map.length * this.map.length) - 1;
	}

	/**
	 * 
	 * @return
	 */
	public int southE() {
		return map[map.length - 1][map.length - 1].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int southW() {
		return map[map.length - 1][0].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int northW() {
		return map[0][0].getId();
	}

	/**
	 * 
	 * @return
	 */
	public int northE() {
		return map[0][map.length - 1].getId();
	}

	/**
	 * Public method for insert pj in map
	 * 
	 * @param pj
	 */
	public void insertPj(Pj pj) {
		int c = pj.getRoom();
		map[c / map.length][c % map.length].insertPj(pj);

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
		boolean tst;

		for (int i = 0; i < rooms.length; i++) {
			x = rooms[i] / map[0].length;
			y = rooms[i] % map[0].length;
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
	 * Public Method for paint map summary
	 */
	public void paint() {
		System.out.println("(turno:" + this.turn + ")");
		System.out.println("(mapa:" + getTMap() + ")");
		door.showDoor();
		for (int x = 0; x < map.length; x++) {

			System.out.print(" _");
		}
		System.out.println();
		for (int i = 0; i < map.length; i++) {

			System.out.print("|");

			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].nPj() == 1) {
					if (j != map[0].length - 1) {
						System.out.print(map[i][j].takePj().getTag() + " ");

					} else {
						System.out.print(map[i][j].takePj().getTag());

					}

				} else if (map[i][j].nPj() > 1) {
					if (j != map[0].length - 1) {
						System.out.print(map[i][j].nPj() + " ");

					} else {
						System.out.print(map[i][j].nPj());

					}

				} else {
					if (j != map[0].length - 1) {
						System.out.print(" " + " ");

					} else {
						System.out.print(" ");

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
		int dimY = 6;
		int throneRoom = (dimX * dimY) - 1;
		int altLock = 3;
		int MAXTURNS = 50;
		int numKeys = 15;
		//
		// // Creation and configuration of the door. It's not especified here because
		// it must continue the indicated in the previous delivery
		//  Add a door to the map (that will be stored into the throne room)
		// mapa.insertarPuerta(puerta);
		//
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
		// Creating the map
		// Parameters: door square, columns number, rows number,
		// depth for the lock
		// The constructor must create the different squares for the map
		Map map = new Map(throneRoom, dimX, dimY, door);
		// Generate the keys and distribute them. In this stage, we pass to the map an array
		// with the identifiers of the squares where the keys are going to be distributed
				int [] idSquaresWithKeys = {3,4,6,8,9,10,11,12,13};
				map.distKeys(idSquaresWithKeys);
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


		// Creating and configuring the door. It is not specified here since
		// it was specified in the previous stage
		// Add the door to the map (it will be stored into the Throne square)

		// Creating the characters
		// Creating a Stark
		// Parameters: name, mark, turn in which it will start the simulation and initial square
				Stark starkE = new Stark("Eddard", 'E', 1, 0);
		// Creating the route for the Stark:
		// (route:E: S S E E N E N E S E S S W S E E)
				Dir[] directionsE = {Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N, Dir.E, Dir.S,
						Dir.E, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E, Dir.S};
				starkE.setRoutes(directionsE);

		// Adding the character into the map
				map.insertPj(starkE);


		// Creating a Targaryen
		// Parameters: name, mark, turn in which it will start the simulation and initial square
				Targaryen targaryenD = new Targaryen("Daenerys", 'D', 1, 0);
		// (route:D: E S S S W S E E N E S S E E)
				Dir[] directionsD = {Dir.E, Dir.S, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E, Dir.E, Dir.N,
						Dir.E, Dir.S, Dir.S, Dir.E, Dir.E};


				targaryenD.setRoutes(directionsD);
		// Adding the character into the map

		map.insertPj(targaryenD);


		// Creating a White Walker
		// Parameters: name, mark, turn in which it will start the simulation and initial square
			WhiteWalkers walker = new WhiteWalkers("Walker", 'W', 1, map.southW());
		// (route:C: N N N E S E N N E N E E S S S S S )
			Dir[] directionsW = {Dir.N, Dir.N, Dir.N, Dir.E, Dir.S, Dir.E, Dir.N, Dir.N,
				Dir.E, Dir.N, Dir.E, Dir.E, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S};
		walker.setRoutes(directionsW);
		// Adding the character into the map
		map.insertPj(walker);


		// Creating a Lannister
		// Parameters: name, mark, turn in which it will start the simulation and initial square
		Lannister lannisterT = new Lannister("Tyrion", 'T', 1, map.getDRoom());
		// (ruta:T: N N W N N W S W W N N W S S S S S E E E E E )
			Dir[] directionsT = {Dir.N, Dir.N, Dir.W, Dir.N, Dir.N, Dir.W, Dir.S, Dir.W,
					Dir.W, Dir.N, Dir.N, Dir.W, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S,
					Dir.E, Dir.E, Dir.E, Dir.E, Dir.E};
		lannisterT.setRoutes(directionsT);
		// Adding the character into the map
		map.insertPj(lannisterT);


		map.paint();
		// Executing the simulation
		// The process method must be executed turn after turn, traversing the map from square 0
		// to the last square and the characters stored in each square must execute their actions
		// in a chronologically order (the characters that arrived first are the first in leaving the square)
	//	for (int i=0; i<maxturns;i++) {
	//		map.process(i);
	//	}


		map.paint();
	}
}

// // Creation of the map board
// // @param: room of the ThroneDoor, n� columns, n� rows y
// // depth of
// // and secret combination
// int dimX = 6;
// int dimY = 6;
// @SuppressWarnings("unused")
// int salaThroneDoor = (dimX * dimY) - 1;
// @SuppressWarnings("unused")
// int treeHeight = 4;
// // Map map = new Map(salaThroneDoor, dimX, dimY, treeHeight);
//
// // Algorithm which reorders the identifiers in order to create the keys
// // in a correct order
// // listaIdKeys = map.generarcombination(listaIdKeys);
// // Combinaci�n de Keys que se insertar�n en la ThroneDoor del Trono
// Key[] combination = new Key[numKeys];
// for (int i = 0; i < combination.length; i++) {
// combination[i] = new Key(listaIdKeys[i]);
// }
// // Crear la ThroneDoor del Trono
// ThroneDoor ThroneDoor = new ThroneDoor(combination, 4);
// // Configurar la ThroneDoor introduciendo la combinaci�n de Keys
// // ThroneDoor.configurar(combination);
// // Cerrar la ThroneDoor, por si inicialmente est� abierta
// // ThroneDoor.cerrar();
// // A�adir la ThroneDoor al mapa
// // mapa.insertarThroneDoor(ThroneDoor);
// // Realizar distintas pruebas de apertura de la ThroneDoor
// for (int i = 0; !ThroneDoor.estaAbierta(); i++) {
// Key Key = new Key(i);
// ThroneDoor.open(Key);
// System.out.println(i);
//
// }
// // Cuando se abra la ThroneDoor mostramos mensaje de apertura de la
// // ThroneDoor
// if (ThroneDoor.estaAbierta()) {
// System.out.println("The Door of the Throne has been opened");
// } else {
// System.out.println("The Door of the Throne stays closed");
// }
// ThroneDoor.showTested();
// // mapa.pintar(); // se mostrar� en este caso �nicamente la informaci�n
// // del
// // mapa
// // Realizar m�s pruebas
//
// // TODO
// }
// }
