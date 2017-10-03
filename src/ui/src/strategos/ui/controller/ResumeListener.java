package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ResumeListener extends Controller implements ActionListener {

    private Controller controller;

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
