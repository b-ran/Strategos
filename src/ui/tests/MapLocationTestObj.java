import strategos.Direction;
import strategos.MapLocation;

public class MapLocationTestObj implements MapLocation{

    private int y;
    private int x;

    public MapLocationTestObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public MapLocation getNeighbour(Direction direction) {
        return null;
    }

    @Override
    public void addNeighbour(Direction direction, MapLocation location) {

    }

    @Override
    public boolean isInPlayArea() {
        return false;
    }
}
