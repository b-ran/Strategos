package strategos;

import java.util.List;

public interface SaveInstance {

	public GameCollections getWorld();

	public List<UnitOwner> getPlayers();

	public UnitOwner getTurn();

}
