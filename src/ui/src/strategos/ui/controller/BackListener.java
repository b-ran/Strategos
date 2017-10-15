package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Back listener for the back button.
 *
 * @author Daniel Pinfold
 * @author Brandon Scott-Hill
 */
class BackListener extends Controller implements ActionListener {

	/**
	 * Instantiates a new Back listener.
	 *
	 * @author Daniel Pinfold
	 * @author Brandon Scott-Hill
	 *
	 * @param controller the controller
	 */
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
