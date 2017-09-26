package strategos.model.tests;

import org.junit.Test;
import strategos.Direction;
import strategos.GameCollections;
import strategos.MapLocation;
import strategos.hexgrid.Map;
import strategos.model.Strategos;
import strategos.model.World;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static strategos.Direction.*;

public class ModelTests {

	@Test
	public void inRangeTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		MapLocation mid = world.getMap().get(3, 3);

		List<MapLocation> hexes = gameState.getTilesInRange(mid, 1);

		assertTrue(hexes.contains(mid));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_WEST)));
	}

	@Test
	public void inRangeTest_2() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		MapLocation mid = world.getMap().get(3, 3);

		List<MapLocation> hexes = gameState.getTilesInRange(mid, 2);

		assertTrue(hexes.contains(mid));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_WEST)));

		for (java.util.Map.Entry<Direction, MapLocation> entry : mid.getNeighbours().entrySet()) {
			assertTrue(hexes.contains(entry.getValue()));
			assertTrue(hexes.contains(entry.getValue().getNeighbour(NORTH_EAST)));
			assertTrue(hexes.contains(entry.getValue().getNeighbour(NORTH_WEST)));
			assertTrue(hexes.contains(entry.getValue().getNeighbour(EAST)));
			assertTrue(hexes.contains(entry.getValue().getNeighbour(WEST)));
			assertTrue(hexes.contains(entry.getValue().getNeighbour(SOUTH_EAST)));
			assertTrue(hexes.contains(entry.getValue().getNeighbour(SOUTH_WEST)));
		}
	}

	@Test
	public void inRangeTest_3() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		MapLocation mid = world.getMap().get(3, 3);

		List<MapLocation> hexes = gameState.getTilesInRange(mid, 3);

		for (int x = 0; x < world.getMap().getData().length; x++) {
			for (int y = 0; y < world.getMap().getData()[x].length; y++) {
				if (world.getMap().getData()[x][y].isInPlayArea()) {
					assertTrue(hexes.contains(world.getMap().getData()[x][y]));
				}
			}
		}
	}



}
