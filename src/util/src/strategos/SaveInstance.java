package strategos;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public interface SaveInstance extends Serializable {

	public GameCollections getWorld();

	public List<UnitOwner> getPlayers();

	public UnitOwner getTurn();

}
