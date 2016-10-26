package door;

import api.Arbol;

/**
 * We are your waifu 
 * Ignacio Caro Cumplido 
 * Javier Ballesteros Moron
 * EC1
 * 2º
 */

/**
 * 
 * Clase Puerta del Trono, con sus atributos necesarios, una bandera cómo estado
 * de la puerta(abierta, cerrada) Un vector que no se modifica y guarda la
 * combinación para reset de la cerradura La cerradura, en arbol. Las llaves
 * probadas, en arbol. Un entero, profundidad, cómo requisito necesario para
 * abrir la puerta.
 * 
 */
public class ThroneDoor {
	private boolean isOpen;
	private Key[] comb;
	private Arbol<Key> cerradura = new Arbol();
	private Arbol<Key> probadas = new Arbol();
	private int prof;

	/**
	 * Constructor por defecto
	 */
	public ThroneDoor() {
		isOpen = false;
		prof = 0;
		cerradura = new Arbol<Key>();
		cerradura = new Arbol<Key>();
		comb = new Key[200];
	}

	/**
	 * Constructor parametrizado, con la profundidad y el vector de llaves cómo
	 * parametros.
	 */
	public ThroneDoor(Key[] keys, int profn) {
		isOpen = false;
		prof = profn;
		cerradura = new Arbol<Key>();
		probadas = new Arbol<Key>();
		comb = keys;
		cfgCerradura(0, keys.length - 1);
	}

	/**
	 * Método para configurar la cerraura a partir del vector pasado.
	 * 
	 * @param ini
	 *            entero con el primer valor a tener en cuenta
	 * @param fin
	 *            entero con el último valor a tener en cuenta
	 */

	private void cfgCerradura(int ini, int fin) {
		int mitad = (ini + fin) / 2;
		cerradura.insertar(comb[mitad]);
		// System.out.print(comb[mitad].toString() + ",");
		if (ini < fin) {
			cfgCerradura(ini, mitad - 1);
			cfgCerradura(mitad + 1, fin);
		}

	}

	/**
	 * Muestra el estado de la cerradura inOrden.
	 */
	public void mostrarCerradura() {

		cerradura.inOrden();

	}

	/**
	 * Muestra el estado de las llaves probadas inOrden.
	 */
	public void mostrarProbadas() {
		probadas.inOrden();
	}

	/**
	 * Método que devuelve el estado de la puerta abierta/cerrada abierta=true
	 * cerrada=false
	 * 
	 * @return estado de la puerta
	 */
	private boolean isOp() {
		return isOpen;
	}

	/**
	 * Método que comprueba el estado de la puerta, y en caso de ser abierta
	 * devolver el mensaje correspondiente.
	 * 
	 * @return estado de la puerta
	 */
	public boolean estaAbierta() {
		return isOpen;

	}

	/**
	 * 
	 * @param key
	 *            Llave que se introduce con un valor !=Null
	 * @return
	 */
	public boolean open(Key key) {
		if (!probadas.pertenece(key)) {
			if (cerradura.pertenece(key)) {
				cerradura.borrar(key);
				probadas.insertar(key);
				if (cerradura.nHojas() <= (cerradura.nNodos() - cerradura
						.nHojas()) && cerradura.depth() < this.prof) {
					isOpen = true;
				}
			} else {
				System.out.println("La llave no pertenece a la cerradura");
			}
		} else {
			System.out
					.println("Esta llave ya se ha probado, no se puede repetir.");
		}
		return isOpen;
	}

}
