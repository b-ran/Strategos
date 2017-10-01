package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ExitListener extends Controller implements ActionListener {

    Controller controller;

    public ExitListener(Controller controller) {
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
    }

}