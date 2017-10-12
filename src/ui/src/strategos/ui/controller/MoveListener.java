package strategos.ui.controller;

import strategos.model.MapLocation;
import strategos.units.Unit;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import static strategos.ui.config.Config.MOVE_INPUT_BUTTON;

class MoveListener extends Controller implements MouseListener, MouseMotionListener {

    private Controller controller;
    private static MouseEvent lastMoveEvent;

    MoveListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMoveEvent = e;
        if (e.getButton() != MOVE_INPUT_BUTTON) return;
        move(e);
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
        if (lastMoveEvent.getButton() != MOVE_INPUT_BUTTON) return;
        move(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void move(MouseEvent e) {
        Unit selectedUnit = controller.getSelectedUnit();
        MapLocation selectedMapLocation = controller.getSelectedMapLocation();

        Point p = getHexPos(e.getX(),e.getY());

        if (selectedMapLocation == null || selectedUnit == null) return;
        if (selectedUnit.getOwner() != view.getUiOwner()) return;

        List<MapLocation> mapLocations = model.getTilesInMoveRange(selectedUnit);

        for (MapLocation maplocation : mapLocations) {
            if (maplocation.getX() == p.x && maplocation.getY() == p.y) {
                model.move(selectedUnit, maplocation);
                controller.setSelectedMapLocation(selectedUnit.getPosition());
            }
        }
        view.repaint();
    }
}
