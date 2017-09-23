package strategos;

public interface MapLocation {

    int getX();

    int getY();

    /**
     * Get the neighbour at the specified orientation relative to this MapLocation.
     * Return a NullHex if no neighbour exists at that position.
     *
     * @param direction - The Direction that the desired MapLocation is, relative to this MapLocation.
     * @return A MapLocation at the specified Direction.
     */
    MapLocation getNeighbour(Direction direction);
}
