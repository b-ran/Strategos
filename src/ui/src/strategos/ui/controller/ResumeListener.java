package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Resume listener for the resume button.
 *
 * @author Brandon Scott-Hill
 */
class ResumeListener extends Controller implements ActionListener {

    private Controller controller;

    /**
     * Instantiates a new Resume listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    ResumeListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.removeEscapeMenu();
        controller.menuToggle();
    }
}
