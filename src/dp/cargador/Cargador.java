package dp.cargador;

import java.util.List;

import door.ThroneDoor;
import map.Dir;
import map.Map;
import pj.Lannister;
import pj.Stark;
import pj.Targaryen;
import pj.WhiteWalkers;

/**
 * Clase creada para ser usada en la utilidad cargador contiene el main del
 * cargador. Se crea una instancia de la clase Estacion, una instancia de la
 * clase Cargador y se procesa el fichero de inicio, es decir, se leen todas las
 * líneas y se van creando todas las instancias de la simulación
 * 
 * @version 5.0 - 27/10/2016
 * @author Profesores DP
 */
public class Cargador {
	/**
	 * número de elementos distintos que tendrá la simulación - Mapa, Stark,
	 * Lannister, Baratheon, Targaryen
	 */
	static final int NUMELTOSCONF = 5;
	/**
	 * atributo para almacenar el mapeo de los distintos elementos
	 */
	static private DatoMapeo[] mapeo;

	/**
	 * constructor parametrizado
	 * 
	 * @param e
	 *            referencia a la instancia del patrón Singleton
	 */
	Cargador() {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0] = new DatoMapeo("MAPA", 5);
		mapeo[1] = new DatoMapeo("STARK", 4);
		mapeo[2] = new DatoMapeo("TARGARYEN", 4);
		mapeo[3] = new DatoMapeo("LANNISTER", 4);
		mapeo[4] = new DatoMapeo("CAMINANTE", 4);

	}

	/**
	 * busca en mapeo el elemento leído del fichero inicio.txt y devuelve la
	 * posición en la que está
	 * 
	 * @param elto
	 *            elemento a buscar en el array
	 * @return res posición en mapeo de dicho elemento
	 */
	private int queElemento(String elto) {
		int res = -1;
		boolean enc = false;

		for (int i = 0; (i < NUMELTOSCONF && !enc); i++) {
			if (mapeo[i].getNombre().equals(elto)) {
				res = i;
				enc = true;
			}
		}
		return res;
	}

	/**
	 * método que crea las distintas instancias de la simulación
	 * 
	 * @param elto
	 *            nombre de la instancia que se pretende crear
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo de la
	 *            instancia
	 */
	void crear(String elto, int numCampos, List<String> vCampos) {
		// Si existe elemento y el n�mero de campos es correcto, procesarlo...
		// si no, error
		int numElto = queElemento(elto);

		// Comprobaci�n de datos básicos correctos
		if ((numElto != -1) && (mapeo[numElto].getCampos() == numCampos)) {
			// procesar
			switch (numElto) {
			case 0:
				crearMapa(numCampos, vCampos);

				break;
			case 1:
				crearStark(numCampos, vCampos);

				break;
			case 2:
				crearTargaryen(numCampos, vCampos);

				break;
			case 3:
				crearLannister(numCampos, vCampos);

				break;
			case 4:
				crearCaminante(numCampos, vCampos);

				break;
			}

		} else
			System.out.println(
					"ERROR Cargador::crear: Datos de configuración incorrectos... " + elto + "," + numCampos + "\n");
	}

	/**
	 * método que crea una instancia de la clase Planta
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearMapa(int numCampos, List<String> vCampos) {

		ThroneDoor door = new ThroneDoor(Integer.parseInt(vCampos.get(4)));
		Map.generateInstance(Integer.parseInt(vCampos.get(3)), Integer.parseInt(vCampos.get(2)),
				Integer.parseInt(vCampos.get(1)), door);
		// inicializar mapa
	}

	/**
	 * método que crea una instancia de la clase Stark
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearStark(int numCampos, List<String> vCampos) {

		Stark stark = new Stark(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)), 0);
		Dir[] direccionesE = { Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N, Dir.E, Dir.S, Dir.E, Dir.S, Dir.S,
				Dir.W, Dir.S, Dir.E, Dir.S };
		stark.setRoutes(direccionesE);
		Map.getInstance().insertPj(stark);
		// Registrar Stark en el mapa
	}

	/**
	 * método que crea una instancia de la clase Targaryen
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearTargaryen(int numCampos, List<String> vCampos) {
		Targaryen targaryen = new Targaryen(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)),
				0);
		Dir[] direccionesD = { Dir.E, Dir.S, Dir.S, Dir.S, Dir.W, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.S, Dir.S,
				Dir.E, Dir.E };

		targaryen.setRoutes(direccionesD);
		Map.getInstance().insertPj(targaryen);
		// Registrar Targaryen en el mapa
	}

	/**
	 * método que crea una instancia de la clase Lannister
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearLannister(int numCampos, List<String> vCampos) {
		Lannister lannister = new Lannister(vCampos.get(1), vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)),
				Map.getInstance().getDRoom());
		Dir[] direccionesT = { Dir.N, Dir.N, Dir.W, Dir.N, Dir.N, Dir.W, Dir.S, Dir.W, Dir.W, Dir.N, Dir.N, Dir.W,
				Dir.S, Dir.S, Dir.S, Dir.S, Dir.S, Dir.E, Dir.E, Dir.E, Dir.E, Dir.E };
		lannister.setRoutes(direccionesT);
		Map.getInstance().insertPj(lannister);
		// Registrar Lannister en el mapa
	}

	/**
	 * método que crea una instancia de la clase White Walker
	 * 
	 * @param numCampos
	 *            número de atributos que tendrá la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearCaminante(int numCampos, List<String> vCampos) {
		WhiteWalkers whiteWalkers = new WhiteWalkers(vCampos.get(1), vCampos.get(2).charAt(0),
				Integer.parseInt(vCampos.get(3)), Map.getInstance().surW());
		Dir[] direccionesC = { Dir.N, Dir.N, Dir.N, Dir.E, Dir.S, Dir.E, Dir.N, Dir.N, Dir.E, Dir.N, Dir.E, Dir.E,
				Dir.S, Dir.S, Dir.S, Dir.S, Dir.S };
		whiteWalkers.setRoutes(direccionesC);
		Map.getInstance().insertPj(whiteWalkers);
		// Registrar Baratheon en el mapa
	}

}
