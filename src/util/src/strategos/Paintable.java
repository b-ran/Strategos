package strategos;

import strategos.terrain.Terrain;

/**
 * Any object that is passed into this library must extend this, to ensure that it has the required methods and fields
 */
public interface Paintable {

    //May not be needed
    public Terrain getTerrain();

    public void setTerrain(Terrain terrain);
}
