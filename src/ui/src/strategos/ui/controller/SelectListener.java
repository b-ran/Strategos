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
    private boolean toggle = true;
    private Unit selectedUnit;
    private MapLocation selectedMapLocation;
    private List<Unit> unitsInAttackRange = new ArrayList<>();
    private List<MapLocation> tilesInMoveRange = new ArrayList<>();

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
        selectedMapLocation = board.get(p.x, p.y);
        if (selectedUnit != null) {
            if (tilesInMoveRange.contains(selectedMapLocation) || unitsInAttackRange.contains(model.getUnitAt(selectedMapLocation))) {
                return;
            }
        }
        if (!toggle) {
            toggle = true;
            view.getGridComponent().setSelection(null);
            view.repaint();
            return;
        }
        select(selectedMapLocation);
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
        selectedMapLocation = board.get(p.x, p.y);
        select(selectedMapLocation);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void select(MapLocation m) {
        controller.setSelectedMapLocation(m);
        selectedUnit = model.getUnitAt(m);
        if (selectedUnit == null) {
            toggle = false;
            view.getGridComponent().setSelection(controller.getSelectedMapLocation());
            view.getSideComponent().setSelection(controller.getSelectedMapLocation(), null);
        } else {
            toggle = true;
            unitsInAttackRange = model.getUnitsInAttackRange(selectedUnit);
            tilesInMoveRange = model.getTilesInMoveRange(selectedUnit);
            view.getGridComponent().setSelection(controller.getSelectedMapLocation(), unitsInAttackRange,  tilesInMoveRange);
            view.getSideComponent().setSelection(controller.getSelectedMapLocation(), selectedUnit);
        }
        view.repaint();
    }

}
