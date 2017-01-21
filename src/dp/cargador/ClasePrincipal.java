package dp.cargador;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las líneas y se van creando todas las instancias de la simulación
 * 
 * @version 4.0 -  15/10/2014 
 * @author Profesores DP
 */
import java.io.FileNotFoundException;
import java.io.IOException;

import map.Map;

public class ClasePrincipal {
	public static void main(String[] args) {
		/**
		 * instancia asociada al fichero de entrada inicio.txt
		 */
		Cargador cargador = new Cargador();
		try {
			/**
			 * Método que procesa línea a línea el fichero de entrada
			 * inicio.txt
			 */
			FicheroCarga.procesarFichero("inicio6x6_a5.txt", cargador);
			Map map = Map.getInstance();
			map.writeMap();
			for (int i = 0; i < 50; i++) {
				map.process(i);
				map.paintMap();
			}

			FicheroCarga.writeFile();
		} catch (FileNotFoundException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		} catch (IOException valor) {
			System.err.println("Excepción capturada al procesar fichero: " + valor.getMessage());
		}
	}
}
