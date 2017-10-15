package strategos.ui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static strategos.ui.config.Config.GAME_INSTRUCTION_MESSAGE;

/**
 * The How to play listener for the how to play button.
 *
 * @author Daniel Pinfold
 */
class HowToPlayListener extends Controller implements ActionListener {


	/**
	 * Instantiates a new How to play listener.
	 *
	 * @author Daniel Pinfold
	 *
	 * @param controller the controller
	 */
	HowToPlayListener(Controller controller) {
		super(controller);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setInstruction();
	}
}
