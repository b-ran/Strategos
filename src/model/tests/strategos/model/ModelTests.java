package strategos.model;

import org.junit.Test;
import strategos.*;
import strategos.hexgrid.Hex;
import strategos.hexgrid.Map;
import strategos.model.units.BridgeImpl;
import strategos.units.Bridge;
import util.TestAlwaysDeadBehaviour;
import util.TestBehaviour;
import strategos.model.units.SwordsmenImpl;
import strategos.units.Unit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static strategos.Direction.*;

public class ModelTests {

	/**
	 * Tests that a getTilesInRange call at range 1 gets all the neighbours
	 */
	@Test
	public void inRangeTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

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

	/**
	 * Tests that a getTilesInRange call at range 2 gets all the neighbours and all the neighbours of those neighbours
	 */
	@Test
	public void inRangeTest_2() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

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

	/**
	 * Tests that a getTilesInRange call at range to contain all the hexes does contain the entire map
	 */
	@Test
	public void inRangeTest_3() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

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

	/**
	 * Tests that a getTilesInRange call at range 0 only gets the MapLocation it was called on
	 */
	@Test
	public void inRangeTest_4() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

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

	/**
	 * Tests that a move call on a Unit correctly calculates the vision of the player
	 */
	@Test
	public void visionTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		unit.setBehaviour(new TestBehaviour(gameState));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST);

		assertTrue(p.getVisibleTiles().size() != 0);
	}

	/**
	 * Tests that the getVisibleTiles call correctly gets all the tiles within the sight range of the unit
	 */
	@Test
	public void visionTest_2() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		unit.setBehaviour(new TestBehaviour(gameState));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST);

		List<MapLocation> hexes = gameState.getTilesInRange(unit.getPosition(), unit.getSightRadius());

		for (MapLocation location : hexes) {
			assertTrue(p.getVisibleTiles().contains(location));
		}
	}

	/**
	 * Tests that moving a unit permanently increases the number of visible tiles of a player
	 */
	@Test
	public void visionTest_3() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		unit.setBehaviour(new TestBehaviour(gameState));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST);
		int visibleSize = p.getVisibleTiles().size();

		gameState.move(unit, EAST);
		assertTrue(p.getVisibleTiles().size() > visibleSize);
	}

	/**
	 * Tests that getting a unit at a location works correctly
	 */
	@Test
	public void findUnitsTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		unit.setBehaviour(new TestBehaviour(gameState));
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		assertTrue(gameState.getUnitAt(world.getMap().get(3,3)).equals(unit));
	}

	/**
	 * Tests that all units within a range are found
	 */
	@Test
	public void findUnitsTest_2() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		SwordsmenImpl unit2 = new SwordsmenImpl(p, null);
		SwordsmenImpl unit3 = new SwordsmenImpl(p, null);
		SwordsmenImpl unit4 = new SwordsmenImpl(p, null);

		unit.setBehaviour(new TestBehaviour(gameState));
		unit2.setBehaviour(new TestBehaviour(gameState));
		unit3.setBehaviour(new TestBehaviour(gameState));
		unit4.setBehaviour(new TestBehaviour(gameState));

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

	/**
	 * Tests that ordering a unit to move works correctly
	 */
	@Test
	public void moveTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);
		unit.setBehaviour(b);
		unit.setPosition(world.getMap().get(3, 3));
		p.getUnits().add(unit);

		gameState.move(unit, EAST);

		assertTrue(b.moved);
	}

	/**
	 * Tests that ordering a unit to move through an impassable location fails
	 */
	@Test
	public void moveTest_2() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);
		unit.setBehaviour(b);
		unit.setPosition(world.getMap().get(3, 3));
		MapLocation location2 = unit.getPosition().getNeighbour(EAST);

		MapLocation hex = new Hex(location2.getX(), location2.getY(), false);
		world.getMap().set(location2.getX(), location2.getY(), hex);
		unit.getPosition().addNeighbour(EAST, hex);

		p.getUnits().add(unit);

		gameState.move(unit, EAST);

		assertFalse(unit.getPosition().getX() == 4);
	}

	/**
	 * Tests that ordering a unit to move through another unit fails
	 */
	@Test
	public void moveTest_3() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		SwordsmenImpl unit2 = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);
		b = new TestBehaviour(gameState);
		unit2.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));
		unit2.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p.getUnits().add(unit2);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(unit2);

		gameState.move(unit, EAST);

		assertFalse(unit.getPosition().getX() == 4);
	}

	/**
	 * Tests that ordering a unit to move through an owned bridge succeeds
	 */
	@Test
	public void moveTest_4() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		BridgeImpl bridge = new BridgeImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);
		b = new TestBehaviour(gameState);
		bridge.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));
		bridge.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p.getUnits().add(bridge);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(bridge);

		gameState.move(unit, EAST);

		assertTrue(unit.getPosition().getX() == 4);
	}

	/**
	 * Tests that ordering a unit to move through a non-owned bridge fails
	 */
	@Test
	public void moveTest_5() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		BridgeImpl bridge = new BridgeImpl(p2, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);
		b = new TestBehaviour(gameState);
		bridge.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));
		bridge.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p2.getUnits().add(bridge);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(bridge);

		gameState.move(unit, EAST);

		assertFalse(unit.getPosition().getX() == 4);
	}

	/**
	 * Tests that ordering a unit to attack a valid unit works correctly
	 */
	@Test
	public void attackTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		SwordsmenImpl unit2 = new SwordsmenImpl(p2, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);
		TestBehaviour b2 = new TestBehaviour(gameState);
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
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		SwordsmenImpl unit2 = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);
		TestBehaviour b2 = new TestBehaviour(gameState);
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

	/**
	 * Tests that attacking a null unit fails
	 */
	@Test
	public void attackTest_3() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.attack(unit, unit.getPosition().getNeighbour(EAST));

		assertFalse(b.attacking);
	}

	/**
	 * Tests that attacking a bridge captures it successfully
	 */
	@Test
	public void attackTest_4() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		BridgeImpl bridge = new BridgeImpl(p2, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);
		TestAlwaysDeadBehaviour b2 = new TestAlwaysDeadBehaviour(gameState);
		bridge.setBehaviour(b2);

		unit.setPosition(world.getMap().get(3, 3));
		bridge.setPosition(unit.getPosition().getNeighbour(EAST));

		p.getUnits().add(unit);
		p2.getUnits().add(bridge);
		world.getAllUnits().add(unit);
		world.getAllUnits().add(bridge);

		assertTrue(p2.getUnits().contains(bridge));

		gameState.attack(unit, bridge.getPosition());

		assertTrue(p2.getUnits().isEmpty());

		for (Unit u : p.getUnits()) {
			if (u instanceof Bridge) {
				assertTrue(u.getOwner().equals(p));
			}
		}
	}

	/**
	 * Tests that a wary command works as expected
	 */
	@Test
	public void waryTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.wary(unit);

		assertTrue(b.wary);
	}

	/**
	 * Tests that an entrench command works
	 */
	@Test
	public void entrenchTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);

		GameCollections world = gameState.getWorld();

		gameState.getPlayers().add(p);

		SwordsmenImpl unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.entrench(unit);

		assertTrue(b.entrenched);
	}

	/**
	 * Tests that saving and loading works correctly
	 */
	@Test
	public void saveTest_1() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);
		gameState.setThisInstancePlayer(p);

		GameCollections world = gameState.getWorld();

		gameState.getPlayers().add(p);

		Unit unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);
		gameState.save();
		gameState.move(unit, EAST);

		assertTrue(unit.getPosition().getX() == 4);

		unit = gameState.getPlayers().get(0).getUnits().get(0);

		SaveInstance save = gameState.getSaves().get(0);

		gameState.load(save);

		unit = gameState.getPlayers().get(0).getUnits().get(0);

		assertTrue(unit.getPosition().getX() == 3);
	}

	/**
	 * Tests that saving and loading works correctly
	 */
	@Test
	public void saveTest_2() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);
		gameState.setThisInstancePlayer(p);
		GameCollections world = gameState.getWorld();

		gameState.getPlayers().add(p);

		Unit unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);
		gameState.save();
		gameState.move(unit, EAST);

		assertTrue(unit.getPosition().getX() == 4);

		SaveInstance save = gameState.getSaves().get(0);

		gameState.getPlayers().get(0).getUnits().remove(0);

		gameState.load(save);

		assertFalse(gameState.getPlayers().get(0).getUnits().isEmpty());

		unit = gameState.getPlayers().get(0).getUnits().get(0);

		assertTrue(gameState.getPlayers().get(0).getUnits().contains(unit));
	}

	/**
	 * Tests that saving and loading works correctly
	 */
	@Test
	public void saveTest_3() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);
		gameState.setThisInstancePlayer(p);
		GameCollections world = gameState.getWorld();

		gameState.getPlayers().add(p);

		Unit unit = new SwordsmenImpl(p, null);
		TestBehaviour b = new TestBehaviour(gameState);

		unit.setBehaviour(b);

		unit.setPosition(world.getMap().get(3, 3));

		p.getUnits().add(unit);
		world.getAllUnits().add(unit);

		gameState.save();
		gameState.move(unit, EAST);
		gameState.move(unit, EAST);
		SaveInstance save = gameState.getSaves().get(0);

		gameState.load(save);

		unit = gameState.getPlayers().get(0).getUnits().get(0);
		assertTrue(unit.getActionPoints() == 2);
	}

	/**
	 * Tests that saving and loading works correctly
	 */
	@Test
	public void saveTest_4() {
		Player p = new Player(false);
		Player p2 = new Player(false);
		Player barbs = new Player(true);
		Strategos gameState = new Strategos(null, new World(new Map(7), new ArrayList<>()), p, p2, barbs);
		gameState.setThisInstancePlayer(p);
		GameCollections world = gameState.getWorld();

		gameState.save();

		gameState.getWorld().setMap(new Map(5));

		assertTrue(gameState.getWorld().getMap().getDiameter() == 5);

		SaveInstance save = gameState.getSaves().get(0);
		gameState.load(save);

		assertTrue(gameState.getWorld().getMap().getDiameter() == 7);
	}

}