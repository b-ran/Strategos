package strategos.hexgrid.tests;

import org.junit.Test;

import strategos.hexgrid.Hex;
import strategos.hexgrid.Map;

import static org.junit.Assert.*;
import static strategos.util.Util.Direction.*;

public class Tests {

	/**
	 * Ensures that creating the map works as expected, and that all neighbours of a given LinkedTerrainHex
	 * 		are as expected.
	 */
	@org.junit.Test
	public void MapConstructionTest_1() {
		Map map = new Map(6);
		Hex midHex = map.get(3, 3);
		
		Hex wHex = map.get(2, 3);
		Hex eHex = map.get(4, 3);
		
		Hex nwHex = map.get(3, 2);
		Hex neHex = map.get(4, 2);
		Hex swHex = map.get(2, 4);
		Hex seHex = map.get(3, 4);
		
		assertTrue(midHex.getNeighbour(WEST).equals(wHex));
		assertTrue(midHex.getNeighbour(EAST).equals(eHex));
		assertTrue(midHex.getNeighbour(NORTH_EAST).equals(neHex));
		assertTrue(midHex.getNeighbour(NORTH_WEST).equals(nwHex));
		assertTrue(midHex.getNeighbour(SOUTH_EAST).equals(seHex));
		assertTrue(midHex.getNeighbour(SOUTH_WEST).equals(swHex));
	}
	
	/**
	 * Tests that a LinkedTerrainHex on the edge of the map has NullHexes as its neighbours.
	 */
	@Test
	public void MapNeighbourTest_1() {
		Map map = new Map(6);
		Hex midHex = map.get(0, 5);
		
		assertFalse(midHex.getNeighbour(WEST).isPassable());
		assertTrue(midHex.getNeighbour(EAST).isPassable());
	}
}
