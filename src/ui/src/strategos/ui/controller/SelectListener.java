package strategos.ui.controller;

import strategos.terrain.Plains;
import strategos.units.Unit;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class SelectListener extends Controller implements MouseListener, MouseMotionListener {

    private Controller controller;
    private boolean toggle;

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
        toggle = !toggle;
        if (!toggle) {
            view.getGridComponent().setSelection(null);
            view.repaint();
            return;
        }
        controller.setSelectedMapLocation(board.get(p.x, p.y));
        select();
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
        select();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void select() {
        Unit selectedUnit = model.getUnitAt(controller.getSelectedMapLocation());
        if (selectedUnit == null) {
            view.getGridComponent().setSelection(controller.getSelectedMapLocation());
        } else {
            view.getGridComponent().setSelection(controller.getSelectedMapLocation(), model.getUnitsInAttackRange(selectedUnit),  model.getTilesInMoveRange(selectedUnit));
        }
        view.repaint();
    }
}
