package strategos.ui.controller;

import strategos.model.GameState;
import strategos.ui.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Load slot listener for loading buttons.
 *
 * @author Daniel Pinfold
 */
class LoadSlotListener extends Controller implements ActionListener {
	private final int index;
	private Controller controller;

	/**
	 * Instantiates a new Load slot listener.
	 *
	 * @author Daniel Pinfold
	 *
	 * @param controller the controller
	 * @param index      the index
	 */
	LoadSlotListener(Controller controller, int index) {
		super(controller);
		this.controller = controller;
		this.index = index - 1;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (index < model.getSaves().size()) {
			model.load(model.getSaves().get(index));
		}
		view.removeEscapeMenu();
		controller.menuToggle();
		view.setGame();
	}
}
