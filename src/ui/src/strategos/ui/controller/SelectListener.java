package strategos.ui.controller;

import strategos.terrain.Plains;
import strategos.units.Unit;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
        drawSelection(e);
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
        drawSelection(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void drawSelection(MouseEvent e) {
        if (!controller.allInput) return;

        Point p = getHexPos(e.getX(),e.getY());
        selectedMapLocation = selectedMapLocation == null ? board.get(p.x, p.y) : null;
        if (selectedMapLocation != null) {
            if (selectedMapLocation.equals(board.get(p.x, p.y))) return;
        }
        if (selectedMapLocation == null) {
            view.getGridComponent().setSelection(null);
            view.repaint();
            return;
        }
        Unit selectedUnit = model.getUnitAt(selectedMapLocation);
        if (selectedUnit == null) {
            view.getGridComponent().setSelection(selectedMapLocation);
        } else {
            view.getGridComponent().setSelection(selectedMapLocation, model.getUnitsInAttackRange(selectedUnit),  model.getTilesInMoveRange(selectedUnit));
        }
        view.repaint();
    }
}
