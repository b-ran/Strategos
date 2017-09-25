package strategos;

import java.util.Map;

public interface MapLocation {

    int getX();

    int getY();

    /**
     * Get the neighbour at the specified orientation relative to this MapLocation.
     * Return a not in-play-area MapLocation if no neighbour exists at that position.
     *
     * @param direction - The Direction that the desired MapLocation is, relative to this MapLocation.
     * @return A MapLocation at the specified Direction.
     */
    MapLocation getNeighbour(Direction direction);

    public void addNeighbour(Direction direction, MapLocation location);

    /**
     * Check if a given MapLocation can be moved into or selected.
     *
     * @return true if the MapLocation is in the play area, false, otherwise
     */
    public boolean isInPlayArea();

    public Map<Direction, MapLocation> getNeighbours();
}
