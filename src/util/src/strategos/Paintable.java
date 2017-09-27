package strategos;

import strategos.terrain.Terrain;

/**
 * Any object that is passed into this library must extend this, to ensure that it has the required methods and fields
 */
public interface Paintable {

    /**
     * Check if a given Paintable can be moved into or selected. These have Terrain of mountains.
     *
     * @return true if the Paintable is in the play area, false, otherwise
     */
    boolean isInPlayArea();

    /**
     * Gets the terrain contained by this Paintable.
     * @return a Terrain object at this Paintable
     */
    public Terrain getTerrain();

    public void setTerrain(Terrain terrain);
}
