package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewGameListener extends Controller implements ActionListener {

    Controller controller;

    public NewGameListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!controller.allInput) return;
        setGameListeners();
        view.setGame();
    }
}
