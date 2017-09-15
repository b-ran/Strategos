package strategos.model;

public class NullHex extends Hex {
	
	@Override
	public boolean isPassable() {
		return false;
	}
}
