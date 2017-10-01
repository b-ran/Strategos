package strategos.model;

import java.util.ArrayList;
import java.util.List;

import strategos.MapLocation;
import strategos.UnitOwner;
import strategos.model.units.UnitImpl;
import strategos.units.Unit;

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
	public UnitOwner copy() {
		UnitOwner newPlayer = new Player(isNPC);
		List<Unit> newUnits = new ArrayList<>();

		for (int i = 0; i < getUnits().size(); i++) {
			newUnits.add(getUnits().get(i).copy());
		}
		newPlayer.setUnits(newUnits);
		return newPlayer;
	}
}
