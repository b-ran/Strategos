package strategos.ui.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MenuListener extends Controller implements KeyListener {

    private boolean toggle = false;

    public MenuListener(Controller controller) {
        super(controller);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && toggle == false) {
            view.addEscapeMenu();
            toggle = true;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){ ;
            view.removeEscapeMenu();
            toggle = false;
        }
        setMenuListeners();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
