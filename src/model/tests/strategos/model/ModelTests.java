package strategos.model;

import org.junit.Test;
import strategos.Direction;
import strategos.GameCollections;
import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.behaviour.Behaviour;
import strategos.hexgrid.Hex;
import strategos.hexgrid.Map;
import strategos.model.Player;
import strategos.model.Strategos;
import strategos.model.World;
import strategos.model.tests.util.TestBehaviour;
import strategos.model.units.SwordsmenImpl;
import strategos.model.units.UnitImpl;
import strategos.terrain.Mountain;
import strategos.terrain.Terrain;
import strategos.units.Unit;

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

	@Test
	public void inRangeTest_4() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		MapLocation mid = world.getMap().get(3, 3);

		List<MapLocation> hexes = gameState.getTilesInRange(mid, 0);

		for (int x = 0; x < world.getMap().getData().length; x++) {
			for (int y = 0; y < world.getMap().getData()[x].length; y++) {
				if (x == 3 && y == 3) {
					continue;
				}
				assertFalse(hexes.contains(world.getMap().get(x, y)));
			}
		}
	}

	@Test
	public void visionTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		unit.setBehaviour(new TestBehaviour(gameState, unit));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST, 1);

		assertTrue(p.getVisibleTiles().size() != 0);
	}

	@Test
	public void visionTest_2() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		unit.setBehaviour(new TestBehaviour(gameState, unit));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST, 1);

		List<MapLocation> hexes = gameState.getTilesInRange(unit.getPosition(), 2);

		for (MapLocation location : hexes) {
			assertTrue(p.getVisibleTiles().contains(location));
		}
	}

	@Test
	public void visionTest_3() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		unit.setBehaviour(new TestBehaviour(gameState, unit));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST, 1);
		int visibleSize = p.getVisibleTiles().size();

		gameState.move(unit, EAST, 1);
		assertTrue(p.getVisibleTiles().size() > visibleSize);
	}

	@Test
	public void findUnitsTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		unit.setBehaviour(new TestBehaviour(gameState, unit));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		assertTrue(gameState.getUnitAt(world.getMap().get(3,3)).equals(unit));
	}

	@Test
	public void findUnitsTest_2() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		SwordsmenImpl unit2 = new SwordsmenImpl(p);
		SwordsmenImpl unit3 = new SwordsmenImpl(p);
		SwordsmenImpl unit4 = new SwordsmenImpl(p);

		unit.setBehaviour(new TestBehaviour(gameState, unit));
		unit2.setBehaviour(new TestBehaviour(gameState, unit2));
		unit3.setBehaviour(new TestBehaviour(gameState, unit3));
		unit4.setBehaviour(new TestBehaviour(gameState, unit4));

		unit.setPosition(world.getMap().get(3, 3));
		unit2.setPosition(world.getMap().get(4, 3));
		unit3.setPosition(world.getMap().get(0, 3));
		unit4.setPosition(world.getMap().get(3, 5));

		p.getUnits().add(unit);
		p.getUnits().add(unit2);
		p.getUnits().add(unit3);
		p.getUnits().add(unit4);

		world.getAllUnits().add(unit);
		world.getAllUnits().add(unit2);
		world.getAllUnits().add(unit3);
		world.getAllUnits().add(unit4);

		List<Unit> units = gameState.getUnitsInRange(unit.getPosition(), 3);

		assertTrue(units.size() == 4);

		assertTrue(units.contains(unit));
		assertTrue(units.contains(unit2));
		assertTrue(units.contains(unit3));
		assertTrue(units.contains(unit4));
	}

	@Test
	public void moveTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);
		unit.setBehaviour(b);
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST, 1);

		assertTrue(b.moved);
	}

	@Test
	public void moveTest_2() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);
		unit.setBehaviour(b);
		unit.setPosition(world.getMap().get(3, 3));
		MapLocation location2 = unit.getPosition().getNeighbour(EAST);

		MapLocation hex = new Hex(location2.getX(), location2.getY(), false);
		world.getMap().set(location2.getX(), location2.getY(), hex);
		unit.getPosition().addNeighbour(EAST, hex);

		p.getUnits().add(unit);

		gameState.move(unit, EAST, 1);

		assertFalse(unit.getPosition().getX() == 4);
	}

	@Test
	public void moveTest_3() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		SwordsmenImpl unit2 = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);

		unit.setBehaviour(b);
		b = new TestBehaviour(gameState, unit2);
		unit2.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));
		unit2.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p.getUnits().add(unit2);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(unit2);

		gameState.move(unit, EAST, 1);

		assertFalse(unit.getPosition().getX() == 4);
	}

	@Test
	public void attackTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		Player p2 = new Player(false);
		gameState.getPlayers().add(p);
		gameState.getPlayers().add(p2);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		SwordsmenImpl unit2 = new SwordsmenImpl(p2);
		TestBehaviour b = new TestBehaviour(gameState, unit);

		unit.setBehaviour(b);
		TestBehaviour b2 = new TestBehaviour(gameState, unit2);
		unit2.setBehaviour(b2);

		unit.setPosition(world.getMap().get(3, 3));
		unit2.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p2.getUnits().add(unit2);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(unit2);

		gameState.attack(unit, unit2.getPosition());

		assertTrue(b.attacking);
	}

	@Test
	public void attackTest_2() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		Player p2 = new Player(false);
		gameState.getPlayers().add(p);
		gameState.getPlayers().add(p2);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		SwordsmenImpl unit2 = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);

		unit.setBehaviour(b);
		TestBehaviour b2 = new TestBehaviour(gameState, unit2);
		unit2.setBehaviour(b2);

		unit.setPosition(world.getMap().get(3, 3));
		unit2.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p.getUnits().add(unit2);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(unit2);

		gameState.attack(unit, unit2.getPosition());

		assertFalse(b.attacking);
	}

	@Test
	public void attackTest_3() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.attack(unit, unit.getPosition().getNeighbour(EAST));

		assertFalse(b.attacking);
	}

	@Test
	public void waryTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.wary(unit);

		assertTrue(b.wary);
	}

	@Test
	public void entrenchTest_1() {
		Strategos gameState = new Strategos(new World(new Map(7), new ArrayList<>()));

		GameCollections world = gameState.getWorld();

		Player p = new Player(false);
		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p);
		TestBehaviour b = new TestBehaviour(gameState, unit);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.entrench(unit);

		assertTrue(b.entrenched);
	}

}