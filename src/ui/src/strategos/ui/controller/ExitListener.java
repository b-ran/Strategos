package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Exit listener for the exit button.
 *
 * @author Brandon Scott-Hill
 */
class ExitListener extends Controller implements ActionListener {

    private final Controller controller;

    /**
     * Instantiates a new Exit listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    ExitListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!controller.allInput) return;
        if (view.status()) {
            view.setMenu();
        } else {
            view.exit();
        }
        controller.menuToggle();
    }

}