package strategos.ui.controller;


import strategos.model.MapLocation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static strategos.ui.config.Config.SELECTION_INPUT_BUTTON;

/**
 * The type Select listener for detecting selecting and doing selection.
 * @author Brandon Scott-Hill
 */
class SelectListener extends Controller implements MouseListener, MouseMotionListener {

    private final Controller controller;
    private static MouseEvent lastMoveEvent;

    /**
     * Instantiates a new Select listener.
     *
     * @author Brandon Scott-Hill
     *
     * @param controller the controller
     */
    SelectListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMoveEvent = e;
        if (e.getButton() != SELECTION_INPUT_BUTTON) return;
        if (!controller.allInput) return;
        Point p = getHexPos(e.getX(),e.getY());
        MapLocation selectedMapLocation = board.get(p.x, p.y);
        controller.setSelectedMapLocation(selectedMapLocation);
        view.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastMoveEvent == null) return;
        if (lastMoveEvent.getButton() != SELECTION_INPUT_BUTTON) return;
        if (!controller.allInput || controller.getSelectedMapLocation() == null) return;
        Point p = getHexPos(e.getX(),e.getY());
        if (controller.getSelectedMapLocation().equals(board.get(p.x, p.y))) {
            return;
        }
        controller.setSelectionToggle(true);
        controller.setSelectedMapLocation(board.get(p.x, p.y));
        view.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
