package strategos.ui.controller;

import strategos.units.*;

import java.awt.event.*;


/**
 * The Wary listener for the wary button.
 *
 * @author Brandon Scott-Hill
 */
class WaryListener extends Controller implements ActionListener {

    private final Controller controller;

    /**
     * Instantiates a new Wary listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    WaryListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override public void actionPerformed(ActionEvent e) {
        Unit unit = controller.model.getUnitAt(controller.getSelectedMapLocation());
        if (unit != null) {
            controller.model.wary(unit);
        }
        view.repaint();
    }
}
