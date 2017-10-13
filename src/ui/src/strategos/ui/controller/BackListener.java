package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BackListener extends Controller implements ActionListener {
	BackListener(Controller controller) {
		super(controller);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (view.isEscapeMenu()) {
			view.setGame();
		} else {
			view.setMenu();
		}
		view.repaint();
	}
}
