package strategos.ui.controller;

import strategos.units.Unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Attack listener for the attack button.
 *
 * @author Brandon Scott-Hill
 */
public class AttackListener extends Controller implements ActionListener {
    private final Controller controller;

    /**
     * Instantiates a new Attack listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    AttackListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override public void actionPerformed(ActionEvent e) {
        Unit unit = controller.getSelectedUnit();
        if (unit == null) return;
        if (unit.getEntrench()) {
            model.entrench(unit);
        } else if (unit.getWary()) {
            model.wary(unit);
        }
        view.repaint();
    }
}
