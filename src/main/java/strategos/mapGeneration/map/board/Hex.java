package strategos.mapGeneration.map.board;

import strategos.util.Entity;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
public class Hex implements Entity {

	Terrain terrain;
	//Index 0 is top edge neighbour the rotates clockwise
	public final Hex[] neighbours;
	//sets if the hex has been seen by player yet
	private boolean revealed = false;
	//Terrain type as from enum
	public final Terrain terrainType;
//	private Image;

	//TODO make more descriptive
	/**
	 *Constructor for hex.
	 * @param neighbours Array of neighbouring Hexes
	 */
	public Hex(Hex[] neighbours, Terrain terrainType) {
		if(neighbours.length!=6)throw new IllegalArgumentException("Hex must have 6 neighbours");
		this.neighbours = neighbours;
		this.terrainType=terrainType;
	}

}
