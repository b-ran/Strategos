package strategos.ui.view;

import strategos.GameCollections;
import strategos.SaveInstance;
import strategos.UnitOwner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkTestSaveInstance implements SaveInstance {


	private final List<UnitOwner> owners;
	private final GameCollections world;
	private final UnitOwner turn;

	public NetworkTestSaveInstance(List<UnitOwner> owners, GameCollections world, UnitOwner turn) {
		this.owners = owners;
		this.world = world;
		this.turn = turn;
	}
	@Override
	public GameCollections getWorld() {
		System.out.println("getting world");
		return world;
	}

	@Override
	public List<UnitOwner> getPlayers() {
		return owners;
	}

	@Override
	public UnitOwner getTurn() {
		return turn;
	}
}
