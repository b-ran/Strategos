package strategos.model;

import strategos.Direction;
import strategos.Observable;
import strategos.terrain.Terrain;
import strategos.units.Unit;

import java.util.List;

public interface GameState extends Observable, EntityHandler, TileHandler {

	/**
	 * Resets the GameState's variables and resets the world for a new turn.
	 */
	void newGame();

	/**
	 * Saves the current world and players. A maximum of 3 saves may be stored before
	 */
	public void save();

	/**
	 * Loads the given SaveInstance into the GameState, setting the world and players to be those stored within the
	 * 		SaveInstance. If the SaveInstance is null, the world is kept the same.
	 * @param toRestore the SaveInstance to load.
	 */
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

	/**
	 * Determines if a given player has won the game or not, by calculating whether or not a given player (not barbarians)
	 * 		has any units left.
	 * @return 1 if player one is victorious, 2 if player two is, and -1 otherwise.
	 */
	int getWinner();

	int getNumberTurns();
}
