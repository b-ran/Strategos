package strategos.ui.controller;

import strategos.units.*;

import java.awt.event.*;


/**
 * The Entrench listener for the Entrench Button.
 *
 * @author Brandon Scott-Hill
 */
class EntrenchListener extends Controller implements ActionListener {

    private final Controller controller;

    /**
     * Instantiates a new Entrench listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    EntrenchListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override public void actionPerformed(ActionEvent e) {
        Unit unit = controller.model.getUnitAt(controller.getSelectedMapLocation());
        if (unit != null) {
            controller.model.entrench(unit);
        }
        view.repaint();
    }
}
