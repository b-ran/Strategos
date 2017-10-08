package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackListener extends Controller implements ActionListener {
	public BackListener(Controller controller) {
		super(controller);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//view.setMenu();
	}
}
