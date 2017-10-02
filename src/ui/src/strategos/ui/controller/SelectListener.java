package strategos.ui.controller;

import strategos.MapLocation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class SelectListener extends Controller implements MouseListener {

    private Controller controller;

    public SelectListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!controller.allInput) return;
        Point p = getHexPos(e.getX(),e.getY());
        selectedMapLocation = board.get(p.x, p.y);
        view.getGridComponent().setSelectedMapLocation(selectedMapLocation);
        view.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
}
