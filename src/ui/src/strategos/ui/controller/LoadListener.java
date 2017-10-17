package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Load listener for the load button.
 *
 * @author Brandon Scott-Hill
 */
class LoadListener extends Controller implements ActionListener {

    /**
     * Instantiates a new Load listener.
     *
     * @author Brandon Scott-Hill
     * @author Daniel Pinfold
     *
     * @param controller the controller
     */
    LoadListener(Controller controller) {
        super(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setLoad();
    }
}
