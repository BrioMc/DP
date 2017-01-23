package api;

/**
 * Group: We are your waifu Members: Ignacio Caro Cumplido Javier Ballesteros
 * Moron
 */

public class Tree<T extends Compare<T>> {

	/** Data stored in the tree node. */
	private T rootData;

	/** Attribute which states wether the tree is empty or not. */
	private boolean isEmpty;

	/** Left son of the current node */
	private Tree<T> tLeft;

	/** Right son of the current node */
	private Tree<T> tRight;

	/**
	 * Defaultconstructor of the class. Initializes an empty tree.
	 */
	public Tree() {
		this.isEmpty = true;
		this.tLeft = null;
		this.tRight = null;
	}

	/**
	 * Creates a new tree with the data received as parameters.
	 * 
	 * @param tLeft
	 *            The left son of the tree which is being created
	 * @param rootData
	 *            Root of the tree which is being created
	 * @param tRight
	 *            Right son of the tree which is being created
	 */

	public Tree(Tree<T> tLeft, T rootData, Tree<T> tRight) {
		this.isEmpty = false;
		this.rootData = rootData;
		this.tLeft = tLeft;
		this.tRight = tRight;
	}

	/**
	 * Returns the left son of the tree
	 * 
	 * @return the left son
	 */
	private Tree<T> getTLeft() {
		return tLeft;
	}

	/**
	 * Returns the right son of the tree
	 * 
	 * @return Right son of the tree
	 */
	private Tree<T> getTRight() {
		return tRight;
	}

	/**
	 * Returns the root of the tree
	 * 
	 * @return Root of the tree
	 */
	private T getRoot() {
		return rootData;
	}

	/**
	 * Checks wether the tree is empty.
	 * 
	 * @return true if the tree is empty, false if not.
	 */
	private boolean empty() {
		return isEmpty;
	}

	/**
	 * Inserts new data in the tree.
	 * 
	 * @param data
	 *            The data to be inserted
	 * @return true if the data has been inserted correctly, false if not
	 */
	public boolean insert(T data) {
		boolean result = true;
		if (empty()) {
			rootData = data;
			isEmpty = false;
		} else {
			if (!(this.rootData.isEqual(data))) {
				Tree<T> aux;
				if (data.compareTo(rootData) < 0) { // data < rootData
					if ((aux = getTLeft()) == null)
						tLeft = aux = new Tree<T>();
				} else { // data > rootData
					if ((aux = getTRight()) == null)
						tRight = aux = new Tree<T>();
				}
				result = aux.insert(data);
			} else
				result = false;
		}
		return result;
	}

	/**
	 * Checks if a certain data is stored in the tree
	 * 
	 * @param data
	 *            The data to search
	 * @return true if the data is found in the tree, false if not
	 */
	public boolean belongs(T data) {
		Tree<T> aux = null;
		boolean found = false;
		if (!empty()) {
			if (this.rootData.isEqual(data))
				found = true;
			else {
				if (data.compareTo(this.rootData) < 0) // data < rootData
					aux = getTLeft();
				else
					// data > rootData
					aux = getTRight();
				if (aux != null)
					found = aux.belongs(data);
			}
		}
		return found;
	}

	/**
	 * Deletes a data from the tree.
	 * 
	 * @param data
	 *            The data we want to remove
	 */
	public void remove(T data) {
		if (!empty()) {
			if (data.compareTo(this.rootData) < 0) { // data<rootData
				if (tLeft != null)
					tLeft = tLeft.pRemove(data);
			} else if (data.compareTo(this.rootData) > 0) { // data>rootData
				if (tRight != null)
					tRight = tRight.pRemove(data);
			} else // En este caso el data es rootData
			{
				if (tLeft == null && tRight == null) {
					isEmpty = true;
				} else
					pRemove(data);
			}
		}
	}

	/**
	 * removes data. Este metodo es utilizado por el metodo remove anterior.
	 * 
	 * @param data
	 *            The data to be removed
	 * @return Returns the tree without the data
	 */
	private Tree<T> pRemove(T data) {
		T dataaux;
		Tree<T> vreturned = this;
		Tree<T> tremove, candidate, antecessor;

		if (!empty()) {
			if (data.compareTo(this.rootData) < 0) { // data<rootData
				if (tLeft != null)
					tLeft = tLeft.pRemove(data);
			} else if (data.compareTo(this.rootData) > 0) { // data>rootData
				if (tRight != null)
					tRight = tRight.pRemove(data);
			} else {
				tremove = this;
				if ((tRight == null) && (tLeft == null)) { /* if it's leaf */
					vreturned = null;
				} else {
					if (tRight == null) { /* Only the left son */
						tremove = tLeft;
						dataaux = this.rootData;
						rootData = tLeft.getRoot();
						tLeft.rootData = dataaux;
						tLeft = tLeft.getTLeft();
						tRight = tremove.getTRight();

						vreturned = this;
					} else {
						if (tLeft == null) { /* Only the right son */
							tremove = tRight;
							dataaux = rootData;
							rootData = tRight.getRoot();
							tRight.rootData = dataaux;
							tRight = tRight.getTRight();
							tLeft = tremove.getTLeft();

							vreturned = this;
						} else { /* It has two sons */
							candidate = this.getTLeft();
							antecessor = this;
							while (candidate.getTRight() != null) {
								antecessor = candidate;
								candidate = candidate.getTRight();
							}

							/* Data sharing of candidates */
							dataaux = rootData;
							this.rootData = (T) candidate.getRoot();
							candidate.rootData = dataaux;
							tremove = candidate;
							if (antecessor == this)
								tLeft = candidate.getTLeft();
							else
								antecessor.tRight = candidate.getTLeft();
						} // Eliminar solo ese nodo, no todo el subTree
					}
					tremove.tLeft = null;
					tremove.tRight = null;
				}
			}
		}
		return vreturned;
	}

	/**
	 * 
	 * 
	 * @return String InOrder travel in the door.
	 */
	public String inOrden() {
		Tree<T> aux = null;
		String m = "";
		if (!empty()) {
			if ((aux = getTLeft()) != null) {
				m += aux.inOrden();
			}

			m += (" " + this.rootData.toString());

			if ((aux = getTRight()) != null) {
				m += aux.inOrden();
			}
		}
		return m;
	}

	public int depth() {
		int depth = 1;
		int rdepth = 0;
		int ldepth = 0;

		Tree<T> aux = null;
		if (!empty()) {
			if ((aux = getTLeft()) != null) {
				ldepth = aux.pdepth(depth);
			}

			if ((aux = getTRight()) != null) {
				rdepth = aux.pdepth(depth);
			}
			if (ldepth > depth)
				depth = ldepth;
			if (rdepth > depth)
				depth = rdepth;
		}
		return depth;
	}

	private int pdepth(int depth) {

		int ldepth = 0;
		int rdepth = 0;
		Tree<T> aux = null;
		if (!empty()) {
			depth++;
			if ((aux = getTLeft()) != null) {
				ldepth = aux.pdepth(depth);
			}

			if ((aux = getTRight()) != null) {
				rdepth = aux.pdepth(depth);
			}
			if (ldepth > depth)
				depth = ldepth;
			if (rdepth > depth)
				depth = rdepth;
		}
		return depth;
	}

	private boolean isLeaf(T data) {
		Tree<T> aux = null;
		boolean leaf = false;
		if (!empty()) {
			if (this.rootData.isEqual(data))
				if (tLeft == null && tRight == null)
					leaf = true;
				else {
					if (data.compareTo(this.rootData) < 0) // data < rootData
						aux = getTLeft();
					else
						// data > rootData
						aux = getTRight();
					if (aux != null)
						leaf = aux.belongs(data);
				}
		}
		return leaf;
	}

	// TODO
	public int nNodos() {
		return countNodes(this);
	}

	public int nHojas() {
		return countLeaf(this);
	}

	private int countNodes(Tree<T> Tree) {
		int total = 0;
		if (Tree != null) {
			total++;
			total += countNodes(Tree.getTLeft());
			total += countNodes(Tree.getTRight());
		}
		return total;
	}

	private int countLeaf(Tree<T> Tree) {
		if (Tree == null) {
			return 0;
		}
		if ((Tree.tRight == null) && (Tree.tLeft == null)) {
			return 1;
		} else {
			return countLeaf(Tree.tLeft) + countLeaf(Tree.tRight);
		}
	}

	/**
	 * Método que main que realiza las pruebas con el árbol.
	 * 
	 * @param args
	 *            Argumentos del main
	 */
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// Tree<Key> Tree = new Tree<Key>();
	// System.out.println("Ejemplos sesion árbol binario de búsqueda");
	//
	// Key[] datas = { new Key(20), new Key(7), new Key(18), new Key(22), new
	// Key(5), new Key(2), new Key(1) };
	//
	// for (Key data : datas) {
	// Tree.insert(data);
	// }
	//
	// // // Insertando datas repetidos
	// // if (Tree.insert(new Integer(22))==false)
	// // System.out.println("El ABB no admite elementos duplicados");
	// //
	// // // Pertenencia de un data
	// // if (Tree.belongs(new Integer(22)))
	// // System.out.println("belongs");
	// // else
	// // System.out.println("NO belongs");
	// //
	// // // Recorrido en inOrden
	// // System.out.println("InOrden");
	// // Tree.inOrden();
	//
	// // Probando el borrado de diferentes datas -- Descomentar estas líneas
	// // para ver qué ocurre
	// // Tree.remove(new Integer(20));
	// // System.out.println("Borrado " + 20);
	// // Tree.remove(new Integer(15));
	// // System.out.println("Borrado " + 15);
	//
	// // Borrando datas del árbol
	// // for (int i = 0; i < datas.length; i++) {
	// // Tree.remove(datas[i]);
	// // System.out.println("Borrado " + datas[i]);
	// // Tree.inOrden();
	// // }
	//
	// // Checking the tree depth
	// System.out.println("la profundidad es " + Tree.depth());
	//
	// // Checking if the data is inside a leaf node
	// if (Tree.isLeaf(new Key(3)))
	// System.out.println(3 + " Es hoja");
	// else
	// System.out.println(3 + " No es hoja");
	//
	// // Calculating how much leafs and internal nodes are in the tree
	//
	// }
}
