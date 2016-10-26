package api;
import door.Key;

/**
 * Implementacion de arbol binario de busqueda.
 * 
 * @version 1.0
 * @author Asignatura Desarrollo de Programas<br/>
 *         <b> Profesores DP </b><br>
 *         Curso 14/15
 */

public class Arbol<T extends Compare<T>> {

	/** Dato almacenado en cada nodo del árbol. */
	private T datoRaiz;

	/** Atributo que indica si el árbol está vacío. */
	private boolean esVacio;

	/** Hijo izquierdo del nodo actual */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual */
	private Arbol<T> hDer;

	/**
	 * Constructor por defecto de la clase. Inicializa un árbol vacío.
	 */
	public Arbol() {
		this.esVacio = true;
		this.hIzq = null;
		this.hDer = null;
	}

	/**
	 * Crea un nuevo árbol a partir de los datos pasados por parámetro.
	 * 
	 * @param hIzq
	 *            El hijo izquierdo del árbol que se está creando
	 * @param datoRaiz
	 *            Raíz del árbol que se está creando
	 * @param hDer
	 *            El hijo derecho del árbol que se está creando
	 */

	public Arbol(Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer) {
		this.esVacio = false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
	}

	/**
	 * Devuelve el hijo izquierdo del árbol
	 * 
	 * @return El hijo izquierdo
	 */
	public Arbol getHijoIzq() {
		return hIzq;
	}

	/**
	 * Devuelve el hijo derecho del árbol
	 * 
	 * @return Hijo derecho del árbol
	 */
	public Arbol getHijoDer() {
		return hDer;
	}

	/**
	 * Devuelve la raíz del árbol
	 * 
	 * @return La raíz del árbol
	 */
	public T getRaiz() {
		return datoRaiz;
	}

	/**
	 * Comprueba si el árbol está vacío.
	 * 
	 * @return verdadero si el árbol está vacío, falso en caso contrario
	 */
	public boolean vacio() {
		return esVacio;
	}

	/**
	 * Inserta un nuevo dato en el árbol.
	 * 
	 * @param dato
	 *            El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso
	 *         contrario
	 */
	public boolean insertar(T dato) {
		boolean resultado = true;
		if (vacio()) {
			datoRaiz = dato;
			esVacio = false;
		} else {
			if (!(this.datoRaiz.isEqual(dato))) {
				Arbol aux;
				if (dato.compareTo(datoRaiz) < 0) { // dato < datoRaiz
					if ((aux = getHijoIzq()) == null)
						hIzq = aux = new Arbol();
				} else { // dato > datoRaiz
					if ((aux = getHijoDer()) == null)
						hDer = aux = new Arbol();
				}
				resultado = aux.insertar(dato);
			} else
				resultado = false;
		}
		return resultado;
	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el árbol
	 * 
	 * @param dato
	 *            El dato a buscar
	 * @return verdadero si el dato se encuentra en el árbol, falso en caso
	 *         contrario
	 */
	public boolean pertenece(T dato) {
		Arbol aux = null;
		boolean encontrado = false;
		if (!vacio()) {
			if (this.datoRaiz.isEqual(dato))
				encontrado = true;
			else {
				if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
					aux = getHijoIzq();
				else
					// dato > datoRaiz
					aux = getHijoDer();
				if (aux != null)
					encontrado = aux.pertenece(dato);
			}
		}
		return encontrado;
	}

	/**
	 * Borrar un dato del árbol.
	 * 
	 * @param dato
	 *            El dato que se quiere borrar
	 */
	public void borrar(T dato) {
		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				if (hIzq != null)
					hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				if (hDer != null)
					hDer = hDer.borrarOrden(dato);
			} else // En este caso el dato es datoRaiz
			{
				if (hIzq == null && hDer == null) {
					esVacio = true;
				} else
					borrarOrden(dato);
			}
		}
	}

	/**
	 * Borrar un dato. Este método es utilizado por el método borrar anterior.
	 * 
	 * @param dato
	 *            El dato a borrar
	 * @return Devuelve el árbol resultante después de haber realizado el
	 *         borrado
	 */
	private Arbol borrarOrden(T dato) {
		T datoaux;
		Arbol retorno = this;
		Arbol aborrar, candidato, antecesor;

		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				if (hIzq != null)
					hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				if (hDer != null)
					hDer = hDer.borrarOrden(dato);
			} else {
				aborrar = this;
				if ((hDer == null) && (hIzq == null)) { /* si es hoja */
					retorno = null;
				} else {
					if (hDer == null) { /* Solo hijo izquierdo */
						aborrar = hIzq;
						datoaux = this.datoRaiz;
						datoRaiz = hIzq.getRaiz();
						hIzq.datoRaiz = datoaux;
						hIzq = hIzq.getHijoIzq();
						hDer = aborrar.getHijoDer();

						retorno = this;
					} else {
						if (hIzq == null) { /* Solo hijo derecho */
							aborrar = hDer;
							datoaux = datoRaiz;
							datoRaiz = hDer.getRaiz();
							hDer.datoRaiz = datoaux;
							hDer = hDer.getHijoDer();
							hIzq = aborrar.getHijoIzq();

							retorno = this;
						} else { /* Tiene dos hijos */
							candidato = this.getHijoIzq();
							antecesor = this;
							while (candidato.getHijoDer() != null) {
								antecesor = candidato;
								candidato = candidato.getHijoDer();
							}

							/* Intercambio de datos de candidato */
							datoaux = datoRaiz;
							this.datoRaiz = (T) candidato.getRaiz(); // TODO
							candidato.datoRaiz = datoaux;
							aborrar = candidato;
							if (antecesor == this)
								hIzq = candidato.getHijoIzq();
							else
								antecesor.hDer = candidato.getHijoIzq();
						} // Eliminar solo ese nodo, no todo el subarbol
					}
					aborrar.hIzq = null;
					aborrar.hDer = null;
				}
			}
		}
		return retorno;
	}

	/**
	 * Recorrido inOrden del árbol.
	 */
	public void inOrden() {
		Arbol aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			System.out.println(this.datoRaiz.toString());

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}
		}
	}

	public int depth() {
		int depth = 1;
		int rdepth = 0;
		int ldepth = 0;

		Arbol aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				ldepth = aux.pdepth(depth);
			}

			if ((aux = getHijoDer()) != null) {
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
		Arbol aux = null;
		if (!vacio()) {
			depth++;
			if ((aux = getHijoIzq()) != null) {
				ldepth = aux.pdepth(depth);
			}

			if ((aux = getHijoDer()) != null) {
				rdepth = aux.pdepth(depth);
			}
			if (ldepth > depth)
				depth = ldepth;
			if (rdepth > depth)
				depth = rdepth;
		}
		return depth;
	}

	public boolean isLeaf(T dato) {
		Arbol aux = null;
		boolean leaf = false;
		if (!vacio()) {
			if (this.datoRaiz.isEqual(dato))
				if (hIzq == null && hDer == null)
					leaf = true;
				else {
					if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
						aux = getHijoIzq();
					else
						// dato > datoRaiz
						aux = getHijoDer();
					if (aux != null)
						leaf = aux.pertenece(dato);
				}
		}
		return leaf;
	}

	// TODO
	public int nNodos() {
		return contarNodos(this);
	}

	public int nHojas() {
		return contarHojas(this);
	}

	private int contarNodos(Arbol arbol) {
		int total = 0;
		if (arbol != null) {
			total++;
			total += contarNodos(arbol.getHijoIzq());
			total += contarNodos(arbol.getHijoDer());
		}
		return total;
	}

	private int contarHojas(Arbol arbol) {
		if (arbol == null) {
			return 0;
		}
		if ((arbol.hDer == null) && (arbol.hIzq == null)) {
			return 1;
		} else {
			return contarHojas(arbol.hIzq) + contarHojas(arbol.hDer);
		}
	}

	/**
	 * Método que main que realiza las pruebas con el árbol.
	 * 
	 * @param args
	 *            Argumentos del main
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arbol<Key> arbol = new Arbol();
		System.out.println("Ejemplos sesion árbol binario de búsqueda");

		Key[] datos = { new Key(20), new Key(7), new Key(18), new Key(22),
				new Key(5), new Key(2), new Key(1) };

		for (int i = 0; i < datos.length; i++) {
			arbol.insertar(datos[i]);
		}

		// // Insertando datos repetidos
		// if (arbol.insertar(new Integer(22))==false)
		// System.out.println("El ABB no admite elementos duplicados");
		//
		// // Pertenencia de un dato
		// if (arbol.pertenece(new Integer(22)))
		// System.out.println("Pertenece");
		// else
		// System.out.println("NO Pertenece");
		//
		// // Recorrido en inOrden
		// System.out.println("InOrden");
		// arbol.inOrden();

		// Probando el borrado de diferentes datos -- Descomentar estas líneas
		// para ver qué ocurre
		// arbol.borrar(new Integer(20));
		// System.out.println("Borrado " + 20);
		// arbol.borrar(new Integer(15));
		// System.out.println("Borrado " + 15);

		// Borrando datos del árbol
		// for (int i = 0; i < datos.length; i++) {
		// arbol.borrar(datos[i]);
		// System.out.println("Borrado " + datos[i]);
		// arbol.inOrden();
		// }

		// Checking the tree depth
		System.out.println("la profundidad es " + arbol.depth());

		// Checking if the data is inside a leaf node
		if (arbol.isLeaf(new Key(3)) == true)
			System.out.println(3 + " Es hoja");
		else
			System.out.println(3 + " No es hoja");

		// Calculating how much leafs and internal nodes are in the tree

	}
}
