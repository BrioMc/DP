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
		System.out.println("*********************************");
		System.out.println("try this method alone for work");
		System.out.println("Test: Create Map");

		Map test = Map.getInstance();
		String mapP = test.toStringMap();
		String mapX = " _ _ _ _ _ _ _ _ _ _\n" + "|  _  | |     | | | |\n" + "| | |_ _  |_|     | |\n"
				+ "| |_     _|_ _| |  _|\n" + "|_|  _|      _ _|  _|\n" + "|    _  | |_|  _|_  |\n"
				+ "|_|_|_ _ _|_ _ _ _ _|\n";

		System.out.println(mapX);
		System.out.println(mapP);
		assertEquals(mapX, mapP);
		System.out.println("Success");

	}

	/**
	 * Test insert Throne Door no Null
	 */
	@Test
	public void testInsertThrone() {
		System.out.println("*********************************");
		System.out.println("Try insert ThroneDoor in Map");
		ThroneDoor x = new ThroneDoor(4);
		Map.generateInstance(4, 6, 10, x);
		assertNotNull(Map.getInstance().getDoor());
		assertEquals(x, Map.getInstance().getDoor());
		System.out.println("Success");

	}

	/**
	 * Test insert Pj no Null
	 */
	@Test
	public void testInsertPj() {
		System.out.println("*********************************");
		System.out.println("Try insert Pj in map");

		Map.generateInstance(4, 6, 10, null);
		Map test = Map.getInstance();
		Lannister pj = new Lannister("Prueba", 'p', 0, 0);
		assertNotNull(pj);
		test.insertPj(pj);
		assertEquals(pj, Map.getInstance().getMap()[0][0].checkPj());
		System.out.println("Success");

	}

	/**
	 * Test that checks that the turn increases when processing
	 */
	@Test
	public void testTurn() {
		System.out.println("*********************************");
		System.out.println("Try increases turnin map");
		Map.generateInstance(4, 6, 10, null);
		Map test = Map.getInstance();
		int x = test.getTurn();
		test.process();
		assertEquals(x + 1, test.getTurn());
		System.out.println("Success");

	}
}
