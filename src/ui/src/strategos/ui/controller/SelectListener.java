package strategos.ui.controller;

import strategos.terrain.Plains;
import strategos.units.Unit;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class SelectListener extends Controller implements MouseListener {

    private Controller controller;

    SelectListener(Controller controller) {
        super(controller);
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!controller.allInput) return;
        Point p = getHexPos(e.getX(),e.getY());
        selectedMapLocation = selectedMapLocation == null ? board.get(p.x, p.y) : null;
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
