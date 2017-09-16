package strategos.model;

import static strategos.util.Config.MAP_RADIUS;
import static strategos.util.Config.MAP_DIAMETER;
import static strategos.util.Util.Direction.*;

public class Map {
	
	public Map() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	private Hex[][] constructMap() {
		Hex[][] map = new Hex[MAP_DIAMETER][MAP_DIAMETER];
		
		for (int q = 0; q < MAP_DIAMETER; q++) {
			for (int r = 0; r < MAP_DIAMETER; r++) {
				map[q][r] = new NullHex();
			}
		}
		Hex centre = new Hex();
		map[MAP_RADIUS][MAP_RADIUS] = centre;
		for (int dX = -MAP_RADIUS; dX < MAP_RADIUS; dX++) {
			int minValue = Math.max(-MAP_RADIUS, -dX - MAP_RADIUS);
			int maxValue = Math.min(MAP_RADIUS, -dX + MAP_RADIUS);
			for (int dY = minValue; dY < maxValue; dY++) {
				int dZ = -dX - dY;
				
				int column = dX + (dZ - (dZ & 1)) / 2;
				int row = dX;
				map[column][row] = new Hex();
			}
		}
		
		for (int q = 0; q < MAP_DIAMETER; q++) {
			for (int r = 0; r < MAP_DIAMETER; r++) {
				populateNeighbours(q, r, map);
			}
		}
		return map;
	}
	
	private void populateNeighbours(int q, int r, Hex[][] map) {
		map[q][r].addNeighbour(EAST, get(q, r + 1, map));
		map[q][r].addNeighbour(WEST, get(q, r - 1, map));

		// If even row
		if (r % 2 == 0) {
			map[q][r].addNeighbour(NORTH_EAST, get(q, r - 1, map));
			map[q][r].addNeighbour(NORTH_WEST, get(q - 1, r - 1, map));
			map[q][r].addNeighbour(SOUTH_EAST, get(q, r + 1, map));
			map[q][r].addNeighbour(SOUTH_WEST, get(q - 1, r + 1, map));
		// If odd row
		} else {
			map[q][r].addNeighbour(NORTH_EAST, get(q + 1, r - 1, map));
			map[q][r].addNeighbour(NORTH_WEST, get(q, r - 1, map));
			map[q][r].addNeighbour(SOUTH_EAST, get(q + 1, r + 1, map));
			map[q][r].addNeighbour(SOUTH_WEST, get(q, r + 1, map));
		}
	}
	
	public Hex get(int x, int y, Hex[][] map) {
		if (x < 0 || x > MAP_DIAMETER || y < 0 || y > MAP_DIAMETER) {
			return new NullHex();
		}
		return map[x][y];
	}
	
}
