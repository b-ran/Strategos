package strategos;

import java.util.ArrayList;

public interface SaveInstance {

	public GameCollections getWorld();

	public ArrayList<UnitOwner> getPlayers();

	public UnitOwner getTurn();

}
