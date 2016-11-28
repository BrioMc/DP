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
<<<<<<< HEAD
	public Square[][] map;
	protected ThroneDoor door;
	protected int doorRoom;
	protected Square addit;
	protected ArrayList<Walls> walls;
	public static Map instance = null;
	protected Grafo graph;
=======
	private Square[][] map;
	private ThroneDoor door;
	private int doorRoom;
	private Square addit;
	private ArrayList<Walls> walls;
>>>>>>> refs/remotes/origin/master

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
<<<<<<< HEAD
		this.graph = new Grafo();
=======
>>>>>>> refs/remotes/origin/master
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
	 * Private method for initializing the walls
	 */
	private void iniWalls(){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(i!=map.length-1)
					walls.add(new Walls(map[i][j].getId(), map[i+1][j].getId()));
				
				if(i!=0)			
					walls.add(new Walls(map[i][j].getId(), map[i-1][j].getId()));
				
				if(j!=map.length-1)
					walls.add(new Walls(map[i][j].getId(), map[i][j+1].getId()));
				
				if(j!=0)			
					walls.add(new Walls(map[i][j].getId(), map[i][j-1].getId()));
			}
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
<<<<<<< HEAD
		return map[map.length - 1][map[0].length - 1].getId();
=======
		return map[map.length - 1][map.length - 1].getId();
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		return map[0][map[0].length - 1].getId();
=======
		return map[0][map.length - 1].getId();
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
=======
		
>>>>>>> refs/remotes/origin/master

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
			if (i == map.length - 1) {
				
			}
			

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
<<<<<<< HEAD
		int dimX = 5;
		int dimY = 5;
		int doorRoom = (dimX * dimY) - 1;
		int altLock = 3;
		// int maxTurns = 50;
		int numKeys = 15;

		// Creating and configuring the door. It is not specified here since
		// it was specified in the previous stage
		// Add the door to the map (it will be stored into the Throne square)

=======
		int dimX = 6;
		int dimY = 6;
		int salaPuerta = (dimX * dimY) - 1;
		int altLock = 3;
	//	int maxTurnos = 50;
		int numKeys = 15;
		Grafo g = new Grafo();
		
		//
		// // Creación y configuración de la Puerta. No se especifica aquí pues
		// se debe seguir

		// // lo indicado en la entrega anterior
		// // Añadir la puerta al mapa (quedará almacenada en la sala del Trono)
		// mapa.insertarPuerta(puerta);
		//
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD

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
		// map.distKeys(idSalasConLlaves);

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
	//	map.paintMap();
		map.showW();
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
=======
		// Creación del mapa
		// Parámetros: sala de la puerta, nº columnas, nº filas,
		// profundidad de la cerradura para la apertura de la puerta
		// El constructor del mapa deberá crear las diferentes salas de las que
		// consta
		Map mapa = new Map(salaPuerta, dimX, dimY, door);
		// Generar llaves y distribuirlas. En esta entrega, pasamos al mapa un
		// array
		// con los identificadores de las salas en las que se distribuirán las
		// llaves
		int[] idSalasConLlaves = { 3, 4, 6, 8, 9, 10, 11, 12, 13 };
		mapa.distKeys(idSalasConLlaves); // La generación de llaves la hará
		// el
		// mapa
		// La distribución de llaves quedará de la siguiente forma:
		// (sala:3: 0 1 1 2 3)
		// (sala:4: 3 4 5 5 6)
		// (sala:6: 7 7 8 9 9)
		// (sala:8: 10 11 11 12 13)
		// (sala:9: 13 14 15 15 16)
		// (sala:10: 17 17 18 19 19)
		// (sala:11: 20 21 21 22 23)
		// (sala:12: 23 24 25 25 26)
		// (sala:13: 27 27 28 29 29)

		// // Creación de personajes
		// // Creación de Stark
		// // Parámetros: nombre, marca, turno en el que debe comenzar a moverse
		// y sala inicial
		Stark starkE = new Stark("Eddard", 'E', 1, 0);

		// // Creación de la ruta de Stark:
		// // (ruta:E: S S E E N E N E S E S S O S E E)
		Dir[] direccionesE = { Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N,
				Dir.E, Dir.S, Dir.E, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E, Dir.S };
		starkE.setRutes(direccionesE);
		//
		// // Inserción de personaje en el mapa
		mapa.insertPj(starkE);
		//
		//
		// // Creación de Targaryen
		// // Parámetros: nombre, marca, turno en el que debe comenzar a moverse
		// y sala inicial
		Targaryen targaryenD = new Targaryen("Daenerys", 'D', 1, 0);
		// // (ruta:D: E S S S O S E E N E S S E E)
		Dir[] direccionesD = { Dir.E, Dir.S, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E,
				Dir.E, Dir.N, Dir.E, Dir.S, Dir.S, Dir.E, Dir.E };

		targaryenD.setRutes(direccionesD);
		// Inserción de personaje en la sala
		mapa.insertPj(targaryenD);

		// Creación de Caminante Blanco
		// Parámetros: nombre, marca, turno en el que debe comenzar a moverse
		// y sala inicial
		WhiteWalkers caminante = new WhiteWalkers("Caminante", 'C', 1,
				mapa.surW());
		// // (ruta:C: N N N E S E N N E N E E S S S S S )
		// EDLineal<Dir> direccionesC = {Dir.N, Dir.N, Dir.N, Dir.E, Dir.S,
		// Dir.E, Dir.N, Dir.N,
		// Dir.E, Dir.N, Dir.E, Dir.E, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S};
		// caminante.asignarRuta(direccionesC);
		// // Inserción de personaje en el mapa
		mapa.insertPj(caminante);

		// Creación de Lannister
		// Parámetros: nombre, marca, turno en el que debe comenzar a moverse
		// y sala inicial
		Lannister lannisterT = new Lannister("Tyrion", 'T', 1, mapa.getDRoom());
		// (ruta:T: N N O N N O S O O N N O S S S S S E E E E E )
		Dir[] direccionesT = { Dir.N, Dir.N, Dir.W, Dir.N, Dir.N, Dir.W, Dir.S,
				Dir.W, Dir.W, Dir.N, Dir.N, Dir.W, Dir.S, Dir.S, Dir.S, Dir.S,
				Dir.S, Dir.E, Dir.E, Dir.E, Dir.E, Dir.E };
		lannisterT.setRutes(direccionesT);
		// Inserción de personaje en el mapa
		mapa.insertPj(lannisterT);

		// Ejecución de la simulación
		// El método procesar se ejecutará turno a turno, recorriendo el mapa
		// desde la sala
		// de entrada hasta la de salida y los personajes almacenados en cada
		// sala ejecutarán
		// sus acciones según orden de llegada
		// for (int i = 0; i < MAXTURNOS; i++) {
		// mapa.procesar(i);
		// }

		mapa.paintMap();
	}

}

// // Creation of the map board
// // @param: room of the ThroneDoor, nº columns, nº rows y
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
// // Combinación de Keys que se insertarán en la ThroneDoor del Trono
// Key[] combination = new Key[numKeys];
// for (int i = 0; i < combination.length; i++) {
// combination[i] = new Key(listaIdKeys[i]);
// }
// // Crear la ThroneDoor del Trono
// ThroneDoor ThroneDoor = new ThroneDoor(combination, 4);
// // Configurar la ThroneDoor introduciendo la combinación de Keys
// // ThroneDoor.configurar(combination);
// // Cerrar la ThroneDoor, por si inicialmente está abierta
// // ThroneDoor.cerrar();
// // Añadir la ThroneDoor al mapa
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
// // mapa.pintar(); // se mostrará en este caso únicamente la información
// // del
// // mapa
// // Realizar más pruebas
//
// // TODO
// }
// }
>>>>>>> refs/remotes/origin/master
