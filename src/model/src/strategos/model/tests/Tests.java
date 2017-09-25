package strategos.model.tests;

import org.junit.Test;
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

public class Tests {

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

		List<MapLocation> hexes = gameState.getTilesInRange(mid, 1);

		assertTrue(hexes.contains(mid));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_WEST)));

		assertTrue(hexes.contains(mid.getNeighbour(NORTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(NORTH_WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(WEST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_EAST)));
		assertTrue(hexes.contains(mid.getNeighbour(SOUTH_WEST)));
	}

}
