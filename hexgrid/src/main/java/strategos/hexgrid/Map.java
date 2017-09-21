package strategos.hexgrid;

import static strategos.util.Config.MAP_RADIUS;
import static strategos.util.Config.MAP_DIAMETER;
import static strategos.util.Util.Direction.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

/**
 * A wrapper for a 2D array of Hexes. The corners of this array are NullHexes, simulating a hexagonal grid.
 * 		Since a grid of tessellated hexagons tapers towards the top and bottom, this offset increases towards the 
 * 		top-left and bottom-right corners. This Map uses flat-topped hexagons, and calculates neighbours based on
 * 		axial coordinates.
 * 
 * @author Daniel Pinfold
 *
 */
public class Map {
	
	private Hex[][] map;
	private final int radius;
	
	/**
	 * Creates a new Map object with the specified diameter.
	 * @param diameter - The maximum size of a row of Hexes.
	 */
	public Map(int diameter) {
		map = constructMap(diameter);
		radius = diameter / 2;
	}
	
	/**
	 * Creates a 2D array of Hex objects. Has an offset of radius-1, where the top left
	 * 		and bottom right corners are NullHexes, up to the size of the offset. This simulates
	 * 		a grid of tessellated hexagons.
	 * 
	 * @return A 2D array of Hexes.
	 */
	private Hex[][] constructMap(int diameter) {
		
		Hex[][] map = new Hex[diameter][diameter];
		
		for (int r = 0; r < diameter; r++) {
			for (int q = 0; q < diameter; q++) {
				map[r][q] = new NullHex(r, q);
			}
		}
		/*Hex centre = new Hex(radius, radius);
		map[radius][radius] = centre;
		System.out.println(radius);
		for (int dX = -radius; dX <= radius; dX++) {
			
			int minValue = Math.max(-radius, -dX - radius);
			int maxValue = Math.min(radius, -dX + radius);
			
			for (int dY = minValue; dY <= maxValue; dY++) {
				int dZ = -dX - dY;
				
				int column = dX;
				int row = dZ;
				System.out.println(column + ", " + row);
				map[column][row] = new Hex(row, column);
			}
		}*/
		boolean left = true;
		int offset = radius;
		for (int r = 0; r < diameter; r++) {
			for (int q = 0; q < diameter; q++) {
				if (left && q < offset || (!left && q >= diameter - offset)) {
					continue;
				}
				set(r, q, map, new Hex(r, q));
			}
			if (offset > 0 && left) {
				offset--;
			}
			if (offset == 0) {
				left = !left;
			}
			if (!left) {
				offset++;
			}
		}

		for (int r = 0; r < map.length; r++) {
			for (int q = 0; q < map[r].length; q++) {
				populateNeighbours(r, q, map);
			}
		}
		return map;
	}
	
	/**
	 * For a given Hex at (r, q), calculate all the neighbours using the axial coordinates system.
	 * 		A map is passed into this function because the map field may not be initialised at this point.
	 * 
	 * @param r - The horizontal position of this Hex.
	 * @param q - The vertical position of this Hex.
	 * @param map - The map value.
	 */
	private void populateNeighbours(int r, int q, Hex[][] map) {
		get(r, q, map).addNeighbour(EAST, get(r + 1, q, map));
		get(r, q, map).addNeighbour(WEST, get(r - 1, q, map));

		get(r, q, map).addNeighbour(NORTH_EAST, get(r + 1, q - 1, map));
		get(r, q, map).addNeighbour(NORTH_WEST, get(r, q - 1, map));
		get(r, q, map).addNeighbour(SOUTH_EAST, get(r, q + 1, map));
		get(r, q, map).addNeighbour(SOUTH_WEST, get(r - 1, q + 1, map));
	}
	
	private Hex get(int x, int y, Hex[][] map) {
		if (x < 0 || x >= map.length || y < 0 || y >= map.length) {
			return new NullHex(x, y);
		}
		
		return map[x][y];
	}
	
	private void set(int x, int y, Hex[][] map, Hex toSet) {
		map[x][y] = toSet;
	}
	
	public Hex get(int x, int y) {
		return get(x, y, map);
	}
	
	public Hex[][] getMap() {
		return map;
	}
	
	/**
	 * Combines the 2D array into a List format, reading left to right, then dropping a line.
	 * @return A List of Hexes contained by the Map.
	 */
	public List<Hex> getMapAsList() {
		List<Hex> temp = new ArrayList<>();
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map.length; y++) {
				temp.add(map[x][y]);
			}
		}
		return temp;
	}
	
}
