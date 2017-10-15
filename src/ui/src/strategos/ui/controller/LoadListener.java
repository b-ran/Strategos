package strategos.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Brandon Scott-Hill - scotthbran
 * @author Daniel Pinfold - pinfoldani
 */
class LoadListener extends Controller implements ActionListener {

    LoadListener(Controller controller) {
        super(controller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setLoad();
    }
}
