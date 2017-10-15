package strategos.model;

import java.util.ArrayList;
import java.util.List;

import strategos.units.Unit;

/**
 * An implementation of the UnitOwner, which contains units and can see a number of tiles. A Player is owned by
 * 		the instance of the Strategos game
 *
 * @author Daniel Pinfold - pinfoldani
 */
public class Player implements UnitOwner{

	private final boolean isNPC;
	private ArrayList<Unit> units = new ArrayList<>();
	private List<MapLocation> visibleTiles = new ArrayList<>();

	public Player(boolean isNPC) {
		this.isNPC = isNPC;
	}

	@Override
	public List<Unit> getUnits() {
		return units;
	}

	@Override
	public void addVisibleTile(MapLocation newTile) {
		visibleTiles.add(newTile);
	}

	@Override
	public void addUnit(Unit newUnit) {
		units.add(newUnit);
	}

	@Override
	public void removeUnit(Unit toRemove) {
		units.remove(toRemove);
	}

	@Override
	public List<MapLocation> getVisibleTiles() {
		return visibleTiles;
	}

	@Override
	public boolean isNPC() {
		return isNPC;
	}

	@Override
	public void setUnits(List<Unit> newUnits) {
		units = new ArrayList<>(newUnits);
	}

	@Override
	public UnitOwner copy(GameState newState) {
		UnitOwner newPlayer = new Player(isNPC);

		List<Unit> newUnits = new ArrayList<>();
		for (int i = 0; i < getUnits().size(); i++) {
			newUnits.add(getUnits().get(i).copy(newPlayer, newState));
		}
		newPlayer.getVisibleTiles().addAll(getVisibleTiles());
		newPlayer.setUnits(newUnits);
		return newPlayer;
	}
}
