package util;

import strategos.terrain.Terrain;

public class TestForest implements Terrain {
	@Override
	public boolean isPassable() {
		return false;
	}
}
