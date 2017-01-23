package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import door.Key;
import map.Square;

public class SquareTest {
	/**
	 * Test : create square
	 */

	@Test
	public void createSquare() {
		System.out.println("*********************************");
		System.out.println("Trying to create a square\n");
		Square x = new Square(5);
		assertNotNull(x);

		System.out.println("Success");
	}
	/**
	 * Test : insert key to square
	 */

	@Test
	public void insertKeySqu() {
		System.out.println("*********************************");
		System.out.println("Trying to insert key in square\n");
		Square x = new Square(5);
		assertNotNull(x);
		x.insertKey(new Key(25));
		System.out.println(x.showKeys());

	}
/**
 * Test : take key from square
 */
	@Test
	public void getKeySqu() {
		System.out.println("*********************************");
		System.out.println("Trying to get key from square\n");
		Square x = new Square(5);
		assertNotNull(x);
		x.insertKey(new Key(25));
		x.insertKey(new Key(1));
		x.insertKey(new Key(17));
		System.out.println(x.showKeys());
		assertEquals(1, x.removeKey().getId());
		System.out.println("Key 1 taked");
		System.out.println(x.showKeys());
			
		

	}
}
