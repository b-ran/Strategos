package strategos.model;

import java.util.HashMap;
import java.util.Map;

import strategos.mapgeneration.map.board.terrain.Terrain;
import strategos.util.Util.Direction;
import static strategos.util.Util.Direction.*;
import strategos.util.Util.Modifier;
import strategos.util.exception.FeatureNotImplementedException;
import strategos.util.exception.RuleViolationException;

/**
 * The hexagonal tile structure of the map, which holds information on this section of the map.
 * @author Daniel Pinfold
 *
 */
public class Hex {
	private Map<Direction, Hex> neighbours;
	
	private int xIndex;
	private int yIndex;
	
	private Terrain terrain;
	
	/**
	 * Creates a new Hex object, initialising the neighbours collection.
	 * This constructor assumes that the neighbours will be added externally.
	 */
	public Hex(int x, int y) {
		setxIndex(x);
		setyIndex(y);
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
		setxIndex(x);
		setyIndex(y);
		neighbours = new HashMap<>();
		neighbours.put(EAST, east);
		neighbours.put(WEST, west);
		neighbours.put(NORTH_EAST, northeast);
		neighbours.put(NORTH_WEST, northwest);
		neighbours.put(SOUTH_EAST, southeast);
		neighbours.put(SOUTH_WEST, southwest);
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
	 * Gets the a map of terrain Modifiers to integers, specifying what statistics
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
	
	/**
	 * Gets the map of neighbours contained by this Hex.
	 * @return a Map of Direction to Hex, the adjacent Hexes to this Hex.
	 */
	public Map<Direction, Hex> getNeighbours() {
		return neighbours;
	}
	
	@Override
	public String toString() {
		return "[" + getxIndex() + "," + getyIndex() + "]";
	}

	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}
}
