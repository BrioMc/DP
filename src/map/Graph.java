package map;

import java.util.Set;

/**
 * @file grafo.h Declaracion de la clase grafo
 * @author <b> Profesores DP </b><br>
 *         <b> Asignatura Desarrollo de Programas</b><br>
 *         <b> Curso 11/12 </b>
 */
public class Graph {
	private static final int MAXVERT = 115;
	private static final int INFINITE = 9999;
	private static final int NOVALUE = -1;

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
	 */
	public void showArcs() {
		int x, y;
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
	 * 
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
	 * @return sig  Parametro de entrada salida que devuelve el siguiente node en
	 *        		    la ruta entre origin y destination
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
	 * 
	 * @param origin : square id from origin
	 * @param destination : square id from destination
	 * @return return distance between origin and destination
	 */
	public int distance(int origin, int destination) {
		int i = 1;
		if (origin == destination) {
			i = 0;
		} else {
			int nex = next(origin, destination);

			i += distance(nex, destination);

		}

		return i;
	}


	/**
	 * Metodo que comprueba si el grafo esta vacio
	 *
	 *@return Retorna un valor booleano que indica si el grafo esta o no vacio
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
	 * 
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
	 * 
	 */
	void floyd() {
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
	 * 
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
	 * 
	 */
	void warshall() {
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
	 * 
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
