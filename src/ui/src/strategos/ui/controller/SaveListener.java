package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SaveListener extends Controller implements ActionListener {

    SaveListener(Controller controller) {
        super(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.save();
    }
}
