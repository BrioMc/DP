package test;

import static org.junit.Assert.*;

import org.junit.Test;

import door.ThroneDoor;
import map.Map;
import pj.Lannister;

public class MapTest {
	/**
	 * Test Map log
	 */
	@Test
	public void testCreateMap() {
		ThroneDoor x = new ThroneDoor(4);
		Map.generateInstance(4, 6, 10, x);

		Map test = Map.getInstance();
		String mapP = test.toStringMap();
		String mapX = " _ _ _ _ _ _ _ _ _ _\n" + "|  _  | |     | | | |\n" + "| | |_ _  |_|     | |\n"
				+ "| |_     _|_ _| |  _|\n" + "|_|  _|      _ _|  _|\n" + "|    _  | |_|  _|_  |\n"
				+ "|_|_|_ _ _|_ _ _ _ _|\n";

		System.out.println(mapX);
		System.out.println(mapP);
		assertEquals(mapX, mapP);
	}

	/**
	 * Test insert Throne Door no Null
	 */
	@Test
	public void testInsertThrone() {
		ThroneDoor x = new ThroneDoor(4);
		Map.generateInstance(4, 6, 10, x);
		assertNotNull(Map.getInstance().getDoor());
		assertEquals(x, Map.getInstance().getDoor());

	}

	/**
	 * Test insert Pj no Null
	 */
	@Test
	public void testInsertPj() {
		Map.generateInstance(4, 6, 10, null);
		Map test = Map.getInstance();
		Lannister pj = new Lannister("Prueba", 'p', 0, 0);
		assertNotNull(pj);
		test.insertPj(pj);
		assertEquals(pj, Map.getInstance().getMap()[0][0].checkPj());
		

	}

	/**
	 * Test that checks that the turn increases when processing
	 */
	@Test
	public void testTurn(){
		Map.generateInstance(4, 6, 10, null);
		Map test = Map.getInstance();
		int x = test.getTurn();
		test.process();
		assertEquals(x+1, test.getTurn());
	}
}
