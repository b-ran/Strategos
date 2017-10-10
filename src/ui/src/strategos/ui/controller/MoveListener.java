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

        System.out.println("players: " + model.getPlayers());
        System.out.println("current turn: " + model.getCurrentTurn());
        if (model.getUnitAt(controller.getSelectedMapLocation()) != null) {
            System.out.println("unit owner: " + model.getUnitAt(controller.getSelectedMapLocation()).getOwner());
        }
        Unit selectedUnit = controller.getSelectedUnit();
        System.out.println();
        if (controller.getSelectedUnit() != null && model.getUnitAt(controller.getSelectedMapLocation()).getOwner() != model.getCurrentTurn()) {
            System.out.println("unit not owned, returning");
            return;
        }

        Point p = getHexPos(e.getX(),e.getY());
        if (selectedUnit == null) {
            System.out.println("unit null, returning");
            return;
        }

        List<MapLocation> mapLocations = model.getTilesInMoveRange(selectedUnit);

        for (MapLocation maplocation : mapLocations) {
            if (maplocation.getX() == p.x && maplocation.getY() == p.y) {
                System.out.println("moving");
                model.move(selectedUnit, maplocation);
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
