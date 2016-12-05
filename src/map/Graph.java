package map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.xml.ws.RequestWrapper;

/**
 * @file grafo.h Declaracion de la clase grafo
 * @author <b> Profesores DP </b><br>
 *         <b> Asignatura Desarrollo de Programas</b><br>
 *         <b> Curso 11/12 </b>
 */
public class Graph {
	public static final int MAXVERT = 115;
	public static final int INFINITE = 9999;
	public static final int NOVALUE = -1;

	/** Numero de nodes del grafo */
	private int numNodes;

	/** Vector que almacena los nodes del grafo */
	private int[] nodes = new int[MAXVERT];

	/** Matriz de adyacencia, para almacenar los arcos del grafo */
	private int[][] arcs = new int[MAXVERT][MAXVERT];

	/** Matriz de Camino (Warshall - Path) */
	private boolean[][] warshallC = new boolean[MAXVERT][MAXVERT];

	/** Matriz de Costes (Floyd - Cost) */
	private int[][] floydC = new int[MAXVERT][MAXVERT];

	/** Matriz de Camino (Floyd - Path) */
	private int[][] floydP = new int[MAXVERT][MAXVERT];

	/**
	 * Metodo constructor por defecto de la clase grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public Graph() {
		int x, y;
		numNodes = 0;

		for (x = 0; x < MAXVERT; x++)
			nodes[x] = x;

		for (x = 0; x < MAXVERT; x++)
			for (y = 0; y < MAXVERT; y++) {
				arcs[x][y] = INFINITE;
				warshallC[x][y] = false;
				floydC[x][y] = INFINITE;
				floydP[x][y] = NOVALUE;
			}

		// Diagonales
		for (x = 0; x < MAXVERT; x++) {
			arcs[x][x] = 0;
			warshallC[x][x] = false;
			floydC[x][x] = 0;
			// floydP[x][x] = NOVALOR;
		}
	}

	/**
	 * 
	 * @param n
	 */
	public Graph(int n) {
		int x, y;
		numNodes = n;

		for (x = 0; x < MAXVERT; x++)
			nodes[x] = x;

		for (x = 0; x < MAXVERT; x++)
			for (y = 0; y < MAXVERT; y++) {
				arcs[x][y] = INFINITE;
				warshallC[x][y] = false;
				floydC[x][y] = INFINITE;
				floydP[x][y] = NOVALUE;
			}
		for (x = 0; x < MAXVERT; x++)

		{
			arcs[x][x] = 0;
			warshallC[x][x] = false;
			floydC[x][x] = 0;
			// floydP[x][x] = NO_VALOR;
		}
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	private Square getSquare(Integer i) {
		Map map = Map.getInstance();
		Square x;
		x = map.map[i / map.getWidth()][i % map.getWidth()];
		return x;
	}

	private void fillArcs() {
		Map map = Map.getInstance();
		ArrayList<Walls> x = map.getWalls();
		for (int i = 0; i < x.size(); i++) {
			Walls m = x.get(i);
			newArc(m.getOrigin(), m.getDestination(), 0);
		}
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
			this.adyacentes(node.getId(), ady);
			if (!ady.isEmpty()) {
				for (Integer i : ady)
					markPropagation(getSquare(i), mark);
			}
		}
	}

	/**
	 * 
	 */
	public void doAtach() {
		Map map = Map.getInstance();
		int count = 0;
		// Other positions
		while (count < (numNodes * 0.05) / 1) {
			// take random square
			int rnd = GenAleatorios.generarNumero(map.getTMap());
			int i = rnd / map.getWidth();
			int j = rnd % map.getWidth();
			int nw = rnd - map.getWidth() - 1;
			int ne = rnd - map.getWidth() + 1;
			int sw = rnd + map.getWidth() - 1;
			int se = rnd + map.getWidth() + 1;
			int s = rnd + map.getWidth();
			int w = rnd - 1;
			int e = rnd + 1;
			int n = rnd - map.getWidth();
			boolean[] x = Map.getInstance().retWalls(i, j);
			// N
			if (x[0]) {
				if (getArc(rnd, w) != 1 || getArc(w, nw) != 1 || getArc(nw, n) != 1 || getArc(rnd, e) != 1
						|| getArc(n, ne) != 1 || getArc(e, ne) != 1) {
					newArc(rnd, n, 1);
					newArc(n, rnd, 1);
					map.walls.remove(new Walls(rnd, n));
					count++;
				}
			} // S
			else if (x[1]) {
				if (getArc(rnd, w) != 1 || getArc(w, sw) != 1 || getArc(sw, s) != 1 || getArc(s, se) != 1
						|| getArc(se, e) != 1 || getArc(e, rnd) != 1) {
					newArc(rnd, s, 1);
					newArc(s, rnd, 1);
					map.walls.remove(new Walls(rnd, s));
					count++;
				}

			} // W
			else if (x[2]) {
				if (getArc(rnd, n) != 1 || getArc(n, nw) != 1 || getArc(nw, w) != 1 || getArc(w, sw) != 1
						|| getArc(sw, s) != 1 || getArc(s, rnd) != 1) {
					newArc(rnd, w, 1);
					newArc(w, rnd, 1);
					map.walls.remove(new Walls(rnd, w));
					count++;
				}
			} // E
			else {
				if (getArc(rnd, n) != 1 || getArc(n, ne) != 1 || getArc(ne, e) != 1 || getArc(e, se) != 1
						|| getArc(se, s) != 1 || getArc(s, rnd) != 1) {
					newArc(rnd, e, 1);
					newArc(e, rnd, 1);
					map.walls.remove(new Walls(rnd, e));
					count++;
				}
			}
		}
	}

	/**
	 * 
	 */
	protected void Kruskal() {
		Map map = Map.getInstance();
		ArrayList<Walls> selected = new ArrayList<>();
		int rnd; // random number
		int mark;
		Walls aux;
		Square origin;
		Square destination;
		// fillArcs();

		while (!map.walls.isEmpty()) {
			map.paintMarks();
			rnd = GenAleatorios.generarNumero(map.walls.size());
			aux = map.walls.remove(rnd);
			origin = getSquare(aux.getOrigin());
			destination = getSquare(aux.getDestination());

			if (origin.getMark() != destination.getMark()) {

				this.newArc(origin.getId(), destination.getId(), 1);
				this.newArc(destination.getId(), origin.getId(), 1);
				// mark = map.map[origin.getId() /
				// map.getLength()][origin.getId() % map.getWidth()].getMark();
				mark = getSquare(aux.getDestination()).getMark();
				markPropagation(origin, mark);

			} else {
				// if ((this.getArc(origin.getId(), destination.getId()) == 1)
				// || (this.getArc(destination.getId(), origin.getId()) == 1)) {
				selected.add(aux);

				// }
			}

		}
		map.walls = selected;
	}

	/**
	 * Metodo que inserta un nuevo arco en el grafo
	 * 
	 * @param origin
	 *            es el node de origin del arco nuevo
	 * @param destination
	 *            es el node de destination del arco nuevo
	 * @param value
	 *            es el peso del arco nuevo
	 * @return true si se pudo insertar
	 */
	public boolean newArc(int origin, int destination, int value) {
		boolean result = false;
		if ((origin >= 0) && (origin < numNodes) && (destination >= 0) && (destination < numNodes)) {
			arcs[origin][destination] = value;
			result = true;
		}
		return result;
	}

	/**
	 * Metodo que retorna el peso de un arco
	 * 
	 * @param origin
	 *            es el primer node del arco
	 * @param destination
	 *            es el segundo node del arco
	 * @return Retorna un valor entero que contiene el peso del arco
	 */
	public int getArc(int origin, int destination) {
		int arco = NOVALUE;
		if ((origin >= 0) && (origin < numNodes) && (destination >= 0) && (destination < numNodes))
			arco = arcs[origin][destination];
		return arco;
	}

	/**
	 * Metodo que borra un arco del grafo
	 * 
	 * @param origin
	 *            es el node de origin del arco nuevo
	 * @param destination
	 *            es el node de destination del arco nuevo
	 * @return true si se pudo borrar
	 */
	public boolean deleteArc(int origin, int destination) {
		boolean resultado = false;
		if ((origin >= 0) && (origin < numNodes) && (destination >= 0) && (destination < numNodes)) {
			arcs[origin][destination] = INFINITE;
			resultado = true;
		}
		return resultado;

	}

	/**
	 * Metodo que muestra los arcos del grafo (la matriz de adyacencia)
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void showArcs() {
		int x, y;
		fillArcs();
		System.out.println("ARCOS:");
		for (x = 0; x < numNodes; x++) {
			for (y = 0; y < numNodes; y++) {
				// cout.width(3);
				if (arcs[x][y] != INFINITE)
					System.out.format("%4d", arcs[x][y]);
				else
					System.out.format("%4s", "#");
			}
			System.out.println();
		}
	}

	/**
	 * Metodo que inserta un nuevo node en el grafo
	 * 
	 * @param n
	 *            es el node que se desea insertar
	 * @return true si se pudo insertar
	 */
	public boolean newnode(int n) {
		boolean resultado = false;

		if (numNodes < MAXVERT) {
			nodes[numNodes] = n;
			numNodes++;
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Metodo que elimina un node del grafo
	 * 
	 * @param node
	 *            node que se desea eliminar
	 * @return true si se pudo borrar
	 */
	public boolean deleteNode(int node) {
		boolean resultado = false;
		int pos = node;

		if ((numNodes > 0) && (pos <= numNodes)) {
			int x, y;
			// Borrar el node
			for (x = pos; x < numNodes - 1; x++) {
				nodes[x] = nodes[x + 1];
				System.out.println(nodes[x + 1]);
			}
			// Borrar en la Matriz de Adyacencia
			// Borra la fila
			for (x = pos; x < numNodes - 1; x++)
				for (y = 0; y < numNodes; y++)
					arcs[x][y] = arcs[x + 1][y];
			// Borra la columna
			for (x = 0; x < numNodes; x++)
				for (y = pos; y < numNodes - 1; y++)
					arcs[x][y] = arcs[x][y + 1];
			// Decrementa el numero de nodes
			numNodes--;
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Metodo que muestra el vector de nodes del grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void shownodes() {
		System.out.println("nodeS:");
		for (int x = 0; x < numNodes; x++)
			System.out.print(nodes[x] + " ");
		System.out.println();
	}

	/**
	 * Metodo que devuelve el siguiente node en la ruta entre un origin y un
	 * destination
	 * 
	 * @param origin
	 *            es el primer node
	 * @param destination
	 *            es el segundo node
	 * @param origin
	 *            es el primer node
	 * @param sig
	 *            parametro de entrada salida que devuelve el siguiente node en
	 *            la ruta entre origin y destination
	 * @return No retorna ningun valor
	 */
	public int next(int origin, int destination) {
		int sig = -1; // Si no hay camino posible
		if ((origin >= 0) && (origin < numNodes) && (destination >= 0) && (destination < numNodes)) {
			if (warshallC[origin][destination]) { // Para comprobar que haya
													// camino
				if (floydP[origin][destination] != NOVALUE)
					sig = next(origin, floydP[origin][destination]);
				else
					sig = destination;
			}
		}
		return sig;
	}

	/**
	 * Metodo que comprueba si el grafo esta vacio
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return Retorna un valor booleano que indica si el grafo esta o no vacio
	 */
	public boolean isEmpty() {
		return (numNodes == 0);
	}

	/**
	 * Metodo que comprueba si dos nodes son adyacentes
	 * 
	 * @param origin
	 *            es el primer node
	 * @param destination
	 *            es el segundo node
	 * @return Retorna un valor booleano que indica si los dos nodes son
	 *         adyacentes
	 */
	public boolean adyacente(int origin, int destination) {
		boolean resultado = false;
		if ((origin >= 0) && (origin < numNodes) && (destination >= 0) && (destination < numNodes))
			resultado = (arcs[origin][destination] != INFINITE);
		return resultado;
	}

	/**
	 * Metodo que devuelve el conjunto de nodes adyacentes al node actual
	 * 
	 * @param origin
	 *            es el node actual
	 * @param ady
	 *            En este conjunto se almacenarán los nodes adyacentes al node
	 *            origin
	 * @return No retorna ningun valor
	 */
	public void adyacentes(int origin, Set<Integer> ady) {
		if ((origin >= 0) && (origin < numNodes)) {
			for (int i = 0; i < numNodes; i++) {
				if ((arcs[origin][i] != INFINITE) && (arcs[origin][i] != 0))
					ady.add(i);
			}
		}
	}

	/**
	 * Metodo que realiza el algoritmo de Floyd sobre el grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void floyd() {
		int i, j, k;

		// Obtener la matriz de adyacencia en P
		for (i = 0; i < numNodes; i++)
			for (j = 0; j < numNodes; j++) {
				floydC[i][j] = arcs[i][j];
				floydP[i][j] = NOVALUE;
			}

		// Iterar
		for (k = 0; k < numNodes; k++)
			for (i = 0; i < numNodes; i++)
				for (j = 0; j < numNodes; j++)
					if (i != j)
						if ((floydC[i][k] + floydC[k][j] < floydC[i][j])) {
							floydC[i][j] = floydC[i][k] + floydC[k][j];
							floydP[i][j] = k;
						}
	}

	/**
	 * Metodo que muestra las matrices de coste y camino de Floyd
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void showFloydC() {
		int x, y;
		System.out.println("floydC:");
		for (y = 0; y < numNodes; y++) {
			for (x = 0; x < numNodes; x++) {
				// cout.width(3);
				System.out.format("%4d", floydC[x][y]);
			}
			System.out.println();
		}

		System.out.println("floydP:");
		for (x = 0; x < numNodes; x++) {
			for (y = 0; y < numNodes; y++) {
				// cout.width(2);
				System.out.format("%4d", floydP[x][y]);
			}
			System.out.println();
		}
	}

	/**
	 * Metodo que realiza el algoritmo de Warshall sobre el grafo
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void warshall() {
		int i, j, k;

		// Obtener la matriz de adyacencia en P
		for (i = 0; i < numNodes; i++)
			for (j = 0; j < numNodes; j++)
				warshallC[i][j] = (arcs[i][j] != INFINITE);

		// Iterar
		for (k = 0; k < numNodes; k++)
			for (i = 0; i < numNodes; i++)
				for (j = 0; j < numNodes; j++)
					warshallC[i][j] = (warshallC[i][j] || (warshallC[i][k] && warshallC[k][j]));
	}

	/**
	 * Metodo que muestra la matriz de Warshall
	 * 
	 * @param ""
	 *            No recibe parametros
	 * @return No retorna ningun valor
	 */
	public void showPW() {
		int x, y;

		System.out.println("warshallC:");
		for (x = 0; x < numNodes; x++) {
			for (y = 0; y < numNodes; y++)
				System.out.format("%6b", warshallC[x][y]);
			System.out.println();
		}
	}

}
