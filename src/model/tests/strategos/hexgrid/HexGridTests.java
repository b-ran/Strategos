package strategos.hexgrid;

import org.junit.Test;
import strategos.Direction;
import strategos.MapLocation;
import strategos.hexgrid.Hex;
import strategos.hexgrid.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HexGridTests {

	/**
	 * Ensures that creating the map works as expected, and that all neighbours of a given Hex
	 * 		are as expected.
	 */
	@org.junit.Test
	public void MapConstructionTest_1() {
		Map map = new Map(6);
		MapLocation midHex = map.get(3, 3);

		MapLocation wHex = map.get(2, 3);
		MapLocation eHex = map.get(4, 3);

		MapLocation nwHex = map.get(3, 2);
		MapLocation neHex = map.get(4, 2);
		MapLocation swHex = map.get(2, 4);
		MapLocation seHex = map.get(3, 4);
		
		assertTrue(midHex.getNeighbour(Direction.WEST).equals(wHex));
		assertTrue(midHex.getNeighbour(Direction.EAST).equals(eHex));
		assertTrue(midHex.getNeighbour(Direction.NORTH_EAST).equals(neHex));
		assertTrue(midHex.getNeighbour(Direction.NORTH_WEST).equals(nwHex));
		assertTrue(midHex.getNeighbour(Direction.SOUTH_EAST).equals(seHex));
		assertTrue(midHex.getNeighbour(Direction.SOUTH_WEST).equals(swHex));
	}
	
	/**
	 * Tests that a Hex on the edge of the map has unplayable Hexes as its neighbours.
	 */
	@Test
	public void MapNeighbourTest_1() {
		Map map = new Map(6);
		MapLocation midHex = map.get(0, 5);
		
		assertFalse(midHex.getNeighbour(Direction.WEST).isInPlayArea());
		assertTrue(midHex.getNeighbour(Direction.EAST).isInPlayArea());
	}
}
