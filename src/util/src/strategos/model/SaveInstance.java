package strategos.model;

import java.util.List;
import java.io.Serializable;

public interface SaveInstance extends Serializable {

	public GameCollections getWorld();

	public List<UnitOwner> getPlayers();

	public UnitOwner getTurn();

}
