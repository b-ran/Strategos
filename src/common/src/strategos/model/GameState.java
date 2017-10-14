package strategos.model;

import strategos.Direction;
import strategos.Observable;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;

public interface GameState extends Observable, EntityHandler, TileHandler {

	void newGame();

	public void save();

	public void load(SaveInstance toRestore);

	/**
	 * Packages the GameState's current information into a SaveInstance and returns it.
	 * The exported SaveInstance contains the World, the Players, and the current Player whose turn it is.
	 *
	 * @return a SaveInstance containing the data structures of the GameState.
	 */
	public SaveInstance export();

	/**
	 * Resets values and prepares the game for the next player to act.
	 */
	public void nextTurn();

	public List<SaveInstance> getSaves();

	/**
	 * Gets the UnitOwner whose turn it is.
	 * @return
	 */
	public UnitOwner getCurrentTurn();

	int getWinner();

	int getNumberTurns();
}
