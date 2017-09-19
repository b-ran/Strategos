package strategos;

import strategos.model.Player;
import strategos.model.World;
import strategos.ui.controller.Controller;
import strategos.ui.view.View;

/**
 * Created as part of Strategos project
 * 28/07/2017.
 */
public class StrategosGame{
	
	private World world;
	private View view;
	
	private Player playerOne;
	private Player playerTwo;
	private Controller controller;

	public StrategosGame() {
		world = new World();
		/*view = new View(world.getAllUnits(), world.getMap().getMapAsList());
		controller = new Controller(world.getAllUnits(), world.getMap(), view);*/
		
		playerOne = new Player();
		playerTwo = new Player();
	}

	public void draw() {
	}

	public static void main(String[] args) {
		new StrategosGame();
	}
}
