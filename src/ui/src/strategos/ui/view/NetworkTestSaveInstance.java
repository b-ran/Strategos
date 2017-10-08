package strategos.ui.view;

import strategos.GameCollections;
import strategos.SaveInstance;
import strategos.UnitOwner;

import java.util.List;

public class NetworkTestSaveInstance implements SaveInstance {
	@Override
	public GameCollections getWorld() {
		System.out.println("getting world");
		return null;
	}

	@Override
	public List<UnitOwner> getPlayers() {
		return null;
	}

	@Override
	public UnitOwner getTurn() {
		return null;
	}
}
