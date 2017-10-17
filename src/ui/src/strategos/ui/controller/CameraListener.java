package strategos.ui.controller;

import java.awt.event.*;

import static strategos.ui.config.Config.HEX_SIZE;

/**
 * The Camera listener.
 *
 * @author Brandon Scott-Hill
 */
class CameraListener extends Controller implements MouseWheelListener, MouseMotionListener {

    /**
     * Instantiates a new Camera listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    CameraListener(Controller controller) {
        super(controller);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            HEX_SIZE += 5;
        } else {
            HEX_SIZE -= 5;
        }
        view.repaint();
    }
}
