package strategos.hexgrid;

public class NullHex extends Hex {
	
	public NullHex(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
	
	@Override
	public String toString() {
		return "[N|N]";
	}
}
