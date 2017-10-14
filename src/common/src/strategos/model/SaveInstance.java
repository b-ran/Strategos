package strategos.model;

import java.util.List;
import java.io.Serializable;

public interface SaveInstance extends Serializable {

	GameCollections getWorld();

	List<UnitOwner> getPlayers();

	UnitOwner getTurn();

}
