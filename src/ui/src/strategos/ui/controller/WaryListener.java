package strategos.ui.controller;

import strategos.units.*;

import java.awt.event.*;


class WaryListener extends Controller implements ActionListener {

    private final Controller controller;

    WaryListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override public void actionPerformed(ActionEvent e) {
        Unit unit = controller.model.getUnitAt(getSelectedMapLocation());
        controller.model.wary(unit);
    }
}
