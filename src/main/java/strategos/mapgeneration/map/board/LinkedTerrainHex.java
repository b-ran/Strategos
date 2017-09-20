package strategos.mapgeneration.map.board;

import strategos.mapgeneration.map.Paintable;
import strategos.mapgeneration.map.board.terrain.Terrain;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
public class LinkedTerrainHex implements Paintable {

	Terrain terrain;
	//Index 0 is top edge neighbour the rotates clockwise
	private final LinkedTerrainHex[] neighbours;
	//sets if the hex has been seen by player yet
	private boolean revealed = false;
	//Terrain type as from enum
	private final Terrain terrainType;
//	private Image;

	//TODO make more descriptive
	/**
	 *Constructor for hex.
	 * @param neighbours Array of neighbouring Hexes
	 */
	public LinkedTerrainHex(LinkedTerrainHex[] neighbours, Terrain terrainType) {
		if(neighbours.length!=6)throw new IllegalArgumentException("LinkedTerrainHex must have 6 neighbours");
		this.neighbours = neighbours;
		this.terrainType=terrainType;
	}


    public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
    }

	public Terrain getTerrain() {
		return terrain;
	}
}
