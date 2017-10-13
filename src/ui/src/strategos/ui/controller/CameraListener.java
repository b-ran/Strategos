package strategos.ui.controller;

import java.awt.event.*;

import static strategos.ui.config.Config.HEX_SIZE;

class CameraListener extends Controller implements MouseWheelListener, MouseMotionListener {

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
