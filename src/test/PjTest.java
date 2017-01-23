package test;

import static org.junit.Assert.*;

import org.junit.Test;

import door.Key;
import map.Map;
import pj.Lannister;
import pj.Stark;
import pj.Targaryen;
import pj.WhiteWalkers;

public class PjTest {
	public PjTest() {
		Map.generateInstance(59, 6, 10, null);

		// TODO Auto-generated constructor stub
	}

	/**
	 * Test Lannister
	 */
	@Test
	public void testLannister() {
		Lannister l = new Lannister("Lanister", 'L', 0, Map.getInstance().getDRoom());
		assertTrue(l instanceof Lannister);
		System.out.println("*****************************");
		String x = "(path:L: N O N N E N N S S O N O O N O O S O O N O O E E S E E S O O S O S O S N E E E N E E E E N N E S S S E S)\n";
		System.out.println("Lannister Map 10x6");
		System.out.println("Expected path");
		System.out.println(x);
		System.out.println("Resulting path");
		System.out.println(l.showPath());
		System.out.println(Map.getInstance().toStringMap());
		assertEquals(x, l.showPath());

	}

	/**
	 * 
	 */
	@Test
	public void testTarg() {
		Targaryen l = new Targaryen("Targaryen", 'A', 0, 0);
		assertTrue(l instanceof Targaryen);
		System.out.println("*****************************");

		String r = "(path:A: S S S N N N E E S E E S O O O N S E S O S O S N E S N E E S O E E N N E S N E E N N E S S S E S)\n";
		System.out.println("Targaryen Map 10x6");
		System.out.println("Expected path");
		System.out.println(r);
		System.out.println("Resulting path");
		System.out.println(l.showPath());
		assertEquals(r, l.showPath());

	}

	/**
	 * 
	 */
	@Test
	public void testStark() {
		Stark l = new Stark("Stark", 'S', 0, 0);
		assertTrue(l instanceof Stark);
		System.out.println("*****************************");

		String r = "(path:S: E E S E E N E E S E E S S S E S)\n";
		System.out.println("Stark Map 10x6");
		System.out.println("Expected path");
		System.out.println(r);
		System.out.println("Resulting path");
		System.out.println(l.showPath());
		assertEquals(r, l.showPath());

	}

	/**
	 * 
	 */
	@Test
	public void testWalker() {
		WhiteWalkers l = new WhiteWalkers("WhiteWalkers", 'W', 0, 0);
		assertTrue(l instanceof WhiteWalkers);
		System.out.println("*****************************");

		String r = "(path:W: N E N E N E E N O O N O O E E S E E N E E S E E S E N N S S O S S E S N O N N N O S S O O O O S O O O S)\n";
		System.out.println("WhiteWalkers Map 10x6");
		System.out.println("Expected path");
		System.out.println(r);
		System.out.println("Resulting path");
		System.out.println(l.showPath());
		assertEquals(r, l.showPath());

	}

	/**
	 * Test that checks that the turn increases
	 */
	@Test
	public void testTurn() {
		System.out.println("*****************************");
		Lannister j = new Lannister("Prueba", 'b', 0, 59);
		int i = j.getTurn();
		System.out.println("Test that checks that the turn increases ");
		j.sumTurn();
		assertEquals(i + 1, j.getTurn());

	}

	/**
	 * Test for insert and take key
	 */
	@Test
	public void testKey() {
		Lannister j = new Lannister("Prueba", 'b', 0, 59);
		Key x = new Key(9);
		j.setKey(x);
		assertEquals(x, j.getKey());

	}

}
