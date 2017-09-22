package strategos.hexgrid;


import strategos.*;
import strategos.exception.FeatureNotImplementedException;
import strategos.exception.RuleViolationException;

import java.util.HashMap;
import java.util.Map;


/**
 * The hexagonal tile structure of the map, which holds information on this section of the map.
 * @author Daniel Pinfold
 *
 */
public class Hex implements Paintable, Graphical {
	private Map<Direction, Hex> neighbours;
	
	private int xIndex;
	private int yIndex;
	
	private Terrain terrain;
	
	/**
	 * Creates a new Hex object, initialising the neighbours collection.
	 * This constructor assumes that the neighbours will be added externally.
	 */
	public Hex(int x, int y) {
		setXIndex(x);
		setYIndex(y);
		neighbours = new HashMap<>();
	}
	
	/**
	 * Creates a new Hex object with pre-specified neighbours.
	 * 
	 * @param east - The neighbouring Hex directly to the right.
	 * @param west - The neighbouring Hex directly to the left.
	 * @param northeast - The neighbouring Hex one to the right and one up.
	 * @param northwest - The neighbouring Hex one to the left and one up.
	 * @param southeast - The neighbouring Hex one to the right and one down.
	 * @param southwest - The neighbouring Hex one to the left and one down.
	 */
	public Hex(int x, int y, Hex east, Hex west, Hex northeast, Hex northwest, Hex southeast, Hex southwest) {
		setXIndex(x);
		setYIndex(y);
		neighbours = new HashMap<>();
		neighbours.put(Direction.EAST, east);
		neighbours.put(Direction.WEST, west);
		neighbours.put(Direction.NORTH_EAST, northeast);
		neighbours.put(Direction.NORTH_WEST, northwest);
		neighbours.put(Direction.SOUTH_EAST, southeast);
		neighbours.put(Direction.SOUTH_WEST, southwest);
	}
	
	/**
	 * Returns whether or not this Hex can be moved onto by a Unit.
	 * Calls the isPassable method on the terrain stored by this tile.
	 * If this Hex is a NullHex, this method will always return false.
	 * 
	 * @return true if the tile can be acted upon or moved onto, false otherwise.
	 */
	public boolean isPassable() {
		// TODO: implement to call terrain.isImpassable().
		throw new FeatureNotImplementedException("Terrain not yet implemented");
		// return terrain.isImpassable();
	}
	
	/**
	 * Gets the a Map of terrain Modifiers to integers, specifying what statistics
	 * 		a given unit will be altered by the terrain.  
	 * @return a Map of Modifier to Integer of terrain modifiers.
	 */
	public Map<Modifier, Integer> getTerrainModifiers() {
		// TODO: implement modifier calls on the terrain field.
		throw new FeatureNotImplementedException("Terrain not yet implemented");
		//return null;
	}
	
	/**
	 * Gets the neighbour at the specified orientation relative to this Hex.
	 * Will return a NullHex if no neighbour exists at that position.
	 *  
	 * @param direction - The Direction that the desired Hex is, relative to this Hex.
	 * @return A Hex at the specified Direction.
	 */
	public Hex getNeighbour(Direction direction) {
		return neighbours.get(direction);
	}
	
	public void addNeighbour(Direction direction, Hex newNeighbour) {
		if (neighbours.get(direction) != null) {
			throw new IllegalArgumentException("Cannot overwrite a neighbour");
		}
		if (neighbours.size() == 6) {
			throw new RuleViolationException("A Hex cannot have more than 6 neighbours");
		}
		neighbours.put(direction, newNeighbour);
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

    public Terrain getTerrain() {
        return terrain;
    }

    /**
	 * Gets the Map of neighbours contained by this Hex.
	 * @return a Map of Direction to Hex, the adjacent Hexes to this Hex.
	 */
	public Map<Direction, Hex> getNeighbours() {
		return neighbours;
	}
	
	@Override
	public String toString() {
		return "[" + getXIndex() + "," + getYIndex() + "]";
	}

	public int getXIndex() {
		return xIndex;
	}

	/**
	 * Changes the x-index of this Hex, i.e. where it is stored in the 2D Map array. WARNING: This method may never need to
	 * 		be used, since Hex indices are usually final. Changing the index could lead to undesired behaviour.
	 * @param xIndex - the new x-index for the Hex.
	 */
	public void setXIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getYIndex() {
		return yIndex;
	}

	/**
	 * Changes the y-index of this Hex, i.e. where it is stored in the 2D Map array. WARNING: This method may never need to
	 * 		be used, since Hex indices are usually final. Changing the index could lead to undesired behaviour.
	 * @param yIndex - the new y-index for the Hex.
	 */
	public void setYIndex(int yIndex) {
		this.yIndex = yIndex;
	}
}
