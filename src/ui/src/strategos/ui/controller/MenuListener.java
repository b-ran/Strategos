package strategos.ui.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Escape Menu listener.
 *
 * @author Brandon Scott-Hill
 */
class MenuListener extends Controller implements KeyListener {


    private Controller controller;

    /**
     * Instantiates a new Menu listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    MenuListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!controller.allInput) return;
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !controller.getMenuToggle()) {
            view.addEscapeMenu();
            controller.menuToggle();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){ ;
            view.removeEscapeMenu();
            controller.menuToggle();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
