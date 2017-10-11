package strategos.ui.controller;

import strategos.model.MapLocation;
import strategos.units.Unit;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

class MoveListener extends Controller implements MouseListener {

    private Controller controller;

    MoveListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (controller.getSelectedMapLocation() == null) return;

        Unit selectedUnit = controller.getSelectedUnit();

        Point p = getHexPos(e.getX(),e.getY());
        if (selectedUnit == null) {
            return;
        }

        List<MapLocation> mapLocations = model.getTilesInMoveRange(selectedUnit);

        for (MapLocation maplocation : mapLocations) {
            if (maplocation.getX() == p.x && maplocation.getY() == p.y) {
                //model.move(selectedUnit, maplocation);
                controller.setSelectedMapLocation(selectedUnit.getPosition());
            }
        }
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
}
