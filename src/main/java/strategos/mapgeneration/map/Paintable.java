package strategos.mapgeneration.map;

import strategos.mapgeneration.map.board.terrain.Terrain;

/**
 * Any object that is passed into this library must extend this, to ensure that it has the required methods and fields
 */
public interface Paintable {

    public void setTerrain(Terrain terrain);

    //May not be needed
    public Terrain getTerrain();
}
