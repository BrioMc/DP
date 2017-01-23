package test;

import static org.junit.Assert.*;

import org.junit.Test;

import door.Key;
import door.ThroneDoor;

public class ThroneDoorTest {

	/**
	 * Test create ThroneDoor
	 */
	@Test
	public void createThroneDoor() {
		System.out.println("*********************************");
		System.out.println("Trying to create a door\n");
		ThroneDoor x = new ThroneDoor(4);
		assertNotNull(x);
		System.out.println("Success");

	}

	/**
	 * Test try open door
	 */
	@Test
	public void tryKeyThroneDoor() {
		System.out.println("*********************************");
		System.out.println("Trying to open the door\n");

		ThroneDoor x = new ThroneDoor(4);
		assertNotNull(x);
		x.open(new Key(1));
		x.open(new Key(9));
		x.open(new Key(14));
		x.open(new Key(25));
		x.open(new Key(29));
		x.open(new Key(3));
		x.open(new Key(7));
		x.open(new Key(17));
		x.open(new Key(21));
		x.open(new Key(5));
		assertTrue(x.isOp());
		System.out.println(x.showDoor());

	}

	/**
	 * Test closed open door
	 */
	@Test
	public void tryCloseDoor() {
		System.out.println("*********************************");
		System.out.println("Trying to close the door\n");

		ThroneDoor x = new ThroneDoor(4);
		assertNotNull(x);
		x.open(new Key(1));
		x.open(new Key(9));
		x.open(new Key(14));
		x.open(new Key(25));
		x.open(new Key(29));
		x.open(new Key(3));
		x.open(new Key(7));
		x.open(new Key(17));
		x.open(new Key(21));
		x.open(new Key(5));
		assertTrue(x.isOp());
		System.out.println(x.showDoor());
		x.closeDoor();
		assertFalse(x.isOp());
		System.out.println(x.showDoor());

	}

}
