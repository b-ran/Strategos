package strategos.ui.controller;

import strategos.MapLocation;
import strategos.units.Unit;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        Point p = getHexPos(e.getX(),e.getY());
        Unit selectedUnit = model.getUnitAt(controller.getSelectedMapLocation());
        if (selectedUnit == null) return;

        List<MapLocation> mapLocations = model.getTilesInMoveRange(selectedUnit);

        for (MapLocation maplocation : mapLocations) {
            if (maplocation.getX() == p.x && maplocation.getY() == p.y) {
                model.move(selectedUnit, maplocation);
                System.out.println("move");
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
