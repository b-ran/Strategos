package strategos;

import java.io.Serializable;
import java.util.ArrayList;

public interface SaveInstance extends Serializable {

	public GameCollections getWorld();

	public ArrayList<UnitOwner> getPlayers();

	public UnitOwner getTurn();

}
