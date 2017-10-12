package strategos.ui.controller;

import strategos.model.GameState;
import strategos.ui.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoadSlotListener extends Controller implements ActionListener {
	private final int index;
	private Controller controller;

	LoadSlotListener(Controller controller, int index) {
		super(controller);
		this.controller = controller;
		this.index = index - 1;

	}

	public LoadSlotListener(GameState model, View view, int index) {
		super(model, view);
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
