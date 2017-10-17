package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Save listener for the save button.
 * @author Brandon Scott-Hill
 * @author Daniel Pinfold
 */
class SaveListener extends Controller implements ActionListener {

    private Controller controller;

    /**
     * Instantiates a new Save listener.
     *
     * @author Daniel Pinfold
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    SaveListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.save();
        view.removeEscapeMenu();
        controller.menuToggle();
    }
}
