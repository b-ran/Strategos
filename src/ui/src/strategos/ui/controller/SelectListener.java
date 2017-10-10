package strategos.ui.controller;


import strategos.MapLocation;
import strategos.units.Unit;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

class SelectListener extends Controller implements MouseListener, MouseMotionListener {

    private Controller controller;

    SelectListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!controller.allInput) return;

        Point p = getHexPos(e.getX(),e.getY());
        MapLocation selectedMapLocation = board.get(p.x, p.y);
        /*if (controller.getSelectedUnit() != null) {
            if (
                    model.getTilesInMoveRange(controller.getSelectedUnit()).contains(
                            model.getWorld().getMap().get(selectedMapLocation.getX(), selectedMapLocation.getY())) ||
                    model.getUnitsInAttackRange(controller.getSelectedUnit()).contains(model.getUnitAt(
                            model.getWorld().getMap().get(selectedMapLocation.getX(), selectedMapLocation.getY())))) {
                return;
            }
        }*/
        if (!controller.getSelectionToggle()) {
            controller.setSelectionToggle(true);
            //view.getGridComponent().setSelection(null);
            view.repaint();
            return;
        }
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
        if (!controller.allInput || controller.getSelectedMapLocation() == null) return;
        Point p = getHexPos(e.getX(),e.getY());
        if (controller.getSelectedMapLocation().equals(board.get(p.x, p.y))) {
            return;
        }
        controller.setSelectedMapLocation(board.get(p.x, p.y));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
