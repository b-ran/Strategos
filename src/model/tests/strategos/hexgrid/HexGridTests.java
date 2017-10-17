package strategos.hexgrid;

import org.junit.Test;
import strategos.Direction;
import strategos.model.MapLocation;
import strategos.terrain.Terrain;
import util.TestForest;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Verifies the funcionality of the hex grid aspect of this library.
 *
 * @author Daniel Pinfold - pinfoldani
 */
public class HexGridTests {

	/**
	 * Ensures that creating the map works as expected, and that all neighbours of a given Hex
	 * are as expected.
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
	 * Tests that setting a hex works correctly
	 */
	@Test
	public void MapConstructionTest_2() {
		Map map = new Map(6);
		MapLocation newHex = new Hex(3, 3, true);
		map.set(3, 3, newHex);

		assertTrue(map.get(3, 3).equals(newHex));
	}

	/**
	 * Tests that a hex's getX() and getY() functions work correctly
	 */
	@Test
	public void MapConstructionTest_3() {
		Map map = new Map(6);
		MapLocation hex = new Hex(3, 3, true);

		assertTrue(hex.getX() == 3 && hex.getY() == 3);
	}

	@Test
	public void MapConstructionTest_4() {
		Map map = new Map(7);
		for (int i = 0; i < map.getDiameter(); i++) {
			for (int y = 0; y < map.getDiameter(); y++) {
				System.out.print(map.get(i, y));
			}
			System.out.println();
		}
	}

	/**
	 * Tests that converting a Map into a list works as expected
	 */
	@Test
	public void MapListTest_1() {
		Map map = new Map(6);

		List<MapLocation> mapList = map.getMapAsList();

		for (int x = 0; x < map.getData().length; x++) {
			for (int y = 0; y < map.getData().length; y++) {
				assertTrue(mapList.contains(map.get(x, y)));
			}
		}
	}

	/**
	 * Tests that getting the terrain works as expected
	 */
	@Test
	public void MapTerrainTest_1() {
		Map map = new Map(6);

		Terrain forest = new TestForest();

		map.get(3, 3).setTerrain(forest);

		assertTrue(map.get(3, 3).getTerrain().equals(forest));
	}

	/**
	 * Tests that using the getTerrainAt function works as expected
	 */
	@Test
	public void MapTerrainTest_2() {
		Map map = new Map(6);

		Terrain forest = new TestForest();

		MapLocation location = map.get(3, 3);

		location.setTerrain(forest);

		assertTrue(map.getTerrainAt(location).equals(forest));
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
