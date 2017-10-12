package strategos.ui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static strategos.ui.config.Config.GAME_INSTRUCTION_MESSAGE;

class HowToPlayListener extends Controller implements ActionListener {


	HowToPlayListener(Controller controller) {
		super(controller);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setInstruction();
	}
}
