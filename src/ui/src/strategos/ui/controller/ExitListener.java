package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ExitListener extends Controller implements ActionListener {

    public ExitListener(Controller controller) {
        super(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.status() == true) {
            view.setMenu();
            setMenuListeners();
        } else {
            view.exit();
        }
    }

}