package map;

import door.Key;
import door.ThroneDoor;

/**
 * We are your waifu Ignacio Caro Cumplido Javier Ballesteros Moron EC1 2º
 */
public class Map {
	private int files;
	private int col;
	private Room[][] mapRoom;
	private Room aditionalRoom;

	/**
	 * Programa principal - EC1.
	 * 
	 * @param args
	 *            Argumentos que recibe el programa principal
	 * @return Retorna la salida del programa
	 */
	public static void main(String args[]) {
		int numKeys = 15;
		// Creación de la lista de identificadores impares
		// {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29}
		int[] listaIdKeys = new int[numKeys];
		int j = 1;
		for (int i = 0; i < numKeys; i++) {
			listaIdKeys[i] = j;
			j = j + 2;
		}
		// Creación del tablero del mapa
		// Parámetros: sala de la ThroneDoor, nº columnas, nº filas y
		// profundidad de
		// combinación secreta
		int dimX = 6;
		int dimY = 6;
		int salaThroneDoor = (dimX * dimY) - 1;
		int alturaArbol = 4;
		// Mapa mapa = new Mapa(salaThroneDoor, dimX, dimY, alturaArbol);

		// Algoritmo que reordena los identificadores para crear las Keys en el
		// orden correcto
		// listaIdKeys = mapa.generarCombinacion(listaIdKeys);
		// Combinación de Keys que se insertarán en la ThroneDoor del Trono
		Key[] combinacion = new Key[numKeys];
		for (int i = 0; i < combinacion.length; i++) {
			combinacion[i] = new Key(listaIdKeys[i]);
		}
		// Crear la ThroneDoor del Trono
		ThroneDoor ThroneDoor = new ThroneDoor(combinacion, 4);
		// Configurar la ThroneDoor introduciendo la combinación de Keys
		// ThroneDoor.configurar(combinacion);
		// Cerrar la ThroneDoor, por si inicialmente está abierta
		// ThroneDoor.cerrar();
		// Añadir la ThroneDoor al mapa
		// mapa.insertarThroneDoor(ThroneDoor);
		// Realizar distintas pruebas de apertura de la ThroneDoor
		for (int i = 0; !ThroneDoor.estaAbierta(); i++) {
			Key Key = new Key(i);
			ThroneDoor.open(Key);
			System.out.println(i);

		}
		// Cuando se abra la ThroneDoor mostramos mensaje de apertura de la
		// ThroneDoor
		if (ThroneDoor.estaAbierta()) {
			System.out.println("La puerta del trono ha sido abierta");
		} else {
			System.out.println("La puerta del trono sigue cerrada");
		}
		ThroneDoor.mostrarProbadas();
		// mapa.pintar(); // se mostrará en este caso únicamente la información
		// del
		// mapa
		// Realizar más pruebas

		// TODO
	}
}
