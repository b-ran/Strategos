package strategos.ui.controller;

import strategos.units.*;

import java.awt.event.*;


class EntrenchListener extends Controller implements ActionListener {

    private final Controller controller;

    EntrenchListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override public void actionPerformed(ActionEvent e) {
        Unit unit = controller.model.getUnitAt(controller.getSelectedMapLocation());
        if (unit != null) {
            controller.model.entrench(unit);
        }
    }
}
