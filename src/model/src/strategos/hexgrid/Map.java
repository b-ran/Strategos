package strategos.hexgrid;

import strategos.Direction;
import strategos.model.GameBoard;
import strategos.model.MapLocation;
import strategos.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper for a 2D array of Hexes. The corners of this array are NullHexes, simulating a hexagonal grid.
 * 		Since a grid of tessellated hexagons tapers towards the top and bottom, this offset increases towards the 
 * 		top-left and bottom-right corners. This Map uses flat-topped hexagons, and calculates neighbours based on
 * 		axial coordinates.
 * 
 * @author Daniel Pinfold - pinfoldani
 *
 */
public class Map implements GameBoard {
	
	private MapLocation[][] map;
	private final int diameter;
	
	/**
	 * Creates a new Map object with the specified diameter.
	 * @param diameter - The maximum size of a row of Hexes.
	 */
	public Map(int diameter) {
		this.diameter = diameter;
		map = constructMap(diameter);
	}

	public Map(MapLocation[][] newMap, int diameter) {
		this.diameter = diameter;
		map = newMap.clone();
	}

	private Hex[][] constructMap(int diameter) {
		// maps are stored using a 2D array of Hexes. For the grid to be a hexagon, it must also have an amount of 'padding'
		// 		on the top left and bottom right corners. Padding is represented as [N] :
		/*
		[N][N][ ][ ][ ]
		[N][ ][ ][ ][ ]
		[ ][ ][ ][ ][ ]
		[ ][ ][ ][ ][N]
		[ ][ ][ ][N][N]
		 */
		// the grid is then drawn with each hex at an offset from the one above it, by 0.5 the radius of a hex (this shows
		// 		the diagram without the unplayable hexes [N] being drawn
		/*
		   [ ][ ][ ]
		 [ ][ ][ ][ ]
		[ ][ ][ ][ ][ ]
		 [ ][ ][ ][ ]
		  [ ][ ][ ]
		 */
		// the result is a hexagonal grid, stored in a 2D array.

		Hex[][] map = new Hex[diameter][diameter];

		// populates the array with unplayable hexes
		for (int r = 0; r < diameter; r++) {
			for (int q = 0; q < diameter; q++) {
				map[q][r] = new Hex(q, r, false);
			}
		}
		// this variable determines where the offset (as described above) is being placed
		boolean left = true;
		// the offset begins at the diameter / 2 (rounded down), and will decrease until it reaches 0, then increase
		//		on the other side
		int offset = diameter / 2;
		for (int r = 0; r < diameter; r++) {
			for (int q = 0; q < diameter; q++) {
				// if the value is within the offset of the unplayable hexes, leave this hex as it is.
				if (left && q < offset || (!left && q >= diameter - offset)) {
					continue;
				}
				// otherwise, set the hex to be playable
				set(r, q, map, new Hex(r, q, true));
			}
			// if the offset is on the left, continue incrementing down to 0
			if (offset > 0 && left) {
				offset--;
			}
			// otherwise, increase it up to the diameter / 2 again
			if (!left) {
				offset++;
			}
			// set the offset to draw on the other side if we have reached the middle y-value
			if (offset == 0) {
				left = !left;
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
	private void populateNeighbours(int r, int q, MapLocation[][] map) {
		get(r, q, map).addNeighbour(Direction.EAST, get(r + 1, q, map));
		get(r, q, map).addNeighbour(Direction.WEST, get(r - 1, q, map));

		get(r, q, map).addNeighbour(Direction.NORTH_EAST, get(r + 1, q - 1, map));
		get(r, q, map).addNeighbour(Direction.NORTH_WEST, get(r, q - 1, map));
		get(r, q, map).addNeighbour(Direction.SOUTH_EAST, get(r, q + 1, map));
		get(r, q, map).addNeighbour(Direction.SOUTH_WEST, get(r - 1, q + 1, map));
	}

	private MapLocation get(int x, int y, MapLocation[][] map) {
		if (x < 0 || x >= map.length || y < 0 || y >= map.length) {
			return new Hex(x, y, false);
		}
		
		return map[x][y];
	}
	
	private void set(int x, int y, MapLocation[][] map, MapLocation toSet) {
		map[x][y] = toSet;
	}

	@Override
	public void set(int x, int y, MapLocation location) {
		set(x, y, map, location);
	}
	
	public MapLocation get(int x, int y) {
		return get(x, y, map);
	}
	
	public MapLocation[][] getData() {
		return map;
	}

	/**
	 * Combines the 2D array into a List format, reading left to right, then dropping a line.
	 * @return A List of Hexes contained by the Map.
	 */
	List<MapLocation> getMapAsList() {
		List<MapLocation> temp = new ArrayList<>();
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map.length; y++) {
				temp.add(map[x][y]);
			}
		}
		return temp;
	}

	public int getDiameter() {
		return diameter;
	}

	@Override
	public Terrain getTerrainAt(MapLocation location) {
		return location.getTerrain();
	}

}
