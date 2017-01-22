package dp.cargador;

/**
 * Clase creada para ser usada en la utilidad cargador
 * estudiada previamente en sesi�n práctica "Excepciones"
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import map.Map;

class FicheroCarga {
	/**
	 * atributo de la clase que indica el numero máximo de campos que se pueden
	 * leer
	 */
	public static final int MAXCAMPOS = 10;

	/**
	 * buffer para almacenar el flujo de entrada
	 */
	private static BufferedReader bufferIn;
	private static BufferedWriter bufferOut;

	/**
	 * Metodo para procesar el fichero. Sin excepciones
	 * 
	 * @return codigoError con el error que se ha producido
	 * @throws Exception.
	 *             Puede lanzar una excepci�n en la apertura del buffer de
	 *             lectura
	 */
	static void procesarFichero(String nombreFichero, Cargador cargador) throws FileNotFoundException, IOException {
		// **String vCampos[]=new String[MAXCAMPOS];
		List<String> vCampos = new ArrayList<String>();
		String S = new String();
		int numCampos = 0;

		System.out.println("Procensando el fichero...");
		bufferIn = new BufferedReader(new FileReader(nombreFichero));// creaci�n
																		// del
		// filtro
		// asociado
		// al
		// flujo
		// de
		// datos

		while ((S = bufferIn.readLine()) != null) {
			System.out.println("S: " + S);
			if (!S.startsWith("--")) {
				vCampos.clear();
				numCampos = trocearLinea(S, vCampos);
				System.out.println(vCampos);
				cargador.crear(vCampos.get(0), numCampos, vCampos);
				// Map.getInstance().paintMap();
			}
		}
		bufferIn.close(); // cerramos el filtro
	}

	static void writeFile(String nombreFichero) throws FileNotFoundException, IOException {
		String nameRec = nombreFichero.substring(4, nombreFichero.length() - 4);
		bufferOut = new BufferedWriter(new FileWriter("record" + nameRec + ".log", false));
		bufferOut = new BufferedWriter(new FileWriter("record" + nameRec + ".log", true));

		Map map = Map.getInstance();
		map.writeInit(bufferOut);
		for (int i = 0; i < 100 && map.getThrone().nPj() == 0; i++) {
			map.process();
			map.paintMap();
			map.writeTurn(bufferOut);

		}
		if (map.getThrone().nPj() == 0) {
			System.out.println("(thronemembers)");
			map.write("(thronemembers)", bufferOut);
		}
		bufferOut.close();
	}

	/**
	 * Metodo para trocear cada l�nea y separarla por campos
	 * 
	 * @param S
	 *            cadena con la l�nea completa le�da
	 * @param vCampos.
	 *            Array de String que contiene en cada posici�n un campo
	 *            distinto
	 * @return numCampos. N�mero campos encontrados
	 */
	private static int trocearLinea(String S, List<String> vCampos) {
		boolean eol = false;
		int pos = 0, posini = 0, numCampos = 0;

		while (!eol) {
			pos = S.indexOf("#");
			if (pos != -1) {
				vCampos.add(new String(S.substring(posini, pos)));
				S = S.substring(pos + 1, S.length());
				numCampos++;
			} else
				eol = true;
		}
		return numCampos;
	}

}
