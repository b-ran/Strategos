package strategos.external;

import org.junit.Test;
import strategos.Direction;
import strategos.model.MapLocation;
import strategos.exception.RuleViolationException;
import strategos.hexgrid.Map;
import strategos.model.Player;
import strategos.model.Strategos;
import strategos.model.units.UnitImpl;
import strategos.units.Unit;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExternalTests {
	@Test
	public void testCopyPlayer() {
		Player player = new Player(false);
		Player copy = (Player) player.copy(null);
		assertEquals(player, copy);
		player.setUnits(Collections.singletonList(new UnitImpl(null, null)));
		assertNotEquals(player, copy);
	}

	@Test(expected = RuleViolationException.class)
	public void testMovingOutOfBounds() {
		Strategos strategos = new Strategos(null, null, null, null);
		Map map = new Map(6);
		MapLocation start =  map.get(0, 5);
		MapLocation outOfBounds = start.getNeighbour(Direction.WEST);
		Unit unit = new UnitImpl(null ,start);
		strategos.move(unit, outOfBounds);
	}
}
