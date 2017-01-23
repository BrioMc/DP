package test;

import static org.junit.Assert.*;

import org.junit.Test;

import door.Key;

public class KeyTest {
	/**
	 * Test : Create key
	 */
	@Test
	public void createKeytest() {
		System.out.println("*********************************");
		System.out.println("Test: Create key");

		Key x = new Key(1);
		assertNotNull(x);
		System.out.println(x.toString());

		System.out.println("Success");

	}

}
