package strategos.ui.controller;

import strategos.model.SaveInstance;
import strategos.units.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The New game listener for new game button.
 *
 * @author Brandon Scott-Hill
 * @author Daniel Pinfold
 */
class NewGameListener extends Controller implements ActionListener {

    private final Controller controller;

    /**
     * Instantiates a new New game listener.
     *
     * @author Brandon Scott-Hill
     * @author Daniel Pinfold
     *
     * @param controller the controller
     */
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
