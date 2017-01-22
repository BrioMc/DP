package test;

import org.junit.Test;


import static org.junit.Assert.*;
import map.Map;

public class MapTest {

	@Test
	public void testCreateMap() {
		Map.generateInstance(4, 6, 10, null);
		Map test = Map.getInstance();
		String mapP = test.toStringMap();
		String mapX = " _ _ _ _ _ _ _ _ _ _\n"+
					  "|  _  | |     | | | |\n"+
					  "| | |_ _  |_|     | |\n"+
					  "| |_     _|_ _| |  _|\n"+
					  "|_|  _|      _ _|  _|\n"+
					  "|    _  | |_|  _|_  |\n"+
					  "|_|_|_ _ _|_ _ _ _ _|\n";

		assertEquals(mapX, mapP);
	}

	@Test
	public void testInsertPj(){
		Map.generateInstance(4, 6, 10, null);
		Map test = Map.getInstance();
			
	}
	
	
	
	
}
