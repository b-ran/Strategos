package strategos.ui.controller;

import strategos.units.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NewGameListener extends Controller implements ActionListener {

    Controller controller;

    NewGameListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.newGame();

        model.notifyObservers(null);

        view.removeEscapeMenu();
        controller.menuToggle();
        view.setGame();
    }
}
